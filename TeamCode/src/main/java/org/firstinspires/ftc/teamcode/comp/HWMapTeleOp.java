package org.firstinspires.ftc.teamcode.comp;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HWMapTeleOp {
    /* In Order of the Ports */

    //Control Hub

    // Motor Ports 0 -3:
    public DcMotor leftBackMotor = null;
    public DcMotor leftFrontMotor = null;
    public DcMotor rightFrontMotor = null;
    public DcMotor rightBackMotor = null;

    //Expansion Hub

    // Motor Ports 0 - 3:
    public DcMotor intakeMotor = null;
    public DcMotor liftMotor = null;

//    public DcMotor capMotor = null;
//    public DcMotor carouselMotor = null;


    //Servo Ports 0-3
//    public Servo dropServo = null;

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

        liftMotor = hwMap.get(DcMotor.class, "liftMotor");
        intakeMotor = hwMap.get(DcMotor.class, "intakeMotor");
        leftBackMotor = hwMap.get(DcMotor.class, "leftBack");
        leftFrontMotor = hwMap.get(DcMotor.class, "leftFront");
        rightFrontMotor = hwMap.get(DcMotor.class, "rightFront");
        rightBackMotor = hwMap.get(DcMotor.class, "rightBack");
        //        capMotor = hwMap.get(DcMotor.class, "capMotor");
        //        carouselMotor = hwMap.get(DcMotor.class, "carouselMotor");
        //        dropServo = hwMap.get(Servo.class,"dropServo");


        //Set Directions
        leftBackMotor.setDirection(DcMotor.Direction.REVERSE);
        leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        rightBackMotor.setDirection(DcMotor.Direction.FORWARD);
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);
        liftMotor.setDirection(DcMotor.Direction.FORWARD);
        //        capMotor.setDirection(DcMotor.Direction.FORWARD);
        //        carouselMotor.setDirection(DcMotor.Direction.REVERSE);

        //Set Behavior
//        leftBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//        leftFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//        rightFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//        rightBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor.setZeroPowerBehavior((DcMotor.ZeroPowerBehavior.BRAKE));

        leftFrontMotor.setPower(0);
        leftBackMotor.setPower(0);
        rightFrontMotor.setPower(0);
        rightBackMotor.setPower(0);
        liftMotor.setPower(0);
        intakeMotor.setPower(0);
        //        capMotor.setPower(0);
        //        carouselMotor.setPower(0);

    }
}

