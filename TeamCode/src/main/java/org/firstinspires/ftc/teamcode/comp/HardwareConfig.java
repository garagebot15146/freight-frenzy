package org.firstinspires.ftc.teamcode.comp;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HardwareConfig {
    /* In Order of the Ports */

    //Control Hub Ports 0 - 3:
    public DcMotor capMotor = null;
    public DcMotor liftMotor = null;
    public DcMotor frontLeftMotor = null;
    public DcMotor frontRightMotor = null;

    //Expansion Hub Ports 0 -3:
    public DcMotor backRightMotor = null;
    public DcMotor backLeftMotor = null;
    public DcMotor carouselMotor = null;
    public DcMotor intakeMotor = null;

    /* Hardware Map Object */
    com.qualcomm.robotcore.hardware.HardwareMap hwMap = null;

    /* Constructor */
    public HardwareConfig() {

    }

    /* Initialize standard Hardware interfaces */
    public void init(com.qualcomm.robotcore.hardware.HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors

        //Control Hub:
        capMotor = hwMap.get(DcMotor.class, "capMotor");
        liftMotor = hwMap.get(DcMotor.class, "liftMotor");
        frontLeftMotor = hwMap.get(DcMotor.class, "leftFront");
        backRightMotor = hwMap.get(DcMotor.class, "rightRear");

        //Expansion Hub:
        frontRightMotor = hwMap.get(DcMotor.class, "rightFront");
        backLeftMotor = hwMap.get(DcMotor.class, "leftRear");
        carouselMotor = hwMap.get(DcMotor.class, "carouselMotor");
        intakeMotor = hwMap.get(DcMotor.class, "intakeMotor");


        //Set Directions

        //Control Hub:
        capMotor.setDirection(DcMotor.Direction.FORWARD);
        liftMotor.setDirection(DcMotor.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);

        //Expansion Hub:
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        carouselMotor.setDirection(DcMotor.Direction.REVERSE);
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);

        //Set Behavior
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        liftMotor.setZeroPowerBehavior((DcMotor.ZeroPowerBehavior.BRAKE));
        // Set all motors to zero power

        //Control Hub
        capMotor.setPower(0);
        liftMotor.setPower(0);
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);

        //Expansion Hub:
        backRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        carouselMotor.setPower(0);
        intakeMotor.setPower(0);
    }
}

