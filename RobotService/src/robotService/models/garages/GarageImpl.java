package robotService.models.garages;

import robotService.common.ExceptionMessages;
import robotService.models.garages.interfaces.Garage;
import robotService.models.robots.interfaces.Robot;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class GarageImpl implements Garage {
    private static final int CAPACITY = 10;
    private Map<String, Robot> robots;

    public GarageImpl() {
        this.robots = new LinkedHashMap<>();
    }

    @Override
    public Map<String, Robot> getRobots() {
        return Collections.unmodifiableMap(this.robots);
    }

    @Override
    public void manufacture(Robot robot) {
        if (robots.size() >= CAPACITY) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_ENOUGH_CAPACITY);
        }

        String name = robot.getName();
        if (robots.containsKey(name)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXISTING_ROBOT, name));
        }

        robots.put(robot.getName(), robot);
    }

    @Override
    public void sell(String robotName, String ownerName) {
        if (!robots.containsKey(robotName)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NON_EXISTING_ROBOT, robotName));
        }

        Robot robot = robots.get(robotName);
        robot.setOwner(ownerName);
        robot.setBought(true);
        robots.remove(robotName);
    }
}
