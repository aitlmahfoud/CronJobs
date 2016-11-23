import service.Schudler;
import service.impl.SchudlerAsFile;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        Schudler scudler = new SchudlerAsFile();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Runnable task = () -> {
            try
            {
                scudler.run();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        };
        executor.submit(task);
        executor.shutdown();
    }

}
