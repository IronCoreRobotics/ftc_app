package com.ironcorerobotics.gls;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Fam on 2/13/2018.
 */

@TeleOp(name = "AdvancedGLSPositionTester")
//@Disabled//Comment this out when you copy this into your code

public class AdvGLSPositionTester extends OpMode

{
    //Declare any global variables here
    //Build any objects like DC motors and such here
//    int zeroPoint;
    int gripperPosition;
    int referenceGripper;
    int liftCurrentPosition;
    boolean wasPressed;
    Servo rightGrip2;
    Servo leftGrip2;
    Servo rightGrip1;
    Servo leftGrip1;
    DcMotor lift;

    public void init()

    {
        //Put your initialization code here
        //Hardware map everything
        initGLS();
    }

    public void loop()

    {
        //Put your main code here
        controlGrip(gamepad2);
    }

    private void initGLS() {
        rightGrip1 = hardwareMap.servo.get("Bottom_Servo_Right");
        leftGrip1 = hardwareMap.servo.get("Bottom_Servo_Left");

        rightGrip2 = hardwareMap.servo.get("Top_Servo_Right");
        leftGrip2 = hardwareMap.servo.get("Top_Servo_Left");
    }

    private void controlGrip(Gamepad gamepad) {
        if (gamepad.left_bumper) {
            if (!wasPressed) {
                wasPressed = true;
                gripperPosition += 1;
            }
        } else if (gamepad.right_bumper) {
            if (!wasPressed) {
                wasPressed = true;
                gripperPosition -= 1;
            }
        } else {wasPressed = false;}

        if (gripperPosition < 1) {
            gripperPosition = 1;
        } else if (gripperPosition > 3) {
            gripperPosition = 3;
        }

        if (gripperPosition == 2) {//Slightly open
            leftGrip1.setPosition(0.30);
            rightGrip1.setPosition(0.4);
            telemetry.addData("Right grip1 position", rightGrip1.getPosition());
            telemetry.addData("Left grip1 position", leftGrip1.getPosition());
            leftGrip2.setPosition(0.48);
            rightGrip2.setPosition(0.39);
            telemetry.addData("Right grip2 position", rightGrip2.getPosition());
            telemetry.addData("Left grip2 position", leftGrip2.getPosition());
        } else if (gripperPosition == 3) {    //Closed
            leftGrip1.setPosition(0.05);
            rightGrip1.setPosition(.64);
            telemetry.addData("Right grip1 position", rightGrip1.getPosition());
            telemetry.addData("Left grip1 position", leftGrip1.getPosition());
            leftGrip2.setPosition(0.26);
            rightGrip2.setPosition(.62);
            telemetry.addData("Right grip2 position", rightGrip2.getPosition());
            telemetry.addData("Left grip2 position", leftGrip2.getPosition());
        } else if (gripperPosition == 1) {    //Open
            leftGrip1.setPosition(0.423);
            rightGrip1.setPosition(0.313);
            telemetry.addData("Right grip1 position", rightGrip1.getPosition());
            telemetry.addData("Left grip1 position", leftGrip1.getPosition());
            leftGrip2.setPosition(0.61);
            rightGrip2.setPosition(0.29);
            telemetry.addData("Right grip2 position", rightGrip2.getPosition());
            telemetry.addData("Left grip2 position", leftGrip2.getPosition());
        }
    }
}
