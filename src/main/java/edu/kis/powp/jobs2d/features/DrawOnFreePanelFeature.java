package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.drivers.DriverManager;

public class DrawOnFreePanelFeature {

	private static Application app;

	public static void setupButtonClick(Application application, DriverManager driverManager) {
		app = application;

		app.getFreePanel().addMouseListener(new MouseInputAdapterForSetupButtonClick(app.getFreePanel(),driverManager.getCurrentDriver()));
	}
}
