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
                                drive.trajectorySequenceBuilder(new Pose2d(34, 64, Math.toRadians(270)))
                                .lineToLinearHeading(new Pose2d(-19, 41.8, Math.toRadians(300)))
                                .lineToLinearHeading(new Pose2d(-64, 49, Math.toRadians(270)))
                                .lineToLinearHeading(new Pose2d(-64, 40, Math.toRadians(180)))
//                                .splineTo(new Vector2d(-19, 41.8), Math.toRadians(300))
//                                .splineTo(new Vector2d(-64, 49), Math.toRadians(270))
//                                .splineTo(new Vector2d(-64, 40), Math.toRadians(180))
                                        .build()
                );


        meepMeep.setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}