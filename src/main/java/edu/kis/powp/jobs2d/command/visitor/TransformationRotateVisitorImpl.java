package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.*;

import java.util.Iterator;

public class TransformationRotateVisitorImpl implements IDriverCommandsVisitor {
	private int angle;
	private int x;
	private int y;
	private DriverCommand tempCommand;

	public TransformationRotateVisitorImpl(int angle) {
		this.angle = angle;
	}

	private void rotate(){
		double cos = Math.cos(Math.toRadians(this.angle));
		double sin = Math.sin(Math.toRadians(this.angle));
		int tempX = this.x;
		this.x = (int) Math.round((this.x * cos + this.y * sin));
		this.y = (int) Math.round((-(tempX * sin) + this.y * cos));
	}

	@Override
	public void doForOperateToCommand(OperateToCommand command) {
		this.x = command.getPosX();
		this.y = command.getPosY();
		this.rotate();
		OperateToCommand operateToCommand = new OperateToCommand(this.x, this.y);
		this.tempCommand = operateToCommand;
	}

	@Override
	public void doForSetPositionCommand(SetPositionCommand command) {
		this.x = command.getPosX();
		this.y = command.getPosY();
		this.rotate();
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
