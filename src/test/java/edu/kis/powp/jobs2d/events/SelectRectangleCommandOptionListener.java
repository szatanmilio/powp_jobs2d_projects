package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.factories.ComplexCommandFactory;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SelectRectangleCommandOptionListener implements ActionListener {

	private ComplexCommandFactory factory;
	public SelectRectangleCommandOptionListener(ComplexCommandFactory factory_)
	{
		factory=factory_;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		List<DriverCommand> commands = new ArrayList<DriverCommand>();

		int x = 100;
		int y = 100;
		int width = 100;
		int height = 100;
		commands.add(factory.generateRectangle(x,y,width,height));

		DriverCommandManager manager = CommandsFeature.getDriverCommandManager();
		manager.setCurrentCommand(commands, "RectCommand");
	}
}