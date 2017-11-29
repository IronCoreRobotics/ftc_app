package com.ironcorerobotics.GameDayOpModes;

import com.ironcorerobotics.ControlClasses.MotorControl;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Fam on 11/9/2017.
 */

@Autonomous(name = "Auto Quad 1 Red New", group = "Test")

public class AutonomousQuadrant1Red extends LinearOpMode

{
    DcMotor motor1;
    DcMotor motor2;
    Servo servo1;
    ColorSensor sensorColor;
    DistanceSensor sensorDistance;
    MotorControl controlMotor2 = new MotorControl(-1);

    @Override public void runOpMode() throws InterruptedException

    {
        motor1 = hardwareMap.dcMotor.get("rightside_Motor");
        motor2 = hardwareMap.dcMotor.get("leftside_Motor");
        servo1 = hardwareMap.servo.get("drawbridge_winch");
        sensorColor = hardwareMap.get(ColorSensor.class, "sensor_color_distance");
        sensorDistance = hardwareMap.get(DistanceSensor.class, "sensor_color_distance");


        waitForStart();

        servo1.setPosition(.53);

        sleep(3000);

        JewelScoreAutonomous("Blue");
    }

    public void JewelScoreAutonomous(String AllianceColor)

    {
        servo1.setPosition(.8);
        servo1.setPosition(0);

        sleep(500);

        if (AllianceColor == "Blue")

        {

            if (sensorColor.red() > sensorColor.blue() && opModeIsActive()) {
                autoCustomDrive("Drive", 1.00);

                brake();

                servo1.setPosition(.53);

                sleep(500);

                autoCustomDrive("Reverse", 1.00);
            }

            if (sensorColor.red() < sensorColor.blue() && opModeIsActive()) {
                autoCustomDrive("Reverse", 1.00);

               brake();

                servo1.setPosition(.53);

                sleep(500);

                autoCustomDrive("Drive", 1.00);
            }
        }

        if (AllianceColor == "Red")

        {

            if (sensorColor.red() > sensorColor.blue() && opModeIsActive()) {
                autoCustomDrive("Reverse", 1.00);

                brake();

                servo1.setPosition(.7);

                sleep(500);

                autoCustomDrive("Drive", 1.00);


            }

            if (sensorColor.red() < sensorColor.blue() && opModeIsActive()) {
                autoCustomDrive("Drive", 1.00);

                brake();

                servo1.setPosition(.7);

                sleep(1000);

                autoCustomDrive("Reverse", 1.00);
            }
        }

       brake();
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
