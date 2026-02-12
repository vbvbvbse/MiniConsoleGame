package gameOrchestrator;

import companyService.CompanyService;
import companyService.SuccessRateMonth;
import companyService.companySalary.CompanySalaryService;
import companyService.positions.Worker;
import fineService.FineService;
import humanService.HumanService;
import humanService.HumanSmoking;
import needService.Need;
import needService.NeedProcessor;
import needService.NeedType;
import needService.needDecision.NeedDecision;
import needService.needDecision.NeedDecisionType;
import output.Output;
import reader.Reader;
import relaxService.RelaxNeedsSatisfy;
import relaxService.RelaxResultType;
import relaxService.RelaxService;

import java.util.ArrayList;
import java.util.List;

public class GameOrchestrator {
    private final HumanSmoking isSmoking;
    private final List<Need> needs = new ArrayList<>(
            List.of(
                    new Need(NeedType.PEE,"писать",4,6,0),
                    new Need(NeedType.EAT,"кушать",5,6,0)
            )
    );
    //инициализация спец классов
    final Output output = new Output();

    final HumanService humanService;

    final NeedProcessor needProcessor;
    final NeedDecision needDecision = new NeedDecision();

    final RelaxService relaxService;
    final RelaxNeedsSatisfy relaxNeedsSatisfy = new RelaxNeedsSatisfy();


    final FineService fineService;


    final Worker workerPosition;
    private CompanyService companyService;
    final CompanySalaryService companySalaryService;

    //инициализация класса, который обрабатывает какой режим работы программы относительно дня был выбран
    final DayDecisionTypeHandler  dayDecisionTypeHandler;
    //инициализация класса, который обрабатывает каждый день, входит ли в рамки кол-ва дней для режима работы программы
    final DayDecisionHandler everyDayDecisionHandler;

    public GameOrchestrator(HumanService humanService, HumanSmoking isSmoking, Worker workerPosition, CompanyService companyService, CompanySalaryService companySalaryService, NeedProcessor needProcessor, RelaxService relaxService, FineService fineService) {
        this.humanService = humanService;
        this.isSmoking = isSmoking;
        shouldAddSmokingToList();
        this.workerPosition = workerPosition;
        this.companyService = companyService;
        this.dayDecisionTypeHandler = new DayDecisionTypeHandler(output,this,companyService);
        this.everyDayDecisionHandler = new DayDecisionHandler(output,dayDecisionTypeHandler);
        this.companySalaryService = companySalaryService;
        this.needProcessor = needProcessor;
        this.relaxService = relaxService;
        this.fineService = fineService;
    }

    //специальный метод
    private void shouldAddSmokingToList(){
        if(isSmoking == HumanSmoking.SMOKING){
            needs.add(new Need(NeedType.SMOKE,"курить",3,6,0));
        }
    }

    //методы для построения логики разных классов
    private void relaxServiceLogic(int hourNow){
        RelaxResultType relaxResultType = relaxService.tryRelax(hourNow);
        relaxNeedsSatisfy.relaxNeedsReset(relaxResultType,needs,needProcessor,hourNow,companyService);
        output.relaxResultOutput(relaxResultType);
    }

    private void relaxServiceLogicAuto(int hourNow){
        RelaxResultType relaxResultType = relaxService.tryRelax(hourNow);
        relaxNeedsSatisfy.relaxNeedsResetAuto(relaxResultType,needs,needProcessor,hourNow,companyService);
    }


    private void needProcessorLogic(int hourNow,Need need){
        if(!need.shouldReset(hourNow)){
            return;
        }
        output.wantDoNeed(need);
        NeedDecisionType decision = needDecision.needDecision();
        output.isDecisionDone(decision,need);
        if(decision == NeedDecisionType.SATISFY){
            needProcessor.satisfy(hourNow,need);
            output.isNeedDone(hourNow);
        } else if(decision == NeedDecisionType.TOLERATE){
            if(!need.shouldResetHigh(hourNow)){
                return;
            }
            fineService.plusFineCount();
            output.failedNeed(need);
        }
        output.outliner();
    }

    private void needProcessorLogicAuto(int hourNow,Need need,NeedDecisionType decision){
        if(!need.shouldReset(hourNow)){
            return;
        }
        if(decision == NeedDecisionType.SATISFY){
            needProcessor.satisfyAuto(hourNow,need);
        } else if(decision == NeedDecisionType.TOLERATE){
            if(!need.shouldResetHigh(hourNow)){
                return;
            }
            fineService.plusFineCount();
        }
    }

    private void fineServiceLogic(int hourNow){
        if(fineService.fineGetMax()) {
            output.fineMaxed();
            relaxServiceLogic(hourNow);
        }
    }
    private void fineServiceLogicAuto(int hourNow){
        if(fineService.fineGetMax()) {
            relaxServiceLogicAuto(hourNow);
        }
    }

    private void workServicesLogic(int hourNow){
        if(companyService.couldWorking(hourNow)) {
            fineService.plusFineCount();
            output.failedWorking();
            fineServiceLogic(hourNow);
            return;
        }
        companyService.workProcess();
        int successRate = companyService.getSuccessRate();
        output.successRateOutputHandler(successRate,workerPosition);
    }

    private void workServicesLogicAuto(int hourNow){
        if(companyService.couldWorking(hourNow)) {
            fineService.plusFineCount();
            fineServiceLogicAuto(hourNow);
            return;
        }
        companyService.workProcess();
    }

    //методы для течения дня
    public void whatDoInHour(int hourNow){
        while(true){
            int choice = Reader.readInt(output.inChoiceWhatDo());
            if(choice==1){
                workServicesLogic(hourNow);
                return;
            } else if(choice==2){
                relaxServiceLogic(hourNow);
                return;
            } else {
                output.badCharWhatDo();
            }

        }
    }
    public void whatDoInHourAuto(int hourNow,int choice){
        while(true){
            if(choice==1){
                workServicesLogicAuto(hourNow);
                return;
            } else if(choice==2) {
                relaxServiceLogicAuto(hourNow);
                return;
            }
        }
    }

    public void needsChecker(int hourNow){
        for(Need need : needs){
            needProcessorLogic(hourNow,need);
        }
        fineServiceLogic(hourNow);
    }
    public void needsCheckerAuto(int hourNow,NeedDecisionType decision){
        for(Need need : needs){
            needProcessorLogicAuto(hourNow,need,decision);
        }
        fineServiceLogicAuto(hourNow);
    }

    public void everyDayReset(){
        companyService.setHoursWorkedNow(0);
        fineService.setFineCount(0);
        relaxService.setRelaxCount(0);
    }


    //метод течения месяца
    public void monthWorkDayFlow(){
        for (int day = 1; day <= 31; day++) {
            if(day==1){
                everyDayDecisionHandler.dayDecisionHandle(day);
            }else if(!everyDayDecisionHandler.checkDayIsInRange(day)){
                everyDayDecisionHandler.dayDecisionHandle(day);
            }
            everyDayDecisionHandler.dayDecisionProvider(day);
            if(day == 31){
                output.outliner();
                output.workMonthIsEnd();
                SuccessRateMonth successRateMonth = companyService.successRateMonthHandler();
                output.outliner();
                output.successRateMonthOutput(successRateMonth);
                companySalaryService.addBonusSalary(successRateMonth);
                output.outliner();
                output.finalSalaryMonth(companySalaryService,workerPosition);
                output.outliner();
            }
        }
    }
}
