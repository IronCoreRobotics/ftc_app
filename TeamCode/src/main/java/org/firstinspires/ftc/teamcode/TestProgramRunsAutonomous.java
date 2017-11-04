package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Fam on 10/31/2017.
 */

@Autonomous(name = "TestAutonomous", group = "Test")

public class TestProgramRunsAutonomous extends LinearOpMode

{

    Servo servo1;

    public void runOpMode() throws InterruptedException
    {
        servo1 = hardwareMap.servo.get("drawbridge_winch");

       servo1.setPosition(0);

        sleep(6000);


        servo1.setPosition(0.5);

    }
}
