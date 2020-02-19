package app;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import lab_robotique.BaseComputePeriodicBoolean;
import lab_robotique.ui.MovingYPlot;

public class AppBoolean {
    private static final Insets DEFAULT_INSETS = new Insets(5, 5, 5, 5);
    private static final long PERIOD_MILLIS = 20;

    private JFrame _frame;
    private MovingYPlot _plot;
    private AbstractButton _button;

    public static void main(String[] args) {

        try {
            AppBoolean appl = new AppBoolean();
            appl.doStuff();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void doStuff() throws Exception {
        // Create the class under test
        BaseComputePeriodicBoolean test = new TestBouton1();

        // Create the frame (window) with all the GUI components
        createFrame();

        // Loop until the window is closed
        long timeMillis = 0;
        while (true) {
            // Pass the current time to the test class
            test.setTimeMillis(timeMillis);

            // Let the test class compute the output value
            boolean rawInputValue = _button.isSelected();
            boolean rawOutputValue = test.compute(rawInputValue);
            int nomalizedInputValue = rawInputValue ? 100 : 20;
            int nomalizedOutputValue = rawOutputValue ? -20 : -100;

            // Push the input and output values to the moving Y plot
            _plot.pushData(nomalizedInputValue, nomalizedOutputValue);
            _frame.repaint();

            // Sleep a bit
            Thread.sleep(PERIOD_MILLIS);
            timeMillis += PERIOD_MILLIS;
        }
    }

    private void createFrame() {
        _frame = new JFrame("Test bouton");
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        _frame.getContentPane().add(createComponents());

        _frame.pack();
        _frame.setLocationRelativeTo(null);
        _frame.setVisible(true);
    }

    private JPanel createComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc;
        JComponent comp;

        panel.setBorder(BorderFactory.createEtchedBorder());

        //////////////////////////////////////
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = DEFAULT_INSETS;
        comp = _plot = new MovingYPlot();
        panel.add(comp, gbc);

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        comp = _button = new JToggleButton("Bouton");
        panel.add(comp, gbc);

        return panel;
    }
}