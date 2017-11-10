package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Fam on 10/31/2017.
 */

@Autonomous(name = "TestAutonomous", group = "Test")

public class TestProgramRunsAutonomous extends LinearOpMode

{
    Servo servo1;
    DcMotor motor1;
    DcMotor motor2;


    public void runOpMode() throws InterruptedException
    {
        motor1 = hardwareMap.dcMotor.get("rightside_Motor");
        motor2 = hardwareMap.dcMotor.get("leftside_Motor");
        servo1 = hardwareMap.servo.get("drawbridge_winch");

        oneEightyTurn(1);
    }

    public void oneEightyTurn(int TurnLength) throws InterruptedException {

        MotorControl ControlMotor1 = new MotorControl(-1.00, gamepad1.left_bumper, gamepad1.right_bumper, 2, 5);
        MotorControl ControlMotor2 = new MotorControl(1.00, gamepad1.left_bumper, gamepad1.right_bumper, 2, 5);

        int startPosition = motor1.getCurrentPosition();

        while(TurnLength > motor1.getCurrentPosition() - startPosition)

        {
            motor1.setPower(ControlMotor1.getControlledSpeed());
            motor2.setPower(-ControlMotor2.getControlledSpeed());
        }

        motor1.setTargetPosition(100);

    }

//    public void verticalDrive(int TravelLength, MotorControl ControlMotor1) throws InterruptedException
//
//    {
//        int startPosition = motor1.getCurrentPosition();
//
//        while(startPosition > motor1.getCurrentPosition() + TravelLength)
//
//        {
//            motor1.setPower(ControlMotor1.getControlledSpeed());
//            motor2.setPower(ControlMotor1.getControlledSpeed());
//        }
//    }
}




//
