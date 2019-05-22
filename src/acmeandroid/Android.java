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
public class Android {
    private Articulation ankle1;
    private Articulation ankle2;
    private Articulation knee1;
    private Articulation knee2;
    private Articulation hip1;
    private Articulation hip2;
    private Articulation waist;
    private Articulation wrist1;
    private Articulation wrist2;
    private Articulation elbow1;
    private Articulation elbow2;
    private Articulation shoulder1;
    private Articulation shoulder2;
    private Articulation neck;
    private Articulation head;
    
    public Android () {
        this.ankle1 = new Articulation(3, 30, 0);
        this.ankle2 = new Articulation(3, 30, 0);
        this.knee1 = new Articulation(3, 90, 0);
        this.knee2 = new Articulation(3, 90, 0);
        this.hip1 = new Articulation(4, 90, 0);
        this.hip2 = new Articulation(4, 90, 0);
        this.waist = new RotatingArticulation(4, 90, 0, 30, 0);
        this.wrist1 = new Articulation(2, 180, 90);
        this.wrist2 = new Articulation(2, 180, 90);
        this.elbow1 = new Articulation(3, 140, 0);
        this.elbow2 = new Articulation(3, 140, 0);
        this.shoulder1 = new RotatingArticulation(2, 180, 0, 360, 0);
        this.shoulder2 = new RotatingArticulation(2, 180, 0, 360, 0);
        this.neck = new Articulation(3, 30, 0);
        this.head = new RotatingArticulation(3, 180, 45, 180, 90);
    }
}
