package edu.kis.powp.jobs2d.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.factories.ComplexCommandFactory;
import edu.kis.powp.jobs2d.factories.ComplexCommandFactory.TestShape;
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
		switch (this.testShape) {
			case FIGURE1:
				FiguresJoe.figureScript1(driverManager.getCurrentDriver());
				break;
			case FIGURE2:
				FiguresJoe.figureScript2(driverManager.getCurrentDriver());
				break;

			case RECTANGLE:
				ComplexCommandFactory.getRectangle().execute(driverManager.getCurrentDriver());
				break;

			case STAR:
				ComplexCommandFactory.getStar().execute(driverManager.getCurrentDriver());
				break;
		}
	}
}
