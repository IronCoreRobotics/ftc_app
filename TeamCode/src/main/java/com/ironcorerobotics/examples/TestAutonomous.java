package com.ironcorerobotics.examples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by shawneverly on 8/19/17.
 */
@Autonomous(name = "Test Motor Count")
//@Disabled
public class TestAutonomous extends LinearOpMode{

    DcMotor motor1;

    public void runOpMode() throws InterruptedException {

        motor1 = hardwareMap.dcMotor.get("Test_Motor_2");

        waitForStart();

        while(opModeIsActive())
        {
            motor1.setPower(-1.00);

            telemetry.addData("Value", motor1.getCurrentPosition());

            telemetry.update();
        }
//        leftSide = hardwareMap.dcMotor.get("left_drive");
//        rightSide = hardwareMap.dcMotor.get("right_drive");
//
//        rightSide.setDirection(DcMotorSimple.Direction.REVERSE);
//
//        waitForStart();
//
//        int startPosition = leftSide.getCurrentPosition();
//        int currentPosition = leftSide.getCurrentPosition();
//
//        leftSide.setPower(0.4);
//        rightSide.setPower(0.4);
//
//        sleep(1000);
//        while((currentPosition - startPosition) < 1120) {
//            currentPosition = leftSide.getCurrentPosition();
//        }
//
//        leftSide.setPower(0);
//        rightSide.setPower(0);
    }

}
