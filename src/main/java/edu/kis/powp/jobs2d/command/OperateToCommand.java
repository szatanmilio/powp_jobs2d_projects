package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.visitor.TransformationFlipVisitorImpl;
import edu.kis.powp.jobs2d.command.visitor.TransformationRotateVisitorImpl;
import edu.kis.powp.jobs2d.command.visitor.IDriverCommandsVisitor;
import edu.kis.powp.jobs2d.command.visitor.TransformationScaleVisitorImpl;

/**
 * Implementation of Job2dDriverCommand for operateTo command functionality.
 */
public class OperateToCommand implements DriverCommand {

	private final int posX;
	private final int posY;

	public OperateToCommand(int posX, int posY) {
		super();
		this.posX = posX;
		this.posY = posY;
	}

	@Override
	public void execute(Job2dDriver driver) {
		driver.operateTo(posX, posY);
	}

	public String toString() {
		return "Operate To\t" +
				"X=" + posX +
				"\tY=" + posY;
	}

	@Override
	public OperateToCommand driverCommandClone() {
		return new OperateToCommand(this.posX, this.posY);
	}

	@Override
	public void accept(IDriverCommandsVisitor visitor) {
		if (visitor instanceof TransformationFlipVisitorImpl) {
			((TransformationFlipVisitorImpl) visitor).visit(this);
		} else if (visitor instanceof TransformationRotateVisitorImpl) {
			((TransformationRotateVisitorImpl) visitor).visit(this);
		} else if (visitor instanceof TransformationScaleVisitorImpl) {
			((TransformationScaleVisitorImpl) visitor).visit(this);
		} else {
			visitor.doForOperateToCommand(this);
		}
	}



	public int getPosY() {
		return posY;
	}

	public int getPosX() {
		return posX;
	}
}
