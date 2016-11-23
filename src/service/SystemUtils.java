package service;

/**
 * Created by Elmahdi Aitlmahfoud on 23/11/2016.
 */
public interface SystemUtils
{
    /**
     * I want to know how the "sleep" call system works,  so i did this few lines of code to simulate
     * the behavior of sleep.
     *
     * in fact java externs the sleep function to the kernel, the kernel put the
     * Thread in the called "sleeped queue", so when the kernel knows that the Thread must rerun
     * it move it to "Runnuble queue"
     *
     * in the link bellow you'll find more detaills on sleep call system
     * http://stackoverflow.com/questions/175882/whats-the-algorithm-behind-sleep
     *
     * @param milliseconds
     */
    public static void sleepUntilMillSecond(long milliseconds){
        long temp = System.currentTimeMillis() + milliseconds;
        while (System.currentTimeMillis() < temp){
        }
    }
}
