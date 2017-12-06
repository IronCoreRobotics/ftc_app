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

        initGripper();

        leftGrip.setPosition(0.30);
        rightGrip.setPosition(.95);

        jewelSlapper.setPosition(.54);

        sleep(1000);

        initLifter();

        lift.setTargetPosition(1350 + zeroPoint);

        sleep(1000);

        autoDrive(50, "Drive", .10);

        brake();

        sleep(1000);

        jewelSlapper.setPosition(0);

        sleep(1000);

        JewelScoreAutonomous("Red", 200);

        sleep(2000);

        //line up to cryptobox

        autoDrive(3090, "Reverse", .50);

        brake();

        //center column

        autoDrive(1185, "Left", .50);

        brake();

        lift.setTargetPosition(50+zeroPoint);

        sleep(1000);

        autoDrive(500, "Drive", .30);

        brake();

        //special assurance

        leftGrip.setPosition(0.45);
        rightGrip.setPosition(0.8);

        sleep(500);

        autoDrive(750, "Reverse", .30);

        brake();

        leftGrip.setPosition(0.30);
        rightGrip.setPosition(.95);

        sleep(1000);

        autoDrive(400, "Drive", .10);

        autoDrive(150, "Drive", .75);

        brake();

        autoDrive(100, "Reverse", .30);

        brake();

        sleep(1000);
    }

    public void JewelScoreAutonomous(String AllianceColor, int knockOffDistance)

    {

        if (AllianceColor == "Blue")

        {

            if (sensorColor.red() < sensorColor.blue() && opModeIsActive()) {
                autoDrive(knockOffDistance, "Reverse", .20);

                brake();

                jewelSlapper.setPosition(.54);

                autoDrive(knockOffDistance-50, "Drive", .20);

                brake();
            }

            if (sensorColor.red() > sensorColor.blue() && opModeIsActive()) {
                autoDrive(knockOffDistance, "Drive", .20);

                brake();

                jewelSlapper.setPosition(.54);

                autoDrive(knockOffDistance+85, "Reverse", .20);

                brake();
            }
        }

        if (AllianceColor == "Red")

        {

            if (sensorColor.red() < sensorColor.blue() && opModeIsActive()) {
                    autoDrive(knockOffDistance, "Drive", .20);

                    brake();

                   jewelSlapper.setPosition(.54);

                    autoDrive(knockOffDistance+85, "Reverse", .20);

                brake();
                }

            if (sensorColor.red() > sensorColor.blue() && opModeIsActive()) {
                autoDrive(knockOffDistance, "Reverse", .20);

                brake();

                jewelSlapper.setPosition(.54);

                autoDrive(knockOffDistance-50, "Drive", .20);

                brake();
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

    private void initGripper() {
        rightGrip = hardwareMap.servo.get("right_grip");
        leftGrip = hardwareMap.servo.get("left_grip");
    }

    private void initLifter()
    {
        lift = hardwareMap.dcMotor.get("LiftMotor");

        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        zeroPoint = lift.getCurrentPosition();
        lift.setPower(0.50);
    }
}
