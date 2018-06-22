package com.ironcorerobotics.TestPrograms;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Fam on 6/7/2018.
 */

@TeleOp(name = "SwerveDriveCodeTest")
//@Disabled//Comment this out when you copy this into your code

public class SwerveDriveExperiment extends OpMode {

        //Declare any global variables here
        //Build any objects like DC motors and such here
    double ASymbol = .2777777778;
    Servo testServo;
    Servo testServo2;
    Servo testServo3;
    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;


        public void init()

        {
            //Put your initialization code here
            //Hardware map everything
            testServo = hardwareMap.servo.get("direction_servo");
            testServo2 = hardwareMap.servo.get("servo2");
            testServo3 = hardwareMap.servo.get("servo3");
            motor1 = hardwareMap.dcMotor.get("drive_motor");
            motor2 = hardwareMap.dcMotor.get("motor2");
            motor3 = hardwareMap.dcMotor.get("motor3");

        }

        public void loop()

        {
            //Put your main code here
            testServo.setDirection(Servo.Direction.REVERSE);
            testServo2.setDirection(Servo.Direction.REVERSE);
            telemetry.addData("servo position", getCaculatedServoPosition(gamepad1.left_stick_x, gamepad1.left_stick_y));
            testServo.setPosition(getCaculatedServoPosition(gamepad1.left_stick_x, gamepad1.left_stick_y));
            testServo2.setPosition(getCaculatedServoPosition(gamepad1.left_stick_x, gamepad1.left_stick_y));
            testServo3.setPosition(getCaculatedServoPosition(gamepad1.left_stick_x, gamepad1.left_stick_y));
            telemetry.update();
            controlMotor();

        }

        public double getCaculatedServoPosition(double x, double y)
        {
            double returnthis = 0;
            if(x > 0 && y < 0)//topright
            {
                double cordfranction = x / y;
                returnthis = ((Math.abs(Math.toDegrees(Math.atan(cordfranction))))*ASymbol)/100;
            }
            if(x > 0 && y > 0)//bottomright
            {
                double cordfranction = y / x;
                returnthis = ((Math.abs(Math.toDegrees(Math.atan(cordfranction)))+90)*ASymbol)/100;
            }
            if(x < 0 && y > 0)//bottomleft
            {
                double cordfranction = x / y;
                returnthis = ((Math.abs(Math.toDegrees(Math.atan(cordfranction)))+180)*ASymbol)/100;
            }
            if(x < 0 && y < 0)//topleft
            {
                double cordfranction = y / x;
                returnthis = ((Math.abs(Math.toDegrees(Math.atan(cordfranction)))+270)*ASymbol)/100;
            }
            if(x == 0 && y == -1.00 || gamepad1.dpad_up) //Forward
            {
                returnthis = 0;
            }
            if(x == 1.00 && y == 0 || gamepad1.dpad_right) //Right
            {
                returnthis = ((90*ASymbol)/100);
            }
            if(x == 0 && y == 1.00 || gamepad1.dpad_down) //Backwards
            {
                returnthis = ((180*ASymbol)/100);
            }
            if(x == -1.00 && y ==0 || gamepad1.dpad_left) //Left
            {
                returnthis = ((270*ASymbol)/100);
            }
            return returnthis;
        }

        public void controlMotor()
        {
            boolean motorRun = false;
            if(gamepad1.a){
                motorRun=true;
            }
            if(gamepad1.b){
                motorRun=false;
            }
            if(motorRun==true) {
                motor1.setPower(1.00);
                motor2.setPower(1.00);
                motor3.setPower(1.00);
            }
            if(motorRun==false) {
                motor1.setPower(0);
                motor2.setPower(0);
                motor3.setPower(0);
            }
        }

    }
