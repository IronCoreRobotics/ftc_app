package com.ironcorerobotics.ControlClasses;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by Fam on 2/13/2018.
 */

@TeleOp(name = "REXIE TELEOP")
//@Disabled

public class LanderArm extends OpMode {

    // Declare OpMode members.
    DcMotor landerMotor;

    int zeroPoint;
    int landerMotorCurrentPosition;


    public void init()
    {
        landerMotor = hardwareMap.dcMotor.get("landerArm_motor");
    }

    public void loop ()
    {

    }

    private void controlLanderArm(Gamepad gamepad) {
        if (gamepad.b){ //&& !lift.isBusy()) {
            landerMotor.setTargetPosition(1385 + zeroPoint);
            landerMotorCurrentPosition = 1385 + zeroPoint;
            telemetry.addData("At position b and count = ",landerMotorCurrentPosition);
        } else if (gamepad.x){ //&& !lift.isBusy()) {
            landerMotor.setTargetPosition(landerMotorCurrentPosition + 20);
            landerMotorCurrentPosition = landerMotorCurrentPosition + 20;
            telemetry.addData("Added x and now at ",landerMotorCurrentPosition);
        }
        telemetry.update();
    }

}
