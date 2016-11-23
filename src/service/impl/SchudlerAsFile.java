package service.impl;

import dao.JobsPool;
import jobs.Job;
import service.FilesService;
import service.JobsFactory;
import service.Schudler;
import service.SystemUtils;

import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Elmahdi Aitlmahfoud on 23/11/2016.
 */
public class SchudlerAsFile implements Schudler
{
    private final int DEFAULT_EXECUTION_TIME = 30;
    FilesService filesService = null ;
    int executionTime = 0;
    int NB_THREAD_TO_CREATE = 1;
    Set<String> jobsAsFile;





    public void run() throws InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        init();
        loadJobsInFileFormat();
        while (true)
        {
            SystemUtils.sleepUntilMillSecond(executionTime * 1000);
            System.out.println(" ----- Father -----");
            executeJobs();
            recalculateExecutionTime();
        }
    }

    private void recalculateExecutionTime()
    {
        if(String.valueOf(filesService.getStringValOf("intelligMode")).toLowerCase().equals("true")){
            long temp = DEFAULT_EXECUTION_TIME;
            Date now = new Date();
            for (String key : JobsPool.tasks.keySet()) {
                long difference = (JobsPool.tasks.get(key).getNextExecutionDate().getTime() - now.getTime())/1000;
                if( difference < temp && difference > 0){
                    temp = difference;
                }
            }
            executionTime = Integer.valueOf((int) temp);
        }

    }


    private boolean init(){
        try
        {
            filesService = new FilesServiceImpl() ;
            executionTime = filesService.getExecutionTime();

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        if(executionTime == 0){
            executionTime = DEFAULT_EXECUTION_TIME;
        }
        return true;
    }


    private void executeJobs() throws InterruptedException, ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        final ExecutorService executor = Executors.newFixedThreadPool(NB_THREAD_TO_CREATE);
        Date now = new Date();
        for (String clazz : JobsPool.tasks.keySet()) {

            if(JobsPool.tasks.get(clazz).getNextExecutionDate().equals(now) || JobsPool.tasks.get(clazz).getNextExecutionDate().compareTo(now)<0){
                Job job = JobsFactory.create(clazz);
                executor.execute(() -> {
                   job.run();
                });
                JobsPool.tasks.get(clazz).recalculateDates();
            }
        }

        executor.shutdown();
    }


    private void loadJobsInFileFormat()
    {
        File folder = new File("src\\jobs\\impl");
        jobsAsFile = new HashSet<>();
        for (final File fileEntry : folder.listFiles()) {
            if (!fileEntry.isDirectory()) {
                jobsAsFile.add("jobs.impl."+fileEntry.getName().toString().replaceAll("\\.java",""));
            }
        }
        NB_THREAD_TO_CREATE = jobsAsFile.size();
    }

}


