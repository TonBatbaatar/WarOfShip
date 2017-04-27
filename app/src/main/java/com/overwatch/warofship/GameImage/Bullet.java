package com.overwatch.warofship.GameImage;

import android.graphics.Bitmap;

public class Bullet implements GameImageInterface {

    private Bitmap myBulletImage;
    private MyShip myPlaneImage;
    private float x;
    private float y;

    public Bullet(Bitmap myBulletImage, MyShip myPlaneImage) {
        this.myBulletImage = myBulletImage;
        this.myPlaneImage = myPlaneImage;
        //initialize the location of bullet of my ship
        x=myPlaneImage.getX()+myPlaneImage.getWidth()/2-5;
        y=myPlaneImage.getY();
    }

    @Override
    public Bitmap getBitmap() {
        //move the bullet automatically
        //the number can change the speed
        y-=20;
        return myBulletImage;
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

