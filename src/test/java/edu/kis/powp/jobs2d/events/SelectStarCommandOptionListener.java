package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.factories.ComplexCommandFactory;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SelectStarCommandOptionListener implements ActionListener {

	private ComplexCommandFactory factory;
	public SelectStarCommandOptionListener(ComplexCommandFactory factory_)
	{
		factory=factory_;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		List<DriverCommand> commands = new ArrayList<DriverCommand>();
		
		commands.add(factory.generateStar());

		DriverCommandManager manager = CommandsFeature.getDriverCommandManager();
		manager.setCurrentCommand(commands, "StarCommand");
	}
}