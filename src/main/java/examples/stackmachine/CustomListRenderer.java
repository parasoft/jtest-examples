/*
 * Created on Sep 10, 2003
 */
package examples.stackmachine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.DefaultListCellRenderer;

/**
 * Custom implementation of the DefaultListCellRenderer
 */
public class CustomListRenderer extends DefaultListCellRenderer {

    private final RunnableStackMachine machineGUI = null;

    /**
     * @param g StackMachineGUI
     */
    @Override
    public void paint(Graphics g) {
        setBackground(Color.yellow);
        super.paint(g);
        Dimension size = getSize();
        g.setColor(Color.gray);
        g.drawLine(0, 0, size.width, 0);
    }
}
