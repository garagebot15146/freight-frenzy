package org.firstinspires.ftc.teamcode.comp.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.comp.HardwareConfig;

@TeleOp(name = "RobotTeleOp", group = "Iterative Opmode")
public class RobotTeleOp extends OpMode {

    HardwareConfig robot = new HardwareConfig();

    @Override
    public void init() {
        telemetry.addData("Status", "initializing");
        robot.init(hardwareMap);

        telemetry.addData("Status", "motorized");

    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {

    }


    //Variables
    boolean lock1 = false;
    boolean state1 = false;
    boolean lock2 = false;
    boolean state2 = false;

    @Override
    public void loop() {

        /* GAMEPAD 1 */

        //driveTrain
        double nerf = 0.6;

        if (gamepad1.b) {
            robot.frontLeftMotor.setPower(gamepad1.left_stick_y);
            robot.frontRightMotor.setPower(gamepad1.right_stick_y);
            robot.backLeftMotor.setPower(gamepad1.left_stick_y);
            robot.backRightMotor.setPower(gamepad1.right_stick_y);
        } else {
            robot.frontLeftMotor.setPower(gamepad1.left_stick_y * nerf);
            robot.frontRightMotor.setPower(gamepad1.right_stick_y * nerf);
            robot.backLeftMotor.setPower(gamepad1.left_stick_y * nerf);
            robot.backRightMotor.setPower(gamepad1.right_stick_y * nerf);
        }

        //intake
            if (gamepad1.right_bumper && !lock1 && !gamepad1.left_bumper) {
                lock1 = true;
                if (state1) {
                    robot.intakeMotor.setPower(0);
                    state1 = false;

                } else {
                    robot.intakeMotor.setPower(1);
                    state1 = true;

                }
            } else if (!gamepad1.right_bumper && lock1 && !gamepad1.left_bumper) {
                lock1 = false;
            }

        //outtake
        if (gamepad1.left_bumper && !lock2 && !gamepad1.right_bumper) {
            lock2 = true;
            if (state2) {
                robot.intakeMotor.setPower(0);
                state2 = false;

            } else {
                robot.intakeMotor.setPower(-1);
                state2 = true;

            }
        } else if (!gamepad1.left_bumper && lock2 && !gamepad1.right_bumper) {
            lock2 = false;
        }

        /* GAMEPAD 2 */

        //carousel
        if (gamepad2.a == true) {
            robot.carouselMotor.setPower(0.3);
        } else {
            robot.carouselMotor.setPower(0);
        }

        //lift
        robot.liftMotor.setPower(gamepad2.left_stick_y);
        telemetry.addData("gamepad:", gamepad2.left_stick_y);
        telemetry.addData("gamepad:", robot.liftMotor.getPower());



        //cap
        robot.capMotor.setPower(gamepad2.right_stick_y * 0.6);


    }

    @Override
    public void stop() {

    }

}
