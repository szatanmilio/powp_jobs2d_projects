package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.visitor.IDriverCommandsVisitor;

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
}
