package com.overwatch.warofship.GameImage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import com.overwatch.warofship.EndlessMode.EndlessModeGameView;

public class BackGround implements GameImageInterface, View.OnTouchListener {

    private Bitmap backGround;
    private Bitmap newBitmap=null;
    private int height=0;

    public BackGround(Bitmap bg){
        this.backGround=bg;
        newBitmap=Bitmap.createBitmap(EndlessModeGameView.SCREEN_WIDTH,EndlessModeGameView.SCREEN_HEIGHT,
                Bitmap.Config.ARGB_8888);
    }

    @Override
    public Bitmap getBitmap() {

        Canvas canvas=new Canvas(newBitmap);
        Paint p = new Paint();

        canvas.drawBitmap(backGround,
                new Rect(0,0,backGround.getWidth(),backGround.getHeight()),
                new Rect(0,height,EndlessModeGameView.SCREEN_WIDTH,EndlessModeGameView.SCREEN_HEIGHT),
                p);

        canvas.drawBitmap(backGround,
                new Rect(0,0,backGround.getWidth(),backGround.getHeight()),
                new Rect(0,(-EndlessModeGameView.SCREEN_WIDTH)+height,EndlessModeGameView.SCREEN_HEIGHT,height),
                p);

        height+=10;
        if (height>EndlessModeGameView.SCREEN_HEIGHT){
            height=0;
        }
        return newBitmap;

    }

    @Override
    public float getX() {
        return 0;
    }

    @Override
    public float getY() {
        return 0;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}

