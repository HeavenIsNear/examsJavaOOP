package robotService.models.procedures;

import robotService.common.ExceptionMessages;
import robotService.models.procedures.interfaces.Procedure;
import robotService.models.robots.interfaces.Robot;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseProcedure implements Procedure {
    protected List<Robot> robots;

    protected BaseProcedure() {
        this.robots = new ArrayList<>();
    }

    @Override
    public String history() {
        StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
        builder.append(System.lineSeparator());

        for (Robot robot : robots) {
            builder.append(robot.toString())
                    .append(System.lineSeparator());
        }

        return builder.toString().trim();
    }

    @Override
    public void doService(Robot robot, int procedureTime) {
        if (robot.getProcedureTime() < procedureTime) {
            throw new IllegalArgumentException(ExceptionMessages.INSUFFICIENT_PROCEDURE_TIME);
        }

        robot.setProcedureTime(robot.getProcedureTime() - procedureTime);
        robots.add(robot);
    }
}
