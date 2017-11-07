package com.ironcorerobotics.gls;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by shawneverly on 11/4/17.
 */

public class LiftController {

    long loc;
    DcMotor lift;

    public LiftController(HardwareMap hardwareMap) {
        lift = hardwareMap.dcMotor.get("LiftMotor");
        loc = 0;
    }

    public void control(Gamepad gamepad) {
        lift.setPower(gamepad.left_stick_y);
        loc = lift.getCurrentPosition();
//        telemetry.addData("motor: ", loc);
    }
}
