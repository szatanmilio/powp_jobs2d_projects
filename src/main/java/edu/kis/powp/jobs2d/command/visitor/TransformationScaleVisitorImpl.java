package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

public class TransformationScaleVisitorImpl implements TransformationScaleVisitor{
	private int scaleRatio;
	private int x;
	private int y;

	public TransformationScaleVisitorImpl(int scaleRatio) {
		this.scaleRatio = scaleRatio;
	}

	@Override
	public void visit(OperateToCommand operateToCommand) {
		this.x = operateToCommand.getPosX();
		this.y = operateToCommand.getPosY();
		this.scale(this.scaleRatio);
	}

	@Override
	public void visit(SetPositionCommand setPositionCommand) {
		this.x = setPositionCommand.getPosX();
		this.y = setPositionCommand.getPosY();
		this.scale(this.scaleRatio);
	}

	private void scale(int scale){
		this.x = (this.x * scale);
		this.y = (this.y * scale);
	}
}
