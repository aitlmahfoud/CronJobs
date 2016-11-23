package test;

import org.junit.Assert;
import org.junit.Test;
import service.SystemUtils;

/**
 * Created by user on 23/11/2016.
 */

public class TestSleep
{


    @Test
    public void doTest(){
        long temp1 = System.currentTimeMillis();
        SystemUtils.sleepUntilMillSecond(5000);
        long temp2 = System.currentTimeMillis();
        long result = temp2-temp1;
        Assert.assertEquals(5000,result);
    }


}
