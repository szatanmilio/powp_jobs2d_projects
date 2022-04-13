package edu.kis.powp.jobs2d.command;
import edu.kis.powp.jobs2d.Job2dDriver;
import java.util.List;

public class ComplexCommand implements DriverCommand {
    private final List<DriverCommand> commandList;

    public ComplexCommand(List<DriverCommand> commandList) {
        this.commandList = commandList;
    }

    @Override
    public void execute(Job2dDriver driver) {
        for (DriverCommand command : this.commandList) {
            command.execute(driver);
        }
    }
}
