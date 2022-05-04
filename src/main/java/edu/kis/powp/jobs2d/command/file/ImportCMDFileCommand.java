package edu.kis.powp.jobs2d.command.file;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImportCMDFileCommand implements IImportCommand {

	@Override
	public List<DriverCommand> importCommandSequence(String text) {
		List<DriverCommand> commandList = new ArrayList<>();
		Scanner scanner = new Scanner(text);
		while (scanner.hasNextLine()) {
			try {
				commandList.add(stringToCommand(scanner.nextLine()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return commandList;
	}

	private DriverCommand stringToCommand(String command) throws Exception {
		try {
			String[] elements = command.split(" ");
			String name = elements[0];
			int x = Integer.parseInt(elements[1]);
			int y = Integer.parseInt(elements[2]);

			switch (name) {
				case "OperateTo":
					return new OperateToCommand(x, y);
				case "SetPosition":
					return new SetPositionCommand(x, y);
				default:
					throw new Exception();
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Command corrupted");
		}
	}
}
