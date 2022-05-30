package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

public class TransformationRotateVisitorImpl implements TransformationRotateVisitor {
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

	private void rotate(int angle){
		double cos = Math.cos(Math.toRadians(angle));
		double sin = Math.sin(Math.toRadians(angle));
		this.x = (int) (this.x * cos - this.y * sin);
		this.y = (int) (this.x * sin + this.y * cos);
	}
}
