package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.Iterator;

public class TransformationScaleVisitorImpl implements IDriverCommandsVisitor {
	private int scaleRatio;
	private int x;
	private int y;

	public TransformationScaleVisitorImpl(int scaleRatio) {
		this.scaleRatio = scaleRatio;
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
	}

	@Override
	public void doForSetPositionCommand(SetPositionCommand command) {
		this.x = command.getPosX();
		this.y = command.getPosY();
		this.scale(this.scaleRatio);
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
