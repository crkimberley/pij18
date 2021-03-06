import java.util.concurrent.Executor;

/**
 * @author crkimberley on 15/11/2016.
 */
// Max time reduced to 100ms
public class SerialExecutorWithPendingTime extends SerialExecutor implements Executor {

    public SerialExecutorWithPendingTime() {
        super();
    }

    public int getMaxPendingTime() {
        return tasks.size() * 100;
    }
}
