package com.ironcorerobotics.TestPrograms;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.ironcorerobotics.ControlClasses.MotorControl;

/**
 * Created by Fam on 10/26/2017.
 */

@Autonomous(name = "Autonomous Test 1", group = "Test")
@Disabled

public class AutonomousTest1 extends LinearOpMode

{
    DcMotor motor1;
    DcMotor motor2;
    MotorControl controlMotor2 = new MotorControl(-1);

    @Override
    public void runOpMode() throws InterruptedException

    {
        motor1 = hardwareMap.dcMotor.get("Test_Motor_2");
        motor2 = hardwareMap.dcMotor.get("Test_Motor");


        waitForStart();

        while (opModeIsActive())

        {


            if (gamepad1.dpad_up == true)
            {
                autoCustomDrive("Drive", 1.00);
            }

        if (gamepad1.dpad_down == true)
        {
            autoCustomDrive("Reverse", 1.00);
        }
        if (gamepad1.dpad_right == true) {
            autoCustomDrive("Right", 1.00);
        }
        if (gamepad1.dpad_left == true) {
            autoCustomDrive("Left", 1.00);
        }
        if (gamepad1.y == true) {
            autoCustomDrive("180", 1.00);
        }
        else {
            brake();
        }
    }

}

    public void autoDrive(int distance, String direction, double speed)

    {
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        int startPosition = motor1.getCurrentPosition();

        if(direction == "Drive")
        {
            while(startPosition + distance > motor1.getCurrentPosition() && opModeIsActive())
            {
                motor1.setPower(speed);
                motor2.setPower(controlMotor2.getSpeed()*speed);
            }
        }

        if(direction == "Reverse")
        {
            while(startPosition - distance < motor1.getCurrentPosition() && opModeIsActive())
            {
                motor1.setPower(-speed);
                motor2.setPower(-speed*controlMotor2.getSpeed());
            }
        }
        if(direction == "Right")
        {
            while(startPosition - distance < motor1.getCurrentPosition() && opModeIsActive())
            {
                motor1.setPower(-speed);
                motor2.setPower(speed*controlMotor2.getSpeed());
            }
        }
        if(direction == "Left")
        {
            while(startPosition + distance > motor1.getCurrentPosition() && opModeIsActive())
            {
                motor1.setPower(speed);
                motor2.setPower(-speed*controlMotor2.getSpeed());
            }
        }
    }

    public void autoCustomDrive(String direction, double speed)
    {
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        int startPosition = motor1.getCurrentPosition();

        if(direction == "180")
        {
            while(startPosition - 3000 < motor1.getCurrentPosition() && opModeIsActive())
            {
                motor1.setPower(-speed);
                motor2.setPower(speed*controlMotor2.getSpeed());
            }
        }

        if(direction == "Right")
        {
            while(startPosition - 1500 < motor1.getCurrentPosition() && opModeIsActive())
            {
                motor1.setPower(-speed);
                motor2.setPower(speed*controlMotor2.getSpeed());
            }
        }

        if(direction == "Left")
        {
            while(startPosition + 1500 > motor1.getCurrentPosition() && opModeIsActive())
            {
                motor1.setPower(speed);
                motor2.setPower(-speed*controlMotor2.getSpeed());
            }
        }

        if(direction == "Reverse")
        {
            if(direction == "Reverse")
            {
                while(startPosition - 1000 < motor1.getCurrentPosition() && opModeIsActive())
                {
                    motor1.setPower(-speed);
                    motor2.setPower(-speed*controlMotor2.getSpeed());
                }
            }
        }

        if(direction == "Drive")
        {
            while(startPosition + 1000 > motor1.getCurrentPosition() && opModeIsActive())
            {
                motor1.setPower(speed);
                motor2.setPower(controlMotor2.getSpeed()*speed);
            }
        }
    }

    public void brake()

    {
            motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            motor1.setTargetPosition(motor1.getCurrentPosition());
            motor2.setTargetPosition(motor2.getCurrentPosition());
    }
}

