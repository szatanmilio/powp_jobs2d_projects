package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.*;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;

import java.util.Iterator;

public class TransformationScaleVisitorImpl implements IDriverCommandsVisitor {
	private int scaleRatio;
	private int x;
	private int y;
	private ComplexCommand complexCommand;
	private DriverCommandManager driverCommandManager;

	public TransformationScaleVisitorImpl(int scaleRatio, DriverCommandManager commandManager) {
		this.scaleRatio = scaleRatio;
		this.complexCommand = new ComplexCommand();
		this.driverCommandManager = commandManager;
	}

	private void scale(int scale){
		this.x = (this.x * scale);
		this.y = (this.y * scale);
	}

	@Override
	public void doForOperateToCommand(OperateToCommand command) {
		this.x = command.getPosX();
		this.y = command.getPosY();
		this.scale(this.scaleRatio);
		OperateToCommand operateToCommand = new OperateToCommand(this.x, this.y);
		this.complexCommand.appendCommand(operateToCommand);
	}

	@Override
	public void doForSetPositionCommand(SetPositionCommand command) {
		this.x = command.getPosX();
		this.y = command.getPosY();
		this.scale(this.scaleRatio);
		SetPositionCommand setPositionCommand = new SetPositionCommand(this.x, this.y);
		this.complexCommand.appendCommand(setPositionCommand);
	}

	@Override
	public void doForCompoundCommand(ICompoundCommand command) {
		Iterator<DriverCommand> iterator = command.iterator();
		while (iterator.hasNext()) {
			DriverCommand tempDriverCommand = iterator.next();
			tempDriverCommand.accept(this);
		}
		this.driverCommandManager.setCurrentCommand(this.complexCommand);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
