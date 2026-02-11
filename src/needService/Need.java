package needService;

public class Need {
    private NeedType needType;
    private String needName;

    private int resetInterval;
    private int resetHighInterval;
    private int resetLast;

    public Need(NeedType needType, String needName, int resetInterval, int resetHighInterval, int resetFirst) {
        this.needType = needType;
        this.needName = needName;
        this.resetInterval = resetInterval;
        this.resetHighInterval = resetHighInterval;
        this.resetLast = resetFirst;
    }

    public void reset(int hourNow) {
        resetLast = hourNow;
    }

    public String getNeedName() {
        return needName;
    }

    public NeedType getNeedType() {
        return needType;
    }

    public boolean shouldReset(int hourNow){
        return hourNow - resetLast >= resetInterval;
    }

    public boolean shouldResetHigh(int hourNow){
        return hourNow - resetLast >= resetHighInterval;
    }

    public int getResetHighInterval(){
        return resetHighInterval;
    }
}
