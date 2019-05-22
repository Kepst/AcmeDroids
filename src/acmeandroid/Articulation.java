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
public class Articulation {
    
    private static final int MAX_VOLTAGE_REQUIRED = 4;
    private static final int MOTION_SPEED = 15;
    
    private final int voltageRequired;
    private final int maxFlexionAngle;
    
    private int currentFlexionAngle;
    
    public Articulation (int voltageRequired, int maxFlexionAngle, int currentFlexionAngle) {
        this.voltageRequired = voltageRequired;
        this.maxFlexionAngle = maxFlexionAngle;
        this.currentFlexionAngle = currentFlexionAngle;
    }
}
