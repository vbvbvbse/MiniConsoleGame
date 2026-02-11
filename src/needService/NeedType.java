package needService;

public class NeedType {
    private String id;

    private NeedType(String id) {
        this.id = id;
    }

    public static final NeedType SMOKE = new NeedType("SMOKE");
    public static final NeedType PEE = new NeedType("PEE");
    public static final NeedType EAT = new NeedType("EAT");

     static NeedType createNewNeedType(String id) {
        return new NeedType(id);
    }
}
