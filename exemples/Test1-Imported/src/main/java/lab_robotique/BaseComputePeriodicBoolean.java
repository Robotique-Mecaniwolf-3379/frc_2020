package lab_robotique;

abstract public class BaseComputePeriodicBoolean
{
    private long _timeMillis;

    abstract public boolean compute(boolean value);

    public BaseComputePeriodicBoolean()
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
