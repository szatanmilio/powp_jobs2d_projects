package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.*;

import java.util.Iterator;

public class TransformationFlipVisitorImpl implements IDriverCommandsVisitor {
	private boolean flipX;
	private boolean flipY;
	private int x;
	private int y;
	private DriverCommand tempCommand;

	public TransformationFlipVisitorImpl(boolean flipX, boolean flipY) {
		this.flipX = flipX;
		this.flipY = flipY;
	}

	private void flip(){
		this.x = flipX ? this.x * -1 : this.x;
		this.y = flipY ? this.y * -1 : this.y;
	}

	@Override
	public void doForOperateToCommand(OperateToCommand command) {
		this.x = command.getPosX();
		this.y = command.getPosY();
		this.flip();
		OperateToCommand operateToCommand = new OperateToCommand(this.x, this.y);
		this.tempCommand = operateToCommand;
	}

	@Override
	public void doForSetPositionCommand(SetPositionCommand command) {
		this.x = command.getPosX();
		this.y = command.getPosY();
		this.flip();
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
