package edu.kis.powp.jobs2d.drivers.decorators;

import edu.kis.powp.jobs2d.Job2dDriver;

public abstract class BaseJob2dDriverDecorator implements Job2dDriver {
	protected Job2dDriver wrappedDriver;

	public BaseJob2dDriverDecorator(Job2dDriver wrappedDriver) {
		this.wrappedDriver = wrappedDriver;
	}

	public void setPosition(int x, int y) {
		wrappedDriver.setPosition(x, y);
	}

	public void operateTo(int x, int y) {
		wrappedDriver.operateTo(x, y);
	}

}
