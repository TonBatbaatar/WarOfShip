package com.overwatch.warofship.GameImage;

import android.graphics.Bitmap;
import android.util.Log;

public class Bullet implements GameImageInterface {

    private Bitmap initialbullet;
    private MyShip myPlaneImage;
    private float x;
    private float y;
    private Bitmap secondbullet;
    private Bitmap thirdbullet;
    private Bitmap fourthbullet;
    private Bitmap shotbullet;

    /**
     * Create player bullet
     *
     * @param initialbullet
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


    }



    @Override
    public Bitmap getBitmap() {

        //move the bullet automatically
        //the number can change the speed
        if(myPlaneImage.levelofbullet==2){
            Log.i("levelofbullet is 2","another bullet");
            shotbullet=secondbullet;
        }
        else if(myPlaneImage.levelofbullet==3){
            Log.i("levelofbullet is 3","another bullet");
            shotbullet=thirdbullet;
        }
        else if(myPlaneImage.levelofbullet==4){
            Log.i("levelofbullet is 4","another bullet");
            shotbullet=fourthbullet;
        }

        y -= 10;

        return shotbullet;
    }

    @Override
    //getter fo the location : x and y
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


    //the method to judge if the bullet is out of screen
    //true: out of screen
    //false: in screen
    public boolean ifOutOfScreen(){
        if(y<=-10){
            return true;
        }else{
            return false;
        }
    }

}

