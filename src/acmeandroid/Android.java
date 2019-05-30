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
    private final RotatingJoint waist;
    private final Joint wrist1;
    private final Joint wrist2;
    private final Joint elbow1;
    private final Joint elbow2;
    private final RotatingJoint shoulder1;
    private final RotatingJoint shoulder2;
    private final Joint neck;
    private final RotatingJoint head;
    private double batteryLevel;
    private double previousSecondAngles[];

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
        this.previousSecondAngles = new double[] { this.ankle1.getCurrentFlexionAngle(),
                this.ankle2.getCurrentFlexionAngle(), this.knee1.getCurrentFlexionAngle(),
                this.knee2.getCurrentFlexionAngle(), this.hip1.getCurrentFlexionAngle(),
                this.hip2.getCurrentFlexionAngle(), this.waist.getCurrentFlexionAngle(),
                this.waist.getCurrentRotationAngle(), this.wrist1.getCurrentFlexionAngle(),
                this.wrist2.getCurrentFlexionAngle(), this.elbow1.getCurrentFlexionAngle(),
                this.elbow2.getCurrentFlexionAngle(), this.shoulder1.getCurrentFlexionAngle(),
                this.shoulder1.getCurrentRotationAngle(), this.shoulder2.getCurrentFlexionAngle(),
                this.shoulder2.getCurrentRotationAngle(), this.neck.getCurrentFlexionAngle(),
                this.head.getCurrentFlexionAngle(), this.head.getCurrentRotationAngle() };
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

        this.batteryLevel += time * 8 / 3 / 1000;

        if (this.batteryLevel > 8) {
            this.batteryLevel = 8;
        } else if (this.batteryLevel < 1) {
            System.out.println("Android broke");
        }
    }

    public int update(int movementTime, int time) {

        int endTime = time + movementTime;

        while (time < endTime) {
            this.updateAngles(1);
            time += 1;
            if (time % 1000 == 0) {
                try {
                    Thread.sleep(10); // CHANGE BACK TO 1000

                } catch (InterruptedException ex) {
                    Logger.getLogger(Android.class.getName()).log(Level.SEVERE, null, ex);
                }
                String batteryLvl = String.format("%.2f", this.batteryLevel);
                System.out.println("\nTime: " + (time / 1000) + " s\nBattery level: " + batteryLvl);

                this.printChanges(0, this.ankle1.getCurrentFlexionAngle(), "Ankle 1 (flex)");
                this.printChanges(1, this.ankle2.getCurrentFlexionAngle(), "Ankle 2 (flex)");
                this.printChanges(2, this.knee1.getCurrentFlexionAngle(), "Knee 1 (flex)");
                this.printChanges(3, this.knee2.getCurrentFlexionAngle(), "Knee 1 (flex)");
                this.printChanges(4, this.hip1.getCurrentFlexionAngle(), "Hip 1 (flex)");
                this.printChanges(5, this.hip2.getCurrentFlexionAngle(), "Hip 1 (flex)");
                this.printChanges(6, this.waist.getCurrentFlexionAngle(), "Waist (flex)");
                this.printChanges(7, this.waist.getCurrentRotationAngle(), "Waist (rotation)");
                this.printChanges(8, this.wrist1.getCurrentFlexionAngle(), "Wrist 1 (flex)");
                this.printChanges(9, this.wrist2.getCurrentFlexionAngle(), "Wrist 2 (flex)");
                this.printChanges(10, this.elbow1.getCurrentFlexionAngle(), "Elbow 1 (flex)");
                this.printChanges(11, this.elbow2.getCurrentFlexionAngle(), "Elbow 2 (flex)");
                this.printChanges(12, this.shoulder1.getCurrentFlexionAngle(), "Shoulder 1 (flex)");
                this.printChanges(13, this.shoulder1.getCurrentRotationAngle(), "Shoulder 1 (rotation)");
                this.printChanges(14, this.shoulder2.getCurrentFlexionAngle(), "Shoulder 2 (flex)");
                this.printChanges(15, this.shoulder2.getCurrentRotationAngle(), "Shoulder 2 (rotation)");
                this.printChanges(16, this.neck.getCurrentFlexionAngle(), "Neck (flex)");
                this.printChanges(17, this.head.getCurrentFlexionAngle(), "Head (flex)");
                this.printChanges(18, this.head.getCurrentRotationAngle(), "Head (rotation)");
            }
        }
        return time;
    }

    public void printChanges(int previous, double current, String name) {
        String differenceFormatted = String.format("%.2f", (current - this.previousSecondAngles[previous]));
        String currentFormatted = String.format("%.2f", current);
        if (current < this.previousSecondAngles[previous]) {
            System.out.println(name + ": " + currentFormatted + " (" + differenceFormatted + ")");
            this.previousSecondAngles[previous] = current;
        } else if (current > this.previousSecondAngles[previous]) {
            System.out.println(name + ": " + currentFormatted + " (+" + differenceFormatted + ")");
            this.previousSecondAngles[previous] = current;
        }
    }

    public String showPositions() {
        String positions = String.format("Positions of each joint:\nRight Ankle: %.2f\nLeft Ankle: %.2f\n"
                + "Right Knee: %.2f\nLeft Knee: %.2f\nRight Hip: %.2f\nLeft Hip: %.2f\nWaist(flex): %.2f "+
                "(rotation): %.2f\nRight Wrist: %.2f\nLeft Wrist: %.2f\nRight Elbow: %.2f\nLeft Elbow: %.2f"+
                "\nRight Shoulder(flex): %.2f (rotation): %.2f\n");
        this.ankle1.getCurrentFlexionAngle();
        this.printChanges(1, this.ankle2.getCurrentFlexionAngle(), "Ankle 2 (flex)");
        this.printChanges(2, this.knee1.getCurrentFlexionAngle(), "Knee 1 (flex)");
        this.printChanges(3, this.knee2.getCurrentFlexionAngle(), "Knee 1 (flex)");
        this.printChanges(4, this.hip1.getCurrentFlexionAngle(), "Hip 1 (flex)");
        this.printChanges(5, this.hip2.getCurrentFlexionAngle(), "Hip 1 (flex)");
        this.printChanges(6, this.waist.getCurrentFlexionAngle(), "Waist (flex)");
        this.printChanges(7, this.waist.getCurrentRotationAngle(), "Waist (rotation)");
        this.printChanges(8, this.wrist1.getCurrentFlexionAngle(), "Wrist 1 (flex)");
        this.printChanges(9, this.wrist2.getCurrentFlexionAngle(), "Wrist 2 (flex)");
        this.printChanges(10, this.elbow1.getCurrentFlexionAngle(), "Elbow 1 (flex)");
        this.printChanges(11, this.elbow2.getCurrentFlexionAngle(), "Elbow 2 (flex)");
        this.printChanges(12, this.shoulder1.getCurrentFlexionAngle(), "Shoulder 1 (flex)");
        this.printChanges(13, this.shoulder1.getCurrentRotationAngle(), "Shoulder 1 (rotation)");
        this.printChanges(14, this.shoulder2.getCurrentFlexionAngle(), "Shoulder 2 (flex)");
        this.printChanges(15, this.shoulder2.getCurrentRotationAngle(), "Shoulder 2 (rotation)");
        this.printChanges(16, this.neck.getCurrentFlexionAngle(), "Neck (flex)");
        this.printChanges(17, this.head.getCurrentFlexionAngle(), "Head (flex)");
        this.printChanges(18, this.head.getCurrentRotationAngle(), "Head (rotation)");

        return positions;
    }

    public int standUp(int time) {
        this.waist.setSpeed(15);
        time = this.update(2000, time);
        this.waist.setSpeed(0);
        // System.out.println(this.waist.getCurrentFlexionAngle());
        time = this.update(1000, time);
        this.knee1.setSpeed(-7.5);
        this.knee2.setSpeed(-7.5);

        time = this.update(6000, time);
        // System.out.println(this.knee1.getCurrentFlexionAngle());
        this.knee1.setSpeed(0);
        this.knee2.setSpeed(0);
        this.hip1.setSpeed(-5.3);
        this.hip2.setSpeed(-5.3);
        time = this.update(8500, time);
        // System.out.println(this.hip1.getCurrentFlexionAngle());
        this.hip1.setSpeed(0);
        this.hip2.setSpeed(0);
        this.knee1.setSpeed(-7.5);
        this.knee2.setSpeed(-7.5);

        time = this.update(6500, time);
        // System.out.println(this.knee1.getCurrentFlexionAngle());
        this.knee1.setSpeed(0);
        this.knee2.setSpeed(0);
        this.hip1.setSpeed(-5.3);
        this.hip2.setSpeed(-5.3);
        time = this.update(8500, time);
        this.hip1.setSpeed(0);
        this.hip2.setSpeed(0);
        // System.out.println(this.hip1.getCurrentFlexionAngle());

        time = this.update(1000, time);

        this.waist.setSpeed(-15);
        time = this.update(2000, time);
        this.waist.setSpeed(0);
        // System.out.println(this.waist.getCurrentFlexionAngle());

        return time;
    }

    public int firstStep(int time) {
        this.knee1.setSpeed(5);
        this.hip1.setSpeed(5);
        time = this.update(3000, time);
        // System.out.println(this.hip1.getCurrentFlexionAngle());

        this.knee1.setSpeed(0);
        this.hip1.setSpeed(0);
        this.ankle2.setSpeed(10);
        time = this.update(1000, time);
        // System.out.println(this.ankle2.getCurrentFlexionAngle());

        this.ankle2.setSpeed(0);
        return time;
    }

    public int stepLeft(int time) {
        this.knee2.setSpeed(5);
        this.hip2.setSpeed(5);
        time = this.update(3000, time);

        this.knee2.setSpeed(0);
        this.hip2.setSpeed(0);
        this.ankle1.setSpeed(10);
        time = this.update(1000, time);

        this.ankle1.setSpeed(0);
        this.knee1.setSpeed(-5);
        this.hip1.setSpeed(-5);
        time = this.update(3000, time);
        // System.out.println(this.hip1.getCurrentFlexionAngle());

        this.knee1.setSpeed(0);
        this.hip1.setSpeed(0);
        this.ankle1.setSpeed(10);
        time = this.update(1000, time);

        return time;
    }

    public int stepRight(int time) {
        return time;
    }

}
