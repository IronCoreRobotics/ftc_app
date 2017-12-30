package com.ironcorerobotics.ControlClasses;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Fam on 10/30/2017.
 */

public class TwoWheelDriveTrain

{
    private DcMotor motor1;
    private DcMotor motor2;
    private MotorControl controlMotor2;
    private boolean opModeState;

    public TwoWheelDriveTrain(DcMotor motor1, DcMotor motor2, MotorControl controlMotor2, boolean opModeState) {
        this.motor1 = motor1;
        this.motor2 = motor2;
        this.controlMotor2 = controlMotor2;
        this.opModeState = opModeState;
    }

    public boolean isOpModeState() {
        return opModeState;
    }

    public void setOpModeState(boolean opModeState) {
        this.opModeState = opModeState;
    }

    public boolean autoDrive(int distance, String direction, double speed)

    {
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        int startPosition = motor1.getCurrentPosition();


        if(direction == "Drive" )
        {
            while(startPosition + distance > motor1.getCurrentPosition()  && isOpModeState() == true)
            {
                motor1.setPower(speed);
                motor2.setPower(controlMotor2.getSpeed()*speed);
            }
        }

        if(direction == "Reverse")
        {
            while(startPosition - distance < motor1.getCurrentPosition() && isOpModeState())
            {
                motor1.setPower(-speed);
                motor2.setPower(-speed*controlMotor2.getSpeed());
            }
        }
        if(direction == "Right")
        {
            while(startPosition - distance < motor1.getCurrentPosition() && opModeState)
            {
                motor1.setPower(-speed);
                motor2.setPower(speed*controlMotor2.getSpeed());
            }
        }
        if(direction == "Left")
        {
            while(startPosition + distance > motor1.getCurrentPosition() && opModeState)
            {
                motor1.setPower(speed);
                motor2.setPower(-speed*controlMotor2.getSpeed());
            }
        }
        return true;
    }

    public void brake() throws InterruptedException

    {
        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motor1.setPower(0);
        motor2.setPower(0);
    }


}
