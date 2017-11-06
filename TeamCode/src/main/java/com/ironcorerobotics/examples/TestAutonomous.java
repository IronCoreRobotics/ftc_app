package com.ironcorerobotics.examples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by shawneverly on 8/19/17.
 */
@Autonomous(name = "TestAutonomous")

public class TestAutonomous extends LinearOpMode{

    DcMotor leftSide;
    DcMotor rightSide;

    public void runOpMode() throws InterruptedException {

        leftSide = hardwareMap.dcMotor.get("left_drive");
        rightSide = hardwareMap.dcMotor.get("right_drive");

        rightSide.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        int startPosition = leftSide.getCurrentPosition();
        int currentPosition = leftSide.getCurrentPosition();

        leftSide.setPower(0.4);
        rightSide.setPower(0.4);

        sleep(1000);
        while((currentPosition - startPosition) < 1120) {
            currentPosition = leftSide.getCurrentPosition();
        }

        leftSide.setPower(0);
        rightSide.setPower(0);
    }

}
