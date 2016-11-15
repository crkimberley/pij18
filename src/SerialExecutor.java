import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

// Code adapted from Java Executor API

class SerialExecutor implements Executor {
    final Queue<Runnable> tasks = new ArrayDeque<Runnable>();
    final Executor executor = new Executor() {
        public void execute(Runnable r) {
            new Thread(r).start();
        }
    };
    Runnable active;

    public synchronized void execute(final Runnable r) {
        tasks.offer(new Runnable() {
            public void run() {
                try {
                    r.run();
                } finally {
                    scheduleNext();
                }
            }
        });
        if (active == null) {
            scheduleNext();
        }
    }

    protected synchronized void scheduleNext() {
        if ((active = tasks.poll()) != null) {
            executor.execute(active);
        }
    }
}