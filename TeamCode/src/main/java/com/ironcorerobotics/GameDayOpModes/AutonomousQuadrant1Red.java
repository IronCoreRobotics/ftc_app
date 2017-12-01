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
    int zeroPoint;
    DcMotor motor1;
    DcMotor motor2;
    Servo jewelSlapper;
    Servo rightGrip;
    Servo leftGrip;
    DcMotor lift;
    ColorSensor sensorColor;
    DistanceSensor sensorDistance;
    MotorControl controlMotor2 = new MotorControl(-1);

    @Override public void runOpMode() throws InterruptedException

    {
        motor1 = hardwareMap.dcMotor.get("rightside_Motor");
        motor2 = hardwareMap.dcMotor.get("leftside_Motor");
        jewelSlapper = hardwareMap.servo.get("drawbridge_winch");
        sensorColor = hardwareMap.get(ColorSensor.class, "sensor_color_distance");
        sensorDistance = hardwareMap.get(DistanceSensor.class, "sensor_color_distance");

        waitForStart();

        initGLS();

        rightGrip.setPosition(0.9);
        leftGrip.setPosition(0.4);

        lift.setTargetPosition(1290 + zeroPoint);

        sleep(4000);

        jewelSlapper.setPosition(.53);

        JewelScoreAutonomous("Red");
    }

    public void JewelScoreAutonomous(String AllianceColor)

    {
        jewelSlapper.setPosition(.7);
        jewelSlapper.setPosition(0.2);

        if (AllianceColor == "Blue")

        {

            if (sensorColor.red() > sensorColor.blue() && opModeIsActive()) {
                autoDrive(500, "Reverse", 1.00);

                brake();

                jewelSlapper.setPosition(.53);

                autoDrive(500, "Drive", 1.00);
            }

            if (sensorColor.red() < sensorColor.blue() && opModeIsActive()) {
                autoDrive(500, "Drive", 1.00);

                brake();

                jewelSlapper.setPosition(.53);

                autoDrive(500, "Reverse", 1.00);
            }
        }

        if (AllianceColor == "Red")

        {

            if (sensorColor.red() > sensorColor.blue() && opModeIsActive()) {
                if (sensorColor.red() < sensorColor.blue() && opModeIsActive()) {
                    autoDrive(500, "Drive", 1.00);

                    brake();

                    jewelSlapper.setPosition(.53);

                    autoDrive(500, "Reverse", 1.00);
                }
            }

            if (sensorColor.red() < sensorColor.blue() && opModeIsActive()) {
                autoDrive(500, "Reverse", 1.00);

                brake();

                jewelSlapper.setPosition(.53);

                autoDrive(500, "Drive", 1.00);
            }
        }

       brake();

        sleep(1000);
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

    private void initGLS() {
        rightGrip = hardwareMap.servo.get("right_grip");
        leftGrip = hardwareMap.servo.get("left_grip");

        rightGrip.setPosition(0.23);
        leftGrip.setPosition(0.94);

        lift = hardwareMap.dcMotor.get("LiftMotor");

        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        zeroPoint = lift.getCurrentPosition();
        lift.setPower(0.75);
    }
}
