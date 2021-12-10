package org.firstinspires.ftc.teamcode.comp;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HardwareConfig {
    /* Public OpMode members. */
    public DcMotor leftMotor = null;
    public DcMotor rightMotor = null;
    public DcMotor intakeMotor = null;
    public DcMotor liftMotor = null;
    public DcMotor carouselMotor = null;

    /* local OpMode members. */
    com.qualcomm.robotcore.hardware.HardwareMap hwMap = null;

    /* Constructor */
    public HardwareConfig() {

    }

    /* Initialize standard Hardware interfaces */
    public void init(com.qualcomm.robotcore.hardware.HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftMotor = hwMap.get(DcMotor.class, "leftMotor");
        rightMotor = hwMap.get(DcMotor.class, "rightMotor");
        intakeMotor = hwMap.get(DcMotor.class, "intakeMotor");
        liftMotor = hwMap.get(DcMotor.class, "liftMotor");
        carouselMotor = hwMap.get(DcMotor.class, "carouselMotor");

        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);
        liftMotor.setDirection(DcMotor.Direction.FORWARD);
        carouselMotor.setDirection(DcMotor.Direction.FORWARD);

        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        liftMotor.setZeroPowerBehavior((DcMotor.ZeroPowerBehavior.BRAKE));
        // Set all motors to zero power
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        intakeMotor.setPower(0);
        liftMotor.setPower(0);
        carouselMotor.setPower(0);
    }
}

