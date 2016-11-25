    /**
 * @author crkimberley on 15/11/2016.
 */
public class ResponsiveUIApp {

    private SerialExecutorWithPendingTime exec = new SerialExecutorWithPendingTime();

    public int getMaxWait() {
        return exec.getMaxPendingTime();
    }

    public void execute(Runnable task) {
        exec.execute(task);
    }
}