package com.ironcorerobotics.GameDayOpModes;

import com.ironcorerobotics.ControlClasses.MotorControl;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Fam on 11/27/2017.
 */


@TeleOp(name = "TeleopFranklins")
//@Disabled

public class TeleopFantasticFranklins extends OpMode {


        int zeroPoint;
        int gripperPosition;

        boolean wasPressed;


        Servo rightGrip;
        Servo leftGrip;
        DcMotor lift;

        DcMotor motor1;
        DcMotor motor2;



        public void init()

        {
            initGLS();
            motor1 = hardwareMap.dcMotor.get("rightside_Motor");
            motor2 = hardwareMap.dcMotor.get("leftside_Motor");
        }

        public void loop()

        {
            drive();
            controlLift(gamepad2);
            controlGrip(gamepad2);
        }

        public void drive()
        {
            MotorControl ControlMotor1 = new MotorControl(-1.00, gamepad1.left_bumper, gamepad1.right_bumper, 2, 5);
            MotorControl ControlMotor2 = new MotorControl(1.00, gamepad1.left_bumper, gamepad1.right_bumper, 2, 5);

            if(gamepad1.left_stick_x == 1)

            {
                motor1.setPower(ControlMotor1.getControlledSpeed());
                motor2.setPower(-ControlMotor2.getControlledSpeed());
            }


            else if(gamepad1.left_stick_x == -1)

            {
                motor1.setPower(-ControlMotor1.getControlledSpeed());
                motor2.setPower(ControlMotor2.getControlledSpeed());
            }

            else if (gamepad1.right_stick_y == 1)

            {
                motor1.setPower(ControlMotor1.getControlledSpeed());
                motor2.setPower(ControlMotor2.getControlledSpeed());
            }
            else if (gamepad1.right_stick_y == -1)
            {
                motor1.setPower(-ControlMotor1.getControlledSpeed());
                motor2.setPower(-ControlMotor2.getControlledSpeed());
            }
            else
            {
                motor1.setPower(0);
                motor2.setPower(0);
            }
        }
        private void initGLS() {
            rightGrip = hardwareMap.servo.get("right_grip");
            leftGrip = hardwareMap.servo.get("left_grip");

//            rightGrip.setPosition(0.9);
//            leftGrip.setPosition(0.4);

            lift = hardwareMap.dcMotor.get("LiftMotor");

            lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            zeroPoint = lift.getCurrentPosition();
            lift.setPower(0.75);
        }


        private void controlGrip(Gamepad gamepad) {
            if(gamepad.left_bumper){
                if(!wasPressed){
                    wasPressed = true;
                    gripperPosition += 1;
                }
            }
            else if(gamepad.right_bumper){
                if(!wasPressed){
                    wasPressed = true;
                    gripperPosition -= 1;
                }
            }
            else{
                wasPressed = false;
            }

            if(gripperPosition < 1){
                gripperPosition = 1;
            }
            else if(gripperPosition > 3){
                gripperPosition = 3;
            }


            if(gripperPosition == 2) {        //Slightly open
                leftGrip.setPosition(0.45);
                rightGrip.setPosition(0.8);
                telemetry.addData("Right grip position", rightGrip.getPosition());
                telemetry.addData("Left grip position", leftGrip.getPosition());
            }
            else if(gripperPosition == 3){    //Closed
                leftGrip.setPosition(0.30);
                rightGrip.setPosition(.95);
                telemetry.addData("Right grip position", rightGrip.getPosition());
                telemetry.addData("Left grip position", leftGrip.getPosition());

            }
            else if(gripperPosition == 1){    //Open
                leftGrip.setPosition(0.6);
                rightGrip.setPosition(0.65);
                telemetry.addData("Right grip position", rightGrip.getPosition());
                telemetry.addData("Left grip position", leftGrip.getPosition());
            }
        }

        private void controlLift(Gamepad gamepad) {
            if (gamepad.b && !lift.isBusy()) {
                lift.setTargetPosition(1350 + zeroPoint);
            } else if (gamepad.y && !lift.isBusy()) {
                lift.setTargetPosition(2550 + zeroPoint);
            } else if (gamepad.a && !lift.isBusy()) {
                lift.setTargetPosition(zeroPoint);
            }
        }

    }




