package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.visitor.TransformationFlipVisitorImpl;
import edu.kis.powp.jobs2d.command.visitor.TransformationRotateVisitorImpl;
import edu.kis.powp.jobs2d.command.visitor.IDriverCommandsVisitor;
import edu.kis.powp.jobs2d.command.visitor.TransformationScaleVisitorImpl;

/**
 * DriverCommand interface.
 */
public interface DriverCommand {

	/**
	 * Execute command on driver.
	 *
	 * @param driver driver.
	 */
	void execute(Job2dDriver job2dDriver);

	default DriverCommand driverCommandClone() {
		return this.driverCommandClone();
	}

	void accept(IDriverCommandsVisitor visitor);

	void accept(TransformationRotateVisitorImpl visitor);

	void accept(TransformationScaleVisitorImpl visitor);

	void accept(TransformationFlipVisitorImpl visitor);
}
