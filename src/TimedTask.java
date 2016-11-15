/**
 * @author crkimberley on 15/11/2016.
 */
public class TimedTask implements Runnable {

    private int sleep;

    public TimedTask(int sleep) {
        this.sleep = sleep <= 1000 ? sleep : 1000;
    }

    public void run() {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException ex) {
            // do nothing
        }
    }
}
