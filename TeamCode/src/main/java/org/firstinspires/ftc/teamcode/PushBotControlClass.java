package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcontroller.external.samples.ConceptCompassCalibration;
import org.firstinspires.ftc.robotcontroller.external.samples.SensorColor;
import org.firstinspires.ftc.robotcontroller.external.samples.SensorREVColorDistance;

/**
 * Created by Fam on 9/9/2017.
 */

@TeleOp(name = "PushBotTest")

public class PushBotControlClass extends OpMode

{
    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;


    public void init()

    {
        motor1 = hardwareMap.dcMotor.get("Rightside_Motor");
        motor2 = hardwareMap.dcMotor.get("Leftside_Motor");
        motor3 = hardwareMap.dcMotor.get("Arm_Motor");
    }

    public void loop()

    {
       MotorControl ControlMotor1 = new MotorControl(1.00, gamepad1.left_bumper, gamepad1.right_bumper);
        MotorControl ControlMotor2 = new MotorControl(-1.00, gamepad1.left_bumper, gamepad1.right_bumper);
        MotorControl ControlMotor3 = new MotorControl(1.00, gamepad1.left_bumper,gamepad1.right_bumper);

        if(gamepad1.left_stick_x == 1)
        {
            motor1.setPower(ControlMotor1.ControlMotorSpeed());
            motor2.setPower(-ControlMotor2.ControlMotorSpeed());
            motor3.setPower(0);
            telemetry.addLine("Turn right" + motor2.getPower());
        }

        else if(gamepad1.left_stick_x == -1)
        {
            motor1.setPower(-ControlMotor1.ControlMotorSpeed());
            motor2.setPower(ControlMotor2.ControlMotorSpeed());
            motor3.setPower(0);
            telemetry.addLine("Turn left" + motor2.getPower());
        }

        else if(gamepad1.dpad_up == true)
        {
            motor3.setPower(ControlMotor3.ControlMotorSpeed());
            telemetry.addLine("Arm go up" + motor2.getPower());
        }

        else if(gamepad1.dpad_down == true)
        {
            motor3.setPower(-ControlMotor3.ControlMotorSpeed());
            telemetry.addLine("Arm go down" + motor2.getPower());
        }

        else
        {
            motor1.setPower(gamepad1.right_stick_y*ControlMotor1.ControlMotorSpeed());
            motor2.setPower(gamepad1.right_stick_y*ControlMotor2.ControlMotorSpeed());
            motor3.setPower(0);
            telemetry.addLine("Movement given to the Y " + motor2.getPower() + ControlMotor1.getPublicOutsideSourceSettingSpeed());
        }
    }

    private void turnRight() {
        motor3.setPower(0);
        if (gamepad1.left_bumper && gamepad1.right_bumper) {
            telemetry.addLine("Turn Right Slow");
            motor2.setPower(.10);
            motor1.setPower(.10);
        } else if (gamepad1.left_bumper) {
            telemetry.addLine("Turn Right Medium");
            motor2.setPower(.50);
            motor1.setPower(.50);
        } else {
            telemetry.addLine("Turn Right Turbo");
            motor2.setPower(1.00);
            motor1.setPower(1.00);
        }

    }

        private void turnLeft() {

        }

}
