package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.*;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;

import java.util.Iterator;

public class TransformationRotateVisitorImpl implements IDriverCommandsVisitor {
	private int angle;
	private int x;
	private int y;
	private ComplexCommand complexCommand;
	private DriverCommandManager driverCommandManager;

	public TransformationRotateVisitorImpl(int angle, DriverCommandManager commandManager) {
		this.angle = angle;
		this.complexCommand = new ComplexCommand();
		this.driverCommandManager = commandManager;
	}

	private void rotate(int angle){
		double cos = Math.cos(Math.toRadians(angle));
		double sin = Math.sin(Math.toRadians(angle));
		int tempX = this.x;
		this.x = (int) Math.round((this.x * cos + this.y * sin));
		this.y = (int) Math.round((-(tempX * sin) + this.y * cos));
	}

	@Override
	public void doForOperateToCommand(OperateToCommand command) {
		this.x = command.getPosX();
		this.y = command.getPosY();
		this.rotate(this.angle);
		OperateToCommand operateToCommand = new OperateToCommand(this.x, this.y);
		this.complexCommand.appendCommand(operateToCommand);
	}

	@Override
	public void doForSetPositionCommand(SetPositionCommand command) {
		this.x = command.getPosX();
		this.y = command.getPosY();
		this.rotate(this.angle);
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
