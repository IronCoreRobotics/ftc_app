package com.ironcorerobotics.TestPrograms;

import com.ironcorerobotics.ControlClasses.MotorControl;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
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
 * Created by Fam on 10/31/2017.
 */

@Autonomous(name = "TestAutonomous", group = "Test")
//@Disabled


public class TestProgramRunsAutonomous extends LinearOpMode

{
    int zeroPoint;
    DcMotor motor1;
    DcMotor motor2;
    Servo jewelSlapper;
    Servo rightGrip;
    Servo leftGrip;
    DcMotor lift;
    ColorSensor sensorColor;
    DistanceSensor sensorDistance;
    MotorControl controlMotor2 = new MotorControl(-1);
    String CryptoboxCipherColumnNumber;

    public static final String TAG = "Vuforia VuMark Sample";

    OpenGLMatrix lastLocation = null;

    VuforiaLocalizer vuforia;


    public void runOpMode() throws InterruptedException {
        motor1 = hardwareMap.dcMotor.get("rightside_Motor");
        motor2 = hardwareMap.dcMotor.get("leftside_Motor");
        jewelSlapper = hardwareMap.servo.get("drawbridge_winch");
        sensorColor = hardwareMap.get(ColorSensor.class, "sensor_color_distance");
        sensorDistance = hardwareMap.get(DistanceSensor.class, "sensor_color_distance");

        waitForStart();

        Vuforia_ReturnMethod();

    }

    public String Vuforia_ReturnMethod()

    {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AY2Sj5T/////AAAAGbK1+PNyJk13kxB6iOR0OXeFpCnuzJkxQuNxbOeGM1V9Ax0U4auJJxn4JivkjM8f/84A9epsY1c7TUVl+OleOZKhN8Ha6cliyNFIMiycgHaNTSe133CIx6Kzcq1rFZcpzsFjOwvsS6fAyZs0GntOkVQIxeTOphNvJof69RTez61GR7SCfRCEQ+kFQh9whm7i8iRf7algPeEMXOQ8TT1iG/kiGorMqtwkMXuwqBBQ8aB/5TKlMavr7am3tpDEF+HXEhOAIz6nfVK8XuzRAC8GgU66vu4Ylu+Hau113OyBh9Kl4jgKyPeVN9VeImFMGu/HjsnZ9wumZLqGfhoNX3EJ3ureS/vFveah4wOawzCnjRTy";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        parameters.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);

        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        telemetry.addData(">", "Press Play to start");
        telemetry.update();

        relicTrackables.activate();

        while (opModeIsActive() ){
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
                    RunMainAutonomousCode();
                    stop();
                } else if (vuMark == RelicRecoveryVuMark.RIGHT) {
                        CryptoboxCipherColumnNumber = "Right";
                        telemetry.addData("Read It", "Dad is RIGHT");
                        telemetry.update();
                    RunMainAutonomousCode();
                    stop();
                } else if (vuMark == RelicRecoveryVuMark.LEFT) {
                        CryptoboxCipherColumnNumber = "Left";
                        telemetry.addData("Read It", "Dad has LEFT");
                        telemetry.update();
                    RunMainAutonomousCode();
                    stop();
                }
            } else {
                telemetry.addData("VuMark", "not visible");
                telemetry.update();
            }

        }
        telemetry.addData("Almost out", "I am almost out of the loop");
        telemetry.update();
        return CryptoboxCipherColumnNumber;
    }


    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }

    private void RunMainAutonomousCode()
    {
        motor1.setPower(-1.00);
        motor2.setPower(-1.00);
        sleep(1000);
        if(Vuforia_ReturnMethod() == "Center")
        {
            motor1.setPower(1.00);
            motor2.setPower(1.00);
            sleep(500);
        }
        if(Vuforia_ReturnMethod() == "Right")
        {
            motor1.setPower(1.00);
            sleep(2000);
        }
        if(Vuforia_ReturnMethod() == "Left")
        {
            motor1.setPower(1.00);
            sleep(500);
        }
        sleep(1000);
    }
}





