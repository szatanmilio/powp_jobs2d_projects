package edu.kis.powp.jobs2d.command.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileSystemView;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.panel.DrawPanelUI;
import edu.kis.legacy.drawer.shape.line.BasicLine;
import edu.kis.powp.appbase.Application;
import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.file.IImportCommand;
import edu.kis.powp.jobs2d.command.file.ImporterFactory;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Subscriber;

public class CommandManagerWindow extends JFrame implements WindowComponent {

	private DriverCommandManager commandManager;

	private JTextArea currentCommandField;

	private String observerListString;
	private JTextArea observerListField;
	private JPanel previewPanel;

	/**
	 *
	 */
	private static final long serialVersionUID = 9204679248304669948L;

	public CommandManagerWindow(DriverCommandManager commandManager) {
		this.setTitle("Command Manager");
		this.setSize(1000, 600);
		Container content = this.getContentPane();
		content.setLayout(new GridBagLayout());
		this.commandManager = commandManager;
		GridBagConstraints c = new GridBagConstraints();

		observerListField = new JTextArea("");
		observerListField.setEditable(false);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(observerListField, c);
		updateObserverListField();

		currentCommandField = new JTextArea("");
		currentCommandField.setEditable(false);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(currentCommandField, c);
		updateCurrentCommandField();

		previewPanel = new JPanel();
		previewPanel.setLayout(new GridBagLayout());
		previewPanel.setBackground(Color.white);
		previewPanel.setBorder(new LineBorder(Color.black));
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 10;
		c.gridx = 1;
		c.weighty = 20;
		content.add(previewPanel, c);


		JButton btnImportCommand = new JButton("Import command");
		btnImportCommand.addActionListener((ActionEvent e) -> this.importCommandSequence());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 1;
		c.weighty = 1;
		content.add(btnImportCommand, c);

		JButton btnClearCommand = new JButton("Clear command");
		btnClearCommand.addActionListener((ActionEvent e) -> this.clearCommand());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 1;
		c.weighty = 1;
		content.add(btnClearCommand, c);

		JButton btnClearObservers = new JButton("Delete observers");
		btnClearObservers.addActionListener((ActionEvent e) -> this.deleteObservers());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnClearObservers, c);
	}

	private void updatePanelPreview(List<DriverCommand> commandList) {
		DrawPanelController dpc = new DrawPanelController();
		dpc.initialize(previewPanel);
		LineDriverAdapter lda = new LineDriverAdapter(dpc, new BasicLine(), "Preview");
		for (DriverCommand dc : commandList) {
			dc.execute(lda);
		}
	}

	private void clearCommand() {
		commandManager.clearCurrentCommand();
		updateCurrentCommandField();
	}

	private String getTextFromFile(String filename) {
		StringBuilder contentBuilder = new StringBuilder();
		try (Stream<String> stream = Files.lines(Paths.get(filename))) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentBuilder.toString();
	}

	private void importCommandSequence() {
		JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
		int returnValue = fileChooser.showOpenDialog(null);
		if (returnValue != JFileChooser.APPROVE_OPTION) return;
		String path = fileChooser.getSelectedFile().getAbsolutePath();
		String[] elements = path.split("\\.");
		String extension = elements[elements.length - 1].toUpperCase();
		try {
			IImportCommand importCommand = ImporterFactory.getImporter(IImportCommand.Type.valueOf(extension));
			String text = getTextFromFile(path);
			List<DriverCommand> commandList = importCommand.importCommandSequence(text);
			commandManager.setCurrentCommand(commandList, fileChooser.getSelectedFile().getName());
			updatePanelPreview(commandList);
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "Error during parsing file!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void updateCurrentCommandField() {
		currentCommandField.setText(commandManager.getCurrentCommandString());
	}

	public void deleteObservers() {
		commandManager.getChangePublisher().clearObservers();
		this.updateObserverListField();
	}

	private void updateObserverListField() {
		observerListString = "";
		List<Subscriber> commandChangeSubscribers = commandManager.getChangePublisher().getSubscribers();
		for (Subscriber observer : commandChangeSubscribers) {
			observerListString += observer.toString() + System.lineSeparator();
		}
		if (commandChangeSubscribers.isEmpty())
			observerListString = "No observers loaded";

		observerListField.setText(observerListString);
	}

	@Override
	public void HideIfVisibleAndShowIfHidden() {
		updateObserverListField();
		if (this.isVisible()) {
			this.setVisible(false);
		} else {
			this.setVisible(true);
		}
	}

}
