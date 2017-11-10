package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Fam on 11/7/2017.
 */
@Autonomous(name = "JewelArm", group = "Test")

public class JewelArm extends LinearOpMode

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
        sensorColor = hardwareMap.get(ColorSensor.class, "sensor_color_distance");
        sensorDistance = hardwareMap.get(DistanceSensor.class, "sensor_color_distance");
        servo1 = hardwareMap.servo.get("drawbridge_winch");
        MotorControl ControlMotor1 = new MotorControl(.20, gamepad1.left_bumper, gamepad1.right_bumper, 2, 5);
        MotorControl ControlMotor2 = new MotorControl(-.20, gamepad1.left_bumper, gamepad1.right_bumper, 2, 5);

        servo1.setPosition(0);

        sleep(1000);

        if (sensorColor.red() > sensorColor.blue()) {
            motor1.setPower(-ControlMotor1.getControlledSpeed());
            motor2.setPower(-ControlMotor2.getControlledSpeed());
        }

        if (sensorColor.red() < sensorColor.blue()) {
            motor1.setPower(ControlMotor1.getControlledSpeed());
            motor2.setPower(ControlMotor2.getControlledSpeed());
        }

        sleep(500);

        motor1.setPower(0);
        motor2.setPower(0);

        servo1.setPosition(.7);

        sleep(1000);


    }

}
