package gameOrchestrator;

import companyService.CompanyService;
import gameOrchestrator.dayDecisionType.AutoDecisionType;
import gameOrchestrator.dayDecisionType.DayDecisionType;
import gameOrchestrator.dayDecisionType.HumanDecisionType;
import menu.MenuDecisionStrategyOfDay;
import output.Output;

import java.util.HashMap;
import java.util.Map;

public class DayDecisionTypeHandler {
    private Map<MenuDecisionStrategyOfDay, DayDecisionType> decisionTypeMap = new HashMap<>();

    public DayDecisionTypeHandler(Output output, GameOrchestrator gameOrchestrator, CompanyService companyService) {
        decisionTypeMap.put(MenuDecisionStrategyOfDay.HumanMakeDecision,new HumanDecisionType(output,gameOrchestrator,companyService));
        decisionTypeMap.put(MenuDecisionStrategyOfDay.AutoMakeDecision,new AutoDecisionType(output, gameOrchestrator, companyService));
    }

    public void dayDecisionTypeHandle(MenuDecisionStrategyOfDay decisionStrategy){
        DayDecisionType dayDecisionType = decisionTypeMap.get(decisionStrategy);
        dayDecisionType.workDayFlow();
    }
}
