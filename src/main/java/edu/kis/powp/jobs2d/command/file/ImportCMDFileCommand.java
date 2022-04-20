package edu.kis.powp.jobs2d.command.file;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ImportCMDFileCommand implements IImportCommand {

	@Override
	public List<DriverCommand> importCommand(String filename) {
		List<DriverCommand> commandList = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			while (br.ready()) {
				commandList.add(stringToCommand(br.readLine()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
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
