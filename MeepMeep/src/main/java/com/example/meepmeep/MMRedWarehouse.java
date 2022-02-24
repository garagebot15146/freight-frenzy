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
//                                        .lineToLinearHeading(new Pose2d(-5, -39, Math.toRadians(120)))
//                                        .UNSTABLE_addTemporalMarkerOffset(0, () ->  liftUp(5.9, 3))
//                                        .UNSTABLE_addTemporalMarkerOffset(0.2, () -> deposit())
//                                        .UNSTABLE_addTemporalMarkerOffset(0.3, () -> liftDown())
                                        .waitSeconds(0.32)
                                        .lineToLinearHeading(new Pose2d(-3, -68, Math.toRadians(180)))
//                                        .UNSTABLE_addTemporalMarkerOffset(0, () -> intake())
                                        .lineToLinearHeading(new Pose2d(53, -68, Math.toRadians(180)))
                                        .waitSeconds(0.2)
//                                        .UNSTABLE_addTemporalMarkerOffset(0, () -> outake())
                                        .lineToLinearHeading(new Pose2d(-3, -68, Math.toRadians(180)))
                                        .lineToLinearHeading(new Pose2d(-3, -40.6, Math.toRadians(120)))
//                                        .UNSTABLE_addTemporalMarkerOffset(0, () ->  liftUp(11, 3))
//                                        .UNSTABLE_addTemporalMarkerOffset(0.2, () -> deposit())
//                                        .UNSTABLE_addTemporalMarkerOffset(0.4, () -> liftDown())
                                        .waitSeconds(0.5)
                                        .lineToLinearHeading(new Pose2d(-3, -68, Math.toRadians(180)))
//                                        .UNSTABLE_addTemporalMarkerOffset(0, () -> intake())
                                        .lineToLinearHeading(new Pose2d(53, -68, Math.toRadians(180)))
                                        .waitSeconds(0.2)
//                                        .UNSTABLE_addTemporalMarkerOffset(0, () -> outake())
                                        .lineToLinearHeading(new Pose2d(-3, -70, Math.toRadians(180)))
                                        .lineToLinearHeading(new Pose2d(-3, -41, Math.toRadians(120)))
//                                        .UNSTABLE_addTemporalMarkerOffset(0, () ->  liftUp(11, 3))
//                                        .UNSTABLE_addTemporalMarkerOffset(0.2, () -> deposit())
                                        .waitSeconds(0.2)
                                        .lineToLinearHeading(new Pose2d(-3, -69, Math.toRadians(180)))
                                        .lineToLinearHeading(new Pose2d(45, -65.5, Math.toRadians(180)))
                                        .build()
                );


        meepMeep.setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}