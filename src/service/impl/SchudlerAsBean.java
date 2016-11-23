package service.impl;

import jobs.Job;
import net.sf.extcos.ComponentQuery;
import net.sf.extcos.ComponentScanner;
import service.FilesService;
import service.Schudler;
import service.SystemUtils;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Elmahdi Aitlmahfoud on 23/11/2016.
 */
public class SchudlerAsBean implements Schudler
{
    private final int DEFAULT_EXECUTION_TIME = 30;
    FilesService filesService = null ;
    int executionTime = 0;
    Set<Class<? extends Job>> jobs;



    public void run() throws InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        init();
        loadJobs();
        while (true)
        {
            executeJobs();
            SystemUtils.sleepUntilMillSecond(executionTime * 1000);
        }
    }



    private void executeJobs() throws InterruptedException, ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        ExecutorService executor = Executors.newFixedThreadPool(Integer.valueOf(jobs.size()).intValue());
        Set<Callable<String>> callables = new HashSet<>();

        for (Class clazz : jobs) {
            Job job = (Job) Class.forName(clazz.toString()).newInstance();
            callables.add(() -> {job.run();return null;});
        }
        executor.invokeAll(callables);
    }



    private void loadJobs()
    {
        jobs = new HashSet<>();
        ComponentScanner scanner = new ComponentScanner();
        scanner.getClasses(new ComponentQuery() {
            @Override
            protected void query() {
                select().from("jobs.impl").andStore(thoseImplementing(Job.class).into(jobs)).returning(none());
            }
        });
    }





    private boolean init(){
        try
        {
            filesService = new FilesServiceImpl();
            executionTime = filesService.getExecutionTime();
        }catch (RuntimeException e){
            e.printStackTrace();
            return false;
        }

        if(executionTime == 0){
            executionTime = DEFAULT_EXECUTION_TIME;
        }
        return true;
    }


}


