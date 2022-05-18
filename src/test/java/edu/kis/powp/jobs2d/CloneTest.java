package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CloneTest {

	@Test
	public void testClone () {

		OperateToCommand operateToCommand = new OperateToCommand(10, 20);
		OperateToCommand copyOfOperateToCommand = operateToCommand.driverCommandClone();
		assertEquals(copyOfOperateToCommand.equals(operateToCommand), false);

		SetPositionCommand setPositionCommand = new SetPositionCommand(10, 20);
		SetPositionCommand copyOfSetPositionCommand = setPositionCommand.driverCommandClone();
		assertEquals(copyOfSetPositionCommand.equals(setPositionCommand), false);

		ComplexCommand complexCommand = new ComplexCommand();
		complexCommand.appendCommand(operateToCommand);
		complexCommand.appendCommand(setPositionCommand);
		ComplexCommand copyOfComplexCommand = complexCommand.driverCommandClone();
		List<DriverCommand> commandList =  copyOfComplexCommand.getCommandList();
		assertEquals(commandList.get(0).equals(operateToCommand), false);
		assertEquals(commandList.get(1).equals(operateToCommand), false);
		assertEquals(commandList.get(0).equals(setPositionCommand), false);
		assertEquals(commandList.get(1).equals(setPositionCommand), false);

		assertEquals(copyOfComplexCommand.equals(complexCommand), false);
	}
}
