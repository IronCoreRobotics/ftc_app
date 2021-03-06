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

    MotorControl ControlMotor1 = new MotorControl(-1.00, 2, 5);
    MotorControl ControlMotor2 = new MotorControl(1.00, 2, 5);


        Servo rightGrip;
        Servo leftGrip;
        DcMotor lift;
     Servo jewelSlapper;


        DcMotor motor1;
        DcMotor motor2;


        public void init()

        {
            initGLS();
            motor1 = hardwareMap.dcMotor.get("rightside_Motor");
            motor2 = hardwareMap.dcMotor.get("leftside_Motor");
            jewelSlapper = hardwareMap.servo.get("drawbridge_winch");

        }

        public void loop()

        {
            jewelSlapper.setPosition(.9);
            drive();
            controlLift(gamepad2);
            controlGrip(gamepad2);
        }

        public void drive() {

            if(gamepad1.y) {
                ControlMotor1.setThirdButton(true);
                ControlMotor2.setThirdButton(true);
                ControlMotor1.setSecondButton(false);
                ControlMotor2.setSecondButton(false);
//                ControlMotor1.setFirstButton(false);
//                ControlMotor2.setFirstButton(false);
            } else if(gamepad1.right_bumper) {
                ControlMotor1.setThirdButton(false);
                ControlMotor2.setThirdButton(false);
                ControlMotor1.setSecondButton(true);
                ControlMotor2.setSecondButton(true);
//                ControlMotor1.setFirstButton(false);
//                ControlMotor2.setFirstButton(false);
            }else if (gamepad1.left_bumper) {
                ControlMotor1.setThirdButton(false);
                ControlMotor2.setThirdButton(false);
                ControlMotor1.setSecondButton(false);
                ControlMotor2.setSecondButton(false);
//                ControlMotor1.setFirstButton(true);
//                ControlMotor2.setFirstButton(true);
            }
            if (gamepad1.left_stick_x == 1) {
                motor1.setPower(ControlMotor1.getControlledSpeed());
                motor2.setPower(-ControlMotor2.getControlledSpeed());
            } else if (gamepad1.left_stick_x == -1) {
                motor1.setPower(-ControlMotor1.getControlledSpeed());
                motor2.setPower(ControlMotor2.getControlledSpeed());
            }
            else if (gamepad1.right_stick_y == 1) {
                motor1.setPower(ControlMotor1.getControlledSpeed());
                motor2.setPower(ControlMotor2.getControlledSpeed());
            } else if (gamepad1.right_stick_y == -1) {
                motor1.setPower(-ControlMotor1.getControlledSpeed());
                motor2.setPower(-ControlMotor2.getControlledSpeed());
            } else if(gamepad1.left_trigger == 1.00 && gamepad1.right_trigger == 1.00)
            {
                motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }
            else {
                motor1.setPower(0);
                motor2.setPower(0);
            }
        }

        private void initGLS() {
            rightGrip = hardwareMap.servo.get("right_grip");
            leftGrip = hardwareMap.servo.get("left_grip");

            lift = hardwareMap.dcMotor.get("LiftMotor");

            lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            zeroPoint = lift.getCurrentPosition();
            lift.setPower(1.00);
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
                leftGrip.setPosition(0.53);
                rightGrip.setPosition(0.73);
                telemetry.addData("Right grip position", rightGrip.getPosition());
                telemetry.addData("Left grip position", leftGrip.getPosition());
            }
        }

        private void controlLift(Gamepad gamepad) {
            if (gamepad.b && !lift.isBusy()) {
                lift.setTargetPosition(1385 + zeroPoint);
            } else if (gamepad.y && !lift.isBusy()) {
                lift.setTargetPosition(2580 + zeroPoint);
            } else if (gamepad.a && !lift.isBusy()) {
                lift.setTargetPosition(zeroPoint);
            }
        }

    public void autoDrive(int distance, String direction, double speed)

    {
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        int startPosition = motor1.getCurrentPosition();

        if(direction == "Drive")
        {
            while(startPosition + distance > motor1.getCurrentPosition())
            {
                motor1.setPower(speed);
                motor2.setPower(-1*speed);
            }
        }

        if(direction == "Reverse")
        {
            while(startPosition - distance < motor1.getCurrentPosition())
            {
                motor1.setPower(-speed);
                motor2.setPower(-speed*-1);
            }
        }
        if(direction == "Right")
        {
            while(startPosition - distance < motor1.getCurrentPosition() )
            {
                motor1.setPower(-speed);
                motor2.setPower(speed*-1);
            }
        }
        if(direction == "Left")
        {
            while(startPosition + distance > motor1.getCurrentPosition())
            {
                motor1.setPower(speed);
                motor2.setPower(-speed*-1);
            }
        }
    }

    }




