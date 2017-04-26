package com.overwatch.warofship.GameImage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Bullet implements GameImageInterface {

    private Bitmap myBulletImage;

    private MyPlane myPlaneImage;

    private float x;
    private float y;

    public Bullet(Bitmap myBulletImage, MyPlane myPlaneImage) {

        this.myBulletImage = myBulletImage;
        this.myPlaneImage = myPlaneImage;

        x=myPlaneImage.getX()+myPlaneImage.getWidth()/2-10;
        y=myPlaneImage.getY()+70;//-myBulletImage.getHeight();
    }

    @Override
    public Bitmap getBitmap() {
        y-=125;
        return myBulletImage;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public boolean ifOutOfScreen(){
        if(y<=-10){
            return true;
        }else{
            return false;
        }
    }


}

