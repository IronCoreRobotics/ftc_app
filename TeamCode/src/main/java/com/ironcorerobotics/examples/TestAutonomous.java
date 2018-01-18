package com.ironcorerobotics.examples;

import com.qualcomm.hardware.lynx.LynxI2cColorRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by shawneverly on 8/19/17.
 */
@Autonomous(name = "Test Refactor")
//@Disabled
public class TestAutonomous extends LinearOpMode {

//    private DcMotor motor1;
//    private DcMotor motor2;
//    private TwoWheelDriveTrain Chassis;

ColorSensor colorRange;

    public void runOpMode() throws InterruptedException {

        colorRange = (LynxI2cColorRangeSensor) hardwareMap.get("Color");

        waitForStart();

        while(opModeIsActive())
        {
            telemetry.addData("Red", colorRange.red());
            telemetry.addData("Blue", colorRange.blue());
            telemetry.update();
        }

//        motor1 = hardwareMap.dcMotor.get("rightside_Motor");
//        motor2 = hardwareMap.dcMotor.get("leftside_Motor");
//
//        waitForStart();
//
//        MotorControl controlMotor2 = new MotorControl(-1);
//        Chassis = new TwoWheelDriveTrain(motor1, motor2, controlMotor2, false);
//
//        final TheAutonomousREXSuper REXIE = new TheAutonomousREXSuper(Chassis);
//
//        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        motor1.setTargetPosition(motor1.getCurrentPosition());
//        motor2.setTargetPosition(motor2.getCurrentPosition());
//
//        sleep(1000);
//
//        final Thread t1 = new Thread() {
//            public void run() {
//
//
//                while (Chassis.isOpModeState() && opModeIsActive())
//
//                {
//                    telemetry.addLine("I am running the robot");
//                    telemetry.update();
//                    Chassis.autoDrive(1000, "Right", .20);
//                    Chassis.brake();
//                    Chassis.setOpModeState(false);
//                    REXIE.Drive().brake();
//
//                }
//            }
//        };
//
//        Thread t2 = new Thread() {
//            public void run() {
//                while (opModeIsActive()) {
//                    telemetry.addLine("I am monitoring the opMode status");
//                    if (opModeIsActive()) {
//                        telemetry.addLine("We are good");
//                    }
//                    if (!opModeIsActive()) {
//                        t1.interrupt();
//                    }
//                    telemetry.update();
//                }
//            }
//        };
//
//        Chassis.setOpModeState(true);
//
//        t2.start();
//        t1.start();
//
//        t2.run();
//        t1.run();

    }
}

//
//        Chassis.setOpModeState(true);
//
//        if (opModeIsActive() == true) {
//            Run(Chassis.autoDrive(500, "Drive", .20));
//            telemetry.addLine("Ln 31");
//            Chassis.brake();
//            telemetry.update();
//            sleep(1000);
//        }
//    }
//
//    public void Run(boolean run)
//    {
//        while (opModeIsActive() == true) {
//            telemetry.addLine("Running");
//            if(opModeIsActive() == false) {
//                telemetry.addLine("Stopping");
//                Chassis.setOpModeState(false);
//                motor1.setPower(0);
//                motor2.setPower(0);
//                sleep(1000);
//                stop();
//            }
//            telemetry.update();
//        }



//        motor1 = hardwareMap.dcMotor.get("Test_Motor_2");
//
//        waitForStart();
//
//        while(opModeIsActive())
//
//            motor1.setPower(-1.00);
//
//            telemetry.addData("Value", motor1.getCurrentPosition());
//
//            telemetry.update();
//
////        leftSide = hardwareMap.dcMotor.get("left_drive");
////        rightSide = hardwareMap.dcMotor.get("right_drive");
////
////        rightSide.setDirection(DcMotorSimple.Direction.REVERSE);
////
////        waitForStart();
////
////        int startPosition = leftSide.getCurrentPosition();
////        int currentPosition = leftSide.getCurrentPosition();
////
////        leftSide.setPower(0.4);
////        rightSide.setPower(0.4);
////
////        sleep(1000);
////        while((currentPosition - startPosition) < 1120) {
////            currentPosition = leftSide.getCurrentPosition();
////
////
////        leftSide.setPower(0);
////        rightSide.setPower(0);



