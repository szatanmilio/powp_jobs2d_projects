package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.visitor.TestVisitor;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.magicpresets.FiguresJoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SelectTestVisitorOptionListner implements ActionListener {
	private final TestVisitor visitor;
	private final Logger logger;

	public SelectTestVisitorOptionListner(Logger logger, TestVisitor visitor) {
		this.logger = logger;
		this.visitor = visitor;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			CommandsFeature.getDriverCommandManager().getCurrentCommand().accept(this.visitor);
			this.visitor.logResults();
		} catch (NullPointerException ex) {
			this.logger.log(Level.WARNING, "Cannot run test because current command is not set.");
		}
	}
}
