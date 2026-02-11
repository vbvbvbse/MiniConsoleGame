package relaxService;

public class RelaxService {
    private int relaxLast;
    private int relaxCount;

    public RelaxService(){
        this.relaxLast = 0;
        this.relaxCount = 0;
    }

    private void plusRelaxCount(){
        relaxCount++;
    }

    private void setRelaxLast(int hourNow){
        relaxLast = hourNow;
    }

    public int getRelaxCount(){
        return relaxCount;
    }

    public RelaxResultType tryRelax(int hourNow){
        if(hourNow == relaxLast){
            return RelaxResultType.RELAX_UNSUCCESS;
        }
        setRelaxLast(hourNow);
        plusRelaxCount();
        return RelaxResultType.RELAX_SUCCESS;
    }

}
