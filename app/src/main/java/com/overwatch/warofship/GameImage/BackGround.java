package com.overwatch.warofship.GameImage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.overwatch.warofship.GameLogic.GameViewInterface;

public class BackGround implements GameImageInterface {

    /**
     * variable declaration
     */
    private Bitmap backGround;
    private Bitmap backGroundPre;//We need to change the size of the background so we need a new bitmap for preparation
    private GameViewInterface currentGameView;
    private int height;// move param
    private Paint p;// paint for draw bitmap

    /**
     * constructor of BackGround
     * @param backGround
     *          bitmap stored picture from game view
     * @param currentGameView
     *          used game view in background
     */
    public BackGround(Bitmap backGround,GameViewInterface currentGameView){
        this.currentGameView = currentGameView;
        this.backGround = backGround;
        this.backGroundPre = Bitmap.createBitmap(currentGameView.getScreenWidth(),currentGameView.getScreenHeight(),
                Bitmap.Config.ARGB_8888);//create the empty Bitmap to draw the background
        this.height = 0;
        this.p = new Paint();
    }

    @Override
    /**
     * getter of drawn bitmap
     */
    public Bitmap getBitmap() {
        Canvas canvas=new Canvas(backGroundPre);//create the preparation canvas

        /**
         * draw the endless background to the screen
         * add two background images to achieve endless
         * roll two bitmap again and again
         */
        canvas.drawBitmap(backGround,
                new Rect(0,0,backGround.getWidth(),backGround.getHeight()),
                new Rect(0,height,currentGameView.getScreenWidth(),currentGameView.getScreenHeight()+height),
                p);
        canvas.drawBitmap(backGround,
                new Rect(0,0,backGround.getWidth(),backGround.getHeight()),
                new Rect(0,-(currentGameView.getScreenHeight())+height,currentGameView.getScreenWidth(),height),
                p);

        /**
         * move the background automatically
         * number change the move speed
         */
        height += 10;

        //this if condition is for loop the background forever
        if (height>currentGameView.getScreenHeight()){
            height=0;
        }

        return backGroundPre;
    }

    @Override
    // getter of location
    public float getX() {
        return 0;
    }
    public float getY() {
        return 0;
    }

}

