package org.firstinspires.ftc.teamcode.Settings;

public class DetectionSettings {

    public Box blueCarouselLeft = new Box(80, 440);
    public Box blueCarouselCenter = new Box(590, 440);
    public Box blueCarouselRight = new Box(1200, 450);


    class Box {
        int x;
        int y;
        Box (int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getX(){
            return x;
        }

        public int getY(){
            return y;
        }
    }

    public int getBlueCarouselLeftX(){
        return blueCarouselLeft.getX();
    }

    public int getBlueCarouselLeftY(){
        return blueCarouselLeft.getY();
    }

    public int getBlueCarouselCenterX(){
        return blueCarouselCenter.getX();
    }

    public int getBlueCarouselCenterY(){
        return blueCarouselCenter.getY();
    }

    public int getBlueCarouselRightX(){
        return blueCarouselRight.getX();
    }

    public int getBlueCarouselRightY(){
        return blueCarouselRight.getY();
    }
}
