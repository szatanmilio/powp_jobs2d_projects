package edu.kis.powp.jobs2d.command.gui;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.line.AbstractLine;
import edu.kis.legacy.drawer.shape.line.BasicLine;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;

import javax.swing.*;
import java.util.List;

public class CommandManagerPreview implements ICommandManagerPreview{
	private DrawPanelController controller;
	private LineDriverAdapter lineAdapter;

	public CommandManagerPreview(JPanel previewPanel) {
		controller = new DrawPanelController();
		controller.initialize(previewPanel);
		lineAdapter = new LineDriverAdapter(controller, new BasicLine(), "Preview");
	}

	public void update(List<DriverCommand> commands) {
		controller.clearPanel();
		for (DriverCommand dc : commands) {
			dc.execute(lineAdapter);
		}
	}

	public void clear() {
		controller.clearPanel();
	}
}
