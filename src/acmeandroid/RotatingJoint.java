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

/**
 * An object of class <code>RotatingJoint</code> inherits all fields and methods
 * from the class <code>Joint</code> and has additional fields and methods for
 * dealing with articulations that can also rotate
 *
 * @author Wellington Regis
 * @author Pedro Stuginski
 * @author Kate Santos
 * @author Marcus Vinicius
 */
public class RotatingJoint extends Joint {

    private final double maxRotationAngle;
    private double currentRotationAngle;
    private double rotationSpeed;

    /**
     * Constructor for the <code>RotatingJoint</code> class
     *
     * @param voltageRequired holds the required energy per second necessary to
     * move the articulation at 15 degrees per second
     * @param maxFlexionAngle holds the maximum flexion angle an articulation
     * can move
     * @param currentFlexionAngle holds the current flexion angle position of
     * the articulation
     * @param maxRotationAngle holds the maximum rotation angle an articulation
     * can move
     * @param currentRotationAngle holds the current rotation angle position of
     * the articulation
     */
    public RotatingJoint(double voltageRequired, double maxFlexionAngle,
            double currentFlexionAngle, double maxRotationAngle,
            double currentRotationAngle) {
        super(voltageRequired, maxFlexionAngle, currentFlexionAngle);
        this.maxRotationAngle = maxRotationAngle;
        this.currentRotationAngle = currentRotationAngle;
        this.rotationSpeed = 0;
    }

    /**
     * Sets rotation angular speed for the articulation
     *
     * @param speed holds the current rotation speed of the articulation, which
     * is used when updating its rotation angle
     */
    public void setRotationSpeed(double speed) {
        this.rotationSpeed = speed;
    }

    /**
     * Receives a time interval and updates the flexion and rotation angles of
     * the articulation based on its motion speeds, checks whether the new
     * angles infringe the physical limits of the robot and returns the energy
     * consumed
     *
     * @param timeInterval
     * @return
     */
    @Override
    public double update(double timeInterval) {
        // Calls for super method for updating flexion angle
        double energyConsumed = super.update(timeInterval);

        // Updates rotation angle
        this.currentRotationAngle += this.rotationSpeed * timeInterval / 1000;

        // Checks for limits
        if (this.currentRotationAngle > maxRotationAngle) {
            this.currentRotationAngle = this.maxRotationAngle;
        } else if (this.currentRotationAngle < 0) {
            this.currentRotationAngle = 0;
        }

        // Calculates final energy consumed (flexion + rotation) 
        energyConsumed += timeInterval * this.voltageRequired
                * Math.abs(this.rotationSpeed / 15) / 1000;

        return energyConsumed;
    }

    /**
     * Gets current rotation angle of the articulation
     *
     * @return double with current rotation angle of the articulation
     */
    public double getCurrentRotationAngle() {
        return currentRotationAngle;
    }
}
