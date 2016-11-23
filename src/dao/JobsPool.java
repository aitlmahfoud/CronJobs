package dao;

import model.Task;
import service.FilesService;
import service.impl.FilesServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Elmahdi Aitlmahfoud on 23/11/2016.
 */
public class JobsPool

{
    public static Map<String, Task> tasks = new HashMap<>();
    static FilesService filesService = new FilesServiceImpl();

    static{
        try
        {
            initTasks();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static void initTasks() throws FileNotFoundException
    {
        for (final File fileEntry : filesService.getClassImplementingJobInterface()) {
             createTask(fileEntry.getName().toString());
        }
    }

    public static void createTask(String taskName){
        Task task = new Task();
        String fileName = "jobs.impl."+taskName.replace(".java","");
        task.setExecuteEvery(filesService.getValOf(fileName));
        task.recalculateDates();
        tasks.put(fileName,task);
    }
}
