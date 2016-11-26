/**
 * @author crkimberley on 15/11/2016.
 */
// Max time reduced to 100ms
public class TimedTask implements Runnable {

    private int sleep;

    public TimedTask(int sleep) {
        this.sleep = sleep <= 100 ? sleep : 100;
    }

    public void run() {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException ex) {
            // do nothing
        }
    }
}
