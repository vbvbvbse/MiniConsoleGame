package companyService.positions;

import companyService.RandomWorkScaleOfSuccess;
import companyService.positions.hard.Cleaner;
import companyService.positions.hard.Security;
import companyService.positions.office.Accountant;
import companyService.positions.office.Engineer;
import companyService.positions.office.Manager;
import humanService.HumanService;

import java.util.HashMap;
import java.util.Map;

public class CompanyPositionProcessor {
    public RandomWorkScaleOfSuccess randomWorkScaleOfSuccess = new RandomWorkScaleOfSuccess();
    private Map<CompanyPositions, Worker> companyPositionsMap = new HashMap<>();
    private Worker workerPosition;


    public CompanyPositionProcessor(HumanService humanService) {
        companyPositionsMap.put(CompanyPositions.HARD_WORKER_CLEANER,new Cleaner());
        companyPositionsMap.put(CompanyPositions.HARD_WORKER_SECURITY,new Security());
        companyPositionsMap.put(CompanyPositions.OFFICE_WORKER_ACCOUNTANT,new Accountant());
        companyPositionsMap.put(CompanyPositions.OFFICE_WORKER_ENGINEER,new Engineer());
        companyPositionsMap.put(CompanyPositions.OFFICE_WORKER_MANAGER,new Manager());
        workerPosition = companyPositionsMap.get(humanService.getPosition());
    }

    public Worker getWorkerPosition() {
        return workerPosition;
    }
}
