package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.comp.HWMapTeleOp;

@TeleOp(name = "EncoderTest", group = "Iterative Opmode")
//@Disabled
public class EncoderTest extends OpMode {

    HWMapTeleOp robot = new HWMapTeleOp();

    @Override
    public void init() {
        telemetry.addData("Status", "initializing");
        robot.init(hardwareMap);

        robot.frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        robot.frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("Status", "motorized");

    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {

    }


    @Override
    public void loop() {
        robot.frontLeftMotor.setPower(-gamepad1.left_stick_y);
        robot.frontRightMotor.setPower(-gamepad1.right_stick_y);
        robot.backRightMotor.setPower(-gamepad1.right_stick_y);
        robot.backLeftMotor.setPower(-gamepad1.left_stick_y);
        robot.liftMotor.setPower(-gamepad2.left_stick_y);;
        telemetry.addData("Encoder Left Front:", robot.frontLeftMotor.getCurrentPosition());
        telemetry.addData("Encoder Right Front:", robot.frontRightMotor.getCurrentPosition());
        telemetry.addData("Encoder Left Back:", robot.backLeftMotor.getCurrentPosition());
        telemetry.addData("Encoder Right Back:", robot.backRightMotor.getCurrentPosition());
        telemetry.addData("Encoder Lift:", robot.liftMotor.getCurrentPosition());

    }

    @Override
    public void stop() {

    }

}
