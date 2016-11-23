package model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Elmahdi Aitlmahfoud on 23/11/2016.
 */
public class Task
{

    private Date lastExecutionDate;
    private Date nextExecutionDate;
    private int executeEvery;


    public Date getLastExecutionDate()
    {
        return lastExecutionDate;
    }

    public void setLastExecutionDate(Date lastExecutionDate)
    {
        this.lastExecutionDate = lastExecutionDate;
    }

    public Date getNextExecutionDate()
    {
        return nextExecutionDate;
    }

    public void setNextExecutionDate(Date nextExecutionDate)
    {
        this.nextExecutionDate = nextExecutionDate;
    }

    public int getExecuteEvery()
    {
        return executeEvery;
    }

    public void setExecuteEvery(int executeEvery)
    {
        this.executeEvery = executeEvery;
    }


    public void recalculateDates()
    {
        setLastExecutionDate(new Date());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(getLastExecutionDate().getTime());
        cal.add(Calendar.SECOND,  getExecuteEvery());
        Date d = new Date(cal.getTime().getTime());
        setNextExecutionDate(d);
    }
}
