package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by Fam on 10/26/2017.
 */

@Autonomous(name = "TestBot1", group = "Test")

public class AutonomousTest1 extends LinearOpMode

{
    DcMotor motor1;
    DcMotor motor2;
    ColorSensor sensorColor;
    DistanceSensor sensorDistance;

    @Override public void runOpMode()

    {


        boolean run = true;

        MotorControl ControlMotor1 = new MotorControl(-1.00);
        MotorControl ControlMotor2 = new MotorControl(1.00);


        while (run == true) {

            if (sensorDistance.getDistance(DistanceUnit.CM) > 15) {
                motor2.setPower(ControlMotor2.getControlledSpeed());
                motor1.setPower(ControlMotor1.getControlledSpeed());
                telemetry.addLine("IN if");
                telemetry.addLine(sensorDistance.getDistance(DistanceUnit.CM) + "Inches");
            }
            else if (sensorDistance.getDistance(DistanceUnit.CM) <= 15) {
                motor2.setPower(-ControlMotor2.getControlledSpeed());
                motor1.setPower(-ControlMotor1.getControlledSpeed());
                sleep(250);
                motor2.setPower(0);
                motor1.setPower(0);
                run = false ;
            }
            else {
                motor2.setPower(ControlMotor2.getControlledSpeed());
                motor1.setPower(ControlMotor1.getControlledSpeed());
            }

        }
    }
    }

