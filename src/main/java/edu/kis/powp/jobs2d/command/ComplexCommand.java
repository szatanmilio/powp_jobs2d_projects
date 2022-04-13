package edu.kis.powp.jobs2d.command;
import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.Iterator;
import java.util.List;

public class ComplexCommand implements ICompoundCommand {
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

    @Override
    public Iterator<DriverCommand> iterator() {
        return commandList.iterator();
    }
}
