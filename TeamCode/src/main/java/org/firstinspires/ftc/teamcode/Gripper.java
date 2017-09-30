package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by kidsaccount on 9/30/17.
 */

@TeleOp(name = "Gripper")
public class Gripper extends OpMode {

    Servo servo1;
    Servo servo2;

    @Override
    public void init() {

        servo1 = hardwareMap.servo.get("Servo1");
        servo2 = hardwareMap.servo.get("Servo2");

        servo1.setPosition(0.565);
        servo2.setPosition(0.725);
    }

    @Override
    public void loop() {

        if(gamepad1.right_bumper){
            servo1.setPosition(0.565);
            servo2.setPosition(0.725);
        }
        else{
            servo1.setPosition(0.702);
            servo2.setPosition(0.616);
        }

        telemetry.addData("servo1: ", gamepad1.left_trigger);
        telemetry.addData("servo2: ", gamepad1.right_trigger);

    }
}
