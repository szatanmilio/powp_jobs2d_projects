package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.*;

import java.util.Iterator;

public class TransformationScaleVisitorImpl implements IDriverCommandsVisitor {
	private int scaleRatio;
	private int x;
	private int y;
	private DriverCommand tempCommand;

	public TransformationScaleVisitorImpl(int scaleRatio) {
		this.scaleRatio = scaleRatio;
	}

	private void scale() {
		this.x = (this.x * this.scaleRatio);
		this.y = (this.y * this.scaleRatio);
	}

	@Override
	public void doForOperateToCommand(OperateToCommand command) {
		this.x = command.getPosX();
		this.y = command.getPosY();
		this.scale();
		OperateToCommand operateToCommand = new OperateToCommand(this.x, this.y);
		this.tempCommand = operateToCommand;
	}

	@Override
	public void doForSetPositionCommand(SetPositionCommand command) {
		this.x = command.getPosX();
		this.y = command.getPosY();
		this.scale();
		SetPositionCommand setPositionCommand = new SetPositionCommand(this.x, this.y);
		this.tempCommand = setPositionCommand;
	}

	@Override
	public void doForCompoundCommand(ICompoundCommand command) {
		Iterator<DriverCommand> iterator = command.iterator();
		ComplexCommand complexCommand = new ComplexCommand();
		while (iterator.hasNext()) {
			DriverCommand tempDriverCommand = iterator.next();
			tempDriverCommand.accept(this);
			complexCommand.appendCommand(this.tempCommand);
		}
		this.tempCommand = complexCommand;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public DriverCommand getTempCommand() {
		return tempCommand;
	}
}
