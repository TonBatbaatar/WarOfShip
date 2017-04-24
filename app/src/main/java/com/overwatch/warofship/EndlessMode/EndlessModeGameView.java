package com.overwatch.warofship.EndlessMode;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import GameLogic.GameLoop;

public class EndlessModeGameView extends SurfaceView {
    private GameLoop gameLoop;
    private SurfaceHolder holder=null;
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    //Create Bitmap for picture to store
    private Bitmap backGround;
    private Bitmap myPlane;
    private Bitmap enemy;
    private Bitmap bullet;
    private Bitmap preparation;
//    private List<GameImage> gameImages = new ArrayList();
//    private List<MyBulletImage> bulletImages = new ArrayList();

    public EndlessModeGameView(Context context){
        super(context);
        gameLoop = new GameLoop(this);
        holder=getHolder();
        holder.addCallback(
                new SurfaceHolder.Callback(){

                    @Override
                    public void surfaceCreated(SurfaceHolder holder) {
                        gameLoop.setRunning(true);
                        gameLoop.start();
                    }

                    @Override
                    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                        EndlessModeGameView.SCREEN_WIDTH = width;
                        EndlessModeGameView.SCREEN_HEIGHT = height;
                    }

                    @Override
                    public void surfaceDestroyed(SurfaceHolder holder) {
                        gameLoop.setRunning(false);
                    }
                });
    }
}