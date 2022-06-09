package edu.kis.powp.jobs2d.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.visitor.IDriverCommandsVisitor;

public class ComplexCommand implements ICompoundCommand {
	private final List<DriverCommand> commands;

	public ComplexCommand() {
		this.commands = new ArrayList<>();
	}

	public ComplexCommand(List<DriverCommand> commandList) {
		this.commands = commandList;
	}

	public void appendCommand(DriverCommand command) {
		this.commands.add(command);
  }

	public String toString() {
		String output="";
		for(DriverCommand com : commands)
			output = output + com.toString() + "\n";
		return output;
	}

	public List<DriverCommand> getCommandList() {
		return commands;
	}

	@Override
	public void execute(Job2dDriver job2dDriver) {
		for (DriverCommand command : commands) {
			command.execute(job2dDriver);
		}
	}

	@Override
	public ComplexCommand driverCommandClone() {
		ComplexCommand tempComplexCommand = new ComplexCommand();
		for (DriverCommand driverCommand : this.commands) {
			DriverCommand tempDriverCommand = driverCommand.driverCommandClone();
			tempComplexCommand.appendCommand(tempDriverCommand);
		}
		return tempComplexCommand;
	}

	@Override
	public void accept(IDriverCommandsVisitor visitor) {
		visitor.doForCompoundCommand(this);
	}

	@Override
	public Iterator<DriverCommand> iterator() {
		return commands.iterator();
  }


	public static final class Builder {

		private final List<DriverCommand> commandList = new ArrayList<>();

		public Builder addCommand(DriverCommand command) {
			commandList.add(command);
			return this;
		}

		public Builder addSetPosition(int x, int y) {
			commandList.add(new SetPositionCommand(x, y));
			return this;
		}

		public Builder addOperateTo(int x, int y) {
			commandList.add(new OperateToCommand(x, y));
			return this;
		}

		public ComplexCommand build() {
			return new ComplexCommand(new ArrayList<>(commandList));
		}
	}
}
