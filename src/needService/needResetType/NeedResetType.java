package needService.needResetType;

import needService.Need;

public interface NeedResetType {
    public void resetType(Need need, int hourNow);
    public void resetTypeAuto(Need need, int hourNow);
}
