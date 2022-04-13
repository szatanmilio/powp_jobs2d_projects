package edu.kis.powp.jobs2d.command;

import java.util.ArrayList;

public class ComplexCommandBuilder {
    private final ArrayList<DriverCommand> commandList = new ArrayList<>();

    public ComplexCommandBuilder addCommand(DriverCommand command) {
        commandList.add(command);
        return this;
    }

    public ComplexCommand build() {
        return new ComplexCommand(commandList);
    }
}
