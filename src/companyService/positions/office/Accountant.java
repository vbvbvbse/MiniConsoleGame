package companyService.positions.office;

import companyService.positions.Worker;

public class Accountant implements Worker {
    private String workPurpose = "отбеливать финансы фирмы.";
    private int salary = 75000;
    @Override
    public void whatDoOnPosition() {
        System.out.println("Вы считаете различные показатели. Следите за тем, чтобы все финансы остались законными.");
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
