/**
 * @author crkimberley on 15/11/2016.
 */
public class ResponsiveUIApp {

    private SerialExecutorWithPendingTime exec = new SerialExecutorWithPendingTime();
    private static int totalTasksStarted = 0;

    public int getMaxWait() {
        return exec.getMaxPendingTime();
    }

    public void execute(Runnable task) {
        System.out.println("\t• total tasks started " + ++totalTasksStarted + " • max wait = " + getMaxWait() + " ms");
        exec.execute(task);
    }
}