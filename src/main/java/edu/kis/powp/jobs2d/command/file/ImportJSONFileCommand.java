package edu.kis.powp.jobs2d.command.file;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ImportJSONFileCommand implements IImportCommand {

	@Override
	public List<DriverCommand> importCommandSequence(String text) {
		List<DriverCommand> commandList = new ArrayList<>();
		JSONParser jsonParser = new JSONParser();
		try {
			JSONArray commandArray = (JSONArray) jsonParser.parse(text);
			commandArray.forEach(jo -> commandList.add(getCommand((JSONObject) jo)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return commandList;
	}

	private DriverCommand getCommand(JSONObject jsonObject) {
		String name = (String) jsonObject.get("name");
		JSONObject position = (JSONObject) jsonObject.get("position");
		int x = ((Long) position.get("x")).intValue();
		int y = ((Long) position.get("y")).intValue();
		switch (name) {
			case "OperateTo":
				return new OperateToCommand(x, y);
			case "SetPosition":
				return new SetPositionCommand(x, y);
			default:
				return null;
		}

	}
}
