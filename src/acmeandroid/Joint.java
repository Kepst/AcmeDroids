package acmeandroid;

/**
 *
 * @author welli
 */
public class Joint {

    protected final int voltageRequired;
    private final int maxFlexionAngle;

    private int currentFlexionAngle;

    private double motionSpeed = 0;

    public Joint(int voltageRequired, int maxFlexionAngle, int currentFlexionAngle) {
        this.voltageRequired = voltageRequired;
        this.maxFlexionAngle = maxFlexionAngle;
        this.currentFlexionAngle = currentFlexionAngle;
    }

    public void setSpeed(double speed) {
        this.motionSpeed = speed;
    }

    public double update(double time) {
        this.currentFlexionAngle += this.motionSpeed * time;
        if (this.currentFlexionAngle > maxFlexionAngle) {
            this.currentFlexionAngle = this.maxFlexionAngle;
        }
        if (this.currentFlexionAngle < 0) {
            this.currentFlexionAngle = 0;
        }
        double battery = time * this.voltageRequired * this.motionSpeed / 15;
        return battery;
    }
}
