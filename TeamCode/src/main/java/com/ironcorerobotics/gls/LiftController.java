package com.ironcorerobotics.gls;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by shawneverly on 11/4/17.
 */

public class LiftController {

    long currentPosition;
    long startPosition;
    DcMotor lift;

    public LiftController(HardwareMap hardwareMap) {
        lift = hardwareMap.dcMotor.get("LiftMotor");
        startPosition = lift.getCurrentPosition();
        currentPosition = lift.getCurrentPosition();
    }

    public void control(Gamepad gamepad) {

        if (gamepad.b) {

        }
        lift.setPower(gamepad.left_stick_y);
        currentPosition = lift.getCurrentPosition();
//        telemetry.addData("motor: ", loc);
    }
}
