package com.ironcorerobotics.ControlClasses;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by FranklinFamily on 10/16/2018.
 */
@TeleOp (name = "Continuous Servo")
public class ContinuousServo extends OpMode {

    Servo servo1;

    public void init() {
        servo1 = hardwareMap.servo.get("servo1");
    }
    public void loop() {
        if(gamepad1.a) {
            servo1.setPosition(1.0);
        }
        else if(gamepad1.b) {
            servo1.setPosition(0);
        }
        else {
            servo1.setPosition(0.50);
        }
    }
}
