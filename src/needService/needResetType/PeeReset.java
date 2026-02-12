package needService.needResetType;

import needService.Need;

public class PeeReset implements NeedResetType {
    @Override
    public void resetType(Need need, int hourNow) {
        System.out.println("Вы пописали! А значит прямо сейчас вы снова попьете.");
        need.reset(hourNow);
    }

    @Override
    public void resetTypeAuto(Need need, int hourNow) {
        need.reset(hourNow);
    }
}
