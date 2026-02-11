package needService.needResetType;

import needService.Need;

public class SmokeReset implements NeedResetType {
    @Override
    public void resetType(Need need, int hourNow) {
        System.out.println("Вы покурили!");
        need.reset(hourNow);
    }
}
