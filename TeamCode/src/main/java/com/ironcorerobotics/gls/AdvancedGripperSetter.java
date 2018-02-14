package com.ironcorerobotics.gls;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Fam on 2/10/2018.
 */

@TeleOp(name = "ADV Gripper Setter")

//@Disabled

public class AdvancedGripperSetter extends OpMode {

    Servo rightGrip2;
    Servo leftGrip2;
    Servo rightGrip1;
    Servo leftGrip1;
    int gripperset;

    @Override
    public void init() {

        rightGrip1 = hardwareMap.servo.get("Bottom_Servo_Right");
        leftGrip1 = hardwareMap.servo.get("Bottom_Servo_Left");

        rightGrip2 = hardwareMap.servo.get("Top_Servo_Right");
        leftGrip2 = hardwareMap.servo.get("Top_Servo_Left");
    }

    @Override
    public void loop() {

        if(gamepad1.dpad_down){
            gripperset = 1;
        } else if(gamepad1.dpad_up){
            gripperset = 2;
        } else if(gripperset == 1) {
            rightGrip1.setPosition(gamepad1.right_trigger);
            leftGrip1.setPosition(gamepad1.left_trigger);

            telemetry.addData("LeftGripLower: ", gamepad1.left_trigger);
            telemetry.addData("RightGripLower: ", gamepad1.right_trigger);
        } else if (gripperset == 2){
            rightGrip2.setPosition(gamepad1.right_trigger);
            leftGrip2.setPosition(gamepad1.left_trigger);

            telemetry.addData("LeftGripUpper: ", gamepad1.left_trigger);
            telemetry.addData("RightGripUpper: ", gamepad1.right_trigger);
        }


    }
}
