package edu.kis.powp.jobs2d.factories;

import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

public class ComplexCommandFactory {
    public enum TestShape {
        FIGURE1, FIGURE2 ,RECTANGLE, STAR
    }

    public ComplexCommand drawRectangle(int x, int y, int width, int height) {
        return new ComplexCommand.Builder()
                .add(new SetPositionCommand(x, y)).add(new OperateToCommand(x+width, y))
                .add(new OperateToCommand(x+width, y+height))
                .add(new OperateToCommand(x, y+height))
                .add(new OperateToCommand(x, y))
                .build();

    }
    public ComplexCommand drawTriangle(int x, int y, int height, int width) {
        return new ComplexCommand.Builder()
                .add(new SetPositionCommand(x, y))
                .add(new OperateToCommand(x, y+height))
                .add(new OperateToCommand(x+width, y+height))
                .add(new OperateToCommand(x, y))
                .build();
    }

    public ComplexCommand drawStar()
    {
        return new ComplexCommand.Builder()
            .add(new SetPositionCommand(60, 20))
            .add(new OperateToCommand(100, 110))
            .add(new OperateToCommand(10, 50))
            .add(new OperateToCommand(110, 50))
            .add(new OperateToCommand(20, 110))
            .add(new OperateToCommand(60, 20))
            .build();
    }
}
