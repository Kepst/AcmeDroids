package acmeandroid;

/**
 *
 * @author welli
 */
public class RotatingJoint extends Joint {

    private final int maxRotationAngle;

    private double currentRotationAngle;

    private double rotationSpeed = 0;

    public RotatingJoint(int voltageRequired, int maxFlexionAngle, int currentFlexionAngle, int maxRotationAngle,
            int currentRotationAngle) {
        super(voltageRequired, maxFlexionAngle, currentFlexionAngle);
        this.maxRotationAngle = maxRotationAngle;
        this.currentRotationAngle = currentRotationAngle;
    }

    public void setRotationSpeed(double speed) {
        this.rotationSpeed = speed;
    }

    public double update(double time) {
        double battery = super.update(time);
        this.currentRotationAngle += this.rotationSpeed * time;
        if (this.currentRotationAngle > maxRotationAngle) {
            this.currentRotationAngle = this.maxRotationAngle;
        }
        if (this.currentRotationAngle < 0) {
            this.currentRotationAngle = 0;
        }
        battery += time * this.voltageRequired * this.rotationSpeed / 15;
        return battery;
    }
}
