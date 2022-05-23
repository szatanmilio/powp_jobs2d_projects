package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.ComplexCommand;

public interface ComplexCommandTransformationVisitor {
	void visit(ComplexCommand complexCommand);
}
