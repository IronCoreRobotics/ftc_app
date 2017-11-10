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


    Servo servo1;
    Servo servo2;
    DcMotor lift;

    @Override
    public void init() {
        servo1 = hardwareMap.servo.get("Servo1");
        servo2 = hardwareMap.servo.get("Servo2");

        servo1.setPosition(0.51);
        servo2.setPosition(0.7);

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
            servo1.setPosition(0.43);
            servo2.setPosition(0.83);
        }
        else if(gamepad.right_bumper){
            servo1.setPosition(0.35);
            servo2.setPosition(0.95);
        }
        else{
            servo1.setPosition(0.51);
            servo2.setPosition(0.7);
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
