package output;

import companyService.SuccessRateDay;
import companyService.SuccessRateMonth;
import companyService.companySalary.CompanySalaryService;
import companyService.positions.Worker;
import needService.Need;
import needService.needDecision.NeedDecisionType;
import relaxService.RelaxResultType;

public class Output {
    //special
    public void outliner(){
        System.out.println("--------------------------------------------------------------------");
    }

    //fineOutput
    public void fineMaxed(){
        System.out.println("Вы накопили максимальное число штрафов, поэтому вам очень плохо и вы идете отдыхать час!");
    }
    public void failedNeed(Need need){
        System.out.printf("Вы терпели слишком много часов... В итоге вам стало плохо, а значит вы получаете один штраф здоровья.\n",need.getResetHighInterval());
    }

    public void failedWorking(){
        System.out.println("Вы работали слишком много часов подряд... Вам плохо, вы получаете один штраф здоровья.");
    }

    //needOutput
    public void isDecisionDone(NeedDecisionType needDecisionType, Need need) {
        System.out.println("Вы выбрали: ");
        switch (needDecisionType) {
            case SATISFY -> System.out.println(need.getNeedName());
            case TOLERATE -> System.out.println("терпеть");
        }
    }

    public void wantDoNeed(Need need){
        System.out.printf("Вы хотите %s!\n",need.getNeedName());
    }

    public void isNeedDone(int hourNow) {
        System.out.printf("Вы погасли потребность в %d\n",hourNow);
    }
    //relaxOutput
    public void relaxResultOutput(RelaxResultType relaxResultType){
        switch (relaxResultType){
            case RELAX_UNSUCCESS -> System.out.println("Вы не можете отдыхать во время одного и того же часа.");
            case RELAX_SUCCESS -> {
                relaxNeedsSatisfyOut(); System.out.println("Вы отлично отдохнули этот час.");
            }
        }
    }

    public void relaxNeedsSatisfyOut(){
        System.out.println("Во время отдыха вы решили погасить все ваши потребности.");
    }
    //companyOutput
    private void isMonthHighProductive(){
        System.out.println("Вы очень продуктивно отработали этот месяц, поэтому вы получаете премию с прибавкой!");
    }

    private void isMonthMidProductive(){
        System.out.println("Вы продуктивно отработали этот месяц, поэтому вы получаете премию!");
    }

    private void isMonthLowProductive(){
        System.out.println("Вы отработали этот месяц непродуктивно, поэтому вы остаетесь без премии.");
    }

    private void isDayHighProductive(){
        System.out.println("Вы очень продуктивно отработали этот день!");
    }

    private void isDayMidProductive(){
        System.out.println("Вы продуктивно отработали этот день!");
    }

    private void isDayLowProductive(){
        System.out.println("Вы отработали этот день непродуктивно. Старайтесь больше!");
    }

    public void successRateMonthOutput(SuccessRateMonth successRateMonth){
        switch(successRateMonth){
            case MONTH_HIGH_PRODUCTIVITY ->  isMonthHighProductive();
            case MONTH_MID_PRODUCTIVITY ->  isMonthMidProductive();
            case MONTH_LOW_PRODUCTIVITY ->  isMonthLowProductive();
        }
    }

    public void successRateDayOutput(SuccessRateDay successRateDay){
        switch(successRateDay){
            case DAY_HIGH_PRODUCTIVITY ->  isDayHighProductive();
            case DAY_MID_PRODUCTIVITY ->  isDayMidProductive();
            case DAY_LOW_PRODUCTIVITY ->  isDayLowProductive();
        }
    }

    public void successRateOutputHandler(int successRate,Worker workerPosition){
        if(successRate == 1){
            System.out.println("У вас немного получилось "+ workerPosition.getWorkPurpose());
        }else if(successRate == 2) {
            System.out.println("У вас нормально получилось " + workerPosition.getWorkPurpose());
        }else if(successRate == 3) {
            System.out.println("У вас отлично получилось "+ workerPosition.getWorkPurpose());
        }
    }
    //orchestratorOutput
    public String inChoiceWhatDo(){
        return "Что мы будем делать в течении этого часа? Работать или отдыхать? (1/2)";
    }

    public void badCharWhatDo(){
        System.out.println("Вы ввели неверный симовол(либо 1, либо 2).");
    }

    public void whatHourNow(int hourNow){
        System.out.printf("%d час: \n",hourNow);
    }

    public void workDayIsEnd(){
        System.out.println("Рабочий день закончился!");
    }

    public void workMonthIsEnd(){
        System.out.println("Рабочий месяц закончился!");
    }
    public void finalSalaryMonth(CompanySalaryService companySalaryService, Worker workerPosition){
        System.out.printf("В итоге за этот месяц вы заработали %d рублей! Из них %d рублей обычная заработная плата, а %d рублей вы получили как премию.\n", companySalaryService.getFinalSalary(),workerPosition.getSalary(), companySalaryService.getBonusSalary());
    }
}
