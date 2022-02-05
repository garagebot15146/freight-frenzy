package org.firstinspires.ftc.teamcode.Settings;

public class DetectionSettings {

    public Box blueCarouselLeft = new Box(15, 80);
    public Box blueCarouselCenter = new Box(640, 100);
    public Box blueCarouselRight = new Box(1200, 160);

    public Box redCarouselLeft = new Box(10, 80);
    public Box redCarouselCenter = new Box(590, 100);
    public Box redCarouselRight = new Box(1200, 160);

    public Box blueWarehouseLeft = new Box(22, 80);
    public Box blueWarehouseCenter = new Box(600, 100);
    public Box blueWarehouseRight = new Box(1200, 160);

    public Box redWarehouseLeft = new Box(15, 80);
    public Box redWarehouseCenter = new Box(640, 100);
    public Box redWarehouseRight = new Box(1200, 160);

    class Box {
        int x;
        int y;

        Box(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    //Blue Carousel
    public int getBlueCarouselLeftX() {
        return blueCarouselLeft.getX();
    }

    public int getBlueCarouselLeftY() {
        return blueCarouselLeft.getY();
    }

    public int getBlueCarouselCenterX() {
        return blueCarouselCenter.getX();
    }

    public int getBlueCarouselCenterY() {
        return blueCarouselCenter.getY();
    }

    public int getBlueCarouselRightX() {
        return blueCarouselRight.getX();
    }
    public int getBlueCarouselRightY() {
        return blueCarouselRight.getY();
    }

    //Red Carousel
    public int getRedCarouselLeftX() {
        return redCarouselLeft.getX();
    }

    public int getRedCarouselLeftY() {
        return redCarouselLeft.getY();
    }

    public int getRedCarouselCenterX() {
        return redCarouselCenter.getX();
    }

    public int getRedCarouselCenterY() {
        return redCarouselCenter.getY();
    }

    public int getRedCarouselRightX() {
        return redCarouselRight.getX();
    }

    public int getRedCarouselRightY() {
        return redCarouselRight.getY();
    }

    //Blue Warehouse
    public int getBlueWarehouseLeftX() {
        return blueWarehouseLeft.getX();
    }

    public int getBlueWarehouseLeftY() {
        return blueWarehouseLeft.getY();
    }

    public int getBlueWarehouseCenterX() {
        return blueWarehouseCenter.getX();
    }

    public int getBlueWarehouseCenterY() {
        return blueWarehouseCenter.getY();
    }

    public int getBlueWarehouseRightX() {
        return blueWarehouseRight.getX();
    }

    public int getBlueWarehouseRightY() {
        return blueWarehouseRight.getY();
    }

    //Red Warehouse
    public int getRedWarehouseLeftX() {
        return redWarehouseLeft.getX();
    }

    public int getRedWarehouseLeftY() {
        return redWarehouseLeft.getY();
    }

    public int getRedWarehouseCenterX() {
        return redWarehouseCenter.getX();
    }

    public int getRedWarehouseCenterY() {
        return redWarehouseCenter.getY();
    }

    public int getRedWarehouseRightX() {
        return redWarehouseRight.getX();
    }

    public int getRedWarehouseRightY() {
        return redWarehouseRight.getY();
    }
}
