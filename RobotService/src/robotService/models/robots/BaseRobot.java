package robotService.models.robots;

import robotService.common.ExceptionMessages;
import robotService.models.robots.interfaces.Robot;

public abstract class BaseRobot implements Robot {
    //  name – String
    //  happiness – int
    //  can't be less than 0 or more than 100. In these cases throw IllegalArgumentException with message "Invalid happiness."
    //  energy – int
    //  can't be less than 0 or more than 100. In these cases throw IllegalArgumentException with message "Invalid energy."
    //  procedureTime – int
    //  owner – String
    //  by default: "Service"
    //  isBought – boolean
    //  by default: false
    //  isRepaired – boolean
    //  by default: false

    private String name;
    private int happiness;
    private int energy;
    private int procedureTime;
    private String owner;
    private boolean isBought;
    private boolean isRepaired;

    protected BaseRobot(String name, int energy, int happiness, int procedureTime) {
        this.setName(name);
        this.setEnergy(energy);
        this.setHappiness(happiness);
        this.setProcedureTime(procedureTime);
        this.setOwner("Service");
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getHappiness() {
        return happiness;
    }

    @Override
    public void setHappiness(int happiness) {
        if (happiness < 0 || happiness > 100) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_HAPPINESS);
        }

        this.happiness = happiness;
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    @Override
    public void setEnergy(int energy) {
        if (energy < 0 || energy > 100) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_ENERGY);
        }

        this.energy = energy;
    }

    @Override
    public int getProcedureTime() {
        return procedureTime;
    }

    @Override
    public void setProcedureTime(int procedureTime) {
        this.procedureTime = procedureTime;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean isBought() {
        return isBought;
    }

    @Override
    public void setBought(boolean bought) {
        isBought = bought;
    }

    @Override
    public boolean isRepaired() {
        return isRepaired;
    }

    @Override
    public void setRepaired(boolean repaired) {
        isRepaired = repaired;
    }

    @Override
    public abstract String toString();
}
