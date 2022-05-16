package edu.kis.powp.jobs2d.drivers.decorators;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class Job2dDriverUsageMonitorDecorator extends BaseJob2dDriverDecorator {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private double totalDistanceTraveled;
	private double operationalDistanceTraveled;
	private int startX, startY;

	public Job2dDriverUsageMonitorDecorator(Job2dDriver wrappedDriver) {
		super(wrappedDriver);
		totalDistanceTraveled = 0;
		operationalDistanceTraveled = 0;
		setStartingPos(0, 0);
	}

	@Override
	public void setPosition(int x, int y) {
		wrappedDriver.setPosition(x, y);
		double distanceTraveled = calculateDistance(startX, startY, x, y);
		totalDistanceTraveled += distanceTraveled;
		logger.log(Level.INFO, "Glowica przesunela sie o : " + distanceTraveled + "\nCalkowite przesuniecie glowicy to : " + totalDistanceTraveled);
		setStartingPos(x, y);
	}

	@Override
	public void operateTo(int x, int y) {
		wrappedDriver.operateTo(x, y);
		double distanceTraveled = calculateDistance(startX, startY, x, y);
		totalDistanceTraveled += distanceTraveled;
		operationalDistanceTraveled += distanceTraveled;
		logger.log(Level.INFO, "Glowica przesunela sie o : " + distanceTraveled + "\nCalkowite przesuniecie glowicy to : " + totalDistanceTraveled);
		logger.log(Level.INFO, "Glowica wykonala prace o dlugosci : " + distanceTraveled + "\nCalkowita dlugosc pracy glowicy to : " + operationalDistanceTraveled);

		setStartingPos(x, y);
	}

	public double getTotalDistanceTraveled() {
		return totalDistanceTraveled;
	}

	public double getOperationalDistanceTraveled() {
		return operationalDistanceTraveled;
	}

	private void setStartingPos(int x, int y) {
		startX = x;
		startY = y;
	}

	private double calculateDistance(int startX, int startY, int x, int y) {
		return Math.sqrt(Math.pow(x - startX, 2) + Math.pow(y - startY, 2));
	}
}
