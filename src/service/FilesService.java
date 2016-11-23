package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by Elmahdi Aitlmahfoud on 23/11/2016.
 */
public interface FilesService
{
    public int getExecutionTime();

    public int getValOf(String key);

    public String getStringValOf(String key);

    public List<File> getClassImplementingJobInterface() throws FileNotFoundException;

    public boolean isImplementingJobInterface(File fileEntry) throws FileNotFoundException;
}
