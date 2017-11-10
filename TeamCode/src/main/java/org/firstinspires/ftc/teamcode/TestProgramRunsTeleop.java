package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Fam on 10/31/2017.
 */


    @TeleOp(name = "TestTeleop")



    public class TestProgramRunsTeleop extends OpMode

{
    long loc;

    DcMotor lift;

    Servo servo1;
    Servo servo2;

    DcMotor motor1;
    DcMotor motor2;



        public void init()

        {
            lift = hardwareMap.dcMotor.get("G_S_L");
            loc = 0;
            servo1 = hardwareMap.servo.get("right_Arm");
            servo2 = hardwareMap.servo.get("left_Arm");

            servo1.setPosition(0.51);
            servo2.setPosition(0.7);
            motor1 = hardwareMap.dcMotor.get("rightside_Motor");
            motor2 = hardwareMap.dcMotor.get("leftside_Motor");
        }

        public void loop()

        {
            lift.setPower(gamepad2.left_stick_y);
            loc = lift.getCurrentPosition();

            if(gamepad2.left_trigger > .50){
                servo1.setPosition(0.43);
                servo2.setPosition(0.83);
                drive();
            }
            else if(gamepad2.right_trigger > .50){
                servo1.setPosition(0.35);
                servo2.setPosition(0.95);
                drive();
            }
            else{
                servo1.setPosition(0.51);
                servo2.setPosition(0.7);
                drive();
            }


        }

    public void drive()
    {
        MotorControl ControlMotor1 = new MotorControl(-1.00, gamepad1.left_bumper, gamepad1.right_bumper, 2, 5);
        MotorControl ControlMotor2 = new MotorControl(1.00, gamepad1.left_bumper, gamepad1.right_bumper, 2, 5);

        if(gamepad1.left_stick_x == 1)

        {
            motor1.setPower(ControlMotor1.getControlledSpeed());
            motor2.setPower(-ControlMotor2.getControlledSpeed());
        }

        else if(gamepad1.left_stick_x == -1)

        {
            motor1.setPower(-ControlMotor1.getControlledSpeed());
            motor2.setPower(ControlMotor2.getControlledSpeed());
        }

        else if (gamepad1.right_stick_y == 1)

        {
            motor1.setPower(ControlMotor1.getControlledSpeed());
            motor2.setPower(ControlMotor2.getControlledSpeed());
        }
        else if (gamepad1.right_stick_y == -1)
        {
            motor1.setPower(-ControlMotor1.getControlledSpeed());
            motor2.setPower(-ControlMotor2.getControlledSpeed());
        }
        else
        {
            motor1.setPower(0);
            motor2.setPower(0);
        }
    }


}
