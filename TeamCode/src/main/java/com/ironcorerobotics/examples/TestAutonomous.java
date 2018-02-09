package com.ironcorerobotics.examples;

import com.ironcorerobotics.ControlClasses.MotorControl;
import com.ironcorerobotics.ControlClasses.TheAutonomousREXSuper;
import com.ironcorerobotics.ControlClasses.TwoWheelDriveTrain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by shawneverly on 8/19/17.
 */
@Autonomous(name = "Test Refactor")
@Disabled
public class TestAutonomous extends LinearOpMode {

    private DcMotor motor1;
    private DcMotor motor2;
    private TwoWheelDriveTrain Chassis;

    public void runOpMode() throws InterruptedException {

        motor1 = hardwareMap.dcMotor.get("rightside_Motor");
        motor2 = hardwareMap.dcMotor.get("leftside_Motor");

        waitForStart();

        MotorControl controlMotor2 = new MotorControl(-1);
        Chassis = new TwoWheelDriveTrain(motor1, motor2, controlMotor2, false);
        final TheAutonomousREXSuper REXIE = new TheAutonomousREXSuper(Chassis);

        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motor1.setTargetPosition(motor1.getCurrentPosition());
        motor2.setTargetPosition(motor2.getCurrentPosition());

        sleep(1000);

        final Thread t1 = new Thread() {
            public void run() {


                while (Chassis.isOpModeState() && opModeIsActive())

                {
                    telemetry.addLine("I am running the robot");
                    telemetry.update();
                    Chassis.autoDrive(1000, "Right", .20);
                    Chassis.brake();
                    Chassis.setOpModeState(false);
                    REXIE.Drive().brake();
                }
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                while (opModeIsActive()) {
                    telemetry.addLine("I am monitoring the opMode status");
                    if (opModeIsActive()) {
                        telemetry.addLine("We are good");
                    }
                    if (!opModeIsActive()) {
                        t1.interrupt();
                    }
                    telemetry.update();
                }
            }
        };

        Thread t3 = new Thread(){
            public void run(){
                while(opModeIsActive())
                {
                    telemetry.addLine("Look at me I am running multiple threads");
                }
            }
        };

        Chassis.setOpModeState(true);

        t2.start();
        t1.start();
        t3.start();

        t2.run();
        t1.run();
        t3.run();

    }
}



