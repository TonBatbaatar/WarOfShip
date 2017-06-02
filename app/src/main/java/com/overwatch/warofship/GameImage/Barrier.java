package com.overwatch.warofship.GameImage;

import android.graphics.Bitmap;

import com.overwatch.warofship.GameLogic.GameViewInterface;

import java.util.Random;

/**
 * Created by Administrator on 2017/6/2.
 */

public class Barrier implements GameImageInterface {
    private Bitmap stone;
    private Random random;
    private GameViewInterface currentGameView;
    private float x;
    private float y;

    public Barrier(Bitmap stone,GameViewInterface gameView){
        this.stone=stone;
        this.random=new Random();
        this.currentGameView=gameView;
        x=random.nextInt(currentGameView.getScreenWidth()-this.stone.getWidth());
        y=-this.stone.getHeight()-20;
    }
    @Override
    public Bitmap getBitmap() {
        y+=10;
        return stone;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }
}
