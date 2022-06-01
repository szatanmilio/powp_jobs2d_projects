package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.Iterator;

public class TransformationFlipVisitorImpl implements IDriverCommandsVisitor {
	private boolean flipX;
	private boolean flipY;
	private int x;
	private int y;

	public TransformationFlipVisitorImpl(boolean flipX, boolean flipY) {
		this.flipX = flipX;
		this.flipY = flipY;
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
	}

	@Override
	public void doForSetPositionCommand(SetPositionCommand command) {
		this.x = command.getPosX();
		this.y = command.getPosY();
		this.flip(this.flipX, this.flipY);
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
