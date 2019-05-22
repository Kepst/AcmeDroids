/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acmeandroid;

/**
 *
 * @author welli
 */
public class RotatingArticulation extends Articulation {
    
    private final int maxRotationAngle;
    
    private int currentRotationAngle;

    public RotatingArticulation(int maxRotationAngle, int currentRotationAngle, int voltageRequired, int maxFlexionAngle, int currentFlexionAngle) {
        super(voltageRequired, maxFlexionAngle, currentFlexionAngle);
        this.maxRotationAngle = maxRotationAngle;
        this.currentRotationAngle = currentRotationAngle;
    }
        
}
