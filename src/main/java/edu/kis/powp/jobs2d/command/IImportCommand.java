package edu.kis.powp.jobs2d.command;

import java.util.List;

public interface IImportCommand {

    List<DriverCommand> importCommand(String filename);
}
