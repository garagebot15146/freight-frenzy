package com.example.meepmeep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MMBlueWarehouse {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 45, Math.toRadians(300), Math.toRadians(300), 15)
                .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d(12.5, 62.5, Math.toRadians(270)))
                                        .lineToLinearHeading(new Pose2d(-8, 40, Math.toRadians(250)))
                                        .waitSeconds(0.2)
//                                        .UNSTABLE_addTemporalMarkerOffset(0, () ->  depositCycle(6.2, 3))
                                        .lineToLinearHeading(new Pose2d(-3, 70, Math.toRadians(180)))
//                .UNSTABLE_addTemporalMarkerOffset(0, () -> intake())
                                        .lineToLinearHeading(new Pose2d(57, 70, Math.toRadians(180)))
//                                        .waitSeconds(1)
                                        .lineToLinearHeading(new Pose2d(57, 65, Math.toRadians(180)))
                                        .waitSeconds(1)
                                        .lineToLinearHeading(new Pose2d(40, 70, Math.toRadians(180)))
//                .UNSTABLE_addTemporalMarkerOffset(0.1, () -> outake())
                                        .waitSeconds(1)
                                        .lineToLinearHeading(new Pose2d(0, 70, Math.toRadians(178)))
                                        .lineToLinearHeading(new Pose2d(-4, 42, Math.toRadians(245)))
//                .UNSTABLE_addTemporalMarkerOffset(0, () -> intakeOff())
                                        .waitSeconds(0.2)
//                                        .UNSTABLE_addTemporalMarkerOffset(0, () ->  depositCycle(10.8, 3))
                                        .lineToLinearHeading(new Pose2d(0, 72, Math.toRadians(180)))
                                        .lineToLinearHeading(new Pose2d(55, 75, Math.toRadians(180)))
                                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}