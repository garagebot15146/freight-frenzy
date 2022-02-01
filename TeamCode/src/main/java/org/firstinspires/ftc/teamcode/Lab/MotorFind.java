package org.firstinspires.ftc.teamcode.Lab;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "MotorFind", group = "Iterative Opmode")
@Disabled
public class MotorFind extends OpMode {
    ElapsedTime runtime = new ElapsedTime();
    DcMotor testMotor = null;

    @Override
    public void init() {
        testMotor = hardwareMap.get(DcMotor.class, "test");
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        testMotor.setPower(gamepad1.left_stick_y);
    }

    @Override
    public void stop() {

    }

}
