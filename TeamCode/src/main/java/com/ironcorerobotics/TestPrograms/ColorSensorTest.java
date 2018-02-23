package com.ironcorerobotics.TestPrograms;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by Fam on 10/28/2017.
 */

@Autonomous(name = "ColorSensorTest", group = "Test")
@Disabled

public class ColorSensorTest extends LinearOpMode{

    DcMotor motor1;
    DcMotor motor2;
    ColorSensor sensorColor;
    DistanceSensor sensorDistance;

    public void runOpMode(){

        motor1 = hardwareMap.dcMotor.get("Rightside_Motor");
        motor2 = hardwareMap.dcMotor.get("Leftside_Motor");
        sensorColor = hardwareMap.get(ColorSensor.class, "sensor_color_distance");
        sensorDistance = hardwareMap.get(DistanceSensor.class, "sensor_color_distance");

        boolean run = true;

//        MotorControl ControlMotor1 = new MotorControl(-1.00, gamepad1.left_bumper, gamepad1.right_bumper, 2, 10);
//        MotorControl ControlMotor2 = new MotorControl(1.00, gamepad1.left_bumper, gamepad1.right_bumper, 2, 10);

        while(sensorDistance.getDistance(DistanceUnit.INCH) > 0 && sensorDistance.getDistance(DistanceUnit.INCH) < 5)
        {
            if (sensorColor.red() > sensorColor.blue()) {
                telemetry.addData("RED", sensorColor.red());
            }

            if (sensorColor.red() < sensorColor.blue()) {
                telemetry.addData("BLUE", sensorColor.blue());
            }
            telemetry.update();
        }
    }
}
