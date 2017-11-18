package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Fam on 10/26/2017.
 */

@Autonomous(name = "Autonomous Test 1", group = "Test")

public class AutonomousTest1 extends LinearOpMode

{
    DcMotor motor1;
    DcMotor motor2;
    MotorControl controlMotor2 = new MotorControl(-1);

    @Override public void runOpMode() throws InterruptedException

    {
        motor1 = hardwareMap.dcMotor.get("Test_Motor_2");
        motor2 = hardwareMap.dcMotor.get("Test_Motor");


        waitForStart();

        autoDriveTwoMotors(1000, "180 Right", 1.00);

        autoDriveTwoMotors(0,"Break", 0);

        sleep(1000);

        autoDriveTwoMotors(1000, "180 Left", 1.00);

        autoDriveTwoMotors(0,"Break", 0);

        sleep(1000);

        autoDriveTwoMotors(0,"180", 1.00);

        autoDriveTwoMotors(0,"Break", 0);

        sleep(1000);
    }

    public void autoDriveTwoMotors(int distance, String direction, double speed)

    {
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        int startPosition = motor1.getCurrentPosition();

        if(direction == "Drive")
        {
            while(startPosition + distance > motor1.getCurrentPosition() && opModeIsActive())
            {
                motor1.setPower(speed);
                motor2.setPower(controlMotor2.getControlledSpeed()*speed);
            }
        }

        if(direction == "Reverse")
        {
            while(startPosition - distance < motor1.getCurrentPosition() && opModeIsActive())
            {
                motor1.setPower(-speed);
                motor2.setPower(-speed*controlMotor2.getControlledSpeed());
            }
        }
        if(direction == "180 Right")
        {
            while(startPosition - distance < motor1.getCurrentPosition() && opModeIsActive())
            {
                motor1.setPower(-speed);
                motor2.setPower(speed*controlMotor2.getControlledSpeed());
            }
        }
        if(direction == "180 Left")
        {
            while(startPosition + distance > motor1.getCurrentPosition() && opModeIsActive())
            {
                motor1.setPower(speed);
                motor2.setPower(-speed*controlMotor2.getControlledSpeed());
            }
        }
        if(direction == "180")
        {
            while(startPosition - 4000 < motor1.getCurrentPosition() && opModeIsActive())
            {
                motor1.setPower(-speed);
                motor2.setPower(speed*controlMotor2.getControlledSpeed());
            }
        }
        if(direction == "Break")
        {
            motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            motor1.setTargetPosition(motor1.getCurrentPosition());
            motor2.setTargetPosition(motor2.getCurrentPosition());
        }

    }
}

