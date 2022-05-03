package edu.kis.powp.jobs2d.command.file;

import edu.kis.powp.jobs2d.command.DriverCommand;

import java.util.List;

public class main {
	public static void main(String[] args) {
		ImportCMDFileCommand importer = new ImportCMDFileCommand();
		List<DriverCommand> commands = importer.importCommandSequence("C:\\Users\\konra\\Git\\powp_jobs2d_projects\\src\\resources\\example.cmd");
		System.out.println("done");
	}
}
