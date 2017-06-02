package com.overwatch.warofship.GameImage;

import android.graphics.Bitmap;
import android.util.Log;

public class Bullet implements GameImageInterface {

    private Bitmap initialbullet;
    private MyShip myPlaneImage;
    private Bitmap myBulletImage;
    private float x;
    private float y;
    private float locationX;
    private Bitmap secondbullet;
    private Bitmap thirdbullet;
    private Bitmap fourthbullet;
    private Bitmap shotbullet;

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
    public Bullet(Bitmap initialbullet, MyShip myPlaneImage,Bitmap secondbullet,Bitmap thirdbullet,Bitmap fourthbullet) {
        this.initialbullet = initialbullet;
        this.secondbullet = secondbullet;
        this.thirdbullet = thirdbullet;
        this.fourthbullet = fourthbullet;
        shotbullet=initialbullet;
        this.myPlaneImage = myPlaneImage;

        //initialize the location of bullet of my ship
        x=myPlaneImage.getX()+myPlaneImage.getWidth()/2-5;
        y=myPlaneImage.getY();

        locationX=x;


    }

    @Override
    /**
     * getter of bitmap
     * move the bullet automatically in this method
     * number change the move speed
     */
    public Bitmap getBitmap() {

        //if the level of the bullet changed , change the image .
        if(myPlaneImage.levelofbullet==2){
            Log.i("levelofbullet is 2","another bullet");
            x=locationX+5-myPlaneImage.getWidth()/2+14;
            shotbullet=secondbullet;
        }
        else if(myPlaneImage.levelofbullet==3){
            Log.i("levelofbullet is 3","another bullet");
            x=locationX+5-myPlaneImage.getWidth()/2+10;
            shotbullet=thirdbullet;
        }
        else if(myPlaneImage.levelofbullet==4){
            Log.i("levelofbullet is 4","another bullet");
            x=locationX+5-myPlaneImage.getWidth()/2;
            shotbullet=fourthbullet;
        }
        //move the bullet automatically
        //the number can change the speed

        y -= 10;

        return shotbullet;
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
    //setter of the location : x and y
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

