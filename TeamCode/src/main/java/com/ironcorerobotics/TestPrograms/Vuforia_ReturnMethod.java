package com.ironcorerobotics.TestPrograms;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

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
 * Created by Fam on 12/1/2017.
 */

@Autonomous(name = "Vuforia_ReturnMethod", group = "Test")
@Disabled


public class Vuforia_ReturnMethod extends LinearOpMode
{
    String CryptoboxCipherColumnNumber;

    public static final String TAG = "Vuforia VuMark Sample";

    OpenGLMatrix lastLocation = null;

    VuforiaLocalizer vuforia;

    public void runOpMode() throws InterruptedException {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AY2Sj5T/////AAAAGbK1+PNyJk13kxB6iOR0OXeFpCnuzJkxQuNxbOeGM1V9Ax0U4auJJxn4JivkjM8f/84A9epsY1c7TUVl+OleOZKhN8Ha6cliyNFIMiycgHaNTSe133CIx6Kzcq1rFZcpzsFjOwvsS6fAyZs0GntOkVQIxeTOphNvJof69RTez61GR7SCfRCEQ+kFQh9whm7i8iRf7algPeEMXOQ8TT1iG/kiGorMqtwkMXuwqBBQ8aB/5TKlMavr7am3tpDEF+HXEhOAIz6nfVK8XuzRAC8GgU66vu4Ylu+Hau113OyBh9Kl4jgKyPeVN9VeImFMGu/HjsnZ9wumZLqGfhoNX3EJ3ureS/vFveah4wOawzCnjRTy";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        telemetry.addData(">", "Press Play to start");
        telemetry.update();
        waitForStart();

        relicTrackables.activate();

        while (opModeIsActive()) {

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

                    if (vuMark == RelicRecoveryVuMark.CENTER) {
                        CryptoboxCipherColumnNumber = "Center";
                    }
                    if (vuMark == RelicRecoveryVuMark.RIGHT) {
                        CryptoboxCipherColumnNumber = "Right";
                    }
                    if (vuMark == RelicRecoveryVuMark.LEFT) {
                        CryptoboxCipherColumnNumber = "Left";
                    }

                }
            } else {
                telemetry.addData("VuMark", "not visible");
            }

            telemetry.update();
        }


    }


    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}




