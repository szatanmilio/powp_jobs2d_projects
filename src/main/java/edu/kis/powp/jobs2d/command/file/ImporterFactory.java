package edu.kis.powp.jobs2d.command.file;

import edu.kis.powp.jobs2d.command.file.IImportCommand.Type;

public class ImporterFactory {
	public static IImportCommand getImporter(Type importerType) {
		switch (importerType) {
			case CMD:
				return new ImportCMDFileCommand();
			case JSON:
				return new ImportJSONFileCommand();
			case XML:
				return new ImportXMLFileCommand();
			default:
				throw new IllegalArgumentException("File format not supported!");
		}
	}
}
