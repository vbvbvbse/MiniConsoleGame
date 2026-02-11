package companyService;

import fineService.FineService;

public class WorkingTooManyHours {

    public void tooMany(int hourNowm, FineService fineService){
        System.out.println("Вы работали слишком много часов подряд... Вам плохо, вы получаете один штраф здоровья.");
        fineService.plusFineCount();
    }
}
