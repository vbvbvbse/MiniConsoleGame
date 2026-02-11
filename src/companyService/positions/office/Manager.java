package companyService.positions.office;

import companyService.positions.Worker;

public class Manager implements Worker {
    private String workPurpose = "думать над продвижением фирменных решений";
    private int salary = 90000;
    @Override
    public void whatDoOnPosition() {
        System.out.println("Вы думаете над рекламой, также то, как лучше работать фирме в следующем месяце.");
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
