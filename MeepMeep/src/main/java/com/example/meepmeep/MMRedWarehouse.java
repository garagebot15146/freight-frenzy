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
                                        .lineToLinearHeading(new Pose2d(-3, -41, Math.toRadians(120)))
//                                        .UNSTABLE_addTemporalMarkerOffset(0, () ->  liftUp(11, 3))
//                                        .UNSTABLE_addTemporalMarkerOffset(1, () -> deposit())
//                                        .UNSTABLE_addTemporalMarkerOffset(2, () -> liftDown())
                                        .waitSeconds(3)
                                        .lineToLinearHeading(new Pose2d(6, -65.5, Math.toRadians(180)))
                                        .lineToLinearHeading(new Pose2d(40, -65.5, Math.toRadians(180)))
                                        .lineToLinearHeading(new Pose2d(40, -48, Math.toRadians(180)))
                                        .build()
                );


        meepMeep.setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}