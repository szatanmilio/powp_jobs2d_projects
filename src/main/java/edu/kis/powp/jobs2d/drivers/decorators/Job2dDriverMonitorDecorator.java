package edu.kis.powp.jobs2d.drivers.decorators;

import edu.kis.powp.jobs2d.Job2dDriver;

public final class Job2dDriverMonitorDecorator extends BaseJob2dDriverDecorator {
	private double totalDistanceTraveled;
	private double operationalDistanceTraveled;
	private int startX, startY;

	public Job2dDriverMonitorDecorator(Job2dDriver wrappedDriver) {
		super(wrappedDriver);
		totalDistanceTraveled = 0;
		operationalDistanceTraveled = 0;
		setStartingPos(0, 0);
	}

	@Override
	public void setPosition(int x, int y) {
		wrappedDriver.setPosition(x, y);
		totalDistanceTraveled += calculateDistance(startX, startY, x, y);
		setStartingPos(x, y);
	}

	@Override
	public void operateTo(int x, int y) {
		wrappedDriver.operateTo(x, y);
		double distanceTraveled = calculateDistance(startX, startY, x, y);
		totalDistanceTraveled += distanceTraveled;
		operationalDistanceTraveled += distanceTraveled;
		setStartingPos(x, y);
	}

	private void setStartingPos(int x, int y) {
		startX = x;
		startY = y;
	}

	// ze wzoru na odleglosc miedzy dwoma punktami
	private double calculateDistance(int startX, int startY, int x, int y) {
		return Math.sqrt(Math.pow(x - startX, 2) + Math.pow(y - startY, 2));
	}
}
