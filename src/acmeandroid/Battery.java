package acmeandroid;

/**
 *
 * @author welli
 */
public class Battery {
    
    private static final double MAX_LEVEL = 8;
    private static final double RECHARGE_RATE = (8 / 3);
    
    private double currentLevel;
    
    public Battery () {
        this.currentLevel = MAX_LEVEL;
    }
}
