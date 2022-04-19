package edu.kis.powp.jobs2d.command;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportJSONfileCommand implements IImportCommand {

    @Override
    public List<DriverCommand> importCommand(String filename) {
        List<DriverCommand> commandList = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try {
            FileReader fileReader = new FileReader(filename);
            JSONArray commandArray = (JSONArray) jsonParser.parse(fileReader);
            commandArray.forEach(jo -> commandList.add(getCommand((JSONObject) jo)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
