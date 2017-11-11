package com.ironcorerobotics.gls;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Fam on 11/4/2017.
 */
@TeleOp(name = "LiftSystem")
public class LiftSystem extends OpMode{

    int zeroPoint;


    Servo rightGrip;
    Servo leftGrip;
    DcMotor lift;

    @Override
    public void init() {
        rightGrip = hardwareMap.servo.get("right_grip");
        leftGrip = hardwareMap.servo.get("left_grip");

        rightGrip.setPosition(0.51);
        leftGrip.setPosition(0.7);

        lift = hardwareMap.dcMotor.get("LiftMotor");

        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        zeroPoint = lift.getCurrentPosition();
        lift.setPower(1);
    }

    @Override
    public void loop() {

        controlLift(gamepad1);

        telemetry.addData("Current position", lift.getCurrentPosition());

        controlGrip(gamepad1);

    }

    private void controlGrip(Gamepad gamepad) {
        if(gamepad.left_bumper) {
            rightGrip.setPosition(0.43);
            leftGrip.setPosition(0.83);
        }
        else if(gamepad.right_bumper){
            rightGrip.setPosition(0.35);
            leftGrip.setPosition(0.95);
        }
        else{
            rightGrip.setPosition(0.51);
            leftGrip.setPosition(0.7);
        }
    }

    private void controlLift(Gamepad gamepad) {
        if (gamepad.b && !lift.isBusy()) {
            lift.setTargetPosition(2700 + zeroPoint);
        } else if (gamepad.y && !lift.isBusy()) {
            lift.setTargetPosition(5400 + zeroPoint);
        } else if (gamepad.a && !lift.isBusy()) {
            lift.setTargetPosition(zeroPoint);
        }
    }
}
