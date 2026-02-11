package humanService;


public enum HumanSmoking {
    SMOKING,
    NO_SMOKING;

    public static String listOf() {
        int i =0;
        String str ="";
        for(HumanSmoking humanSmoking : HumanSmoking.values()){
            i++;
            str += i+")"+humanSmoking.name()+", ";
        }

        return str.substring(0,str.length()-2);
    }

}
