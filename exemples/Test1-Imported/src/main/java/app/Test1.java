package app;

import lab_robotique.BaseComputePeriodic;

public class Test1 extends BaseComputePeriodic
{
    private static final double KP = 0.05;
    private double _lastOutput;

    public Test1()
    {
        // ...
    }

    @Override
    public double compute(double value)
    {
        double error = value - _lastOutput;
        double outputValue = _lastOutput + error * KP;

        _lastOutput = outputValue;
        return outputValue;
    }

}
