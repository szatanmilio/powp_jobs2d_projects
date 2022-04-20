package edu.kis.powp.jobs2d.command;

public class ComplexCommandFactory {
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
}
