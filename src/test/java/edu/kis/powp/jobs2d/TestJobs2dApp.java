package edu.kis.powp.jobs2d;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.appbase.Application;

import edu.kis.powp.jobs2d.command.gui.CommandManagerWindow;
import edu.kis.powp.jobs2d.command.gui.CommandManagerWindowCommandChangeObserver;
import edu.kis.powp.jobs2d.command.visitor.CommandCountingVisitor;

import edu.kis.powp.jobs2d.command.gui.CommandManagerPreview;
import edu.kis.powp.jobs2d.drivers.DriverComposite;

import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.decorators.Job2dDriverUsageMonitorDecorator;
import edu.kis.powp.jobs2d.drivers.gui.DriverUpdateInfoObserver;
import edu.kis.powp.jobs2d.events.*;
import edu.kis.powp.jobs2d.factories.ComplexCommandFactory;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.DrawOnFreePanelFeature;
import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;

public class TestJobs2dApp {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Setup test concerning preset figures in context.
	 *
	 * @param application Application context.
	 */
	private static void setupPresetTests(Application application) {
		SelectTestFigureOptionListener figure1 = new SelectTestFigureOptionListener(
				DriverFeature.getDriverManager(), ComplexCommandFactory.TestShape.FIGURE1);
		SelectTestFigure2OptionListener figure2 = new SelectTestFigure2OptionListener(
				DriverFeature.getDriverManager());
		SelectTestFigureOptionListener rectangle = new SelectTestFigureOptionListener(
				DriverFeature.getDriverManager(), ComplexCommandFactory.TestShape.RECTANGLE);
		SelectTestFigureOptionListener star = new SelectTestFigureOptionListener(
				DriverFeature.getDriverManager(), ComplexCommandFactory.TestShape.STAR);
		SelectTestFigureOptionListener starScale2 = new SelectTestFigureOptionListener(
				DriverFeature.getDriverManager(), ComplexCommandFactory.TestShape.STAR_SCALE_2);

		application.addTest("Figure Joe 1", figure1);
		application.addTest("Figure Joe 2", figure2);
		application.addTest("Rectangle", rectangle);
		application.addTest("Star", star);
		application.addTest("Star Scale 2", starScale2);
	}

	/**
	 * Setup test using driver commands in context.
	 *
	 * @param application Application context.
	 */
	private static void setupCommandTests(Application application) {
		ComplexCommandFactory factory = new ComplexCommandFactory();
		application.addTest("Load secret command", new SelectLoadSecretCommandOptionListener());
		application.addTest("Star Command", new SelectStarCommandOptionListener(factory));
		application.addTest("Rect Command", new SelectRectangleCommandOptionListener(factory));

		application.addTest("Run command", new SelectRunCurrentCommandOptionListener(DriverFeature.getDriverManager()));

	}

	private static void setupVisitors(Application application) {
		application.addTest("Commands Counting Visitor", new SelectCommandsCountingVisitorOptionListner(logger, new CommandCountingVisitor(logger)));
	}

	/**
	 * Setup driver manager, and set default Job2dDriver for application.
	 *
	 * @param application Application context.
	 */
	private static void setupDrivers(Application application) {
		DriverComposite driverComposite = new DriverComposite();
		DriverFeature.addDriver("Line, Logger, Special Simulators", driverComposite);

		DriverUpdateInfoObserver observer = new DriverUpdateInfoObserver();
		DriverFeature.getDriverManager().getChangePublisher().addSubscriber(observer);

		Job2dDriver loggerDriver = new LoggerDriver();
		DriverFeature.addDriver("Logger driver", loggerDriver);
		driverComposite.addDriver(loggerDriver);

		DrawPanelController drawerController = DrawerFeature.getDrawerController();
		Job2dDriver driver = new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic");
		DriverFeature.addDriver("Line Simulator", driver);
		driverComposite.addDriver(driver);

		DriverFeature.addDriver("Line Simulator with monitor", new Job2dDriverUsageMonitorDecorator(driver));

		driver = new LineDriverAdapter(drawerController, LineFactory.getSpecialLine(), "special");
		DriverFeature.addDriver("Special line Simulator", driver);
		driverComposite.addDriver(driver);

		DriverFeature.addDriver("Special line Simulator with monitor", new Job2dDriverUsageMonitorDecorator(driver));
	}

	private static void setupWindows(Application application) {

		CommandManagerWindow commandManager = new CommandManagerWindow(CommandsFeature.getDriverCommandManager());
		CommandManagerPreview preview = new CommandManagerPreview(commandManager.getPreviewPanel());
		commandManager.setPreview(preview);
		application.addWindowComponent("Command Manager", commandManager);

		CommandManagerWindowCommandChangeObserver windowObserver = new CommandManagerWindowCommandChangeObserver(
				commandManager);
		CommandsFeature.getDriverCommandManager().getChangePublisher().addSubscriber(windowObserver);
	}

	/**
	 * Setup menu for adjusting logging settings.
	 *
	 * @param application Application context.
	 */
	private static void setupLogger(Application application) {

		application.addComponentMenu(Logger.class, "Logger", 0);
		application.addComponentMenuElement(Logger.class, "Clear log",
				(ActionEvent e) -> application.flushLoggerOutput());
		application.addComponentMenuElement(Logger.class, "Fine level", (ActionEvent e) -> logger.setLevel(Level.FINE));
		application.addComponentMenuElement(Logger.class, "Info level", (ActionEvent e) -> logger.setLevel(Level.INFO));
		application.addComponentMenuElement(Logger.class, "Warning level",
				(ActionEvent e) -> logger.setLevel(Level.WARNING));
		application.addComponentMenuElement(Logger.class, "Severe level",
				(ActionEvent e) -> logger.setLevel(Level.SEVERE));
		application.addComponentMenuElement(Logger.class, "OFF logging", (ActionEvent e) -> logger.setLevel(Level.OFF));
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Application app = new Application("Jobs 2D");
				DrawerFeature.setupDrawerPlugin(app);
				CommandsFeature.setupCommandManager();

				DriverFeature.setupDriverPlugin(app);
				setupDrivers(app);
				setupPresetTests(app);
				setupCommandTests(app);
				setupLogger(app);
				setupWindows(app);
				setupVisitors(app);

				DrawOnFreePanelFeature.setupButtonClick(app, DriverFeature.getDriverManager());

				app.setVisibility(true);
			}
		});
	}

}
