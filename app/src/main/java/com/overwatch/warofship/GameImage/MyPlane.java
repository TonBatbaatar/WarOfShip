package com.overwatch.warofship.GameImage;

/**
 * Created by daxi on 2017/4/25.
 */

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.overwatch.warofship.EndlessMode.EndlessModeGameView;

public class MyPlane implements GameImageInterface {

    private Bitmap myPlaneImage;
    private Bitmap newBitmap = null;

    private float x;
    private float y;
    private float width;
    private float height;

    public MyPlane(Bitmap mp){
        this.myPlaneImage=mp;
        newBitmap=Bitmap.createBitmap(EndlessModeGameView.SCREEN_HEIGHT,EndlessModeGameView.SCREEN_WIDTH,
                Bitmap.Config.ARGB_8888);

        width=myPlaneImage.getWidth();
        height=myPlaneImage.getHeight();
        x=(EndlessModeGameView.SCREEN_WIDTH-width)/2;
        y=EndlessModeGameView.SCREEN_HEIGHT-height-10;

    }

    @Override
    public Bitmap getBitmap() {

        Canvas canvas=new Canvas(newBitmap);
        Paint paint=new Paint();

        canvas.drawBitmap(this.myPlaneImage,
                new Rect(0,0,this.myPlaneImage.getWidth(),this.myPlaneImage.getHeight()),
                new Rect(0,0,this.myPlaneImage.getWidth(),this.myPlaneImage.getHeight()),
                paint);

        return newBitmap;
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

    public boolean ifPlaneSelected(float x, float y){
        if (x>this.getX()
                &&y>this.getY()
                &&x<this.getX()+this.myPlaneImage.getWidth()
                &&y<this.getY()+this.myPlaneImage.getHeight()){
            return true;
        }else {
            return false;
        }
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

}

