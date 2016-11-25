import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author crkimberley on 25/11/2016.
 */
public class ResponsiveUIUser implements Runnable {

    private static int userNumber = 0, TASKS_PER_USER = 10, LONG_WAIT = 5000;
    private final static ResponsiveUIApp app = new ResponsiveUIApp();
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    private int userID, waitThreshold;

    ResponsiveUIUser(int waitThreshold) {
        this.waitThreshold = waitThreshold;
        userID = ++userNumber;
        executorService.execute(this);
    }

    public void run() {
        for (int taskID = 0; taskID < TASKS_PER_USER; taskID++) {
            while (app.getMaxWait() > waitThreshold) {
                System.out.println("User:" + userID + " says the is site down! They'll come back later");
                System.out.println("Max waiting time = " + app.getMaxWait()
                        + " ms, but I'm only prepared to wait " + waitThreshold + " ms");
                try {
                    TimeUnit.MILLISECONDS.sleep(LONG_WAIT);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            int sleep = (int) (Math.random() * 90) + 10;
            synchronized(app) {
                app.execute(new TimedTask(sleep));
            }
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    static ExecutorService getExecutorService() {
        return executorService;
    }
}
