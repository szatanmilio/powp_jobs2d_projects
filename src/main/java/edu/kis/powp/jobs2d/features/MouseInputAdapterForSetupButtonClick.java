package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.Job2dDriver;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseInputAdapterForSetupButtonClick extends MouseInputAdapter {

    private JPanel panelToDrawOn;
    private Job2dDriver driver;

    public MouseInputAdapterForSetupButtonClick(JPanel panelToDrawOn, Job2dDriver driver) {
        this.panelToDrawOn = panelToDrawOn;
        this.driver = driver;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point clickPoint = e.getPoint();
        clickPoint.x -= panelToDrawOn.getSize().width/2;
        clickPoint.y -= panelToDrawOn.getSize().height/2;
        if (e.getButton() == MouseEvent.BUTTON1) {
            driver.operateTo(clickPoint.x, clickPoint.y);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            driver.setPosition(clickPoint.x, clickPoint.y);
        }
    }
}
