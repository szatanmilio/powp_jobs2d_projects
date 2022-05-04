package edu.kis.powp.jobs2d.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;

public class ComplexCommand implements DriverCommand {

	private Collection<DriverCommand> commands;

	public ComplexCommand() {
		this.commands = new ArrayList<>();
	}

	public ComplexCommand(ArrayList<DriverCommand> driverCommands) {
		this.commands = new ArrayList<>(driverCommands);
	}

	@Override
	public void execute(Job2dDriver job2dDriver) {
		for(DriverCommand command: commands) {
			command.execute(job2dDriver);
		}
	}

	public static final class Builder {

		private final List<DriverCommand> commandList = new ArrayList<>();

		public Builder add(DriverCommand command) {
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
