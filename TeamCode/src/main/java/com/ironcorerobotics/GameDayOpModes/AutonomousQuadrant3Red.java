package com.ironcorerobotics.GameDayOpModes;

import com.ironcorerobotics.ControlClasses.MotorControl;
import com.qualcomm.hardware.lynx.LynxI2cColorRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by Fam on 11/9/2017.
 */

@Autonomous(name = "Auto Quad 3 Red New", group = "Test")

public class AutonomousQuadrant3Red extends LinearOpMode

{
    int zeroPoint;
    DcMotor motor1;
    DcMotor motor2;
    Servo jewelSlapper;
    Servo rightGrip;
    Servo leftGrip;
    DcMotor lift;
    ColorSensor sensorColor;
    MotorControl controlMotor2 = new MotorControl(-1);
    String CryptoboxCipherColumnNumber;

    public static final String TAG = "Vuforia VuMark Sample";

    OpenGLMatrix lastLocation = null;

    VuforiaLocalizer vuforia;

    @Override public void runOpMode() throws InterruptedException

    {
        motor1 = hardwareMap.dcMotor.get("rightside_Motor");
        motor2 = hardwareMap.dcMotor.get("leftside_Motor");
        jewelSlapper = hardwareMap.servo.get("drawbridge_winch");
        sensorColor = (LynxI2cColorRangeSensor) hardwareMap.get("Color");

        initVuforia();

        waitForStart();

        VuforiaReturnMethod();

        initGripper();

        leftGrip.setPosition(0.30);
        rightGrip.setPosition(.95);

        jewelSlapper.setPosition(.9);

        sleep(1000);

        initLifter();

        lift.setTargetPosition(1350 + zeroPoint);

        sleep(1000);

        autoDrive(25, "Drive", .10);

        brake();

        sleep(500);

        jewelSlapper.setPosition(0);

        sleep(500);

        JewelScoreAutonomous("Red", 280);

        sleep(500);

        //line up to cryptobox

        autoDrive(2250, "Reverse", .30);

        brake();

        autoDrive(1125, "Left", .30);

        brake();

        if(CryptoboxCipherColumnNumber == "Center") {

            telemetry.addLine("Putting it in the center column");
            telemetry.update();

            autoDrive(1285, "Reverse", .30);

            brake();
        } else if (CryptoboxCipherColumnNumber == "Right")
        {
            telemetry.addLine("Putting it in the right column");
            telemetry.update();

            autoDrive(585, "Reverse", .30);

            brake();
        } else if (CryptoboxCipherColumnNumber == "Left")
        {
            telemetry.addLine("Putting it in the left column");
            telemetry.update();

            autoDrive(1730, "Reverse", .30);
        } else {
            telemetry.addLine("Putting it in the center column");
            telemetry.update();

            autoDrive(1285, "Reverse", .30);

            brake();
        }

        //center column


        autoDrive(1110, "Left", .30);

        brake();

        lift.setTargetPosition(zeroPoint);

        sleep(1000);

        autoDrive(50, "Drive", .30);

        //special assurance

        leftGrip.setPosition(0.45);
        rightGrip.setPosition(0.8);

        sleep(500);

        autoDrive(750, "Reverse", .30);

        brake();

        leftGrip.setPosition(0.30);
        rightGrip.setPosition(.95);

        sleep(1000);

        autoDrive(400, "Drive", .10);

        autoDrive(150, "Drive", .75);

        brake();

        autoDrive(600, "Reverse", .30);

        brake();

        sleep(1000);
    }

    public void JewelScoreAutonomous(String AllianceColor, int knockOffDistance)

    {

        if (AllianceColor == "Blue")

        {

            if (sensorColor.red() < sensorColor.blue() && opModeIsActive()) {
                autoDrive(knockOffDistance, "Reverse", .10);

                brake();

                jewelSlapper.setPosition(.9);

                autoDrive(knockOffDistance-25, "Drive", .10);

                motor1.setPower(0);
                motor2.setPower(0);

                brake();
            }

            if (sensorColor.red() > sensorColor.blue() && opModeIsActive()) {
                autoDrive(knockOffDistance, "Drive", .10);

                brake();

                jewelSlapper.setPosition(.9);

                autoDrive(knockOffDistance+25, "Reverse", .10);

                motor1.setPower(0);
                motor2.setPower(0);

                brake();
            }
        }

        if (AllianceColor == "Red")

        {

            if (sensorColor.red() < sensorColor.blue() && opModeIsActive()) {
                autoDrive(knockOffDistance, "Drive", .10);

                brake();

                jewelSlapper.setPosition(.9);

                autoDrive(knockOffDistance+25, "Reverse", .10);

                motor1.setPower(0);
                motor2.setPower(0);

                brake();
            }

            if (sensorColor.red() > sensorColor.blue() && opModeIsActive()) {
                autoDrive(knockOffDistance, "Reverse", .10);

                brake();

                jewelSlapper.setPosition(.9);

                autoDrive(knockOffDistance-25, "Drive", .10);

                motor1.setPower(0);
                motor2.setPower(0);

                brake();
            }
        }

        brake();

        sleep(1000);
    }

    public void autoDrive(int distance, String direction, double speed)

