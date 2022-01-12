package org.firstinspires.ftc.teamcode.comp;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HWMapTeleOp {
    /* In Order of the Ports */

    //Control Hub

    // Motor Ports 0 - 3:
    public DcMotor capMotor = null;
    public DcMotor liftMotor = null;
    public DcMotor frontLeftMotor = null;
    public DcMotor frontRightMotor = null;

    //Servo Ports 0-3
    public Servo dropServo = null;


    //Expansion Hub

    // Motor Ports 0 -3:
    public DcMotor backRightMotor = null;
    public DcMotor backLeftMotor = null;
    public DcMotor carouselMotor = null;
    public DcMotor intakeMotor = null;

    /* Hardware Map Object */
    com.qualcomm.robotcore.hardware.HardwareMap hwMap = null;

    /* Constructor */
    public HWMapTeleOp() {

    }

    /* Initialize standard Hardware interfaces */
    public void init(com.qualcomm.robotcore.hardware.HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors

        //Control Hub:

        // Motor Ports 0 - 3:
        capMotor = hwMap.get(DcMotor.class, "capMotor");
        liftMotor = hwMap.get(DcMotor.class, "liftMotor");
        frontLeftMotor = hwMap.get(DcMotor.class, "leftFront");
        frontRightMotor = hwMap.get(DcMotor.class, "rightFront");

        //Servo Ports 0-3
        dropServo = hwMap.get(Servo.class,"dropServo");

        //Expansion Hub:

        // Motor Ports 0 - 3:
        backRightMotor = hwMap.get(DcMotor.class, "rightRear");
        backLeftMotor = hwMap.get(DcMotor.class, "leftRear");
        carouselMotor = hwMap.get(DcMotor.class, "carouselMotor");
        intakeMotor = hwMap.get(DcMotor.class, "intakeMotor");


        //Set Directions

        //Control Hub:
        capMotor.setDirection(DcMotor.Direction.FORWARD);
        liftMotor.setDirection(DcMotor.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);

        //Expansion Hub:
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        carouselMotor.setDirection(DcMotor.Direction.REVERSE);
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);

        //Set Behavior
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor.setZeroPowerBehavior((DcMotor.ZeroPowerBehavior.BRAKE));

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

