package org.firstinspires.ftc.teamcode.Lab;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Settings.HWMapTeleOp;

@TeleOp(name = "EncoderTest", group = "Iterative Opmode")
@Disabled
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
        double nerf = 0.6;

        //DRIVE
        double forward = -gamepad1.left_stick_y;
        double side = gamepad1.left_stick_x; //Positive means right
        double turn = gamepad1.right_stick_x; //Positive means turn right


        double leftFrontPower = (forward + side + turn);
        double leftBackPower = (forward - side + turn);
        double rightFrontPower = (forward - side - turn);
        double rightBackPower = (forward + side - turn);

        // Send power to wheel motors
        robot.leftFrontMotor.setPower(leftFrontPower * nerf);
        robot.rightFrontMotor.setPower(rightFrontPower * nerf);
        robot.leftBackMotor.setPower(leftBackPower * nerf);
        robot.rightBackMotor.setPower(rightBackPower * nerf);

        robot.liftMotor.setPower(gamepad2.left_stick_y);;
        telemetry.addData("Encoder Left Front:", robot.leftFrontMotor.getCurrentPosition());
        telemetry.addData("Encoder Right Front:", robot.rightFrontMotor.getCurrentPosition());
        telemetry.addData("Encoder Left Back:", robot.leftBackMotor.getCurrentPosition());
        telemetry.addData("Encoder Right Back:", robot.rightBackMotor.getCurrentPosition());
        telemetry.addData("Encoder Lift:", robot.liftMotor.getCurrentPosition());

        telemetry.addData("Power Left Front:", robot.leftFrontMotor.getPower());
        telemetry.addData("Power Right Front:", robot.rightFrontMotor.getPower());
        telemetry.addData("Power Left Back:", robot.leftBackMotor.getPower());
        telemetry.addData("Power Right Back:", robot.rightBackMotor.getPower());

    }

    @Override
    public void stop() {

    }

}
