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
        Android android = new Android();
        Integer time = 0; //miliseconds
        System.out.println("-----Getting up-----");
        time = android.standUp(time);
        System.out.println("-----First step-----");
        time = android.firstStep(time);
        System.out.println("-----Walking-----");
        time = android.stepLeft(time);
        time = android.stepRight(time);
        
        System.out.println();
        System.out.println(android.showPositions());

    }

}
