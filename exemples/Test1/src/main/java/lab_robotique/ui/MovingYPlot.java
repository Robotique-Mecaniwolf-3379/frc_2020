package lab_robotique.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JComponent;

public class MovingYPlot extends JComponent
{
    private static final long serialVersionUID = 1L;
    private int[] _ySeries2 = new int[0];
    private int[] _ySeries1 = new int[0];
    private int _dataLength = 0;

    private int _head;
    private int _tail;
    private BasicStroke _fatStroke;
    private BasicStroke _thinStroke;

    public MovingYPlot()
    {
        setPreferredSize(new Dimension(640, 240));
        createDataBuffers(640);

        addComponentListener(new MyComponentListener());
    }

    public void createDataBuffers(int length)
    {
        assert (length > 0);

        _dataLength = length;
        _ySeries1 = new int[length];
        _ySeries2 = new int[length];

        _head = _dataLength - 1;
        _tail = 0;
    }

    public void pushData(int y1, int y2)
    {
        _ySeries1[_head] = y1;
        _ySeries2[_head] = y2;

        // Move up the head index and tail index
        _head = inc(_head);
        _tail = inc(_tail);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        Dimension size = getSize();

        // Clear background
        g2d.setBackground(Color.BLACK);
        g2d.clearRect(0, 0, size.width, size.height);

        g2d.translate(0, size.height >> 1);
        g2d.scale(1, -1);

        // Draw series 1
        g2d.setColor(Color.WHITE);
        g2d.setStroke(getFatStroke());
        drawSeries(g2d, _ySeries1);

        // Draw series 2
        g2d.setColor(Color.GREEN);
        g2d.setStroke(getThinStroke());
        drawSeries(g2d, _ySeries2);
    }

    private Stroke getThinStroke()
    {
        if (_thinStroke == null)
        {
            _thinStroke = new BasicStroke(1);
        }

        return _thinStroke;
    }

    private Stroke getFatStroke()
    {
        if (_fatStroke == null)
        {
            _fatStroke = new BasicStroke(3);
        }

        return _fatStroke;
    }

    private void drawSeries(Graphics2D g2d, int[] ys)
    {
        for (int i = 0; i < _dataLength - 2; i++)
        {
            int indexThis = (_tail + i) % _dataLength;
            int indexNext = inc(indexThis);

            int y1 = ys[indexThis];
            int y2 = ys[indexNext];
            g2d.drawLine(i, y1, i + 1, y2);
        }
    }

    private int inc(int index)
    {
        return (index + 1) % _dataLength;
    }

    /////////////////////////
    private class MyComponentListener implements ComponentListener
    {
        public MyComponentListener()
        {
            // ...
        }

        @Override
        public void componentHidden(ComponentEvent arg0)
        {
            // ...
        }

        @Override
        public void componentMoved(ComponentEvent arg0)
        {
            // ...
        }

        @Override
        public void componentResized(ComponentEvent evt)
        {
            createDataBuffers(getSize().width);
        }

        @Override
        public void componentShown(ComponentEvent arg0)
        {
            // ...
        }

    }
}
