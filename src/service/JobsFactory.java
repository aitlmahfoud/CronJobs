package service;

import jobs.Job;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Elmahdi Aitlmahfoud on 23/11/2016.
 */
public interface JobsFactory
{
    public static Map<String, Job> jobs = new HashMap<>();

    public static Job create(String key) throws ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        Job job = jobs.get(key);
        if(job == null){
            job = (Job) Class.forName(key).newInstance();
            jobs.put(key,job);
        }
        return  job;
    }
}
