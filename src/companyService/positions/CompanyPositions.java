package companyService.positions;


public enum CompanyPositions {
    HARD_WORKER_CLEANER,
    HARD_WORKER_SECURITY,
    OFFICE_WORKER_ACCOUNTANT,
    OFFICE_WORKER_ENGINEER,
    OFFICE_WORKER_MANAGER;

    public static String listOf() {
        int i =0;
        String str ="";
        for(CompanyPositions companyPositions : CompanyPositions.values()){
            i++;
            str += i+")"+companyPositions.name()+", ";
        }

        return str.substring(0,str.length()-2);
    }

}
