package needService;


import fineService.FineService;

 public class NeedTolerateFail {

     public void fail(Need need, FineService fineService){
        System.out.printf("Вы терпели %d часов. В итоге вам стало плохо, а значит вы получаете один штраф здоровья.\n",need.getResetHighInterval());
        fineService.plusFineCount();
    }
}
