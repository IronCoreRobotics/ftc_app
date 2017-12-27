package com.ironcorerobotics.ControlClasses;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.configuration.MotorConfiguration;

/**
 * Created by Fam on 10/30/2017.
 */

public class TwoWheelDriveTrain

{
    DcMotor motor1;
    DcMotor motor2;
    MotorControl controlMotor2 = new MotorControl(-1);
    Boolean OpModeCondition;

    public TwoWheelDriveTrain(Boolean opModeCondition) {
        OpModeCondition = opModeCondition;
    }

    public Boolean getOpModeCondition() {
        return OpModeCondition;
    }

    public void autoDrive(int distance, String direction, double speed)

    {
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        int startPosition = motor1.getCurrentPosition();

        if(direction == "Drive")
        {
            while(startPosition + distance > motor1.getCurrentPosition() && getOpModeCondition())
            {
                motor1.setPower(speed);
                motor2.setPower(controlMotor2.getSpeed()*speed);
            }
        }

        if(direction == "Reverse")
        {
            while(startPosition - distance < motor1.getCurrentPosition() && getOpModeCondition())
            {
                motor1.setPower(-speed);
                motor2.setPower(-speed*controlMotor2.getSpeed());
            }
        }
        if(direction == "Right")
        {
            while(startPosition - distance < motor1.getCurrentPosition() && getOpModeCondition())
            {
                motor1.setPower(-speed);
                motor2.setPower(speed*controlMotor2.getSpeed());
            }
        }
        if(direction == "Left")
        {
            while(startPosition + distance > motor1.getCurrentPosition() && getOpModeCondition())
            {
                motor1.setPower(speed);
                motor2.setPower(-speed*controlMotor2.getSpeed());
            }
        }
    }


}
