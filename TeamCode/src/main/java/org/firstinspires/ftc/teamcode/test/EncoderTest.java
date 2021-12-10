package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.comp.HardwareConfig;

@TeleOp(name = "EncoderTest", group = "Iterative Opmode")
public class EncoderTest extends OpMode {

    HardwareConfig robot = new HardwareConfig();

    @Override
    public void init() {
        telemetry.addData("Status", "initializing");
        robot.init(hardwareMap);

        robot.leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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
        robot.leftMotor.setPower(gamepad1.left_stick_y);
        robot.rightMotor.setPower(gamepad1.right_stick_y);
        telemetry.addData("Encoder Left:", robot.leftMotor.getCurrentPosition());
        telemetry.addData("Encoder Right:", robot.rightMotor.getCurrentPosition());
    }

    @Override
    public void stop() {

    }

}
