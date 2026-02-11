package needService.needResetType;

import needService.Need;

public class EatReset implements NeedResetType {
    @Override
    public void resetType(Need need, int hourNow) {
        System.out.println("Вы покушали!");
        need.reset(hourNow);
    }
}
