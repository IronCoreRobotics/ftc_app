package com.ironcorerobotics.TestPrograms;

import com.ironcorerobotics.ControlClasses.MotorControl;
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

@TeleOp(name = "SwerveDriveCode")
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
    MotorControl ControlMotor1 = new MotorControl(1.00, 2, 5);
    MotorControl ControlMotor2 = new MotorControl(-1.00, 2, 5);
    MotorControl ControlMotor3 = new MotorControl(1.00,2,5);
    BNO055IMU imu;
    Orientation angles;


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
            testServo.setDirection(Servo.Direction.REVERSE);
            testServo2.setDirection(Servo.Direction.REVERSE);
            motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            motor3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
            parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
            parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
            parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
            parameters.loggingEnabled      = true;
            parameters.loggingTag          = "IMU";
            parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

            imu = hardwareMap.get(BNO055IMU.class, "imu");
            imu.initialize(parameters);

        }

        public void loop()

        {
            //Put your main code here
            testServo.setPosition(getCaculatedServoPosition(gamepad1.left_stick_x, gamepad1.left_stick_y));
            testServo2.setPosition(getCaculatedServoPosition(gamepad1.left_stick_x, gamepad1.left_stick_y));
            testServo3.setPosition(getCaculatedServoPosition(gamepad1.left_stick_x, gamepad1.left_stick_y));
            //telemetry.addData("servo position", getCaculatedServoPosition(gamepad1.left_stick_x, gamepad1.left_stick_y));
            telemetry.addData("robot position", getUpdatedIMUHeading());
            telemetry.update();
            controlMotors();
        }

        public double getCaculatedServoPosition(double x, double y)
        {
            double returnthis = 0;
            if(x > 0 && y < 0)//topright
            {
                double cordfranction = x / y;
                returnthis = ((Math.abs(Math.toDegrees(Math.atan(cordfranction)))+90)*ASymbol)/100;
            }
            if(x > 0 && y > 0)//bottomright
            {
                double cordfranction = y / x;
                returnthis = ((Math.abs(Math.toDegrees(Math.atan(cordfranction)))+180)*ASymbol)/100;
            }
            if(x < 0 && y > 0)//bottomleft
            {
                double cordfranction = x / y;
                returnthis = ((Math.abs(Math.toDegrees(Math.atan(cordfranction)))+270)*ASymbol)/100;
            }
            if(x < 0 && y < 0)//topleft
            {
                double cordfranction = y / x;
                returnthis = ((Math.abs(Math.toDegrees(Math.atan(cordfranction))))*ASymbol)/100;
            }
            if(x == 0 && y == -1.00 || gamepad1.dpad_up) //Forward
            {
                returnthis = ((90*ASymbol)/100);
            }
            if(x == 1.00 && y == 0 || gamepad1.dpad_right) //Right
            {
                returnthis = ((180*ASymbol)/100);
            }
            if(x == 0 && y == 1.00 || gamepad1.dpad_down) //Backwards
            {
                returnthis = ((270*ASymbol)/100);
            }
            if(x == -1.00 && y ==0 || gamepad1.dpad_left) //Left
            {
                returnthis = 0;
            }
            return returnthis;
        }

        public void controlMotors()
        {
            boolean motorRun = false;
            if(gamepad1.a) {
                motorRun = true;
            }
            if(motorRun==true) {motor1.setPower(ControlMotor1.getControlledSpeed());
                motor2.setPower(ControlMotor2.getControlledSpeed());
                motor3.setPower(ControlMotor3.getControlledSpeed());}
            if(motorRun==false) {
                motor1.setPower(0);
                motor2.setPower(0);
                motor3.setPower(0);
            }if(gamepad1.y) {
            ControlMotor1.setThirdButton(true);
            ControlMotor2.setThirdButton(true);
            ControlMotor3.setThirdButton(true);
            ControlMotor1.setSecondButton(false);
            ControlMotor2.setSecondButton(false);
            ControlMotor3.setSecondButton(false);
            ControlMotor1.setFirstButton(false);
            ControlMotor2.setFirstButton(false);
            ControlMotor3.setFirstButton(false);

        } else if(gamepad1.right_bumper) {
            ControlMotor1.setThirdButton(false);
            ControlMotor2.setThirdButton(false);
            ControlMotor3.setThirdButton(false);
            ControlMotor1.setSecondButton(true);
            ControlMotor2.setSecondButton(true);
            ControlMotor3.setSecondButton(true);
            ControlMotor1.setFirstButton(false);
            ControlMotor2.setFirstButton(false);
            ControlMotor3.setFirstButton(false);
        }else if (gamepad1.left_bumper) {
            ControlMotor1.setThirdButton(false);
            ControlMotor2.setThirdButton(false);
            ControlMotor3.setThirdButton(false);
            ControlMotor1.setSecondButton(false);
            ControlMotor2.setSecondButton(false);
            ControlMotor3.setSecondButton(false);
            ControlMotor1.setFirstButton(true);
            ControlMotor2.setFirstButton(true);
            ControlMotor3.setFirstButton(true);
        }else if (gamepad1.right_stick_x < 0){//turning//left
            motor1.setPower(-ControlMotor1.getControlledSpeed());
            motor2.setPower(ControlMotor2.getControlledSpeed());
            motor3.setPower(-ControlMotor3.getControlledSpeed());
            testServo.setPosition(0);
            testServo2.setPosition(0);
            testServo3.setPosition(0);
        }else if (gamepad1.right_stick_x > 0){//right
            motor1.setPower(ControlMotor1.getControlledSpeed());
            motor2.setPower(-ControlMotor2.getControlledSpeed());
            motor3.setPower(ControlMotor3.getControlledSpeed());
            testServo.setPosition(0);
            testServo2.setPosition(0);
            testServo3.setPosition(0);
        }
//        else if (gamepad1.b){
//            boolean run = true;
//            telemetry.addLine("I AM IN THE LOOP");
//            while (run == true) {
//                motor1.setPower(ControlMotor1.getControlledSpeed());
//                motor2.setPower(-ControlMotor2.getControlledSpeed());
//                motor3.setPower(ControlMotor3.getControlledSpeed());
//                if(getUpdatedIMUHeading() == 90){
//                    run = false;
//                }
//            }
//        }

        }

    public double getUpdatedIMUHeading(){
        double convertedHeading = 0;
        angles   = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double currentOrientation = AngleUnit.DEGREES.normalize(angles.firstAngle);
        telemetry.update();
        if(currentOrientation > 0){
            convertedHeading =  360 - currentOrientation;
        }else if(currentOrientation < 0){
            convertedHeading = Math.abs(currentOrientation);
        }
        return convertedHeading;
    }

    }
