package companyService.positions.hard;

import companyService.positions.Worker;

public class Security implements Worker {
    private String workPurpose = "охранять рабочее пространство фирмы.";
    private int salary = 55000;
    @Override
    public void whatDoOnPosition() {
        System.out.println("Вы охраняете рабочее пространство фирмы, смотрите в камеры.");
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
