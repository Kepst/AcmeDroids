package acmeandroid;

/**
 *
 * @author welli
 */
public class Android {
    private Joint ankle1;
    private Joint ankle2;
    private Joint knee1;
    private Joint knee2;
    private Joint hip1;
    private Joint hip2;
    private Joint waist;
    private Joint wrist1;
    private Joint wrist2;
    private Joint elbow1;
    private Joint elbow2;
    private Joint shoulder1;
    private Joint shoulder2;
    private Joint neck;
    private Joint head;
    private int battery;
    
    public Android () {
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

        this.battery = 8;
    }

    public void update(double time) {
        this.battery -= this.ankle1.update(time);
        this.battery -= this.ankle2.update(time);
        this.battery -= this.knee1.update(time);
        this.battery -= this.knee2.update(time);
        this.battery -= this.hip1.update(time);
        this.battery -= this.hip2.update(time);
        this.battery -= this.waist.update(time);
        this.battery -= this.wrist1.update(time);
        this.battery -= this.wrist2.update(time);
        this.battery -= this.elbow1.update(time);
        this.battery -= this.elbow2.update(time);
        this.battery -= this.shoulder1.update(time);
        this.battery -= this.shoulder2.update(time);
        this.battery -= this.neck.update(time);
        this.battery -= this.head.update(time);

        this.battery += time * 8/3;
        if(this.battery > 8){
            this.battery = 8;
        }
        if(this.battery < 1){
            System.out.println("Android broke");
        }
    }

    public void standUp(){
        this.waist.setSpeed(15);
        this.update(2);
        this.waist.setSpeed(0);
        
    }
}
