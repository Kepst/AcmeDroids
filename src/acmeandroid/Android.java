package acmeandroid;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author welli
 */
public class Android {

    private final Joint ankle1;
    private final Joint ankle2;
    private final Joint knee1;
    private final Joint knee2;
    private final Joint hip1;
    private final Joint hip2;
    private final Joint waist;
    private final Joint wrist1;
    private final Joint wrist2;
    private final Joint elbow1;
    private final Joint elbow2;
    private final Joint shoulder1;
    private final Joint shoulder2;
    private final Joint neck;
    private final Joint head;
    private double batteryLevel;

    public Android() {
        this.ankle1 = new Joint(3, 30, 0);
        this.ankle2 = new Joint(3, 30, 0);
        this.knee1 = new Joint(3, 90, 90);
        this.knee2 = new Joint(3, 90, 90);
        this.hip1 = new Joint(4, 90, 90);
        this.hip2 = new Joint(4, 90, 90);
        this.waist = new RotatingJoint(4, 90, 0, 30, 0);
        this.wrist1 = new Joint(2, 180, 90);
        this.wrist2 = new Joint(2, 180, 90);
        this.elbow1 = new Joint(3, 140, 0);
        this.elbow2 = new Joint(3, 140, 0);
        this.shoulder1 = new RotatingJoint(2, 180, 0, 360, 0);
        this.shoulder2 = new RotatingJoint(2, 180, 0, 360, 0);
        this.neck = new Joint(3, 30, 0);
        this.head = new RotatingJoint(3, 180, 0, 180, 0);
        this.batteryLevel = 8.0;
    }

    public void updateAngles(double time) {

        this.batteryLevel -= this.ankle1.update(time);
        this.batteryLevel -= this.ankle2.update(time);
        this.batteryLevel -= this.knee1.update(time);
        this.batteryLevel -= this.knee2.update(time);
        this.batteryLevel -= this.hip1.update(time);
        this.batteryLevel -= this.hip2.update(time);
        this.batteryLevel -= this.waist.update(time);
        this.batteryLevel -= this.wrist1.update(time);
        this.batteryLevel -= this.wrist2.update(time);
        this.batteryLevel -= this.elbow1.update(time);
        this.batteryLevel -= this.elbow2.update(time);
        this.batteryLevel -= this.shoulder1.update(time);
        this.batteryLevel -= this.shoulder2.update(time);
        this.batteryLevel -= this.neck.update(time);
        this.batteryLevel -= this.head.update(time);

        this.batteryLevel += 8 / 3;

        if (this.batteryLevel > 8) {
            this.batteryLevel = 8;
        } else if (this.batteryLevel < 1) {
            System.out.println("Android broke");
        }
    }

    public void update(double movementTime, double time) {
        while(time < movementTime) {
            this.updateAngles(0.001);
            if((time % 1) == 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Android.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("1 second");
            }
            time += 0.001;
        }
    }
    
    public void standUp(double time) {
        this.waist.setSpeed(15);
        this.update(2, time);
        this.waist.setSpeed(0);
        System.out.println(this.waist.getCurrentFlexionAngle());
        this.update(1, time);
        this.knee1.setSpeed(-7.5);
        this.knee2.setSpeed(-7.5);

        this.update(6, time);
        System.out.println(this.knee1.getCurrentFlexionAngle());
        this.knee1.setSpeed(0);
        this.knee2.setSpeed(0);
        this.hip1.setSpeed(-5.3);
        this.hip2.setSpeed(-5.3);
        this.update(8.5, time);
        System.out.println(this.hip1.getCurrentFlexionAngle());
        this.hip1.setSpeed(0);
        this.hip2.setSpeed(0);
        this.knee1.setSpeed(-7.5);
        this.knee2.setSpeed(-7.5);

        this.update(6, time);
        System.out.println(this.knee1.getCurrentFlexionAngle());
        this.knee1.setSpeed(0);
        this.knee2.setSpeed(0);
        this.hip1.setSpeed(-5.3);
        this.hip2.setSpeed(-5.3);
        this.update(8.5, time);
        this.hip1.setSpeed(0);
        this.hip2.setSpeed(0);
        System.out.println(this.hip1.getCurrentFlexionAngle());

        this.update(1, time);

        this.waist.setSpeed(-15);
        this.update(2, time);
        this.waist.setSpeed(0);
        System.out.println(this.waist.getCurrentFlexionAngle());
    }
}
