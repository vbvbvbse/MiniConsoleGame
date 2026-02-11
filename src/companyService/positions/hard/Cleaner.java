package companyService.positions.hard;

import companyService.positions.Worker;

public class Cleaner implements Worker {
    private String workPurpose = "очищать рабочее пространство фирмы.";
    private int salary = 45000;
    @Override
    public void whatDoOnPosition() {
        System.out.println("Вы чистите пол, мусорные баки. Наводите порядок.");
    }
    @Override
    public String getWorkPurpose(){
        return workPurpose;
    }

    @Override
    public int getSalary() {
        return salary;
    }
}
