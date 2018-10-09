/*
 * Created on Sep 10, 2003
 */
package examples.stackmachine;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JList;
import javax.swing.JViewport;

public class StackList extends JList {

    private final RunnableStackMachine machineGUI;

    StackList(RunnableStackMachine machineGUI) {
        updateModel(true);
        this.machineGUI = machineGUI;
        CustomListRenderer renderer = new CustomListRenderer();
        renderer.setBackground(Color.yellow);
        renderer.setForeground(Color.red);
        renderer.setOpaque(true);
        setCellRenderer(renderer);
    }

    void updateModel(boolean lifoModel) {
        if (lifoModel) {
            _currentStackModel = _lifoStackModel;
        } else {
            _currentStackModel = _fifoStackModel;
        }
        setModel(_currentStackModel);
    }

    void updateStackSizeLabel() {
        this.machineGUI._stackSizeLabel.setText("Number of elements: " + _currentStackModel.size());
    }

    void push(String value) {
        _currentStackModel.push(value);
        updateStackSizeLabel();
        adjustScroll();
    }

    void pop() {
        _currentStackModel.pop();
        updateStackSizeLabel();
        adjustScroll();
    }

    void add() {
        _currentStackModel.add();
        updateStackSizeLabel();
        adjustScroll();
    }

    void subtract() {
        _currentStackModel.subtract();
        updateStackSizeLabel();
        adjustScroll();
    }

    void multiply() {
        _currentStackModel.multiply();
        updateStackSizeLabel();
        adjustScroll();
    }

    void divide() {
        _currentStackModel.divide();
        updateStackSizeLabel();
        adjustScroll();
    }

    void adjustScroll() {
        JViewport viewport = this.machineGUI._scrollPane.getViewport();
        viewport.setBackground(Color.white);
        int list_size = _currentStackModel.getSize();
        if (list_size == 0)
            return;
        Rectangle cell_bounds = getCellBounds(list_size - 1, list_size - 1);
        int cell_height = cell_bounds.height;
        int list_height = cell_height * list_size;
        int list_component_height = this.machineGUI._scrollPane.getSize().height;
        if (list_component_height > list_height) {
            int position_y = -(list_component_height - list_height);
            viewport.setViewPosition(new Point(0, position_y));
        } else {
            viewport.setViewPosition(new Point(0, 0));
        }
        viewport.repaint();
    }

    private AbstractStackMachine _currentStackModel = null;

    private LifoStackMachine _lifoStackModel = new LifoStackMachine();

    private FifoStackMachine _fifoStackModel = new FifoStackMachine();
}
