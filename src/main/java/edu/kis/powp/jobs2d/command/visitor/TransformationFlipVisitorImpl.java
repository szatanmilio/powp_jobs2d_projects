package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

public class TransformationFlipVisitorImpl implements TransformationFlipVisitor {
	private boolean flipX;
	private boolean flipY;
	private int x;
	private int y;

	public TransformationFlipVisitorImpl(boolean flipX, boolean flipY) {
		this.flipX = flipX;
		this.flipY = flipY;
	}

	@Override
	public void visit(OperateToCommand operateToCommand) {
		this.x = operateToCommand.getPosX();
		this.y = operateToCommand.getPosY();
		this.flip(this.flipX, this.flipY);
	}

	@Override
	public void visit(SetPositionCommand setPositionCommand) {
		this.x = setPositionCommand.getPosX();
		this.y = setPositionCommand.getPosY();
		this.flip(this.flipX, this.flipY);
	}

	private void flip(boolean flipX, boolean flipY){
		this.x = flipX ? this.x * -1 : this.x;
		this.y = flipY ? this.y * -1 : this.y;
	}

	@Override
	public void doForOperateToCommand(OperateToCommand command) {

	}

	@Override
	public void doForSetPositionCommand(SetPositionCommand command) {

	}

	@Override
	public void doForCompoundCommand(ICompoundCommand command) {

	}
}
