package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.visitor.TestVisitor;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.magicpresets.FiguresJoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectTestVisitorOptionListner implements ActionListener {
	private final TestVisitor visitor;

	public SelectTestVisitorOptionListner(TestVisitor visitor) {
		this.visitor = visitor;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CommandsFeature.getDriverCommandManager().getCurrentCommand().accept(this.visitor);
		this.visitor.logResults();
	}
}
