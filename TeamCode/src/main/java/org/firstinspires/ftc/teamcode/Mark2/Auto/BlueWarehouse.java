package org.firstinspires.ftc.teamcode.Mark2.Auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Settings.DetectionSettings;
import org.firstinspires.ftc.teamcode.Settings.HWMapMecanum;
import org.firstinspires.ftc.teamcode.Settings.HWMapTeleOp;
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

import org.firstinspires.ftc.teamcode.Settings.HWMapTank;
import org.openftc.easyopencv.OpenCvWebcam;

@Autonomous(name = "Blue Warehouse", group = "Autonomous")
//@Disabled
public class BlueWarehouse extends LinearOpMode {

    //Runtime
    private ElapsedTime runtime = new ElapsedTime();

    //    //Vision
    OpenCvWebcam webcam;
    public static String position;
    SkystoneDeterminationPipeline pipeline;

    //Road Runner
    static HWMapMecanum drive;

    //Lift + Non RR HW Map
    HWMapTeleOp robot = new HWMapTeleOp();


    //Display on Dashboard
    private FtcDashboard dashboard;

    @Override
    public void runOpMode() {
        //Hardware map
        drive = new HWMapMecanum(hardwareMap);
        robot.init(hardwareMap);

        //Set starting position
        Pose2d startPose = new Pose2d(34, 64, Math.toRadians(270));
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

        String route = "CENTER";

//PATH CONSTANTS

//
////CASE Left INIT START
        Trajectory trajectoryA1 = drive.trajectoryBuilder(startPose)
                .lineToLinearHeading(new Pose2d(10, 41.8, Math.toRadians(30)))
                .build();
        Trajectory trajectoryA2 = drive.trajectoryBuilder(trajectoryA1.end())
                .lineToLinearHeading(new Pose2d(6, 74, Math.toRadians(220)))
                .build();
        Trajectory trajectoryA3 = drive.trajectoryBuilder(trajectoryA2.end().plus(new Pose2d(0,0, Math.toRadians(10))))
                .strafeTo(new Vector2d(50, 74))
                .build();
        Trajectory trajectoryA4 = drive.trajectoryBuilder(trajectoryA3.end())
                .strafeTo(new Vector2d(-62, 40))
                .build();
////CASE Left INIT END

////CASE Center INIT START
        Trajectory trajectoryB1 = drive.trajectoryBuilder(startPose)
                .strafeTo(new Vector2d(-39, 56))
                .build();
        Trajectory trajectoryB2 = drive.trajectoryBuilder(trajectoryB1.end())
                .strafeTo(new Vector2d(-39, 40))
                .build();
////CASE Center INIT END

//CASE Right INIT START
        Trajectory trajectoryC1 = drive.trajectoryBuilder(startPose)
                .strafeTo(new Vector2d(-39, 56))
                .build();
        Trajectory trajectoryC2 = drive.trajectoryBuilder(trajectoryC1.end())
                .strafeTo(new Vector2d(-39, 41))
                .build();
//CASE Right INIT END


        telemetry.addData("Status", "Pipeline Initializing");
        telemetry.update();

        //Start Moving
        waitForStart();
        if (isStopRequested()) return;
        telemetry.update();

        telemetry.update();
          route = "LEFT";
//        route = position;
        telemetry.addData("Ring Position", position);
        telemetry.update();
        FtcDashboard.getInstance().stopCameraStream();

        switch (route) {
            case "LEFT":
                drive.followTrajectory(trajectoryA1);
                liftUp(11.3, 3);
                deposit();
                liftDown();
                drive.followTrajectory(trajectoryA2);
                encoderDrive(0.5, -20, -20, 5);

                telemetry.addData("Path Left", "Complete");
                telemetry.update();
                break;
            case "CENTER":
                drive.followTrajectory(trajectoryA1);
                liftUp(9, 3);
                deposit();
                liftDown();
                drive.followTrajectory(trajectoryA2);
                encoderDrive(0.5, -20, -20, 5);

                telemetry.addData("Path Center", "Complete");
                telemetry.update();
                break;
            case "RIGHT":
                drive.followTrajectory(trajectoryA1);
                liftUp(6.3, 3);
                deposit();
                liftDown();
                drive.followTrajectory(trajectoryA2);
                encoderDrive(0.5, -20, -20, 5);

                telemetry.addData("Path Right", "Complete");
                telemetry.update();
                break;
            default:
                drive.followTrajectory(trajectoryA1);
                drive.turn(Math.toRadians(104));
                drive.followTrajectory(trajectoryA2);
                liftUp(11, 2);
                pause(1);
                deposit();
                liftDown();
                drive.followTrajectory(trajectoryA3);
                drive.turn(Math.toRadians(-70));
                drive.followTrajectory(trajectoryA4);
                encoderDrive(0.1, -2.2, -2.2, 3);
                duckBlue(3);
                drive.turn(Math.toRadians(-50));
                encoderDrive(0.2, 5, 5, 3);
                liftReset(1);

                telemetry.addData("Path Default", "Complete");
                telemetry.update();
        }

    }

