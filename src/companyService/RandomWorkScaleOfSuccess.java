package companyService;

import java.util.Random;

public class RandomWorkScaleOfSuccess {
    Random random = new Random();
    public int randomScaleSuccess(String text){
        int randoms = random.nextInt(1,4);
        if(randoms == 1){
            System.out.println("У вас немного получилось "+ text);
            return 1;
        }else if(randoms == 2){
            System.out.println("У вас нормально получилось " + text);
            return 2;
        }else {
            System.out.println("У вас отлично получилось "+ text);
            return 3;
        }
    }
}
