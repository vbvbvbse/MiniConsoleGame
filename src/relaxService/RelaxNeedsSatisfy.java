package relaxService;

import companyService.CompanyService;
import needService.Need;
import needService.NeedProcessor;

import java.util.List;

 public class RelaxNeedsSatisfy {

     public void relaxNeedsReset(RelaxResultType relaxResultType, List<Need> needs, NeedProcessor needProcessor, int hourNow, CompanyService companyService){
        if(relaxResultType == RelaxResultType.RELAX_UNSUCCESS){
             return;
        }
        for(Need need : needs){
            needProcessor.satisfy(hourNow,need);
        }
        companyService.resetHoursWorked(0);
    }
     public void relaxNeedsResetAuto(RelaxResultType relaxResultType, List<Need> needs, NeedProcessor needProcessor, int hourNow, CompanyService companyService){
         if(relaxResultType == RelaxResultType.RELAX_UNSUCCESS){
             return;
         }
         for(Need need : needs){
             needProcessor.satisfyAuto(hourNow,need);
         }
         companyService.resetHoursWorked(0);
     }
}
