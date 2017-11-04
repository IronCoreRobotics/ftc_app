package com.ironcore.gls;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by shawneverly on 11/4/17.
 */

public class GripController {

    Servo rightGrip;
    Servo leftGrip;

    GripController(HardwareMap hardwareMap) {
        this.rightGrip = hardwareMap.servo.get("right_grip");
        this.leftGrip = hardwareMap.servo.get("left_grip");

        rightGrip.setPosition(0.51);
        leftGrip.setPosition(0.7);
    }

    public void control(Gamepad gamepad2) {

        if(gamepad2.left_bumper){
            rightGrip.setPosition(0.43);
            leftGrip.setPosition(0.83);
        }
        else if(gamepad2.right_bumper){
            rightGrip.setPosition(0.35);
            leftGrip.setPosition(0.95);
        }
        else{
            rightGrip.setPosition(0.51);
            leftGrip.setPosition(0.7);
        }
    }
}
