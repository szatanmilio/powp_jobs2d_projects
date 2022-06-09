package edu.kis.powp.jobs2d.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.factories.ComplexCommandFactory;
import edu.kis.powp.jobs2d.factories.ComplexCommandFactory.TestShape;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.magicpresets.FiguresJoe;

public class SelectTestFigureOptionListener implements ActionListener {


	private DriverManager driverManager;
	private TestShape testShape;

	public SelectTestFigureOptionListener(DriverManager driverManager, TestShape shape) {
		this.driverManager = driverManager;
		this.testShape = shape;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Job2dDriver driver = driverManager.getCurrentDriver();
		List<DriverCommand> commands = new ArrayList<DriverCommand>();
		ComplexCommand complexCommand = new ComplexCommand(commands);
		switch (this.testShape) {
			case FIGURE1:
				FiguresJoe.figureScript1(driver);
				break;
			case FIGURE2:
				FiguresJoe.figureScript2(driver);
				break;
			case RECTANGLE:
				int x = 100;
				int y = 100;
				int width = 100;
				int height = 100;
				commands.add(new SetPositionCommand(x, y));
				commands.add(new OperateToCommand(x + width, y));
				commands.add(new OperateToCommand(x + width, y + height));
				commands.add(new OperateToCommand(x, y + height));
				commands.add(new OperateToCommand(x, y));
				complexCommand.execute(driver);
				break;
			case STAR:
				commands.add(new SetPositionCommand(60, 20));
				commands.add(new OperateToCommand(100, 110));
				commands.add(new OperateToCommand(10, 50));
				commands.add(new OperateToCommand(110, 50));
				commands.add(new OperateToCommand(20, 110));
				commands.add(new OperateToCommand(60, 20));
				complexCommand.execute(driver);
				break;

			case STAR_SCALE_2:
				new ComplexCommandFactory().getStarScale2().execute(driverManager.getCurrentDriver());
				break;
		}
	}
}
