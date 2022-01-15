package org.firstinspires.ftc.teamcode.comp.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.comp.HWMapTeleOp;


@Autonomous(name = "Warehouse Park", group = "Autonomous")
//@Disabled
public class WarehousePark extends LinearOpMode {

    //Runtime
    private ElapsedTime runtime = new ElapsedTime();

    //HWMap
    HWMapTeleOp robot = new HWMapTeleOp();


    @Override
    public void runOpMode() {
        //Hardware map
        robot.init(hardwareMap);


        telemetry.addData("Status", "Pipeline Initializing");
        telemetry.update();

        waitForStart();
        if (isStopRequested()) return;
        telemetry.update();

        encoderDrive(0.2, 15, 15, 5);
    }


    public void pause(double seconds){
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < seconds) {
        }

    }

    public void encoderDrive(double speed, double leftInches, double rightInches, double timeoutS) {
        int newLeftFrontTarget;
        int newLeftRearTarget;
        int newRightFrontTarget;
        int newRightRearTarget;


        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftFrontTarget = robot.frontLeftMotor.getCurrentPosition() + (int)(leftInches * 50);
            newLeftRearTarget = robot.backLeftMotor.getCurrentPosition() + (int)(leftInches * 200);
            newRightFrontTarget = robot.frontRightMotor.getCurrentPosition() + (int)(rightInches * 200);
            newRightRearTarget = robot.backRightMotor.getCurrentPosition() + (int)(rightInches * 200);

            robot.frontLeftMotor.setTargetPosition(newLeftFrontTarget);
            robot.backLeftMotor.setTargetPosition(newLeftRearTarget);
            robot.frontRightMotor.setTargetPosition(newRightFrontTarget);
            robot.backRightMotor.setTargetPosition(newRightRearTarget);

            // Turn On RUN_TO_POSITION
            robot.frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.frontLeftMotor.setPower(speed);
            robot.backLeftMotor.setPower(speed);
            robot.frontRightMotor.setPower(speed);
            robot.backRightMotor.setPower(speed);

            while (opModeIsActive() && (runtime.seconds() < timeoutS) && (robot.frontLeftMotor.isBusy() && robot.backLeftMotor.isBusy() && robot.frontRightMotor.isBusy() && robot.backRightMotor.isBusy())) {
            }

            // Stop all motion;
            robot.frontLeftMotor.setPower(0);
            robot.backLeftMotor.setPower(0);
            robot.frontRightMotor.setPower(0);
            robot.backRightMotor.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}
