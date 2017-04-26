package com.overwatch.warofship.GameImage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import com.overwatch.warofship.EndlessMode.EndlessModeGameView;

public class BackGround implements GameImageInterface {

    private Bitmap backGround;
    private Bitmap backGroundPre=null;//We need to change the size of the background so we need a new bitmap for preparation
    private int height=0;

    public BackGround(Bitmap backGround){
        this.backGround=backGround;
        this.backGroundPre=Bitmap.createBitmap(EndlessModeGameView.SCREEN_WIDTH,EndlessModeGameView.SCREEN_HEIGHT,
                Bitmap.Config.ARGB_8888);//create the empty Bitmap to draw the background
    }

    @Override
    public Bitmap getBitmap() {
        Canvas canvas=new Canvas(backGroundPre);//create the canvas to draw the background
        Paint p = new Paint();

        //The following code is for draw the endless background to the screen
        //so we need to add two background images
        //and move it to let player fell moving
        canvas.drawBitmap(backGround,
                new Rect(0,0,backGround.getWidth(),backGround.getHeight()),
                new Rect(0,height,EndlessModeGameView.SCREEN_WIDTH,EndlessModeGameView.SCREEN_HEIGHT+height),
                p);
        canvas.drawBitmap(backGround,
                new Rect(0,0,backGround.getWidth(),backGround.getHeight()),
                new Rect(0,-(EndlessModeGameView.SCREEN_HEIGHT)+height,EndlessModeGameView.SCREEN_WIDTH,height),
                p);

        //move the background automatically
        //the number can change the move speed
        height+=10;
        //this if condition is for loop the background forever
        if (height>EndlessModeGameView.SCREEN_HEIGHT){
            height=0;
        }
        return backGroundPre;
    }

    @Override
    //No need for set the x and y for background
    //because we need to draw it at full screen
    public float getX() {
        return 0;
    }
    public float getY() {
        return 0;
    }

}

