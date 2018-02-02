package com.ironcorerobotics.ControlClasses;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Fam on 12/2/2017.
 */

@TeleOp(name = "JewelSetter")
//@Disabled

public class JewelSlapperSetter extends OpMode {

    Servo servo1;

    @Override
    public void init() {

        servo1 = hardwareMap.servo.get("drawbridge_winch");
    }

    @Override
    public void loop() {

        servo1.setPosition(gamepad1.left_trigger);

        telemetry.addData("servo1: ", gamepad1.left_trigger);

    }
}

