package edu.kis.powp.jobs2d.command;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ImportXMLFileCommand implements IImportCommand {
    private final List<DriverCommand> commandList = new ArrayList<>();
    @Override
    public List<DriverCommand> importCommand(String filename) {
        processFile(filename);
        return commandList;
    }

    private void addCommand(String commandName, String x, String y) {
        int xValue = Integer.parseInt(x);
        int yValue = Integer.parseInt(y);
        switch (commandName) {
            case "OperateTo":
                commandList.add(new OperateToCommand(xValue, yValue));
                break;
            case "SetPosition":
                commandList.add(new SetPositionCommand(xValue, yValue));
                break;
            default:
                throw new IllegalArgumentException("Command not supported!");
        }
    }

    private void processFile(String filename) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(filename));
            document.getDocumentElement().normalize();
            NodeList list = document.getDocumentElement().getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    addCommand(element.getNodeName(),
                            element.getAttribute("x"),
                            element.getAttribute("y"));
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
