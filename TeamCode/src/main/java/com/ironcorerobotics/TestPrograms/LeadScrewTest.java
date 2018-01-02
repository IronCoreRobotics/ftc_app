package com.ironcorerobotics.TestPrograms;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by FranklinFamily on 12/30/2017.
 */

@TeleOp (name = "Lead Screw Test", group = "Test")
//@Disabled //Comment this out when you copy this into your code


public class LeadScrewTest extends OpMode

{
    //Declare any global variables here
    //Build any objects like DC motors and such here
    DcMotor testMotor;

    public void init()

    {
        //Put your initialization code here
        //Hardware map everything
        testMotor = hardwareMap.dcMotor.get("test_motor");

    }

    public void loop()

    {
        //Put your main code here

            if (gamepad1.dpad_left)
            {
                    testMotor.setPower(1.00);
                    telemetry.addLine("left");
            }
            if (gamepad1.dpad_right)
            {
                    testMotor.setPower(-1.00);
                    telemetry.addLine("You hesitated");
            }
            if (!gamepad1.dpad_left && !gamepad1.dpad_right)
            {
                testMotor.setPower(0);
            }
            else
            {
                telemetry.addLine("Nothing");
            }
            telemetry.update();
    }
}


