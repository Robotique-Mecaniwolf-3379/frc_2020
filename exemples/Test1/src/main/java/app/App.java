package app;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;

import lab_robotique.BaseComputePeriodic;
import lab_robotique.ui.MovingYPlot;

public class App {
    private static final int MAX_SLIDER_RANGE = 128;
    private static final Insets DEFAULT_INSETS = new Insets(5, 5, 5, 5);
    private static final long PERIOD_MILLIS = 20;
    
    private JFrame _frame;
    private MovingYPlot _plot;
    private JSlider _slider;

    public static void main(String[] args) {
        
        try
        {
            App appl = new App();
            appl.doStuff();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    
    }
    
    private void doStuff() throws Exception
    {
        // Create the class under test
        BaseComputePeriodic test = new Test1();

        // Create the frame (window) with all the GUI components
        createFrame();

        // Loop until the window is closed
        long timeMillis = 0;
        while (true)
        {
            // Pass the current time to the test class
            test.setTimeMillis(timeMillis);

            // Let the test class compute the output value
            int rawInputValue = _slider.getValue();
            double nomalizedInputValue = (double) rawInputValue / MAX_SLIDER_RANGE;
            double nomalizedOutputValue = test.compute(nomalizedInputValue);
            int rawOutputValue = (int) (nomalizedOutputValue * MAX_SLIDER_RANGE);

            // Push the input and output values to the moving Y plot
            _plot.pushData(rawInputValue, rawOutputValue);
            _frame.repaint();

            // Sleep a bit
            Thread.sleep(PERIOD_MILLIS);
            timeMillis += PERIOD_MILLIS;
        }
    }

    private void createFrame()
    {
        _frame = new JFrame("Test");
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        _frame.getContentPane().add(createComponents());

        _frame.pack();
        _frame.setLocationRelativeTo(null);
        _frame.setVisible(true);
    }

    private JPanel createComponents()
    {
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
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        comp = _slider = new JSlider(-MAX_SLIDER_RANGE, MAX_SLIDER_RANGE);
        _slider.setOrientation(JSlider.VERTICAL);
        _slider.addMouseListener(new MyMouseListener());
        panel.add(comp, gbc);

        return panel;
    }

    ///////////////
    private class MyMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent arg0) {

        }

        @Override
        public void mouseEntered(MouseEvent arg0) {

        }

        @Override
        public void mouseExited(MouseEvent arg0) {

        }

        @Override
        public void mousePressed(MouseEvent arg0) {

        }

        @Override
        public void mouseReleased(MouseEvent arg0) {
            _slider.setValue(0);
		}

    }

}