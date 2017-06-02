package com.overwatch.warofship.GameImage;

import android.graphics.Bitmap;

public class Bullet implements GameImageInterface {

    private Bitmap myBulletImage;
    private float x;
    private float y;

    /**
     * Create player bullet
     *
     * @param myBulletImage
     *              the Bitmap used for draw
     *
     * @param myPlaneImage
     *              player ship to initialize location of bullet
     *
     */
    public Bullet(Bitmap myBulletImage, MyShip myPlaneImage) {
        this.myBulletImage = myBulletImage;
        this.x = myPlaneImage.getX()+myPlaneImage.getWidth()/2-5;//initialize the location of bullet of my ship
        this.y = myPlaneImage.getY();
    }

    @Override
    /**
     * getter of bitmap
     * move the bullet automatically in this method
     * number change the move speed
     */
    public Bitmap getBitmap() {
        y -= 10;
        return myBulletImage;
    }

    @Override
    /**
     * getter of location
     * setter of location
     */
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }

    /**
     * judge if the bullet is out of screen
     * @return
     *          return true if bullet out of screen
     *          return false if bullet no out of screen
     */
    public boolean ifOutOfScreen(){
        if(y<=-10){
            return true;
        }else{
            return false;
        }
    }

}

