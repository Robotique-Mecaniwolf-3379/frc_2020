package lab_robotique;

abstract public class BaseComputePeriodic
{
    private long _timeMillis;

    abstract public double compute(double value);

    public BaseComputePeriodic()
    {
        // ...
    }

    public void setTimeMillis(long timeMillis)
    {
        _timeMillis = timeMillis;
    }

    public long getTimeMillis()
    {
        return _timeMillis;
    }
}
