import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author crkimberley on 25/11/2016.
 */
public class ResponsiveUIUser implements Runnable {

    private static int userNumber = 0, TASKS_PER_USER = 50, LONG_WAIT = 5000;
    private static ResponsiveUIApp app = new ResponsiveUIApp();
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private int userID, waitThreshold;

    public ResponsiveUIUser(int waitThreshold) {
        this.waitThreshold = waitThreshold;
        userID = ++userNumber;
    }

    public void run() {
        Runnable task;
        for (int taskID = 0; taskID < TASKS_PER_USER; taskID++) {
            while (app.getMaxWait() > waitThreshold) {
                System.out.println("User:" + userID + " says the is site down! They'll come back later");
                try {
                    TimeUnit.MILLISECONDS.sleep(LONG_WAIT);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
