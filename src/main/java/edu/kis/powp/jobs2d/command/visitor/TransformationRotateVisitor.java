package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

public interface TransformationRotateVisitor {
	void visit(OperateToCommand operateToCommand);

	void visit(SetPositionCommand setPositionCommand);
}
