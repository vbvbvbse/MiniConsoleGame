package fineService;


public class FineService {
    private int fineCount;
    private final int FINE_COUNT_MAX = 3;

    public void plusFineCount(){
        if(fineCount < FINE_COUNT_MAX){
            fineCount++;
        }
    }

    public void setFineCount(int fineCount) {
        this.fineCount = fineCount;
    }

    public boolean fineGetMax(){
        if(fineCount >= FINE_COUNT_MAX){
            resetFineCount();
            return true;
        }else{
            return false;
        }
    }

    public boolean isFineMaxed(){
        return fineCount == FINE_COUNT_MAX;
    }

    public int getFineCount(){
        return fineCount;
    }

    private void resetFineCount(){
        fineCount = 0;
    }

}
