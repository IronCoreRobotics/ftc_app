package com.ironcorerobotics.gls;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Fam on 11/4/2017.
 */
@TeleOp(name = "LiftSystem1")
@Disabled
public class LiftSystem extends OpMode{

    int zeroPoint;
    int gripperPosition;

    boolean wasPressed;

    Servo rightGrip;
    Servo leftGrip;
    DcMotor lift;

    @Override
    public void init() {
        initGLS();
    }

    private void initGLS() {
        rightGrip = hardwareMap.servo.get("right_grip");
        leftGrip = hardwareMap.servo.get("left_grip");

        rightGrip.setPosition(0.9);
        leftGrip.setPosition(0.4);

        lift = hardwareMap.dcMotor.get("LiftMotor");

        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        zeroPoint = lift.getCurrentPosition();
        lift.setPower(1);

        gripperPosition = 1;
        wasPressed = false;

        telemetry.addData("Right grip position",rightGrip.getPosition());
        telemetry.addData("Left grip position",leftGrip.getPosition());
    }

    @Override
    public void loop() {

        controlLift(gamepad1);

        telemetry.addData("Current position", lift.getCurrentPosition());

        controlGrip(gamepad1);

    }

    private void controlGrip(Gamepad gamepad) {
        if(gamepad.right_bumper){
            if(!wasPressed){
                wasPressed = true;
                gripperPosition += 1;
            }
        }
        else if(gamepad.left_bumper){
            if(!wasPressed){
                wasPressed = true;
                gripperPosition -= 1;
            }
        }
        else{
            wasPressed = false;
        }

        if(gripperPosition < 1){
            gripperPosition = 1;
        }
        else if(gripperPosition > 3){
            gripperPosition = 3;
        }

        telemetry.addData("Right grip position", rightGrip.getPosition());
        telemetry.addData("Left grip position", leftGrip.getPosition());

        if(gripperPosition == 2) {        //Slightly open
            rightGrip.setPosition(0.6);
            leftGrip.setPosition(0.666);
        }
        else if(gripperPosition == 3){    //Closed
            rightGrip.setPosition(0.23);
            leftGrip.setPosition(0.94);
        }
        else if(gripperPosition == 1){    //Open
            rightGrip.setPosition(0.9);
            leftGrip.setPosition(0.4);
        }
    }

    private void controlLift(Gamepad gamepad) {
        telemetry.addData("Lift Status ", lift.isBusy());
        if (gamepad.b && !lift.isBusy()) {
            telemetry.addData("Middle",lift.getCurrentPosition());
            lift.setTargetPosition(1290 + zeroPoint);
        } else if (gamepad.y && !lift.isBusy()) {
            telemetry.addData("Top", lift.getCurrentPosition());
            lift.setTargetPosition(2500 + zeroPoint);
        } else if (gamepad.a && !lift.isBusy()) {
            telemetry.addData("Bottom",lift.getCurrentPosition());
            lift.setTargetPosition(zeroPoint);
        }
    }
}
