package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.jobs2d.command.DriverCommand;

import java.util.List;

public interface ICommandManagerPreview {
    void clear();
    void update(List<DriverCommand> commands);
}
