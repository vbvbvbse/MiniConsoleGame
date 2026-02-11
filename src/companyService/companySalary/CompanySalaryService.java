package companyService.companySalary;

import companyService.SuccessRateMonth;
import companyService.positions.Worker;

public class CompanySalaryService {
    private int finalSalary;
    private int bonusSalary;

    public CompanySalaryService(Worker workerPosition) {
        finalSalary = workerPosition.getSalary();
        bonusSalary = 0;
    }
    public void addBonusSalary(SuccessRateMonth successRateMonth) {
        switch(successRateMonth) {
            case MONTH_HIGH_PRODUCTIVITY->bonusSalary = 45000;
            case MONTH_MID_PRODUCTIVITY ->bonusSalary = 30000;
            case MONTH_LOW_PRODUCTIVITY ->bonusSalary = 0;
        }
        finalSalary += bonusSalary;
    }
    public int getFinalSalary(){
        return finalSalary;
    }
    public int getBonusSalary(){
        return bonusSalary;
    }
}
