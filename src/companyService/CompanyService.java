package companyService;

import companyService.positions.Worker;

public class CompanyService {
    private final RandomWorkScaleOfSuccess  randomWorkScaleOfSuccess = new RandomWorkScaleOfSuccess();

    private Worker workerPosition;
    private boolean isPositionGuideHasTold;

    private int hoursWorkedNow = 0;
    //rates
    private int successRate;
    private int highRate;
    private int midRate;
    private int lowRate;
    //purposeOfRates
    private int highProductiveDay;
    private int midProductiveDay;
    private int lowProductiveDay;

    public CompanyService(Worker workerPosition) {
        isPositionGuideHasTold = false;
        this.workerPosition = workerPosition;
    }

    public void setHoursWorkedNow(int hoursWorkedNow) {
        this.hoursWorkedNow = hoursWorkedNow;
    }

    public boolean couldWorking(int hourNow){
        int HOURS_COULD_WORKING_MAX = 5;
        return hoursWorkedNow >= HOURS_COULD_WORKING_MAX;
    }

    public void resetHoursWorked(int hourNow){
        this.hoursWorkedNow = hourNow;
    }

    public void workProcess() {
        if(!isPositionGuideHasTold) {
            workerPosition.whatDoOnPosition();
            isPositionGuideHasTold = true;
        }
        hoursWorkedNow++;
        this.successRate = randomWorkScaleOfSuccess.randomScaleSuccess();
        successRateAdder(successRate);
    }


    private void successRateAdder(int successRate) {
        switch(successRate) {
            case 1-> lowRate++;
            case 2-> midRate++;
            case 3-> highRate++;
        }
    }

    public int getSuccessRate() {
        return successRate;
    }

    public SuccessRateDay successRateDayHandler(){
        if (highRate > midRate && highRate > lowRate) {
        highProductiveDay++;
        return SuccessRateDay.DAY_HIGH_PRODUCTIVITY;
        }

        if (midRate > highRate && midRate > lowRate) {
            midProductiveDay++;
            return SuccessRateDay.DAY_MID_PRODUCTIVITY;
        }

        lowProductiveDay++;
        return SuccessRateDay.DAY_LOW_PRODUCTIVITY;
    }

    public SuccessRateMonth successRateMonthHandler(){
        if(highProductiveDay > lowProductiveDay && highProductiveDay > midProductiveDay) {
            return SuccessRateMonth.MONTH_HIGH_PRODUCTIVITY;
        }
        if(midProductiveDay > highProductiveDay && midProductiveDay > lowProductiveDay) {
            return SuccessRateMonth.MONTH_MID_PRODUCTIVITY;
        }
        return SuccessRateMonth.MONTH_LOW_PRODUCTIVITY;

    }
}
