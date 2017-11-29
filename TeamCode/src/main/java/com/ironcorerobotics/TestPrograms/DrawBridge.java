package com.ironcorerobotics.TestPrograms;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by tmwat on 10/30/2017.
 */

@Autonomous(name = "Drawbridge_Winch", group = "Test")
@Disabled

public class DrawBridge extends LinearOpMode

{

    Servo servo1;

    @Override
    public void runOpMode() throws InterruptedException

    {

        servo1 = hardwareMap.servo.get("drawbridge_winch");


        servo1.setPosition(0);

        sleep(800);

        servo1.setPosition(0.5);

        sleep(3000);

        servo1.setPosition(1.00);

        sleep(800);


    }
}