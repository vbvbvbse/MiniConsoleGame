package needService;

import humanService.HumanSmoking;
import needService.needResetType.EatReset;
import needService.needResetType.NeedResetType;
import needService.needResetType.PeeReset;
import needService.needResetType.SmokeReset;

import java.util.HashMap;
import java.util.Map;

public class NeedProcessor {
    private Map<NeedType, NeedResetType> needProcessorMap = new HashMap<>();

    public NeedProcessor(HumanSmoking isSmoking) {
        needProcessorMap.put(NeedType.PEE,new PeeReset());
        needProcessorMap.put(NeedType.EAT,new EatReset());
        if(isSmoking == HumanSmoking.SMOKING){
            needProcessorMap.put(NeedType.SMOKE,new SmokeReset());
        }
    }


    public void satisfy(int hourNow,Need need) {
        NeedResetType needResetType = needProcessorMap.get(need.getNeedType());
        needResetType.resetType(need,hourNow);
    }
}
