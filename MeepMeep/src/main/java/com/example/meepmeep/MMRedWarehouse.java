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
                                        .lineToLinearHeading(new Pose2d(-5, -42, Math.toRadians(110)))
                                        .waitSeconds(0.2)
//                                        .UNSTABLE_addTemporalMarkerOffset(0, () ->  depositCycle(10.5, 3))
                                        .lineToLinearHeading(new Pose2d(-3, -68, Math.toRadians(180)))
//                                        .UNSTABLE_addTemporalMarkerOffset(0, () -> intake())
                                        .lineToLinearHeading(new Pose2d(53, -68, Math.toRadians(180)))
                                        .waitSeconds(0.2)
//                                        .UNSTABLE_addTemporalMarkerOffset(0.1, () -> outake())
                                        .lineToLinearHeading(new Pose2d(0, -68, Math.toRadians(180)))
                                        .lineToLinearHeading(new Pose2d(-4, -47.2, Math.toRadians(105)))
                                        .waitSeconds(0.2)
//                                        .UNSTABLE_addTemporalMarkerOffset(0, () ->  depositCycle(10.5, 3))
                                        .lineToLinearHeading(new Pose2d(0, -68, Math.toRadians(180)))
//                                        .UNSTABLE_addTemporalMarkerOffset(0, () -> intake())
                                        .lineToLinearHeading(new Pose2d(55, -68, Math.toRadians(180)))
                                        .waitSeconds(0.2)
//                                        .UNSTABLE_addTemporalMarkerOffset(0.1, () -> outake())
                                        .lineToLinearHeading(new Pose2d(0, -68, Math.toRadians(180)))
                                        .lineToLinearHeading(new Pose2d(-5, -45.2, Math.toRadians(107)))
                                        .waitSeconds(0.2)
//                                        .UNSTABLE_addTemporalMarkerOffset(0, () ->  depositCycle(10.7, 3))
                                        .lineToLinearHeading(new Pose2d(-3, -68, Math.toRadians(180)))
                                        .lineToLinearHeading(new Pose2d(53, -68, Math.toRadians(180)))
                                        .build()
                );


        meepMeep.setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}