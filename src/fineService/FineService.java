package fineService;


public class FineService {
    private int fineCount;
    private final int FINE_COUNT_MAX = 3;

    private RelaxAfterMaxFines relaxAfterMaxFines = new RelaxAfterMaxFines();

    public FineService() {
        this.fineCount = 0;
    }

    public void plusFineCount(){
        if(fineCount < FINE_COUNT_MAX){
            fineCount++;
        }
    }

    public void fineGetMax(){
        if(fineCount == FINE_COUNT_MAX){
            resetFineCount();
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
