package com.ironcorerobotics.TestPrograms;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/**
 * Created by Fam on 1/6/2018.
 */


@Autonomous(name = "Gyro Scope Test", group = "Test")
@Disabled

public class GyroScopeTest extends LinearOpMode

{
    BNO055IMU imu;
    Orientation angles;
    DcMotor motor1;
    DcMotor motor2;

    public void runOpMode() throws InterruptedException {

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        motor1 = hardwareMap.dcMotor.get("rightside_Motor");
        motor2 = hardwareMap.dcMotor.get("leftside_Motor");

        waitForStart();

        while(opModeIsActive())
        {
            DriveStraight(10);
        }

    }

    public void DriveStraight(int correctionSensitivity)
    {
        double RightPower;
        double LeftPower;

        while(opModeIsActive())
        {
            angles   = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            double currentOrientation = 0 - AngleUnit.DEGREES.normalize(angles.firstAngle);
            if(currentOrientation < 0)
            {
                LeftPower = -1.00 + (java.lang.Math.abs(currentOrientation) );
                telemetry.addData("Turning right by slowing down left to ", LeftPower);
                motor2.setPower(LeftPower);
                motor1.setPower(1.00);
            }
            if(currentOrientation > 0)
            {
                RightPower = 1.00 - (java.lang.Math.abs(currentOrientation));
                telemetry.addData("Turning left by slowing down right to ", RightPower);
                motor1.setPower(RightPower);
                motor2.setPower(-1.00);
            }
            telemetry.addData("Orientation = ", java.lang.Math.abs(currentOrientation));
            telemetry.update();
        }
    }

}
