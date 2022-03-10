package com.example.meepmeep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MMRedWarehouse {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 45, Math.toRadians(300), Math.toRadians(300), 15)
                .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d(12.5, -62.5, Math.toRadians(90)))
                                        .lineToLinearHeading(new Pose2d(-5, -45, Math.toRadians(110)))
                                        .waitSeconds(0.2)
//                                        .UNSTABLE_addTemporalMarkerOffset(0, () ->  depositCycle(6.9, 3))
                                        .lineToLinearHeading(new Pose2d(0, -68, Math.toRadians(180)))
//                                        .UNSTABLE_addTemporalMarkerOffset(0, () -> intake())
                                        .lineToLinearHeading(new Pose2d(53, -68, Math.toRadians(178)))
                                        .waitSeconds(0.2)
//                                        .UNSTABLE_addTemporalMarkerOffset(0.1, () -> outake())
                                        .lineToLinearHeading(new Pose2d(0, -68, Math.toRadians(185)))
                                        .lineToLinearHeading(new Pose2d(-5, -45, Math.toRadians(110)))
                                        .waitSeconds(0.3)
//                                        .UNSTABLE_addTemporalMarkerOffset(0, () ->  depositCycle(10.5, 3))
                                        .lineToLinearHeading(new Pose2d(0, -68, Math.toRadians(178)))
//                                        .UNSTABLE_addTemporalMarkerOffset(0, () -> intake())
                                        .lineToLinearHeading(new Pose2d(51, -68, Math.toRadians(178)))
                                        .waitSeconds(0.3)
//                                        .UNSTABLE_addTemporalMarkerOffset(0.1, () -> outake())
                                        .lineToLinearHeading(new Pose2d(0, -68, Math.toRadians(185)))
                                        .lineToLinearHeading(new Pose2d(-5, -45, Math.toRadians(110)))
                                        .waitSeconds(0.2)
//                                        .UNSTABLE_addTemporalMarkerOffset(0, () ->  depositCycle(10.7, 3))
                                        .lineToLinearHeading(new Pose2d(0, -68, Math.toRadians(178)))
                                        .lineToLinearHeading(new Pose2d(51, -68, Math.toRadians(178)))
//                                        .UNSTABLE_addTemporalMarkerOffset(0, () -> liftDown())
                                        .build()
                );


        meepMeep.setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}