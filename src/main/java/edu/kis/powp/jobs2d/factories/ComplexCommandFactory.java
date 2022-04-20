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
                .addSetPosition(x, y)
                .addOperateTo(x+width, y)
                .addOperateTo(x+width, y+height)
                .addOperateTo(x, y+height)
                .addOperateTo(x, y)
                .build();

    }

    public ComplexCommand drawTriangle(int x, int y, int height, int width) {
        return new ComplexCommand.Builder()
                .addSetPosition(x, y)
                .addOperateTo(x, y+height)
                .addOperateTo(x+width, y+height)
                .addOperateTo(x, y)
                .build();
    }

    public ComplexCommand drawStar() {
        return new ComplexCommand.Builder()
                .addSetPosition(60, 20)
                .addOperateTo(100, 110)
                .addOperateTo(10, 50)
                .addOperateTo(110, 50)
                .addOperateTo(20, 110)
                .addOperateTo(60, 20)
                .build();
    }
}
