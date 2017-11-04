package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Fam on 10/28/2017.
 */

@Autonomous(name = "MountBalance", group = "Test")

public class MountBalanceStone extends LinearOpMode{

    DcMotor motor1;
    DcMotor motor2;

    public void runOpMode(){

        motor1 = hardwareMap.dcMotor.get("Rightside_Motor");
        motor2 = hardwareMap.dcMotor.get("Leftside_Motor");

        MotorControl ControlMotor1 = new MotorControl(-1.00, gamepad1.left_bumper, gamepad1.right_bumper, 2, 10);
        MotorControl ControlMotor2 = new MotorControl(1.00, gamepad1.left_bumper, gamepad1.right_bumper, 2, 10);

        motor1.setPower(ControlMotor1.getControlledSpeed());
        motor2.setPower(ControlMotor2.getControlledSpeed());

        sleep(2000);

        motor1.setPower(0);
        motor2.setPower(0);
    }
}
