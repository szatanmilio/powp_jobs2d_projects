package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.*;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;

import java.util.Iterator;

public class TransformationFlipVisitorImpl implements IDriverCommandsVisitor {
	private boolean flipX;
	private boolean flipY;
	private int x;
	private int y;
	private ComplexCommand complexCommand;
	private DriverCommandManager driverCommandManager;

	public TransformationFlipVisitorImpl(boolean flipX, boolean flipY, DriverCommandManager commandManager) {
		this.flipX = flipX;
		this.flipY = flipY;
		this.complexCommand = new ComplexCommand();
		this.driverCommandManager = commandManager;
	}

	private void flip(boolean flipX, boolean flipY){
		this.x = flipX ? this.x * -1 : this.x;
		this.y = flipY ? this.y * -1 : this.y;
	}

	@Override
	public void doForOperateToCommand(OperateToCommand command) {
		this.x = command.getPosX();
		this.y = command.getPosY();
		this.flip(this.flipX, this.flipY);
		OperateToCommand operateToCommand = new OperateToCommand(this.x, this.y);
		this.complexCommand.appendCommand(operateToCommand);
	}

	@Override
	public void doForSetPositionCommand(SetPositionCommand command) {
		this.x = command.getPosX();
		this.y = command.getPosY();
		this.flip(this.flipX, this.flipY);
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
