package app;

import lab_robotique.BaseComputePeriodic;

public class Test2 extends BaseComputePeriodic
{
    private static final double MAX_DIFF = 0.025;
    private double _lastOutput;

    public Test2()
    {
        // ...
    }

    @Override
    public double compute(double inputValue)
    {
        double diff = inputValue - _lastOutput;

        if(Math.abs(diff) > MAX_DIFF) {
            diff = Math.signum(diff) * MAX_DIFF;
        }

        double outputValue = _lastOutput + diff;

        _lastOutput = outputValue;
        return outputValue;
    }

}
