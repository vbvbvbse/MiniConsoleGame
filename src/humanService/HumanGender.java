package humanService;

public enum HumanGender {
    MALE,
    FEMALE;

    public static String listOf() {
        int i =0;
        String str ="";
        for(HumanGender humanGender : HumanGender.values()){
            i++;
            str += i+")"+humanGender.name()+", ";
        }

        return str.substring(0,str.length()-2);
    }

}
