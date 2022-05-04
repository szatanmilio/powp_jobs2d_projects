package edu.kis.powp.jobs2d.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.visitor.IVisitor;

public class ComplexCommand implements ICompoundCommand {

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
	public void accept(IVisitor visitor) {
		visitor.doForCompoundCommand(this);
	}

	@Override
	public Iterator<DriverCommand> iterator() {
		return commands.iterator();
	}
}
