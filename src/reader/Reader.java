package reader;

import companyService.positions.CompanyPositions;
import humanService.HumanGender;
import humanService.HumanSmoking;

import java.util.Scanner;

public class Reader {

    private static final Scanner SCANNER = new Scanner(System.in);
    public static int readInt(String prompt){
        System.out.println(prompt);
        return Integer.parseInt(SCANNER.nextLine());
    }
    public static int readIntNLN(String prompt){
        System.out.print(prompt);
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static int readInt(String prompt, String prompt2){
        System.out.printf(prompt,prompt2);
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static String readString(String prompt){
        System.out.println(prompt);
        return SCANNER.nextLine();
    }

    public static String readStringNLN(String prompt){
        System.out.print(prompt);
        return SCANNER.nextLine();
    }

    public static HumanGender smartReaderGender(String input){
        while(true){
            int choice = Reader.readInt(input,HumanGender.listOf());
            switch (choice){
                case 1->{return HumanGender.MALE;}
                case 2->{return HumanGender.FEMALE;}
                default -> System.out.println("Вы ввели неверный символ(либо 1, либо 2)");
            }
        }
    }

    public static HumanSmoking smartReaderSmoke(String input){
        while(true){
            int choice = Reader.readInt(input,HumanSmoking.listOf());
            switch (choice){
                case 1->{return HumanSmoking.SMOKING;}
                case 2->{return HumanSmoking.NO_SMOKING;}
                default -> System.out.println("Вы ввели неверный символ(либо 1, либо 2)");
            }
        }
    }

    public static CompanyPositions smartReaderPos(String input) {
        while (true) {
            int choice = Reader.readInt(input,CompanyPositions.listOf());
            switch (choice) {
                case 1 -> {
                    return CompanyPositions.HARD_WORKER_CLEANER;
                }
                case 2 -> {
                    return CompanyPositions.HARD_WORKER_SECURITY;
                }
                case 3 -> {
                    return CompanyPositions.OFFICE_WORKER_ACCOUNTANT;
                }
                case 4 -> {
                    return CompanyPositions.OFFICE_WORKER_ENGINEER;
                }
                case 5 -> {
                    return CompanyPositions.OFFICE_WORKER_MANAGER;
                }
                default -> System.out.println("Вы ввели неверный символ(от 1 до 5)");
            }
        }
    }

}
