package com.ironcorerobotics.gls;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Fam on 11/4/2017.
 */
@TeleOp(name = "LiftSetter")
@Disabled
public class LiftSetter extends OpMode{


    DcMotor lift;

    @Override
    public void init() {
        initGLS();
    }

    private void initGLS() {



        lift = hardwareMap.dcMotor.get("LiftMotor");




    }

    @Override
    public void loop() {
        lift.setPower(-gamepad1.right_stick_y*0.5);

        telemetry.addData("Current position", lift.getCurrentPosition());

     }


}
