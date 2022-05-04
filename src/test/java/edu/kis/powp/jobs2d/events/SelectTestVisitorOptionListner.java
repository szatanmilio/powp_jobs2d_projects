package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.visitor.CommandCountingVisitor;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SelectTestVisitorOptionListner implements ActionListener {
	private final CommandCountingVisitor visitor;
	private final Logger logger;

	public SelectTestVisitorOptionListner(Logger logger, CommandCountingVisitor visitor) {
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
