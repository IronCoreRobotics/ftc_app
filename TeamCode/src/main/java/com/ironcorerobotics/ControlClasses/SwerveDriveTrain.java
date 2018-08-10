package com.ironcorerobotics.ControlClasses;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Fam on 7/31/2018.
 */

public class SwerveDriveTrain {

    private boolean servoForwardButton;
    private boolean servoRightButton;
    private boolean servoLeftButton;
    private boolean servoBackButton;
    private boolean pointForward;
    private boolean pointRight;
    private boolean pointLeft;
    private boolean pointBack;
    private double swerveX;
    private double swerveY;
    private double turnX;
    private double power;
    boolean runGyroThread;
    double ASymbol = .2777777778;

    public void updateSwerveDriveTrain(boolean servoForwardButton, boolean servoRightButton, boolean servoLeftButton, boolean servoBackButton, boolean pointForward, boolean pointRight, boolean pointLeft, boolean pointBack, double swerveX, double swerveY, double turnX, double power) {
        this.servoForwardButton = servoForwardButton;
        this.servoRightButton = servoRightButton;
        this.servoLeftButton = servoLeftButton;
        this.servoBackButton = servoBackButton;
        this.pointForward = pointForward;
        this.pointRight = pointRight;
        this.pointLeft = pointLeft;
        this.pointBack = pointBack;
        this.swerveX = swerveX;
        this.swerveY = swerveY;
        this.turnX = turnX;
        this.power = power;
    }

    private double getCaculatedServoPosition() {
        double returnthis = 0;
        if (this.swerveX > 0 && this.swerveY < 0)//topright
        {
            double cordfranction = this.swerveX / this.swerveY;
            returnthis = ((Math.abs(Math.toDegrees(Math.atan(cordfranction))) + 90) * ASymbol) / 100;
        }
        if (this.swerveX > 0 && this.swerveY > 0)//bottomright
        {
            double cordfranction = this.swerveY / this.swerveX;
            returnthis = ((Math.abs(Math.toDegrees(Math.atan(cordfranction))) + 180) * ASymbol) / 100;
        }
        if (this.swerveX < 0 && this.swerveY > 0)//bottomleft
        {
            double cordfranction = this.swerveX / this.swerveY;
            returnthis = ((Math.abs(Math.toDegrees(Math.atan(cordfranction))) + 270) * ASymbol) / 100;
        }
        if (this.swerveX < 0 && this.swerveY < 0)//topleft
        {
            double cordfranction = this.swerveY / this.swerveX;
            returnthis = ((Math.abs(Math.toDegrees(Math.atan(cordfranction)))) * ASymbol) / 100;
        }
        if (this.swerveX == 0 && this.swerveY == -1.00 || servoForwardButton) //Forward
        {
            returnthis = ((90 * ASymbol) / 100);
        }
        if (this.swerveX == 1.00 && this.swerveY == 0 || servoRightButton) //Right
        {
            returnthis = ((180 * ASymbol) / 100);
        }
        if (this.swerveX == 0 && this.swerveY == 1.00 || servoBackButton) //Backwards
        {
            returnthis = ((270 * ASymbol) / 100);
        }
        if (this.swerveX == -1.00 && this.swerveY == 0 || servoLeftButton) //Left
        {
            returnthis = 0;
        }
        return returnthis;
    }

    public boolean isRunGyroThread() {
        return runGyroThread;
    }

    public void setRunGyroThread(boolean runGyroThread) {
        this.runGyroThread = runGyroThread;
    }

    public void RUNSwerveDriveTrain(DcMotor motor1, DcMotor motor2, DcMotor motor3, Servo testServo, Servo testServo2, Servo testServo3, MotorControl controlMotor1, MotorControl controlMotor2, MotorControl controlMotor3) {
        boolean motorRun = false;
        testServo.setPosition(getCaculatedServoPosition());
        testServo2.setPosition(getCaculatedServoPosition());
        testServo3.setPosition(getCaculatedServoPosition());
        if (power > 0) {
            motorRun = true;
            runGyroThread = false;
        }
        if (motorRun == true) {
            motor1.setPower(controlMotor1.getControlledSpeed());
            motor2.setPower(controlMotor2.getControlledSpeed());
            motor3.setPower(controlMotor3.getControlledSpeed());
        }
        if (motorRun == false) {
            motor1.setPower(0);
            motor2.setPower(0);
            motor3.setPower(0);
        } if (this.turnX < 0) {//turning//left
            motor1.setPower(-controlMotor1.getControlledSpeed());
            motor2.setPower(controlMotor2.getControlledSpeed());
            motor3.setPower(-controlMotor3.getControlledSpeed());
            testServo.setPosition(0);
            testServo2.setPosition(0);
            testServo3.setPosition(0);
        } if (this.turnX > 0) {//right
            motor1.setPower(controlMotor1.getControlledSpeed());
            motor2.setPower(-controlMotor2.getControlledSpeed());
            motor3.setPower(controlMotor3.getControlledSpeed());
            testServo.setPosition(0);
            testServo2.setPosition(0);
            testServo3.setPosition(0);
        } if (pointLeft || pointRight || pointBack || pointForward){
            setRunGyroThread(true);

        }
    }

    public void initSwerveDrive(Servo testServo, Servo testServo2, DcMotor motor1, DcMotor motor2, DcMotor motor3){
        testServo.setDirection(Servo.Direction.REVERSE);
        testServo2.setDirection(Servo.Direction.REVERSE);
        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }



}
