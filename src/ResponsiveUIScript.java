/**
 * Created by chris on 25/11/2016.
 */
public class ResponsiveUIScript {
    private int NUMBER_OF_USERS = 25;

    public static void main(String[] args) {
        new ResponsiveUIScript().launch();
    }

    private void launch() {
        for (int i=0; i<10; i++) {
            new ResponsiveUIUser((1000 * (int) (Math.random() * 10)) + 10000);
        }
        ResponsiveUIUser.getExecutorService().shutdown();
    }
}