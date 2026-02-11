package gameOrchestrator;

import companyService.CompanyService;
import companyService.SuccessRateDay;
import companyService.SuccessRateMonth;
import companyService.WorkingTooManyHours;
import companyService.companySalary.CompanySalaryService;
import companyService.positions.Worker;
import fineService.FineService;
import fineService.RelaxAfterMaxFines;
import humanService.HumanService;
import humanService.HumanSmoking;
import needService.Need;
import needService.NeedProcessor;
import needService.NeedTolerateFail;
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
    //special classes
    final Output output = new Output();

    final HumanService humanService;

    final NeedProcessor needProcessor;
    final NeedDecision needDecision = new NeedDecision();
    final NeedTolerateFail needTolerateFail = new NeedTolerateFail();

    final RelaxService relaxService;
    final RelaxNeedsSatisfy relaxNeedsSatisfy = new RelaxNeedsSatisfy();


    final FineService fineService;
    final RelaxAfterMaxFines relaxAfterMaxFines = new RelaxAfterMaxFines();


    final Worker workerPosition;
    final CompanyService companyService;
    final WorkingTooManyHours workingTooManyHours = new WorkingTooManyHours();
    final CompanySalaryService companySalaryService;

    public GameOrchestrator(HumanService humanService, HumanSmoking isSmoking, Worker workerPosition, CompanyService companyService, CompanySalaryService companySalaryService, NeedProcessor needProcessor, RelaxService relaxService, FineService fineService) {
        this.humanService = humanService;
        this.isSmoking = isSmoking;
        shouldAddSmokingToList();
        this.workerPosition = workerPosition;
        this.companyService = companyService;
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
            needTolerateFail.fail(need, fineService);
        }
        output.outliner();
    }

    private void fineServiceLogic(int hourNow){
        if(!fineService.isFineMaxed()){
            return;
        }
        fineService.fineGetMax();
        relaxAfterMaxFines.relaxAfterMaxFines();
        relaxServiceLogic(hourNow);
    }

    //методы для течения дня
    private void whatDoInHour(int hourNow){
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

    private void needsChecker(int hourNow){
        for(Need need : needs){
            needProcessorLogic(hourNow,need);
        }
        fineServiceLogic(hourNow);
    }

    //методы работы
    private void workServicesLogic(int hourNow){
        if(companyService.couldWorking(hourNow)) {
            workingTooManyHours.tooMany(hourNow, fineService);
            fineServiceLogic(hourNow);
            return;
        }
        companyService.workProcess();
    }

    //метод течения дня
    private void workDayFlow(){
        for (int hourNow = 1; hourNow <= 12; hourNow++) {
            output.whatHourNow(hourNow);
            output.outliner();
            needsChecker(hourNow);
            whatDoInHour(hourNow);
            output.outliner();
            if(hourNow == 12){
                output.outliner();
                output.workDayIsEnd();
                SuccessRateDay successRateDay = companyService.successRateDayHandler();
                output.successRateDayOutput(successRateDay);
                output.outliner();
            }
        }
    }

    public void monthWorkDayFlow(){
        for (int day = 1; day <= 31; day++) {
            output.outliner();
            System.out.printf("День %d: \n",day);
            output.outliner();
            workDayFlow();
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
