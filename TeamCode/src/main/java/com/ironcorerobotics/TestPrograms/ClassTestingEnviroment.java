package com.ironcorerobotics.TestPrograms;

import com.ironcorerobotics.ControlClasses.MotorControl;
import com.ironcorerobotics.ControlClasses.SwerveDriveTrain;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/**
 * Created by Fam on 6/7/2018.
 */

@TeleOp(name = "SwerveDriveClassCode")
//@Disabled//Comment this out when you copy this into your code

public class ClassTestingEnviroment extends OpMode {

    //Declare any global variables here
    //Build any objects like DC motors and such here
    double ASymbol = .2777777778;
    Servo testServo;
    Servo testServo2;
    Servo testServo3;
    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    MotorControl ControlMotor1 = new MotorControl(1.00, 2, 5);
    MotorControl ControlMotor2 = new MotorControl(-1.00, 2, 5);
    MotorControl ControlMotor3 = new MotorControl(1.00, 2, 5);
    BNO055IMU imu;
    Orientation angles;
    boolean runGyroThread;
    SwerveDriveTrain COMET = new SwerveDriveTrain();


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
        COMET.initSwerveDrive(testServo, testServo2, motor1, motor2, motor3);
        ControlMotor1.initMotorControl();
        ControlMotor2.initMotorControl();
        ControlMotor3.initMotorControl();
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
    }

    public void loop()

    {
        //Put your main code here
        COMET.updateSwerveDriveTrain(gamepad1.y, gamepad1.b, gamepad1.x, gamepad1.a, gamepad1.dpad_up, gamepad1.dpad_right, gamepad1.dpad_left, gamepad1.dpad_down, gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.right_trigger);
        COMET.RUNSwerveDriveTrain(motor1, motor2, motor3, testServo, testServo2, testServo3, ControlMotor1, ControlMotor2, ControlMotor3);
        ControlMotor1.updateMotorControl(gamepad1.right_bumper, gamepad1.left_bumper, gamepad1.left_trigger);
        ControlMotor2.updateMotorControl(gamepad1.right_bumper, gamepad1.left_bumper, gamepad1.left_trigger);
        ControlMotor3.updateMotorControl(gamepad1.right_bumper, gamepad1.left_bumper, gamepad1.left_trigger);
        if (COMET.isRunGyroThread()) {
            t1.run();
        }
    }

    Thread t1 = new Thread() {
        public void run() {

            if (gamepad1.dpad_right) {
                while (COMET.isRunGyroThread() == true) {
                    motor1.setPower(ControlMotor1.getControlledSpeed());
                    motor2.setPower(-ControlMotor2.getControlledSpeed());
                    motor3.setPower(ControlMotor3.getControlledSpeed());
                    if (getUpdatedIMUHeading() > 70 && getUpdatedIMUHeading() < 110) {
                        COMET.setRunGyroThread(false);
                        t1.interrupt();
                        telemetry.addLine("I AM IN THE IF");
                        break;
                    }
                }
            } else if (gamepad1.dpad_up) {
                while (COMET.isRunGyroThread() == true) {
                    motor1.setPower(ControlMotor1.getControlledSpeed());
                    motor2.setPower(-ControlMotor2.getControlledSpeed());
                    motor3.setPower(ControlMotor3.getControlledSpeed());
                    if (getUpdatedIMUHeading() > 340 && getUpdatedIMUHeading() < 360) {
                        COMET.setRunGyroThread(false);
                        t1.interrupt();
                        telemetry.addLine("I AM IN THE IF");
                        break;
                    }
                }
            } else if (gamepad1.dpad_down) {
                while (COMET.isRunGyroThread() == true) {
                    motor1.setPower(ControlMotor1.getControlledSpeed());
                    motor2.setPower(-ControlMotor2.getControlledSpeed());
                    motor3.setPower(ControlMotor3.getControlledSpeed());
                    if (getUpdatedIMUHeading() > 160 && getUpdatedIMUHeading() < 200) {
                        COMET.setRunGyroThread(false);
                        t1.interrupt();
                        telemetry.addLine("I AM IN THE IF");
                        break;
                    }
                }
            } else if (gamepad1.dpad_left) {
                while (COMET.isRunGyroThread() == true) {
                    motor1.setPower(ControlMotor1.getControlledSpeed());
                    motor2.setPower(-ControlMotor2.getControlledSpeed());
                    motor3.setPower(ControlMotor3.getControlledSpeed());
                    if (getUpdatedIMUHeading() > 250 && getUpdatedIMUHeading() < 290) {
                        COMET.setRunGyroThread(false);
                        t1.interrupt();
                        telemetry.addLine("I AM IN THE IF");
                        break;
                    }
                }
            }
        }

    };

    public double getUpdatedIMUHeading() {
        double convertedHeading = 0;
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double currentOrientation = AngleUnit.DEGREES.normalize(angles.firstAngle);
        if (currentOrientation > 0) {
            convertedHeading = 360 - currentOrientation;
        } else if (currentOrientation < 0) {
            convertedHeading = Math.abs(currentOrientation);
        }
        return convertedHeading;
    }
}





