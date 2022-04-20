package edu.kis.powp.jobs2d.command.file;

import edu.kis.powp.jobs2d.command.DriverCommand;

import java.util.List;

public interface IImportCommand {

	List<DriverCommand> importCommand(String filename);
}