    public void liftUp(double inches, double timeoutS) {
        robot.liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        int newTarget = (int) (inches * 200);

        robot.liftMotor.setTargetPosition(newTarget);
        runtime.reset();

        robot.liftMotor.setPower(1);

        robot.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        while (opModeIsActive() && robot.liftMotor.isBusy() && (runtime.seconds() < timeoutS)) {

        }
    }

    public void liftDown() {
        robot.liftMotor.setTargetPosition(0);
        runtime.reset();

        robot.liftMotor.setPower(0.3);

        robot.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (opModeIsActive() && robot.liftMotor.isBusy() && (runtime.seconds() < 5)) {

        }

        robot.liftMotor.setPower(0);
    }

    public void liftReset(double timeoutS){
        robot.liftMotor.setTargetPosition(0);
        runtime.reset();
        robot.liftMotor.setPower(0.3);
        robot.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (opModeIsActive() && robot.liftMotor.isBusy() && (runtime.seconds() < timeoutS)) {
        }
        robot.liftMotor.setPower(0);
    }

    public void deposit(){
        robot.dropServo.setPosition(0.3);
        pause(1);
        robot.dropServo.setPosition(0.75);
        telemetry.addData("Servo", "Worked");
    }

    public void pause(double seconds){
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < seconds) {
        }

    }

    public void duckBlue(double seconds){
        runtime.reset();
        robot.carouselMotor.setPower(-0.25);
        while (opModeIsActive() && runtime.seconds() < seconds) {
        }
        robot.carouselMotor.setPower(0);
    }
    public void encoderDrive(double speed, double leftInches, double rightInches, double timeoutS) {
        int newLeftFrontTarget;
        int newLeftRearTarget;
        int newRightFrontTarget;
        int newRightRearTarget;


        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftFrontTarget = robot.leftFrontMotor.getCurrentPosition() + (int)(leftInches * 50);
            newLeftRearTarget = robot.leftBackMotor.getCurrentPosition() + (int)(leftInches * 200);
            newRightFrontTarget = robot.rightFrontMotor.getCurrentPosition() + (int)(rightInches * 200);
            newRightRearTarget = robot.rightBackMotor.getCurrentPosition() + (int)(rightInches * 200);

            robot.leftFrontMotor.setTargetPosition(newLeftFrontTarget);
            robot.leftBackMotor.setTargetPosition(newLeftRearTarget);
            robot.rightFrontMotor.setTargetPosition(newRightFrontTarget);
            robot.rightBackMotor.setTargetPosition(newRightRearTarget);

            // Turn On RUN_TO_POSITION
            robot.leftFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.leftBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.leftFrontMotor.setPower(speed);
            robot.leftBackMotor.setPower(speed);
            robot.rightFrontMotor.setPower(speed);
            robot.rightBackMotor.setPower(speed);

            while (opModeIsActive() && (runtime.seconds() < timeoutS) && (robot.leftFrontMotor.isBusy() && robot.leftBackMotor.isBusy() && robot.rightFrontMotor.isBusy() && robot.rightBackMotor.isBusy())) {
            }

            // Stop all motion;
            robot.leftFrontMotor.setPower(0);
            robot.leftBackMotor.setPower(0);
            robot.rightFrontMotor.setPower(0);
            robot.rightBackMotor.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.leftBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    // Pipeline class
    public static class SkystoneDeterminationPipeline extends OpenCvPipeline
    {

        static final Scalar BLUE = new Scalar(0, 0, 255);
        static final Scalar GREEN = new Scalar(0, 255, 0);

        static DetectionSettings box = new DetectionSettings();
        static final Point REGION1_TOPLEFT_ANCHOR_POINT = new Point(box.getBlueCarouselLeftX(),box.getBlueCarouselLeftY());
        static final Point REGION2_TOPLEFT_ANCHOR_POINT = new Point(box.getBlueCarouselCenterX(),box.getBlueCarouselCenterY());
        static final Point REGION3_TOPLEFT_ANCHOR_POINT = new Point(box.getBlueCarouselRightX(),box.getBlueCarouselRightY());
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