package edu.kis.powp.jobs2d.command;

import java.util.ArrayList;
import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;

public class ComplexCommand implements DriverCommand {

	private List<DriverCommand> commands;

	public ComplexCommand()
	{
		this.commands = new ArrayList<>();
	}
	public void appendCommand(DriverCommand command)
	{
		this.commands.add(command);
	}

	@Override
	public void execute(Job2dDriver job2dDriver) {
		for(DriverCommand command: commands)
		{
			command.execute(job2dDriver);
		}
	}

	@Override
	public ComplexCommand driverCommandClone() {
		ComplexCommand tempComplexCommand = new ComplexCommand();
		for(DriverCommand driverCommand : this.commands) {
			DriverCommand tempDriverCommand = driverCommand.driverCommandClone();
			tempComplexCommand.appendCommand(tempDriverCommand);
		}
		return tempComplexCommand;
	}
}
