package menu;

import companyService.CompanyService;
import companyService.companySalary.CompanySalaryService;
import companyService.positions.CompanyPositionProcessor;
import companyService.positions.CompanyPositions;
import companyService.positions.Worker;
import fineService.FineService;
import gameOrchestrator.GameOrchestrator;
import humanService.HumanGender;
import humanService.HumanService;
import humanService.HumanSmoking;
import needService.NeedProcessor;
import reader.Reader;
import relaxService.RelaxService;

public class Menu {

    private void startText(){
        System.out.println("Игра работа человека!");
    }

    private void startSimulation(){
        System.out.println("Запуск симуляции дней: ");
    }

    public void start(){
        startText();
        //create human
        HumanService humanService = new HumanService();

        humanService.setName(Reader.readStringNLN("Введите имя вашего работника: "));

        humanService.setAge(Reader.readIntNLN( "Введите возраст вашего вашего работника: "));

        humanService.setGender(Reader.smartReaderGender("Введите пол вашего человека(%s): "));

        humanService.setPosition(Reader.smartReaderPos("Введите должность вашего работника(%s): "));

        humanService.setIsSmoking(Reader.smartReaderSmoke("Введите курит ли человек(%s): "));

        //create worker by human
        CompanyPositionProcessor companyPositionProcessor = new CompanyPositionProcessor(humanService);
        Worker workerPosition = companyPositionProcessor.getWorkerPosition();
        //create services
        CompanyService companyService = new CompanyService(workerPosition);
        CompanySalaryService companySalaryService = new CompanySalaryService(workerPosition);

        NeedProcessor needProcessor = new NeedProcessor(humanService.getIsSmoking());
        RelaxService relaxService = new RelaxService();
        FineService fineService = new FineService();
        //injection in orchestrator
        GameOrchestrator gameOrchestrator = new GameOrchestrator(
                humanService,
                humanService.getIsSmoking(),
                workerPosition,
                companyService,
                companySalaryService,
                needProcessor,
                relaxService,
                fineService
        );
        startSimulation();
        gameOrchestrator.monthWorkDayFlow();
    }

}
