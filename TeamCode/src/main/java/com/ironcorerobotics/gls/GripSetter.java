package com.ironcorerobotics.gls;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by kidsaccount on 10/25/17.
 */

@TeleOp(name = "GripSetterNew")

@Disabled

public class GripSetter extends OpMode {

    Servo servo1;

    Servo servo2;

    @Override
    public void init() {

        servo1 = hardwareMap.servo.get("left_grip");
        servo2 = hardwareMap.servo.get("right_grip");


    }

    @Override
        public void loop() {

        servo1.setPosition(gamepad1.left_trigger);
        servo2.setPosition(gamepad1.right_trigger);

        telemetry.addData("servo1: ", gamepad1.left_trigger);
        telemetry.addData("servo2: ", gamepad1.right_trigger);

    }
}
