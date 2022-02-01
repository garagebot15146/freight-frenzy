//package org.firstinspires.ftc.teamcode.Mark1.Auto;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.util.ElapsedTime;
//import org.firstinspires.ftc.teamcode.Settings.HWMapTeleOp;
//
//
//@Autonomous(name = "Warehouse Park", group = "Autonomous")
//@Disabled
//public class WarehousePark extends LinearOpMode {
//
//    //Runtime
//    private ElapsedTime runtime = new ElapsedTime();
//
//    //HWMap
//    HWMapTeleOp robot = new HWMapTeleOp();
//
//
//    @Override
//    public void runOpMode() {
//        //Hardware map
//        robot.init(hardwareMap);
//
//
//        telemetry.addData("Status", "Pipeline Initializing");
//        telemetry.update();
//
//        waitForStart();
//        if (isStopRequested()) return;
//        telemetry.update();
//
//        encoderDrive(0.5, -30, -30, 5);
//    }
//
//
//    public void pause(double seconds){
//        runtime.reset();
//        while (opModeIsActive() && runtime.seconds() < seconds) {
//        }
//
//    }
//
//    public void encoderDrive(double speed, double leftInches, double rightInches, double timeoutS) {
//        int newLeftFrontTarget;
//        int newLeftRearTarget;
//        int newRightFrontTarget;
//        int newRightRearTarget;
//
//
//        // Ensure that the opmode is still active
//        if (opModeIsActive()) {
//
//            // Determine new target position, and pass to motor controller
//            newLeftFrontTarget = robot.leftFrontMotor.getCurrentPosition() + (int)(leftInches * 50);
//            newLeftRearTarget = robot.leftBackMotor.getCurrentPosition() + (int)(leftInches * 200);
//            newRightFrontTarget = robot.rightFrontMotor.getCurrentPosition() + (int)(rightInches * 200);
//            newRightRearTarget = robot.rightBackMotor.getCurrentPosition() + (int)(rightInches * 200);
//
//            robot.leftFrontMotor.setTargetPosition(newLeftFrontTarget);
//            robot.leftBackMotor.setTargetPosition(newLeftRearTarget);
//            robot.rightFrontMotor.setTargetPosition(newRightFrontTarget);
//            robot.rightBackMotor.setTargetPosition(newRightRearTarget);
//
//            // Turn On RUN_TO_POSITION
//            robot.leftFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            robot.leftBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            robot.rightFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            robot.rightBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//            // reset the timeout time and start motion.
//            runtime.reset();
//            robot.leftFrontMotor.setPower(speed);
//            robot.leftBackMotor.setPower(speed);
//            robot.rightFrontMotor.setPower(speed);
//            robot.rightBackMotor.setPower(speed);
//
//            while (opModeIsActive() && (runtime.seconds() < timeoutS) && (robot.leftFrontMotor.isBusy() && robot.leftBackMotor.isBusy() && robot.rightFrontMotor.isBusy() && robot.rightBackMotor.isBusy())) {
//                robot.intakeMotor.setPower(1);
//            }
//
//            // Stop all motion;
//            robot.leftFrontMotor.setPower(0);
//            robot.leftBackMotor.setPower(0);
//            robot.rightFrontMotor.setPower(0);
//            robot.rightBackMotor.setPower(0);
//
//            // Turn off RUN_TO_POSITION
//            robot.leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            robot.leftBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            robot.rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            robot.rightBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        }
//    }
//}
