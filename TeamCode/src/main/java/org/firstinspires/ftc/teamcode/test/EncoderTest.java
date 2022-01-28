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

        robot.leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        robot.leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.leftBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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
        robot.leftFrontMotor.setPower(-gamepad1.left_stick_y);
        robot.rightFrontMotor.setPower(-gamepad1.right_stick_y);
        robot.rightBackMotor.setPower(-gamepad1.right_stick_y);
        robot.leftBackMotor.setPower(-gamepad1.left_stick_y);
        robot.liftMotor.setPower(-gamepad2.left_stick_y);;
        telemetry.addData("Encoder Left Front:", robot.leftFrontMotor.getCurrentPosition());
        telemetry.addData("Encoder Right Front:", robot.rightFrontMotor.getCurrentPosition());
        telemetry.addData("Encoder Left Back:", robot.leftBackMotor.getCurrentPosition());
        telemetry.addData("Encoder Right Back:", robot.rightBackMotor.getCurrentPosition());
        telemetry.addData("Encoder Lift:", robot.liftMotor.getCurrentPosition());

    }

    @Override
    public void stop() {

    }

}
