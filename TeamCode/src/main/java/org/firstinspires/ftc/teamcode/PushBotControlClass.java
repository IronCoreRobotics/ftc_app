package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

/**
 * Created by Fam on 9/9/2017.
 */

@TeleOp(name = "PushBotTest")

public class PushBotControlClass extends OpMode

{
    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    ColorSensor sensorColor;
    DistanceSensor sensorDistance;
    MotorControl controlMotor1;

    public void init()

    {
        motor1 = hardwareMap.dcMotor.get("Rightside_Motor");
        motor2 = hardwareMap.dcMotor.get("Leftside_Motor");
        motor3 = hardwareMap.dcMotor.get("Arm_Motor");
        sensorColor = hardwareMap.get(ColorSensor.class, "sensor_color_distance");
        sensorDistance = hardwareMap.get(DistanceSensor.class, "sensor_color_distance");
        controlMotor1 = new MotorControl(1.00, gamepad1.left_bumper, gamepad1.right_bumper, 2, 10);
    }

    public void loop()

    {

        MotorControl ControlMotor2 = new MotorControl(-1.00, gamepad1.left_bumper, gamepad1.right_bumper, 2, 10);
        MotorControl ControlMotor3 = new MotorControl(1.00, gamepad1.left_bumper,gamepad1.right_bumper, 2, 10);

        if(gamepad1.left_stick_x == 1)

        {
            motor1.setPower(controlMotor1.getControlledSpeed());
            motor2.setPower(-ControlMotor2.getControlledSpeed());
            motor3.setPower(0);
            telemetry.addLine("Turning Right");
        }

        else if(gamepad1.left_stick_x == -1)

        {
            motor1.setPower(-controlMotor1.getControlledSpeed());
            motor2.setPower(ControlMotor2.getControlledSpeed());
            motor3.setPower(0);
            telemetry.addLine("Turning Left");
        }

        else if(gamepad1.dpad_up)

        {
            motor3.setPower(ControlMotor3.getControlledSpeed());
            telemetry.addLine("Arm Going Up");
        }

        else if(gamepad1.dpad_down)

        {
            motor3.setPower(-ControlMotor3.getControlledSpeed());
            telemetry.addLine("Arm Going Down");
        }

        else

        {
            motor1.setPower(gamepad1.right_stick_y*controlMotor1.getControlledSpeed());
            motor2.setPower(gamepad1.right_stick_y*ControlMotor2.getControlledSpeed());
            motor3.setPower(0);
        }
    }
}
