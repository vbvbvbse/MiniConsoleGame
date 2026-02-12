package companyService;

import java.util.Random;

public class RandomWorkScaleOfSuccess {
    Random random = new Random();

    public int randomScaleSuccess(){
        int randoms = random.nextInt(1,4);
        if(randoms == 1){
            return 1;
        }else if(randoms == 2){
            return 2;
        }else {
            return 3;
        }
    }
}
