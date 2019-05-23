package acmeandroid;

/**
 *
 * @author welli
 */
public class AcmeAndroid {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Android android = new Android();
            }
        };
        thread.start();  
    }

}
