package examples.stackmachine;

//
// This class uses StackMachine.java as a client.
// Please check the comments in StackMachine.java for more information.
//
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * @author staff
 */
public class RunnableStackMachine extends JFrame implements ComponentListener, WindowListener {

    RunnableStackMachine() {
        super("Stack Machine Example");
        _stackSizeLabel = new JLabel("Number of elements: 0");
        Container content_pane = getContentPane();
        content_pane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel stackPanel = createStackPanel();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.gridwidth = 2;
        gbc.insets.top = 5;
        gbc.insets.left = 5;
        content_pane.add(_stackSizeLabel, gbc);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(0, 5, 5, 5);
        content_pane.add(stackPanel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0;
        content_pane.add(createInputPanel(), gbc);
        setSize(400, 450);
        _stackList.adjustScroll();
        addComponentListener(this);
        addWindowListener(this);
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowOpened(WindowEvent e) {
    }

    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        _stackList.adjustScroll();
    }

    public void componentShown(ComponentEvent e) {
    }

    private JPanel createStackPanel() {
        _stackList = new StackList(this);
        _stackList.setToolTipText("Stack");
        _scrollPane = new JScrollPane(_stackList);
        _stackSizeLabel.setForeground(Color.black);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.weightx = 1;
        gbc.weighty = 1;
        panel.add(_scrollPane, gbc);
        gbc.weighty = 0;
        panel.add(createStackButtonPanel(), gbc);
        return panel;
    }

    private JPanel createStackButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = GridBagConstraints.RELATIVE;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        PushButton pushButton = new PushButton();
        pushButton.setToolTipText("Add an element to the stack");
        PopButton popButton = new PopButton();
        popButton.setToolTipText("Remove an element from the stack");
        panel.add(pushButton, gbc);
        panel.add(popButton, gbc);
        return panel;
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.weightx = 1;
        panel.add(createRadioOptionsPanel(), gbc);
        gbc.insets.top = 8;
        panel.add(createInputFieldsPanel(), gbc);
        panel.add(createOperationButtonsPanel(), gbc);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 5;
        panel.add(new JPanel(), gbc);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        panel.add(createApiPanel(), gbc);
        return panel;
    }

    private JPanel createApiPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        return panel;
    }

    private JPanel createOperationButtonsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = GridBagConstraints.RELATIVE;
        gbc.gridy = 0;
        AddButton addButton = new AddButton();
        addButton.setToolTipText("Remove two elements from the stack and add the sum of them");
        SubtractButton subtractButton = new SubtractButton();
        subtractButton.setToolTipText("Remove two elements from the stack and add the sub of them");
        MultiplyButton multiplyButton = new MultiplyButton();
        multiplyButton.setToolTipText("Remove two elements from the stack and add the dev of them");
        DivideButton divideButton = new DivideButton();
        divideButton.setToolTipText("Remove two elements from the stack and add the mult of them");
        panel.add(addButton, gbc);
        panel.add(subtractButton, gbc);
        panel.add(multiplyButton, gbc);
        panel.add(divideButton, gbc);
        return panel;
    }

    private JPanel createInputFieldsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.weightx = 1;
        panel.add(createPushPanel(), gbc);
        return panel;
    }

    private JPanel createRadioOptionsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.weightx = 1;
        JLabel title = new JLabel("Stack Model:");
        title.setForeground(Color.black);
        panel.add(title, gbc);
        LifoRadioButton lifoRadioButton = new LifoRadioButton();
        FifoRadioButton fifoRadioButton = new FifoRadioButton();
        lifoRadioButton.setToolTipText("LIFO stack model");
        fifoRadioButton.setToolTipText("FIFO stack model");
        ButtonGroup group = new ButtonGroup();
        group.add(lifoRadioButton);
        group.add(fifoRadioButton);
        panel.add(lifoRadioButton, gbc);
        panel.add(fifoRadioButton, gbc);
        return panel;
    }

    private JPanel createPushPanel() {
        _pushTextField = new PushTextField();
        _pushTextField.setToolTipText("Input field");
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        JLabel input = new JLabel("Input:");
        input.setForeground(Color.black);
        panel.add(input, gbc);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.insets.right = 0;
        panel.add(_pushTextField, gbc);
        return panel;
    }

    class PushTextField extends JTextField implements ActionListener {

        PushTextField() {
            super(3);
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent event) {
            _stackList.push(getText());
            setText("");
        }
    }

    class LifoRadioButton extends JRadioButton implements ActionListener {

        LifoRadioButton() {
            super("LIFO");
            setActionCommand("LIFO");
            setSelected(true);
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent event) {
            _stackList.updateModel(true);
            _stackList.updateStackSizeLabel();
            _stackList.adjustScroll();
        }
    }

    class FifoRadioButton extends JRadioButton implements ActionListener {

        FifoRadioButton() {
            super("FIFO");
            setActionCommand("FIFO");
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent event) {
            _stackList.updateModel(false);
            _stackList.updateStackSizeLabel();
            _stackList.adjustScroll();
        }
    }

    class PushButton extends ImageButton implements ActionListener {

        PushButton() {
            super("img/push_button.gif", "img/push_buttonr.gif", "img/push_buttonp.gif");
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent event) {
            _stackList.push(_pushTextField.getText());
        }
    }

    class PopButton extends ImageButton implements ActionListener {

        PopButton() {
            super("img/pop_button.gif", "img/pop_buttonr.gif", "img/pop_buttonp.gif");
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent event) {
            _stackList.pop();
        }
    }

    class AddButton extends ImageButton implements ActionListener {

        AddButton() {
            super("img/add_button.gif", "img/add_buttonr.gif", "img/add_buttonp.gif");
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent event) {
            _stackList.add();
        }
    }

    class SubtractButton extends ImageButton implements ActionListener {

        SubtractButton() {
            super("img/subtract_button.gif", "img/subtract_buttonr.gif", "img/subtract_buttonp.gif");
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent event) {
            _stackList.subtract();
        }
    }

    class MultiplyButton extends ImageButton implements ActionListener {

        MultiplyButton() {
            super("img/multiply_button.gif", "img/multiply_buttonr.gif", "img/multiply_buttonp.gif");
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent event) {
            _stackList.multiply();
        }
    }

    class DivideButton extends ImageButton implements ActionListener {

        DivideButton() {
            super("img/divide_button.gif", "img/divide_buttonr.gif", "img/divide_buttonp.gif");
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent event) {
            _stackList.divide();
        }
    }

    class ImageButton extends JButton {

        ImageButton(String button_image, String buttonr_image, String buttonp_image) {
            super(new ImageIcon(RunnableStackMachine.class.getResource(button_image)));
            setRolloverIcon(new ImageIcon(RunnableStackMachine.class.getResource(buttonr_image)));
            setPressedIcon(new ImageIcon(RunnableStackMachine.class.getResource(buttonp_image)));
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setMargin(new Insets(0, 0, 0, 0));
        }
    }

    public JLabel _stackSizeLabel;

    public StackList _stackList;

    public PushTextField _pushTextField;

    public JScrollPane _scrollPane;

    //////////
    public static void main(String[] args) {
        RunnableStackMachine frame = new RunnableStackMachine();
        frame.setLocation(300, 300);
        frame.setVisible(true);
    }
}
