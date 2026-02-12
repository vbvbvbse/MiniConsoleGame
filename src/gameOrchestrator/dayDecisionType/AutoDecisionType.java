package gameOrchestrator.dayDecisionType;

import companyService.CompanyService;
import companyService.SuccessRateDay;
import gameOrchestrator.GameOrchestrator;
import needService.needDecision.NeedDecisionType;
import output.Output;

import java.util.Random;

public class AutoDecisionType implements DayDecisionType{
    private GameOrchestrator gameOrchestrator;
    private CompanyService companyService;
    private Output output;
    private Random random = new Random();
    SuccessRateDay successRateDay;
    public AutoDecisionType(Output output, GameOrchestrator gameOrchestrator, CompanyService companyService) {
        this.gameOrchestrator=gameOrchestrator;
        this.companyService=companyService;
        this.output=output;
    }
    @Override
    public void workDayFlow() {
        for (int hourNow = 1; hourNow <= 12; hourNow++) {
            gameOrchestrator.needsCheckerAuto(hourNow,random.nextBoolean()?NeedDecisionType.SATISFY:NeedDecisionType.TOLERATE);
            gameOrchestrator.whatDoInHourAuto(hourNow,random.nextInt(1,3));
            if(hourNow == 12){
                SuccessRateDay successRateDay = companyService.successRateDayHandler();
                output.successRateDayOutput(successRateDay);
                output.outliner();
            }
        }
    }
}
