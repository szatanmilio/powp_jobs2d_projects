package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.visitor.TransformationFlipVisitorImpl;
import edu.kis.powp.jobs2d.command.visitor.TransformationRotateVisitorImpl;
import edu.kis.powp.jobs2d.command.visitor.IDriverCommandsVisitor;
import edu.kis.powp.jobs2d.command.visitor.TransformationScaleVisitorImpl;

/**
 * Implementation of Job2dDriverCommand for setPosition command functionality.
 */
public class SetPositionCommand implements DriverCommand {

	private final int posX;
	private final int posY;

	public SetPositionCommand(int posX, int posY) {
		super();
		this.posX = posX;
		this.posY = posY;
	}

	@Override
	public void execute(Job2dDriver driver) {
		driver.setPosition(posX, posY);
	}

	@Override
	public SetPositionCommand driverCommandClone() {
		return new SetPositionCommand(this.posX, this.posY);
	}

	@Override
	public String toString() {
		return "Set Position\t" +
				"X=" + posX +
				"\tY=" + posY;
	}

	@Override
	public void accept(IDriverCommandsVisitor visitor) {
		visitor.doForSetPositionCommand(this);
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}
}
