import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author crkimberley on 25/11/2016.
 */
public class ResponsiveUIUser implements Runnable {

    private static int userNumber = 0, NUMBER_OF_USERS = 10, TASKS_PER_USER = 10, LONG_WAIT = 5000;
    private final static ResponsiveUIApp app = new ResponsiveUIApp();
    private final static ExecutorService executorService = Executors.newFixedThreadPool(25);
    private int userID, waitThreshold;

    ResponsiveUIUser() {}

    ResponsiveUIUser(int waitThreshold) {
        this.waitThreshold = waitThreshold;
        userID = userNumber++;
        executorService.execute(this);
    }

    public static void main(String[] args) {
        new ResponsiveUIUser().launch();
    }

    private void launch() {
        for (int i=0; i<NUMBER_OF_USERS; i++) {
            // new user has random waitThreshold between 1000 and 2000 ms
            new ResponsiveUIUser(((int) (Math.random() * 1000)) + 1000);
        }
        executorService.shutdown();
    }

    public void run() {
        for (int taskID = 0; taskID < TASKS_PER_USER; taskID++) {
            while (app.getMaxWait() > waitThreshold) {
                System.out.println("\nUSER " + userID + " says...site down! Max wait " + app.getMaxWait()
                        + " ms. I'll only wait " + waitThreshold + " ms. Retry in " + LONG_WAIT + "ms\n");
                try {
                    TimeUnit.MILLISECONDS.sleep(LONG_WAIT);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            // random task length between 10 and 100 ms
            int sleep = (int) (Math.random() * 90) + 10;
            synchronized(this) {
                System.out.print("\tUSER " + userID + " • TASK " + taskID);
                app.execute(new TimedTask(sleep));
            }
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}