package com.ironcorerobotics.GameDayOpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Fam on 11/9/2017.
 */

@Autonomous(name = "Auto Quad 4 Blue New", group = "Test")

public class AutonomousQuadrant4Blue extends LinearOpMode

{
    DcMotor motor1;
    DcMotor motor2;
    Servo servo1;
    ColorSensor sensorColor;
    DistanceSensor sensorDistance;

    @Override
    public void runOpMode() throws InterruptedException

    {
        motor1 = hardwareMap.dcMotor.get("rightside_Motor");
        motor2 = hardwareMap.dcMotor.get("leftside_Motor");
        servo1 = hardwareMap.servo.get("drawbridge_winch");
        sensorColor = hardwareMap.get(ColorSensor.class, "sensor_color_distance");
        sensorDistance = hardwareMap.get(DistanceSensor.class, "sensor_color_distance");

        waitForStart();

        JewelScoreAutonomous("Blue");
    }

    public void JewelScoreAutonomous(String AllianceColor)

    {

        servo1.setPosition(0);

        sleep(1000);

        if (AllianceColor == "Blue")

        {

            if (sensorColor.red() > sensorColor.blue() && opModeIsActive()) {
                motor2.setPower(-.30); //backwards

                sleep(350);

                motor2.setPower(0);

                servo1.setPosition(.7);

                sleep(1000);

                motor2.setPower(.30);

                sleep(250);


            }

            if (sensorColor.red() < sensorColor.blue() && opModeIsActive()) {
                motor1.setPower(.30);

                sleep(350);

                motor1.setPower(0);
                motor2.setPower(0);

                servo1.setPosition(.7);

                sleep(1000);

                motor1.setPower(-.30);

                sleep(250);
            }
        }

        if (AllianceColor == "Red")

        {

            if (sensorColor.red() > sensorColor.blue()&& opModeIsActive()) {
                motor2.setPower(.30);

                sleep(350);

                motor2.setPower(0);

                servo1.setPosition(.7);

                sleep(1000);

                motor2.setPower(-.30);

                sleep(250);


            }

            if (sensorColor.red() < sensorColor.blue()&& opModeIsActive()) {
                motor1.setPower(-.30);

                sleep(350);

                motor1.setPower(0);
                motor2.setPower(0);

                servo1.setPosition(.7);

                sleep(1000);

                motor1.setPower(.30);

                sleep(250);
            }
        }

        motor1.setPower(0);
        motor2.setPower(0);

        sleep(1000);
    }
}
