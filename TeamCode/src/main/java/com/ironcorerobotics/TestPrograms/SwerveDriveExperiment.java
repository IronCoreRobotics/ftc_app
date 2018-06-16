package com.ironcorerobotics.TestPrograms;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
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

        public void init()

        {
            //Put your initialization code here
            //Hardware map everything
            testServo = hardwareMap.servo.get("test_servo");
        }

        public void loop()

        {
            //Put your main code here
            testServo.setDirection(Servo.Direction.REVERSE);
            telemetry.addData("angle", getdirectionangle(gamepad1.left_stick_x, gamepad1.left_stick_y));
            telemetry.addData("servo position",(getdirectionangle(gamepad1.left_stick_x, gamepad1.left_stick_y)*ASymbol)/100);
            testServo.setPosition((getdirectionangle(gamepad1.left_stick_x, gamepad1.left_stick_y)*ASymbol)/100);
            telemetry.update();

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
            if(x < 0 && y < 0)//toplef
            {
                double cordfranction = y / x;
                returnthis = ((Math.abs(Math.toDegrees(Math.atan(cordfranction)))+270)*ASymbol)/100;
            }
            return returnthis;
        }

    }
