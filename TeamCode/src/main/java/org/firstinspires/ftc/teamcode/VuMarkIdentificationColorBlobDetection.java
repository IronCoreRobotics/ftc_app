/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.firstinspires.ftc.teamcode;

import android.graphics.Bitmap;
import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.vuforia.Frame;
import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcontroller.external.samples.ConceptVuforiaNavigation;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.vision.ColorBlobDetector;
import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.List;
import java.util.concurrent.BlockingQueue;



// good post for how to intercept vuforia:  https://ftcforum.usfirst.org/forum/ftc-technology/android-studio/49652-how-to-use-opencv-with-vuforia



/**
 * This OpMode illustrates the basics of using the Vuforia engine to determine
 * the identity of Vuforia VuMarks encountered on the field. The code is structured as
 * a LinearOpMode. It shares much structure with {@link ConceptVuforiaNavigation}; we do not here
 * duplicate the core Vuforia documentation found there, but rather instead focus on the
 * differences between the use of Vuforia for navigation vs VuMark identification.
 *
 * @see ConceptVuforiaNavigation
 * @see VuforiaLocalizer
 * @see VuforiaTrackableDefaultListener
 * see  ftc_app/doc/tutorial/FTC_FieldCoordinateSystemDefinition.pdf
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 *
 * IMPORTANT: In order to use this OpMode, you need to obtain your own Vuforia license key as
 * is explained in {@link ConceptVuforiaNavigation}.
 */

@Autonomous(name="Concept: VuMark and color detect", group ="Concept")
//@Disabled
public class VuMarkIdentificationColorBlobDetection extends LinearOpMode {

    public static final String TAG = "VuforiaOpenCV";

    OpenGLMatrix lastLocation = null;

    // set color for red and blue detectors
    Scalar blueHolorHsv = null;
    Scalar redHolorHsv = null;
    private ColorBlobDetector    blueDetector = null;
    private ColorBlobDetector    redDetector = null;







    public void setupOpenCV() {
        blueDetector = new ColorBlobDetector();
        redDetector = new ColorBlobDetector();

        // blue ball
        // Touched rgba color: (23.0, 117.0, 171.0, 255.0)
        // Touched hsva color: (143.15625, 220.328125, 170.765625, 0.0)

        // red ball
        // Touched rgba color: (221.0, 51.0, 51.0, 255.0)
        // Touched hsva color: (255.0, 195.65625, 221.40625, 0.0)


        // cura icon blue
        // Touched rgba color: (38.0, 51.0, 87.0, 255.0)
        // Touched hsva color: (158.015625, 144.859375, 87.109375, 0.0)


        // set color for red and blue detectors
        blueHolorHsv = new Scalar(158.015625, 144.859375, 87.109375, 0.0);     //(h,s,v,a);
        redHolorHsv = new Scalar(255);     //(h,s,v,a);


        blueDetector.setHsvColor(blueHolorHsv);
        redDetector.setHsvColor(redHolorHsv);

    }



    //    private Mat                  mRgba;
//    private Scalar               mBlobColorRgba;
//    private Scalar               mBlobColorHsv;
//    private ColorBlobDetector    mDetector;
    //        mDetector = new ColorBlobDetector();
//
//
//    public void onCameraViewStarted(int width, int height) {
//        mRgba = new Mat(height, width, CvType.CV_8UC4);

//        mBlobColorRgba = new Scalar(255);
//        mBlobColorHsv = new Scalar(255);
//    }


    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    VuforiaLocalizer vuforia;

    @Override public void runOpMode() {



/*
        BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(hardwareMap.appContext) {
            @Override
            public void onManagerConnected(int status) {
                switch (status) {
                    case LoaderCallbackInterface.SUCCESS:
                    {
                        Log.i(TAG, "OpenCV loaded successfully");
                        setupOpenCV();
//                        mOpenCvCameraView.enableView();
//                        mOpenCvCameraView.setOnTouchListener(ColorBlobDetectionActivity.this);
                    } break;
                    default:
                    {
                        super.onManagerConnected(status);
                    } break;
                }
            }
        };*/

        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "fail::::  Internal OpenCV library not found. Using OpenCV Manager for initialization");
            //OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, hardwareMap.appContext, mLoaderCallback);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            setupOpenCV();
            //mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }






