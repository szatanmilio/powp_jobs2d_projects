package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.visitor.TransformationFlipVisitorImpl;
import edu.kis.powp.jobs2d.command.visitor.TransformationRotateVisitorImpl;
import edu.kis.powp.jobs2d.command.visitor.TransformationScaleVisitorImpl;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class TransformationVisitorTest {
	@Test
	public void visitorTest() {

		DriverCommandManager driverCommandManager = new DriverCommandManager();
		OperateToCommand operateToCommand = new OperateToCommand(10, 20);
		SetPositionCommand setPositionCommand = new SetPositionCommand(10, 20);

		// flip visitor operateTo
		TransformationFlipVisitorImpl flipVisitor = new TransformationFlipVisitorImpl(true, false, driverCommandManager);
		flipVisitor.doForOperateToCommand(operateToCommand);
		assertEquals(flipVisitor.getX(), -10);
		assertEquals(flipVisitor.getY(), 20);

		flipVisitor = new TransformationFlipVisitorImpl(true, true, driverCommandManager);
		flipVisitor.doForOperateToCommand(operateToCommand);
		assertEquals(flipVisitor.getX(), -10);
		assertEquals(flipVisitor.getY(), -20);

		flipVisitor = new TransformationFlipVisitorImpl(false, true, driverCommandManager);
		flipVisitor.doForOperateToCommand(operateToCommand);
		assertEquals(flipVisitor.getX(), 10);
		assertEquals(flipVisitor.getY(), -20);

		flipVisitor = new TransformationFlipVisitorImpl(false, false, driverCommandManager);
		flipVisitor.doForOperateToCommand(operateToCommand);
		assertEquals(flipVisitor.getX(), 10);
		assertEquals(flipVisitor.getY(), 20);


		// flip visitor setPosition
		flipVisitor = new TransformationFlipVisitorImpl(true, false, driverCommandManager);
		flipVisitor.doForSetPositionCommand(setPositionCommand);
		assertEquals(flipVisitor.getX(), -10);
		assertEquals(flipVisitor.getY(), 20);

		flipVisitor = new TransformationFlipVisitorImpl(true, true, driverCommandManager);
		flipVisitor.doForSetPositionCommand(setPositionCommand);
		assertEquals(flipVisitor.getX(), -10);
		assertEquals(flipVisitor.getY(), -20);

		flipVisitor = new TransformationFlipVisitorImpl(false, true, driverCommandManager);
		flipVisitor.doForSetPositionCommand(setPositionCommand);
		assertEquals(flipVisitor.getX(), 10);
		assertEquals(flipVisitor.getY(), -20);

		flipVisitor = new TransformationFlipVisitorImpl(false, false, driverCommandManager);
		flipVisitor.doForSetPositionCommand(setPositionCommand);
		assertEquals(flipVisitor.getX(), 10);
		assertEquals(flipVisitor.getY(), 20);


		// rotate visitor operateTo
		TransformationRotateVisitorImpl rotateVisitor = new TransformationRotateVisitorImpl(90, driverCommandManager);
		rotateVisitor.doForOperateToCommand(operateToCommand);
		assertEquals(rotateVisitor.getX(), 20);
		assertEquals(rotateVisitor.getY(), -10);

		rotateVisitor = new TransformationRotateVisitorImpl(180, driverCommandManager);
		rotateVisitor.doForOperateToCommand(operateToCommand);
		assertEquals(rotateVisitor.getX(), -10);
		assertEquals(rotateVisitor.getY(), -20);

		rotateVisitor = new TransformationRotateVisitorImpl(270, driverCommandManager);
		rotateVisitor.doForOperateToCommand(operateToCommand);
		assertEquals(rotateVisitor.getX(), -20);
		assertEquals(rotateVisitor.getY(), 10);


		// rotate visitor setPosition
		rotateVisitor = new TransformationRotateVisitorImpl(90, driverCommandManager);
		rotateVisitor.doForSetPositionCommand(setPositionCommand);
		assertEquals(rotateVisitor.getX(), 20);
		assertEquals(rotateVisitor.getY(), -10);

		rotateVisitor = new TransformationRotateVisitorImpl(180, driverCommandManager);
		rotateVisitor.doForSetPositionCommand(setPositionCommand);
		assertEquals(rotateVisitor.getX(), -10);
		assertEquals(rotateVisitor.getY(), -20);

		rotateVisitor = new TransformationRotateVisitorImpl(270, driverCommandManager);
		rotateVisitor.doForSetPositionCommand(setPositionCommand);
		assertEquals(rotateVisitor.getX(), -20);
		assertEquals(rotateVisitor.getY(), 10);

		// scale visitor operateTo
		TransformationScaleVisitorImpl scaleVisitor = new TransformationScaleVisitorImpl(2, driverCommandManager);
		scaleVisitor.doForOperateToCommand(operateToCommand);
		assertEquals(scaleVisitor.getX(), 20);
		assertEquals(scaleVisitor.getY(), 40);

		// scale visitor setPosition
		scaleVisitor = new TransformationScaleVisitorImpl(2, driverCommandManager);
		scaleVisitor.doForSetPositionCommand(setPositionCommand);
		assertEquals(scaleVisitor.getX(), 20);
		assertEquals(scaleVisitor.getY(), 40);

	}
}