package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.visitor.IElement;

/**
 * DriverCommand interface.
 */
public interface DriverCommand extends IElement {

	/**
	 * Execute command on driver.
	 * 
	 * @param driver driver.
	 */
	public void execute(Job2dDriver job2dDriver);
}