        /*
         * To start up Vuforia, tell it the view that we wish to use for camera monitor (on the RC phone);
         * If no camera monitor is desired, use the parameterless constructor instead (commented out below).
         */
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);






        // OR...  Do Not Activate the Camera Monitor View, to save power
        // VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        /*
         * IMPORTANT: You need to obtain your own license key to use Vuforia. The string below with which
         * 'parameters.vuforiaLicenseKey' is initialized is for illustration only, and will not function.
         * A Vuforia 'Development' license key, can be obtained free of charge from the Vuforia developer
         * web site at https://developer.vuforia.com/license-manager.
         *
         * Vuforia license keys are always 380 characters long, and look as if they contain mostly
         * random data. As an example, here is a example of a fragment of a valid key:
         *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
         * Once you've obtained a license key, copy the string from the Vuforia web site
         * and paste it in to your code onthe next line, between the double quotes.
         */
        parameters.vuforiaLicenseKey = "AcDvDvD/////AAAAGfBJop3/P0BumQC40OGRKDx3yjJXpwqiPkQmzaxXUdO2pHpiFLPmVqj1nHx7f7xZdC3rjU0DwoIX3+mIqPphNtD3BGUXhRNdUQ6FIYoY4PHAM5soQCDICPz0Zon6NPeJR5JinC/DxUR7NGogzfwyxO6g1GkIPTy5ABJNDR5ttHDGCnQi5lPBKyk0whif7YGNezT9gxuX5G9hL+vs1fysn/1+r5iKo9/JAkKjnIXPbLL+OzfjhGesRrOA9U4C9baZRPdtM6T+vuvRxUAiUsR5EGxw94+DpGDF+kaLyB46P1aon1uDPYSHPnnDuaS9ZR6h78j2/Rc9EvJEWB7H4Zp9y7Yvxx0t39/rBgGdCk/mbcd/";

        /*
         * We also indicate which camera on the RC that we wish to use.
         * Here we chose the back (HiRes) camera (for greater range), but
         * for a competition robot, the front camera might be more convenient.
         */
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);
        vuforia.setFrameQueueCapacity(2);



        /**
         * Load the data set containing the VuMarks for Relic Recovery. There's only one trackable
         * in this data set: all three of the VuMarks in the game were created from this one template,
         * but differ in their instance id information.
         * @see VuMarkInstanceId
         */
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        telemetry.addData(">", "Press Play to start");
        telemetry.update();



        waitForStart();

        relicTrackables.activate();

        while (opModeIsActive()) {

            /**
             * See if any of the instances of {@link relicTemplate} are currently visible.
             * {@link RelicRecoveryVuMark} is an enum which can have the following values:
             * UNKNOWN, LEFT, CENTER, and RIGHT. When a VuMark is visible, something other than
             * UNKNOWN will be returned by {@link RelicRecoveryVuMark#from(VuforiaTrackable)}.
             */
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {

                /* Found an instance of the template. In the actual game, you will probably
                 * loop until this condition occurs, then move on to act accordingly depending
                 * on which VuMark was visible. */
                telemetry.addData("VuMark", "%s visible", vuMark);

                /* For fun, we also exhibit the navigational pose. In the Relic Recovery game,
                 * it is perhaps unlikely that you will actually need to act on this pose information, but
                 * we illustrate it nevertheless, for completeness. */
                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getPose();
                telemetry.addData("Pose", format(pose));

                /* We further illustrate how to decompose the pose into useful rotational and
                 * translational components */
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
            }
            else {
                telemetry.addData("VuMark", "not visible");
            }

            telemetry.update();




            Image rgbImage = getImage();
            findBalls(rgbImage);



        }
    }

    private void findBalls(Image rgbImage) {
        if (rgbImage != null) {


            if (blueDetector != null) {
                /*rgb is now the Image object that weve used in the video*/
                Bitmap bitmap = Bitmap.createBitmap(rgbImage.getWidth(), rgbImage.getHeight(), Bitmap.Config.RGB_565);
                bitmap.copyPixelsFromBuffer(rgbImage.getPixels());

                //put the image into a MAT for OpenCV
                Mat opencvMatImage = new Mat(rgbImage.getWidth(), rgbImage.getHeight(), CvType.CV_8UC4);
                Utils.bitmapToMat(bitmap, opencvMatImage);

                blueDetector.process(opencvMatImage);
                List<MatOfPoint> contours = blueDetector.getContours();
                Log.e(TAG, "Contours count: " + contours.size());
//               Imgproc.boundingRect(opencvMatImage);
            }


        }
    }


    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }


    public Image getImage() {
        try (MyCloseableFrame frame = new MyCloseableFrame(vuforia.getFrameQueue().take())) {
            long numImages = frame.getNumImages();
            for (int i = 0; i < numImages; i++) {
                final Image img = frame.getImage(i);
                int fmt = img.getFormat();
                if (fmt == PIXEL_FORMAT.RGB565) {
                    return img;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    class MyCloseableFrame extends VuforiaLocalizer.CloseableFrame implements AutoCloseable {
        public MyCloseableFrame(VuforiaLocalizer.CloseableFrame frame) {
            super(frame);
        }
    }
}
