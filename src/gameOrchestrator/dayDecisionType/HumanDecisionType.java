package gameOrchestrator.dayDecisionType;

import companyService.CompanyService;
import companyService.SuccessRateDay;
import gameOrchestrator.GameOrchestrator;

import output.Output;

public class HumanDecisionType implements DayDecisionType{
    private Output output;
    private GameOrchestrator gameOrchestrator;
    private CompanyService companyService;

    public HumanDecisionType(Output output, GameOrchestrator gameOrchestrator, CompanyService companyService) {
        this.output = output;
        this.gameOrchestrator = gameOrchestrator;
        this.companyService = companyService;
    }

    @Override
    public void workDayFlow() {
        for (int hourNow = 1; hourNow <= 12; hourNow++) {
            output.outliner();
            output.whatHourNow(hourNow);
            output.outliner();
            gameOrchestrator.needsChecker(hourNow);
            gameOrchestrator.whatDoInHour(hourNow);
            if(hourNow == 12){
                output.outliner();
                output.workDayIsEnd();
                SuccessRateDay successRateDay = companyService.successRateDayHandler();
                output.successRateDayOutput(successRateDay);
                output.outliner();
            }
        }
    }
}
