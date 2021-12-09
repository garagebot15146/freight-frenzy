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


    @Override
    public void loop() {

        //GAMEPAD 1
        if(gamepad1.a){
            robot.leftMotor.setPower(gamepad1.left_stick_y);
            robot.rightMotor.setPower(gamepad1.right_stick_y);
        } else {
            robot.leftMotor.setPower(gamepad1.left_stick_y * 0.6);
            robot.rightMotor.setPower(gamepad1.right_stick_y * 0.6);
        }

        //GAMEPAD 2
        if(gamepad2.a == true){
            robot.carouselMotor.setPower(1);
        } else{
            robot.carouselMotor.setPower(0);
        }
    }

    @Override
    public void stop() {

    }

}