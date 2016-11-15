import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author crkimberley on 09/11/2016.
 */
public class TextLoopUsingSerialExecutor implements Runnable {

    public final static int COUNT = 10;
    private final static int SIZE = 100;

    private final String name;

    public TextLoopUsingSerialExecutor(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < COUNT; i++) {
            System.out.println("Loop:" + name + ", iteration:" + i + ".");
        }
    }

    public static void main(String args[]) {
        if (args.length < 1 || (!args[0].equals("0") && !args[0].equals("1"))) {
            System.out.println("USAGE:  java TextLoop <mode>");
            System.out.println(" mode 0: without threads");
            System.out.println(" mode 1: with threads");
        } else if (args[0].equals("0")) {
            System.out.println("Mode 0 --- without threads");
            for (int i = 0; i < SIZE; i++) {
                Runnable r = new TextLoopUsingSerialExecutor("Thread " + i + ".");
                r.run();
            }
        } else {
            System.out.println("Mode 1 --- with threads");
            SerialExecutor e = new SerialExecutor();
            for (int i = 0; i < SIZE; i++) {
                Runnable r = new TextLoopUsingSerialExecutor("Thread " + i + ".");
                e.execute(r);
            }
        }
    }
}