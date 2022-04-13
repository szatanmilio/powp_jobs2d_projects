package edu.kis.powp.jobs2d.events;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import edu.kis.powp.jobs2d.features.DriverFeature;

public class MouseListener extends MouseInputAdapter {

    private JPanel drawArea;
    private int drawAreaWidth;
    private int drawAreaHeight;

    private final static int LEFT_BUTTON = 1;
    private final static int RIGHT_BUTTON = 3;

    public MouseListener(JPanel drawArea) {
        this.drawArea = drawArea;
    }
    
    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        drawAreaWidth = drawArea.getWidth();
        drawAreaHeight = drawArea.getHeight();

        int correctedX = e.getX() - drawAreaWidth / 2;
        int correctedY = e.getY() - drawAreaHeight / 2;

        switch (e.getButton()) {
            case LEFT_BUTTON:
                DriverFeature.getDriverManager().getCurrentDriver().operateTo(correctedX, correctedY);
                break;
        
            case RIGHT_BUTTON:
                DriverFeature.getDriverManager().getCurrentDriver().setPosition(correctedX, correctedY);
                break;

            default:
                break;
        }
    };
}
