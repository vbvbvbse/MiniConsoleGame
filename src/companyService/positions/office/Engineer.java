package companyService.positions.office;

import companyService.positions.Worker;

public class Engineer implements Worker {
    private String workPurpose = "разрабатывать различные фирменные решения.";
    private int salary = 100000;
    @Override
    public void whatDoOnPosition() {
        System.out.println("Вы разрабатываете различные решения, выполняете мысленную работу, пытаетесь что-то придумать.");
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
