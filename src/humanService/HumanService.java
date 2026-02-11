package humanService;

import companyService.positions.CompanyPositions;

public class HumanService {
    private String name;
    private int age;
    private HumanGender gender;
    private CompanyPositions position;
    private HumanSmoking isSmoking;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public HumanSmoking getIsSmoking() {
        return isSmoking;
    }

    public void setGender(HumanGender gender) {
        this.gender = gender;
    }


    public void setPosition(CompanyPositions position) {
        this.position = position;
    }

    public CompanyPositions getPosition() {
        return position;
    }

    public void setIsSmoking(HumanSmoking isSmoking) {
        this.isSmoking = isSmoking;
    }
}
