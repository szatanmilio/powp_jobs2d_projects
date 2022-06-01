package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.Iterator;

public class TransformationRotateVisitorImpl implements IDriverCommandsVisitor {
	private int angle;
	private int x;
	private int y;

	public TransformationRotateVisitorImpl(int angle) {
		this.angle = angle;
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
	}

	@Override
	public void doForSetPositionCommand(SetPositionCommand command) {
		this.x = command.getPosX();
		this.y = command.getPosY();
		this.rotate(this.angle);
	}

	@Override
	public void doForCompoundCommand(ICompoundCommand command) {
		Iterator<DriverCommand> iterator = command.iterator();
		while (iterator.hasNext()) {
			DriverCommand tempDriverCommand = iterator.next();
			tempDriverCommand.accept(this);
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