    {
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        int startPosition = motor1.getCurrentPosition();

        if(direction == "Drive")
        {
            while(startPosition + distance > motor1.getCurrentPosition() && opModeIsActive())
            {
                motor1.setPower(speed);
                motor2.setPower(controlMotor2.getSpeed()*speed);
            }
        }

        if(direction == "Reverse")
        {
            while(startPosition - distance < motor1.getCurrentPosition() && opModeIsActive())
            {
                motor1.setPower(-speed);
                motor2.setPower(-speed*controlMotor2.getSpeed());
            }
        }
        if(direction == "Right")
        {
            while(startPosition - distance < motor1.getCurrentPosition() && opModeIsActive())
            {
                motor1.setPower(-speed);
                motor2.setPower(speed*controlMotor2.getSpeed());
            }
        }
        if(direction == "Left")
        {
            while(startPosition + distance > motor1.getCurrentPosition() && opModeIsActive())
            {
                motor1.setPower(speed);
                motor2.setPower(-speed*controlMotor2.getSpeed());
            }
        }
    }

    public void autoCustomDrive(String direction, double speed)
    {
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        int startPosition = motor1.getCurrentPosition();

        if(direction == "180")
        {
            while(startPosition - 3000 < motor1.getCurrentPosition() && opModeIsActive())
            {
                motor1.setPower(-speed);
                motor2.setPower(speed*controlMotor2.getSpeed());
            }
        }

        if(direction == "Right")
        {
            while(startPosition - 1500 < motor1.getCurrentPosition() && opModeIsActive())
            {
                motor1.setPower(-speed);
                motor2.setPower(speed*controlMotor2.getSpeed());
            }
        }

        if(direction == "Left")
        {
            while(startPosition + 1500 > motor1.getCurrentPosition() && opModeIsActive())
            {
                motor1.setPower(speed);
                motor2.setPower(-speed*controlMotor2.getSpeed());
            }
        }

        if(direction == "Reverse")
        {
            if(direction == "Reverse")
            {
                while(startPosition - 1000 < motor1.getCurrentPosition() && opModeIsActive())
                {
                    motor1.setPower(-speed);
                    motor2.setPower(-speed*controlMotor2.getSpeed());
                }
            }
        }

        if(direction == "Drive")
        {
            while(startPosition + 1000 > motor1.getCurrentPosition() && opModeIsActive())
            {
                motor1.setPower(speed);
                motor2.setPower(controlMotor2.getSpeed()*speed);
            }
        }
    }

    public void brake()

    {
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motor1.setTargetPosition(motor1.getCurrentPosition());
        motor2.setTargetPosition(motor2.getCurrentPosition());
    }

    private void initGripper() {
        rightGrip = hardwareMap.servo.get("right_grip");
        leftGrip = hardwareMap.servo.get("left_grip");
    }

    private void initLifter()
    {
        lift = hardwareMap.dcMotor.get("LiftMotor");

        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        zeroPoint = lift.getCurrentPosition();
        lift.setPower(0.50);
    }

    private void initVuforia()
    {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AY2Sj5T/////AAAAGbK1+PNyJk13kxB6iOR0OXeFpCnuzJkxQuNxbOeGM1V9Ax0U4auJJxn4JivkjM8f/84A9epsY1c7TUVl+OleOZKhN8Ha6cliyNFIMiycgHaNTSe133CIx6Kzcq1rFZcpzsFjOwvsS6fAyZs0GntOkVQIxeTOphNvJof69RTez61GR7SCfRCEQ+kFQh9whm7i8iRf7algPeEMXOQ8TT1iG/kiGorMqtwkMXuwqBBQ8aB/5TKlMavr7am3tpDEF+HXEhOAIz6nfVK8XuzRAC8GgU66vu4Ylu+Hau113OyBh9Kl4jgKyPeVN9VeImFMGu/HjsnZ9wumZLqGfhoNX3EJ3ureS/vFveah4wOawzCnjRTy";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        parameters.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
    }

    private void VuforiaReturnMethod()
    {
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);

        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        relicTrackables.activate();
        boolean RunVuforia = true;
        while (opModeIsActive() && RunVuforia && time < 7) {
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);

            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {

                telemetry.addData("VuMark", "%s visible", vuMark);

                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) relicTemplate.getListener()).getPose();
                telemetry.addData("Pose", format(pose));

                if (pose != null) {
                    VectorF trans = pose.getTranslation();
                    Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

                    // Extract the X, Y, and Z components of the offset of the target relative to the robot
                    double tX = trans.get(0);
                    double tY = trans.get(1);
                    double tZ = trans.get(2);

                    // Extract the rotational components of the target relative to the robot
                    double rX = rot.firstAngle;
                    double rY = rot.secondAngle;
                    double rZ = rot.thirdAngle;
                }
                if (vuMark == RelicRecoveryVuMark.CENTER) {
                    CryptoboxCipherColumnNumber = "Center";
                    telemetry.addData("Read It", "Dad is Centered");
                    telemetry.update();
                    RunVuforia = false;
                } else if (vuMark == RelicRecoveryVuMark.RIGHT) {
                    CryptoboxCipherColumnNumber = "Right";
                    telemetry.addData("Read It", "Dad is RIGHT");
                    telemetry.update();
                    RunVuforia = false;
                } else if (vuMark == RelicRecoveryVuMark.LEFT) {
                    CryptoboxCipherColumnNumber = "Left";
                    telemetry.addData("Read It", "Dad has LEFT");
                    telemetry.update();
                    RunVuforia = false;
                }
            } else {
                telemetry.addData("VuMark", "not visible");
                telemetry.update();
                CryptoboxCipherColumnNumber = "Center";
            }

        }
    }

    String format(OpenGLMatrix transformationMatrix)

    {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}