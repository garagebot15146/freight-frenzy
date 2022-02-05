package org.firstinspires.ftc.teamcode.Lab;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Settings.DetectionSettings;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

import org.openftc.easyopencv.OpenCvWebcam;

@Autonomous(name = "Detect Test", group = "Autonomous")
//@Disabled
public class DetectTest extends LinearOpMode {

    //Runtime
    private ElapsedTime runtime = new ElapsedTime();

    //Vision
    OpenCvWebcam webcam;
    public static String position;
    SkystoneDeterminationPipeline pipeline;


    //Display on Dashboard
    private FtcDashboard dashboard;

    @Override
    public void runOpMode() {
        // Camera Init
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        webcam.setPipeline(new SkystoneDeterminationPipeline());
        FtcDashboard.getInstance().startCameraStream(webcam, 30);
        webcam.setMillisecondsPermissionTimeout(2500);
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(1280, 720, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {
            }
        });



        telemetry.addData("Status", "Pipeline Initializing");
        telemetry.update();

        waitForStart();
        if (isStopRequested()) return;
        telemetry.update();


        String route = "LEFT";
//        String route = position;
//        telemetry.addData("Ring Position", position);
        telemetry.update();
        FtcDashboard.getInstance().stopCameraStream();

        switch (route) {
            case "LEFT":
                telemetry.addData("Path Left", "Complete");
                telemetry.update();
                pause(2);
                break;
            case "CENTER":
                telemetry.addData("Path Center", "Complete");
                telemetry.update();
                pause(2);
                break;
            case "RIGHT":
                telemetry.addData("Path Right", "Complete");
                telemetry.update();
                pause(2);
                break;
            default:
                telemetry.addData("Path Default", "Complete");
                telemetry.update();
                pause(2);
        }

    }

    public void pause(double seconds){
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < seconds) {
        }

    }

    // Pipeline class
    public static class SkystoneDeterminationPipeline extends OpenCvPipeline
    {

        static final Scalar BLUE = new Scalar(0, 0, 255);
        static final Scalar GREEN = new Scalar(0, 255, 0);

        static DetectionSettings box = new DetectionSettings();

        //Blue Carousel
//        static final Point REGION1_TOPLEFT_ANCHOR_POINT = new Point(box.getBlueCarouselLeftX(),box.getBlueCarouselLeftY());
//        static final Point REGION2_TOPLEFT_ANCHOR_POINT = new Point(box.getBlueCarouselCenterX(),box.getBlueCarouselCenterY());
//        static final Point REGION3_TOPLEFT_ANCHOR_POINT = new Point(box.getBlueCarouselRightX(),box.getBlueCarouselRightY());

//        //Red Carousel
//        static final Point REGION1_TOPLEFT_ANCHOR_POINT = new Point(box.getRedCarouselLeftX(),box.getRedCarouselLeftY());
//        static final Point REGION2_TOPLEFT_ANCHOR_POINT = new Point(box.getRedCarouselCenterX(),box.getRedCarouselCenterY());
//        static final Point REGION3_TOPLEFT_ANCHOR_POINT = new Point(box.getRedCarouselRightX(),box.getRedCarouselRightY());

        //Blue Warehouse
        static final Point REGION1_TOPLEFT_ANCHOR_POINT = new Point(box.getBlueWarehouseLeftX(),box.getBlueWarehouseLeftY());
        static final Point REGION2_TOPLEFT_ANCHOR_POINT = new Point(box.getBlueWarehouseCenterX(),box.getBlueWarehouseCenterY());
        static final Point REGION3_TOPLEFT_ANCHOR_POINT = new Point(box.getBlueWarehouseRightX(),box.getBlueWarehouseRightY());

//        //Red Warehouse
//        static final Point REGION1_TOPLEFT_ANCHOR_POINT = new Point(box.getRedWarehouseLeftX(),box.getRedWarehouseLeftY());
//        static final Point REGION2_TOPLEFT_ANCHOR_POINT = new Point(box.getRedWarehouseCenterX(),box.getRedWarehouseCenterY());
//        static final Point REGION3_TOPLEFT_ANCHOR_POINT = new Point(box.getRedWarehouseRightX(),box.getRedWarehouseRightY());

        static final int REGION_WIDTH = 50;
        static final int REGION_HEIGHT = 50;

        Point region1_pointA = new Point(
                REGION1_TOPLEFT_ANCHOR_POINT.x,
                REGION1_TOPLEFT_ANCHOR_POINT.y);
        Point region1_pointB = new Point(
                REGION1_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
                REGION1_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);
        Point region2_pointA = new Point(
                REGION2_TOPLEFT_ANCHOR_POINT.x,
                REGION2_TOPLEFT_ANCHOR_POINT.y);
        Point region2_pointB = new Point(
                REGION2_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
                REGION2_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);
        Point region3_pointA = new Point(
                REGION3_TOPLEFT_ANCHOR_POINT.x,
                REGION3_TOPLEFT_ANCHOR_POINT.y);
        Point region3_pointB = new Point(
                REGION3_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
                REGION3_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);

        /*
         * Working variables
         */
        Mat region1_Cb, region2_Cb, region3_Cb;
        Mat YCrCb = new Mat();
        Mat Cb = new Mat();
        int avg1, avg2, avg3;

        void inputToCb(Mat input)
        {
            Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
            Core.extractChannel(YCrCb, Cb, 2);
        }

        @Override
        public void init(Mat firstFrame)
        {
            inputToCb(firstFrame);
            region1_Cb = Cb.submat(new Rect(region1_pointA, region1_pointB));
            region2_Cb = Cb.submat(new Rect(region2_pointA, region2_pointB));
            region3_Cb = Cb.submat(new Rect(region3_pointA, region3_pointB));
        }

        @Override
        public Mat processFrame(Mat input)
        {
            inputToCb(input);
            avg1 = (int) Core.mean(region1_Cb).val[0];
            avg2 = (int) Core.mean(region2_Cb).val[0];
            avg3 = (int) Core.mean(region3_Cb).val[0];

            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region1_pointA, // First point which defines the rectangle
                    region1_pointB, // Second point which defines the rectangle
                    BLUE, // The color the rectangle is drawn in
                    2); // Thickness of the rectangle lines

            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region2_pointA, // First point which defines the rectangle
                    region2_pointB, // Second point which defines the rectangle
                    BLUE, // The color the rectangle is drawn in
                    2); // Thickness of the rectangle lines

            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region3_pointA, // First point which defines the rectangle
                    region3_pointB, // Second point which defines the rectangle
                    BLUE, // The color the rectangle is drawn in
                    2); // Thickness of the rectangle lines

            int maxOneTwo = Math.max(avg1, avg2);
            int max = Math.max(maxOneTwo, avg3);

            if(max == avg1) // Was it from region 1?
            {
                position = "LEFT"; // Record our analysis

                Imgproc.rectangle(
                        input, // Buffer to draw on
                        region1_pointA, // First point which defines the rectangle
                        region1_pointB, // Second point which defines the rectangle
                        GREEN, // The color the rectangle is drawn in
                        -1); // Negative thickness means solid fill
            }
            else if(max == avg2) // Was it from region 2?
            {
                position = "CENTER"; // Record our analysis

                Imgproc.rectangle(
                        input, // Buffer to draw on
                        region2_pointA, // First point which defines the rectangle
                        region2_pointB, // Second point which defines the rectangle
                        GREEN, // The color the rectangle is drawn in
                        -1); // Negative thickness means solid fill
            }
            else if(max == avg3) // Was it from region 3?
            {
                position = "RIGHT"; // Record our analysis

                Imgproc.rectangle(
                        input, // Buffer to draw on
                        region3_pointA, // First point which defines the rectangle
                        region3_pointB, // Second point which defines the rectangle
                        GREEN, // The color the rectangle is drawn in
                        -1); // Negative thickness means solid fill
            }

            return input;
        }
    }


}