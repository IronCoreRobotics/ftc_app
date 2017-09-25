package org.firstinspires.ftc.teamcode;

/**
 * Created by Fam on 9/23/2017.
 */

public class MotorControl

{
    private double speed;
    private boolean LB;
    private boolean RB;
    public double publicOutsideSourceSettingSpeed;

    public MotorControl(double speed, boolean LB, boolean RB) {
        this.speed = speed;
        this.LB = LB;
        this.RB = RB;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setPublicOutsideSourceSettingSpeed(double publicOutsideSourceSettingSpeed) {
        this.publicOutsideSourceSettingSpeed = publicOutsideSourceSettingSpeed;
    }

    public double getPublicOutsideSourceSettingSpeed() {
        return publicOutsideSourceSettingSpeed;
    }

    public double ControlMotorSpeed()
    {
        if(this.LB == false && this.RB == false)
        {
            setPublicOutsideSourceSettingSpeed(1);
            setSpeed(this.speed);
        }

        if(this.LB == true && this.RB == false)
        {
            setPublicOutsideSourceSettingSpeed(2);
            setSpeed(this.speed/2);
        }

        if(this.LB == true && this.RB == true)
        {
            setPublicOutsideSourceSettingSpeed(10);
            setSpeed(this.speed/10);
        }

        return this.speed;
    }


}
