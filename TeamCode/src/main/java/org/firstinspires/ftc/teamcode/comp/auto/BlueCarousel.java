package org.firstinspires.ftc.teamcode.comp.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.comp.HWMapTeleOp;
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

import org.firstinspires.ftc.teamcode.comp.HWMapAuto;
import org.openftc.easyopencv.OpenCvWebcam;

@Autonomous(name = "Blue Carousel", group = "Autonomous")
//@Disabled
public class BlueCarousel extends LinearOpMode {

    //Runtime
    private ElapsedTime runtime = new ElapsedTime();

    //Vision
    OpenCvWebcam webcam;
    public static String position;
    SkystoneDeterminationPipeline pipeline;

    //Road Runner
    static HWMapAuto drive;

    //Lift + Non RR HW Map
    HWMapTeleOp robot = new HWMapTeleOp();


    //Display on Dashboard
    private FtcDashboard dashboard;

    @Override
    public void runOpMode() {
        //Hardware map
        drive = new HWMapAuto(hardwareMap);
        robot.init(hardwareMap);

        //Set starting position
        Pose2d startPose = new Pose2d(-39, 61, Math.toRadians(270));
        drive.setPoseEstimate(startPose);

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

//PATH CONSTANTS

//
////CASE A INIT START
        Trajectory trajectoryA1 = drive.trajectoryBuilder(startPose)
                .strafeTo(new Vector2d(-39, 40))
                .build();
        Trajectory trajectoryA2 = drive.trajectoryBuilder(trajectoryA1.end().plus(new Pose2d(0, 0, Math.toRadians(90))), false)
                .strafeTo(new Vector2d(-29, 40))
                .build();
////CASE A INIT END
//
////CASE B INIT START
//        Trajectory trajectoryB1 = drive.trajectoryBuilder(startPose)
//                .strafeTo(new Vector2d(-6, -12))
//                .build();
////CASE B INIT END

//CASE C INIT START

//        Trajectory trajectoryC1 = drive.trajectoryBuilder(startPose)
//                .strafeTo(new Vector2d(-6, -12))
//                .build();
//        Trajectory trajectoryC2 = drive.trajectoryBuilder(trajectoryC1.end())
//                .strafeTo(new Vector2d(59, -45.5))
//                .build();
//        Trajectory trajectoryC3 = drive.trajectoryBuilder(trajectoryC2.end())
//                .strafeTo(new Vector2d(-6, -31))
//                .build();
//        Trajectory trajectoryC4 = drive.trajectoryBuilder(trajectoryC3.end())
//                .strafeTo(new Vector2d(-18, -31))
//                .build();
//        Trajectory trajectoryC5 = drive.trajectoryBuilder(trajectoryC4.end())
//                .strafeTo(new Vector2d(-22, -31))
//                .build();
//        Trajectory trajectoryC6 = drive.trajectoryBuilder(trajectoryC5.end())
//                .strafeTo(new Vector2d(-12, -31))
//                .build();
//        Trajectory trajectoryC7 = drive.trajectoryBuilder(trajectoryC6.end())
//                .strafeTo(new Vector2d(-30, -31))
//                .build();
//        Trajectory trajectoryC8 = drive.trajectoryBuilder(trajectoryC7.end())
//                .strafeTo(new Vector2d(-37, -31))
//                .build();
//        Trajectory trajectoryC9 = drive.trajectoryBuilder(trajectoryC8.end())
//                .strafeTo(new Vector2d(-14, -31))
//                .build();
//        Trajectory trajectoryC10 = drive.trajectoryBuilder(trajectoryC9.end())
//                .strafeTo(new Vector2d(-42, -21))
//                .build();
//        Trajectory trajectoryC105 = drive.trajectoryBuilder(trajectoryC10.end())
//                .strafeTo(new Vector2d(-43, -30.2))
//                .build();
//        Trajectory trajectoryC11 = drive.trajectoryBuilder(trajectoryC105.end())
//                .strafeTo(new Vector2d(61, -39))
//                .build();
//        Trajectory trajectoryC12 = drive.trajectoryBuilder(trajectoryC11.end())
//                .strafeTo(new Vector2d(7, -34))
//                .build();
//CASE C INIT END


        telemetry.addData("Status", "Pipeline Initializing");
        telemetry.update();

        waitForStart();
        if (isStopRequested()) return;
        telemetry.update();


        String route = "LEFT";
//        String route = position;
        telemetry.addData("Ring Position", position);
        telemetry.update();
        FtcDashboard.getInstance().stopCameraStream();

        switch (route) {
            case "LEFT":
                drive.followTrajectory(trajectoryA1);
                drive.turn(Math.toRadians(100));
                liftUp(0.7, 15, 5);
                drive.followTrajectory(trajectoryA2);
                deposit();
                telemetry.addData("Path Left", "Complete");
                telemetry.update();
                break;
            case "CENTER":
//                drive.followTrajectory(trajectoryB1);

                telemetry.addData("Path Center", "Complete");
                telemetry.update();
                break;
            case "RIGHT":
//                drive.followTrajectory(trajectoryC1);

                telemetry.addData("Path Right", "Complete");
                telemetry.update();
                break;
            default:
                telemetry.addData("Detection:", "Failed");
        }

    }

    public void liftUp(double speed, double inches, double timeoutS) {
        int newTarget;

        robot.liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        if (opModeIsActive()) {

            newTarget = robot.liftMotor.getCurrentPosition() + (int)(inches * 200);
            robot.liftMotor.setTargetPosition(newTarget);
            robot.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            runtime.reset();
            robot.liftMotor.setPower(Math.abs(speed));
            while (opModeIsActive() && (runtime.seconds() < timeoutS) && (robot.liftMotor.isBusy())) {
                telemetry.addData("Path1", newTarget);
                telemetry.addData("Path2", robot.liftMotor.getCurrentPosition());
                telemetry.update();
            }

            robot.liftMotor.setPower(0);
        }
    }

    public void deposit(){
        robot.dropServo.setPosition(0);
        sleep(1000);
        robot.dropServo.setPosition(1);
        sleep(1000);
    }

    // Pipeline class
    public static class SkystoneDeterminationPipeline extends OpenCvPipeline
    {

        static final Scalar BLUE = new Scalar(0, 0, 255);
        static final Scalar GREEN = new Scalar(0, 255, 0);

        static final Point REGION1_TOPLEFT_ANCHOR_POINT = new Point(180,290);
        static final Point REGION2_TOPLEFT_ANCHOR_POINT = new Point(590,308);
        static final Point REGION3_TOPLEFT_ANCHOR_POINT = new Point(973,308);
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