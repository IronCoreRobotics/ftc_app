package com.ironcorerobotics.GameDayOpModes;

import com.ironcorerobotics.ControlClasses.MotorControl;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Fam on 2/13/2018.
 */

@TeleOp(name = "REXIE TELEOP")
//@Disabled

public class StateBoundREXIE_TELEOP extends OpMode {


    int zeroPoint;
    int gripperPosition;

    boolean wasPressed;

    MotorControl ControlMotor1 = new MotorControl(-1.00, 2, 5);
    MotorControl ControlMotor2 = new MotorControl(1.00, 2, 5);


    Servo rightGrip2;
    Servo leftGrip2;
    Servo rightGrip1;
    Servo leftGrip1;
    DcMotorEx lift;
    Servo jewelSlapper;
    int referenceGripper;
    int liftCurrentPosition;



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
            //ControlMotor1.setFirstButton(false);
            //ControlMotor2.setFirstButton(false);
        } else if(gamepad1.right_bumper) {
            ControlMotor1.setThirdButton(false);
            ControlMotor2.setThirdButton(false);
            ControlMotor1.setSecondButton(true);
            ControlMotor2.setSecondButton(true);
            //ControlMotor1.setFirstButton(false);
            //ControlMotor2.setFirstButton(false);
        }else if (gamepad1.left_bumper) {
            ControlMotor1.setThirdButton(false);
            ControlMotor2.setThirdButton(false);
            ControlMotor1.setSecondButton(false);
            ControlMotor2.setSecondButton(false);
            //ControlMotor1.setFirstButton(true);
            //ControlMotor2.setFirstButton(true);
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
        rightGrip1 = hardwareMap.servo.get("Bottom_Servo_Right");
        leftGrip1 = hardwareMap.servo.get("Bottom_Servo_Left");

        rightGrip2 = hardwareMap.servo.get("Top_Servo_Right");
        leftGrip2 = hardwareMap.servo.get("Top_Servo_Left");

        //lift = hardwareMap.dcMotorEx.get("LiftMotor");

        lift = (DcMotorEx)hardwareMap.get(DcMotor.class, "LiftMotor");

        PIDCoefficients pidOrig = lift.getPIDCoefficients(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("PID", "p=" + pidOrig.p);
        telemetry.addData("PID", "i=" + pidOrig.i);
        telemetry.addData("PID", "d=" + pidOrig.d);

        PIDCoefficients pidNew = new PIDCoefficients(8.0, 0.05, 0.0);

        lift.setPIDCoefficients(DcMotor.RunMode.RUN_TO_POSITION, pidNew);

        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        zeroPoint = lift.getCurrentPosition();
        lift.setPower(1.00);
    }


    private void controlGrip(Gamepad gamepad) {
        if (gamepad.left_bumper) {
            if (!wasPressed) {
                wasPressed = true;
                gripperPosition += 1;
            }
        } else if (gamepad.right_bumper) {
            if (!wasPressed) {
                wasPressed = true;
                gripperPosition -= 1;
            }
        } else {
            wasPressed = false;
        }

        if (gripperPosition < 1) {
            gripperPosition = 1;
        } else if (gripperPosition > 3) {
            gripperPosition = 3;
        }

        if (gamepad.dpad_up) {
            referenceGripper = 1;
        } else if (gamepad.dpad_right) {
            referenceGripper = 2;
        } else if (gamepad.dpad_down){
            referenceGripper = 3;
        }

        if (gripperPosition == 2) {//Slightly open
            if(referenceGripper == 3){
                leftGrip1.setPosition(0.30);
                rightGrip1.setPosition(0.4);
                telemetry.addData("Right grip1 position", rightGrip1.getPosition());
                telemetry.addData("Left grip1 position", leftGrip1.getPosition());
            }else if(referenceGripper == 1){
                leftGrip2.setPosition(0.48);
                rightGrip2.setPosition(0.39);
                telemetry.addData("Right grip2 position", rightGrip2.getPosition());
                telemetry.addData("Left grip2 position", leftGrip2.getPosition());
            } else if(referenceGripper == 2){
                leftGrip1.setPosition(0.30);
                rightGrip1.setPosition(0.4);
                telemetry.addData("Right grip1 position", rightGrip1.getPosition());
                telemetry.addData("Left grip1 position", leftGrip1.getPosition());
                leftGrip2.setPosition(0.48);
                rightGrip2.setPosition(0.39);
                telemetry.addData("Right grip2 position", rightGrip2.getPosition());
                telemetry.addData("Left grip2 position", leftGrip2.getPosition());
            }

        } else if (gripperPosition == 3) {//Closed
            if(referenceGripper == 3){
                leftGrip1.setPosition(0.05);
                rightGrip1.setPosition(.64);
                telemetry.addData("Right grip1 position", rightGrip1.getPosition());
                telemetry.addData("Left grip1 position", leftGrip1.getPosition());
            }else if(referenceGripper == 1){
                leftGrip2.setPosition(0.26);
                rightGrip2.setPosition(.62);
                telemetry.addData("Right grip2 position", rightGrip2.getPosition());
                telemetry.addData("Left grip2 position", leftGrip2.getPosition());
            } else if(referenceGripper == 2){
                leftGrip1.setPosition(0.05);
                rightGrip1.setPosition(.64);
                telemetry.addData("Right grip1 position", rightGrip1.getPosition());
                telemetry.addData("Left grip1 position", leftGrip1.getPosition());
                leftGrip2.setPosition(0.26);
                rightGrip2.setPosition(0.62);
                telemetry.addData("Right grip2 position", rightGrip2.getPosition());
                telemetry.addData("Left grip2 position", leftGrip2.getPosition());
            }

        } else if (gripperPosition == 1) {    //Open
            if(referenceGripper == 3){
                leftGrip1.setPosition(0.373);
                rightGrip1.setPosition(0.313);
                telemetry.addData("Right grip1 position", rightGrip1.getPosition());
                telemetry.addData("Left grip1 position", leftGrip1.getPosition());
            }else if(referenceGripper == 1){
                leftGrip2.setPosition(0.61);
                rightGrip2.setPosition(0.29);
                telemetry.addData("Right grip2 position", rightGrip2.getPosition());
                telemetry.addData("Left grip2 position", leftGrip2.getPosition());
            } else if(referenceGripper == 2){
                leftGrip1.setPosition(0.373);
                rightGrip1.setPosition(0.313);
                telemetry.addData("Right grip1 position", rightGrip1.getPosition());
                telemetry.addData("Left grip1 position", leftGrip1.getPosition());
                leftGrip2.setPosition(0.61);
                rightGrip2.setPosition(0.29);
                telemetry.addData("Right grip2 position", rightGrip2.getPosition());
                telemetry.addData("Left grip2 position", leftGrip2.getPosition());
            }
        }
    }

    private void controlLift(Gamepad gamepad) {
        if (gamepad.b){ //&& !lift.isBusy()) {
            lift.setTargetPosition(1385 + zeroPoint);
            liftCurrentPosition = 1385 + zeroPoint;
            telemetry.addData("At position b and count = ",liftCurrentPosition);
        } else if (gamepad.y){ //&& !lift.isBusy()) {
            lift.setTargetPosition(2580 + zeroPoint);
            liftCurrentPosition = 2580 + zeroPoint;
            telemetry.addData("At position y and count = ",liftCurrentPosition);
        } else if (gamepad.a){ //&& !lift.isBusy()) {
            lift.setTargetPosition(zeroPoint);
            liftCurrentPosition = zeroPoint;
            telemetry.addData("At position a and count = ",liftCurrentPosition);
        } else if (gamepad.x){ //&& !lift.isBusy()) {
            lift.setTargetPosition(liftCurrentPosition + 20);
            liftCurrentPosition = liftCurrentPosition + 20;
            telemetry.addData("Added x and now at ",liftCurrentPosition);
        }
        telemetry.update();
    }

}
