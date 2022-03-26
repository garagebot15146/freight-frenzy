package org.firstinspires.ftc.teamcode.Mark4.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Settings.HWMapTeleOp;

@TeleOp(name = "TeleOp", group = "Iterative Opmode")
//@Disabled
public class GarTeleOp extends OpMode {

    HWMapTeleOp robot = new HWMapTeleOp();

    int POS = 0;

    @Override
    public void init() {
        telemetry.addData("Status", "initializing");
        robot.init(hardwareMap);
        robot.liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

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

    boolean lockServo = false;
    boolean stateServo = false;


    @Override
    public void loop() {

        /* GAMEPAD 1 */

        //driveTrain
        double nerf = 0.8;

        //DRIVE
        double forward = -gamepad1.left_stick_y;
        double side = gamepad1.right_stick_x; //Positive means right
        double turn = gamepad1.right_stick_y; //Positive means turn right


        double leftFrontPower = (forward + side + turn);
        double leftBackPower = (forward - side + turn);
        double rightFrontPower = (forward - side - turn);
        double rightBackPower = (forward + side - turn);

        // Send power to wheel motors
        robot.leftFrontMotor.setPower(leftFrontPower * nerf);
        robot.rightFrontMotor.setPower(rightFrontPower * nerf);
        robot.leftBackMotor.setPower(leftBackPower * nerf);
        robot.rightBackMotor.setPower(rightBackPower * nerf);

        //outtake
        double intakeNerf = 0.8;
        if (gamepad1.right_bumper && !lock1 && !gamepad1.left_bumper) {
            lock1 = true;
            if (state1) {
                robot.intakeMotor.setPower(0);
                state1 = false;

            } else {
                robot.intakeMotor.setPower(1 * intakeNerf);
                state1 = true;

            }
        } else if (!gamepad1.right_bumper && lock1 && !gamepad1.left_bumper) {
            lock1 = false;
        }

        //intake
        if (gamepad1.left_bumper && !lock2 && !gamepad1.right_bumper) {
            lock2 = true;
            if (state2) {
                robot.intakeMotor.setPower(0);
                state2 = false;

            } else {
                robot.intakeMotor.setPower(-1 * intakeNerf);
                state2 = true;

            }
        } else if (!gamepad1.left_bumper && lock2 && !gamepad1.right_bumper) {
            lock2 = false;
        }

        /* GAMEPAD 2 */

        //carousel
        if (gamepad2.a == true) {
            robot.carouselMotor.setPower(0.53);
        } else if (gamepad2.b == true) {
            robot.carouselMotor.setPower(-0.53);
        } else {
            robot.carouselMotor.setPower(0);
        }

        if (gamepad2.left_bumper && !lockServo) {
            lockServo = true;
            if (stateServo) {
                robot.dropServo.setPosition(0.58);
                stateServo = false;

            } else {
                robot.dropServo.setPosition(0.2);
                stateServo = true;

            }
        } else if (!gamepad2.left_bumper && lockServo) {
            lockServo = false;
        }


        if(robot.liftMotor.getCurrentPosition() < 1000 & robot.dropServo.getPosition() == 0.3){
            robot.dropServo.setPosition(0.58);
            telemetry.addData("lift", "below safe region");
        }

        //lift
        POS -= gamepad2.left_stick_y * 21;
        robot.liftMotor.setTargetPosition(POS);
        if (POS < 0) {
            POS = 0;
        }
        telemetry.addData("test", POS);
        robot.liftMotor.setPower(1);
        robot.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        telemetry.addData("lift encoder", robot.liftMotor.getCurrentPosition());
//        if(robot.liftMotor.getCurrentPosition() <= 0){
//            robot.liftMotor.setPower(Math.abs(gamepad2.left_stick_y));
//        } else {
//            robot.liftMotor.setPower(gamepad2.left_stick_y);
//        }

        telemetry.addData("lift encoder", robot.liftMotor.getCurrentPosition());


//        //cap
//        robot.capMotor.setPower(gamepad2.right_stick_y * 0.6);
    }

    @Override
    public void stop() {

    }

}
