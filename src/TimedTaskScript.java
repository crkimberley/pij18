/**
 * @author crkimberley on 15/11/2016.
 */
public class TimedTaskScript {

    public static void main(String[] args) {
        new TimedTaskScript().launch();
    }

    private void launch() {
        SerialExecutorWithPendingTime e = new SerialExecutorWithPendingTime();
        for (int i = 1; i < 1200; i=i+100) {
            e.execute(new TimedTask(i));
            System.out.println("MaxPendingTime = " + e.getMaxPendingTime());
        }
        while (e.tasks.size() > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e1) {
                // do nothing
            }
            System.out.println("MaxPendingTime = " + e.getMaxPendingTime());
        }
    }
}