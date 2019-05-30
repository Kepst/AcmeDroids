/* 
 * Copyright (C) 2019 Wellington Regis, Pedro Stuginski, Kate Santos, Marcus Vinicius
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package acmeandroid;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An object of class <code>Android</code> represents a robots, containing all
 * of its articulations and battery level, as well as an array used to compare
 * angle changes in the robot second by second
 *
 * @author Wellington Regis
 * @author Pedro Stuginski
 * @author Kate Santos
 * @author Marcus Vinicius
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
    private final double previousSecondAngles[];
    private double batteryLevel;

    /**
     * Constructor for object of class <code>Android</code> where the angles of
     * the articulation are set in an initial sitting position
     */
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
        this.previousSecondAngles = new double[]{this.ankle1.getCurrentFlexionAngle(),
            this.ankle2.getCurrentFlexionAngle(), this.knee1.getCurrentFlexionAngle(),
            this.knee2.getCurrentFlexionAngle(), this.hip1.getCurrentFlexionAngle(),
            this.hip2.getCurrentFlexionAngle(), this.waist.getCurrentFlexionAngle(),
            this.waist.getCurrentRotationAngle(), this.wrist1.getCurrentFlexionAngle(),
            this.wrist2.getCurrentFlexionAngle(), this.elbow1.getCurrentFlexionAngle(),
            this.elbow2.getCurrentFlexionAngle(), this.shoulder1.getCurrentFlexionAngle(),
            this.shoulder1.getCurrentRotationAngle(), this.shoulder2.getCurrentFlexionAngle(),
            this.shoulder2.getCurrentRotationAngle(), this.neck.getCurrentFlexionAngle(),
            this.head.getCurrentFlexionAngle(), this.head.getCurrentRotationAngle()};
    }

    /**
     * Receives a time interval and updates all angles in all articulation
     * depending on their motion speed, as well as updates battery level
     * checking for full charge and low level below critical limit
     *
     * @param timeInterval holds the time interval used to update angles and
     * battery level
     */
    public void updateAngles(double timeInterval) {

        // Updates angles in all articulations
        this.batteryLevel -= this.ankle1.update(timeInterval);
        this.batteryLevel -= this.ankle2.update(timeInterval);
        this.batteryLevel -= this.knee1.update(timeInterval);
        this.batteryLevel -= this.knee2.update(timeInterval);
        this.batteryLevel -= this.hip1.update(timeInterval);
        this.batteryLevel -= this.hip2.update(timeInterval);
        this.batteryLevel -= this.waist.update(timeInterval);
        this.batteryLevel -= this.wrist1.update(timeInterval);
        this.batteryLevel -= this.wrist2.update(timeInterval);
        this.batteryLevel -= this.elbow1.update(timeInterval);
        this.batteryLevel -= this.elbow2.update(timeInterval);
        this.batteryLevel -= this.shoulder1.update(timeInterval);
        this.batteryLevel -= this.shoulder2.update(timeInterval);
        this.batteryLevel -= this.neck.update(timeInterval);
        this.batteryLevel -= this.head.update(timeInterval);

        // Battery regeneration
        this.batteryLevel += timeInterval * 8 / 3 / 1000;

        // Checks for limits
        if (this.batteryLevel > 8) {
            this.batteryLevel = 8;
        } else if (this.batteryLevel < 1) {
            System.out.println("Android broke");
        }
    }

    /**
     * Repeatedly updates angles and battery level for a certain period of time
     *
     * @param movementTime holds the total movement time in milliseconds
     * @param time holds the current time since the robot first started moving
     * @return the time when the total movement is finished
     */
    public int update(int movementTime, int time) {

        int endTime = time + movementTime;

        while (time < endTime) {
            // Updates angles in a millisecond
            this.updateAngles(1);

            // Updates time
            time++;

            // Does this only every full second has passed
            if (time % 1000 == 0) {
                // Makes program sleep for a second
                try {
                    Thread.sleep(10); // CHANGE BACK TO 1000
                } catch (InterruptedException ex) {
                    Logger.getLogger(Android.class.getName()).log(Level.SEVERE, null, ex);
                }

                // Prints current time, battery level and angle changes since last second
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

    /**
     * Prints changes in articulation angles second by second
     *
     * @param previousSecondIndex holds the index used to retrieve the angle of
     * the specific articulation 1 second before from the array containing all
     * the angles in the previous second
     * @param currentAngle holds the current angle of the specific articulation
     * @param jointName holds the name of the articulation and the type of the
     * angle
     */
    public void printChanges(int previousSecondIndex, double currentAngle, String jointName) {
        String differenceFormatted = String.format("%.2f", (currentAngle - this.previousSecondAngles[previousSecondIndex]));
        String currentFormatted = String.format("%.2f", currentAngle);
        if (currentAngle < this.previousSecondAngles[previousSecondIndex]) {
            System.out.println(jointName + ": " + currentFormatted + " (" + differenceFormatted + ")");
            this.previousSecondAngles[previousSecondIndex] = currentAngle;
        } else if (currentAngle > this.previousSecondAngles[previousSecondIndex]) {
            System.out.println(jointName + ": " + currentFormatted + " (+" + differenceFormatted + ")");
            this.previousSecondAngles[previousSecondIndex] = currentAngle;
        }
    }

    /**
     * Creates a formatted String with all the angles in the robot
     * 
     * @return a formatted String with all the angles in the robot
     */
    public String showPositions() {
        String positions = String.format(
                "Positions of each joint:\nRight Ankle: %.2f\nLeft Ankle: %.2f\n"
                + "Right Knee: %.2f\nLeft Knee: %.2f\nRight Hip: %.2f\nLeft Hip: %.2f\nWaist(flex): %.2f "
                + "(rotation): %.2f\nRight Wrist: %.2f\nLeft Wrist: %.2f\nRight Elbow: %.2f\nLeft Elbow: %.2f"
                + "\nRight Shoulder(flex): %.2f (rotation): %.2f\nLeft Shoulder(flex): %.2f (rotation): %.2f\n"
                + "Neck: %.2f\nHead(flex): %.2f (rotation):%.2f",
                this.ankle1.getCurrentFlexionAngle(), this.ankle2.getCurrentFlexionAngle(),
                this.knee2.getCurrentFlexionAngle(), this.hip1.getCurrentFlexionAngle(),
                this.knee1.getCurrentFlexionAngle(), this.hip2.getCurrentFlexionAngle(),
                this.waist.getCurrentFlexionAngle(), this.waist.getCurrentRotationAngle(),
                this.wrist1.getCurrentFlexionAngle(), this.wrist2.getCurrentFlexionAngle(),
                this.elbow1.getCurrentFlexionAngle(), this.elbow2.getCurrentFlexionAngle(),
                this.shoulder1.getCurrentFlexionAngle(), this.shoulder1.getCurrentRotationAngle(),
                this.shoulder2.getCurrentFlexionAngle(), this.shoulder2.getCurrentRotationAngle(),
                this.neck.getCurrentFlexionAngle(), this.head.getCurrentFlexionAngle(),
                this.head.getCurrentRotationAngle());

        return positions;
    }

    /**
     * Algorithm used to make the robot stand from a sitting position
     * 
     * @param time holds the current real time
     * @return time when robot has finished standing up
     */
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

    /**
     * 
     * @param time
     * @return 
     */
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
