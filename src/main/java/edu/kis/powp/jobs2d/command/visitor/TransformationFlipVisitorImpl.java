package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

public class TransformationFlipVisitorImpl implements TransformationFlipVisitor{
	int x;
	int y;

	@Override
	public void visit(OperateToCommand operateToCommand) {
		this.x = operateToCommand.getPosX();
		this.y = operateToCommand.getPosY();
	}

	@Override
	public void visit(SetPositionCommand setPositionCommand) {
		this.x = setPositionCommand.getPosX();
		this.y = setPositionCommand.getPosY();
	}

	private void flip(boolean flipX, boolean flipY){
		this.x = flipX ? this.x * -1 : this.x;
		this.y = flipY ? this.y * -1 : this.y;
	}
}
