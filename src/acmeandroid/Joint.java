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
 * An object of class <code>Joint</code> represents an articulation of a robot
 *
 * @author Wellington Regis
 * @author Pedro Stuginski
 * @author Kate Santos
 * @author Marcus Vinicius
 */
public class Joint {

    protected final double voltageRequired;
    private final double maxFlexionAngle;
    private double currentFlexionAngle;
    private double motionSpeed;

    /**
     * Constructor for class <code>Joint</code>
     *
     * @param voltageRequired holds the required energy per second necessary to
     * move an articulation at 15 degrees per second
     * @param maxFlexionAngle holds the maximum flexion angle an articulation
     * can move
     * @param currentFlexionAngle holds the current angle position of the
     * articulation
     */
    public Joint(double voltageRequired, double maxFlexionAngle, double currentFlexionAngle) {
        this.voltageRequired = voltageRequired;
        this.maxFlexionAngle = maxFlexionAngle;
        this.currentFlexionAngle = currentFlexionAngle;
        this.motionSpeed = 0;
    }

    /**
     * Sets flexion angular speed for the articulation
     *
     * @param speed holds the current angular speed of the articulation, which
     * is used when updating its flexion angle
     */
    public void setSpeed(double speed) {
        this.motionSpeed = speed;
    }

    /**
     * Receives a time interval and updates the flexion angle of the
     * articulation based on its motion speed, checks whether the new angle
     * infringes the physical limits of the robot and returns the energy
     * consumed
     *
     * @param timeInterval holds the time interval to be used in updating the
     * flexion angle of the articulation and calculating the energy consumed
     * @return the energy consumed in V
     */
    public double update(double timeInterval) {
        // Updates flexion angle
        this.currentFlexionAngle += this.motionSpeed * timeInterval / 1000;
        
        // Checks for limits
        if (this.currentFlexionAngle > maxFlexionAngle) {
            this.currentFlexionAngle = this.maxFlexionAngle;
        } else if (this.currentFlexionAngle < 0) {
            this.currentFlexionAngle = 0;
        }
        
        // Calculates energy Consumed 
        double energyConsumed = timeInterval * this.voltageRequired
                * Math.abs(this.motionSpeed / 15) / 1000;

        return energyConsumed;
    }

    /**
     * Gets current flexion angle for articulation
     *
     * @return double with the current flexion angle of the articulation
     */
    public double getCurrentFlexionAngle() {
        return currentFlexionAngle;
    }
}
