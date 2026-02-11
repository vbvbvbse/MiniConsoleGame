package needService.needDecision;

import reader.Reader;

public class NeedDecision {

    public NeedDecisionType needDecision() {
        while(true){
            int input = Reader.readInt("Будете ли вы гасить потребность или терпеть? (1/2)");
            if(input == 1){
                return NeedDecisionType.SATISFY;
            }
            else if(input == 2){
                return NeedDecisionType.TOLERATE;
            }
            else{
                System.out.println("Вы ввели неверный символ(либо 1, либо 2.");
            }
        }
    }

}
