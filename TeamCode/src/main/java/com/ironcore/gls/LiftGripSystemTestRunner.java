package com.ironcore.gls;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by shawneverly on 11/4/17.
 */

@TeleOp(name = "GLSTest")
public class LiftGripSystemTestRunner extends OpMode {


    GripController gripController;
    LiftController liftController;

    @Override
    public void init() {
        gripController = new GripController(hardwareMap);
        liftController = new LiftController(hardwareMap);
    }

    @Override
    public void loop() {
        gripController.control(gamepad2);
        liftController.control(gamepad2);
    }
}
