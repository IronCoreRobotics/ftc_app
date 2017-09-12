package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Fam on 9/9/2017.
 */


@TeleOp(name = "PushBotTest")
public class PushBotControlClass extends OpMode

{


    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    Servo servo1;
    Servo servo2;


    public void init()

    {
        motor1 = hardwareMap.dcMotor.get("Rightside_Motor");
        motor2 = hardwareMap.dcMotor.get("Leftside_Motor");
        motor3 = hardwareMap.dcMotor.get("Arm_Motor");

    }


    public void loop()

    {

        if(gamepad1.left_stick_x == 1)

        {
            motor2.setPower(100);
            motor1.setPower(100);
            motor3.setPower(0);
        }

        else if(gamepad1.left_stick_x == -1)

        {
            motor1.setPower(-100);
            motor2.setPower(-100);
            motor3.setPower(0);
        }

        else if(gamepad1.dpad_up == true)
        {
            motor3.setPower(100);
        }

        else if(gamepad1.dpad_down == true)
        {
            motor3.setPower(-100);
        }

        else

        {
            motor1.setPower(gamepad1.right_stick_y);
            motor2.setPower(-gamepad1.right_stick_y);
            motor3.setPower(0);
        }


    }

}
