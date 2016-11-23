package service.impl;

import service.FilesService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by Elmahdi Aitlmahfoud on 23/11/2016.
 */
public class FilesServiceImpl implements FilesService
{
    Properties   prop  = null;
    InputStream  input = null;
    static String BASE_DIR = null;


    public int getExecutionTime(){
        if(init()){
            return Integer.valueOf(prop.getProperty("executionTime"));
        }
        return 0;
    }

    public int getValOf(String key){
        if(init()){
            return Integer.valueOf(prop.getProperty(key));
        }
        return 0;
    }

    public String getStringValOf(String key){
        if(init()){
            return prop.getProperty(key);
        }
        return "";
    }

    public List<File> getClassImplementingJobInterface() throws FileNotFoundException
    {
        File folder = new File(BASE_DIR);
        List<File> files = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (!fileEntry.isDirectory() && isImplementingJobInterface(fileEntry)) {
                files.add(fileEntry);
            }
        }
        return files;
    }

    public boolean isImplementingJobInterface(File fileEntry) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(fileEntry);
        String line = "";
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.contains(fileEntry.getName().replace(".java",""))) break;
        }

        if(line.replaceAll(" ","").contains("implementsJob")) return true;
        return false;
    }


    private boolean init(){
        try {
            prop   = new Properties();
            input  = new FileInputStream("properties/schudler.properties");
            BASE_DIR = "src\\jobs\\impl";
            prop.load(input);
        }
        catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
