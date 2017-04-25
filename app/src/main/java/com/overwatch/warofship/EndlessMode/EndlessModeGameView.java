package com.overwatch.warofship.EndlessMode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.overwatch.warofship.GameImage.BackGround;
import com.overwatch.warofship.GameImage.Bullet;
import com.overwatch.warofship.GameImage.EnemyShip;
import com.overwatch.warofship.GameImage.GameImageInterface;
import com.overwatch.warofship.GameImage.MyPlane;
import com.overwatch.warofship.R;

import java.util.ArrayList;
import java.util.List;

public class EndlessModeGameView extends SurfaceView {
    private GameLoop gameLoop;
    private SurfaceHolder holder=null;

    private Paint p=new Paint();

    private static int count;

    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;


    //Create Bitmap for picture to store
    private Bitmap backGround;
    private Bitmap myPlane;
    private Bitmap enemy;
    private Bitmap bullet;
    private Bitmap preparation;
    private List<GameImageInterface> gameImages = new ArrayList();
    private List<Bullet> bulletImages = new ArrayList();

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

        this.init();// initialize the images.
        count=0;
    }

    ////Method for initialize the game images:
    //initialize the bitmaps with the images.
    private void init(){
        backGround= BitmapFactory.decodeResource(getResources(), R.mipmap.background);
        myPlane= BitmapFactory.decodeResource(getResources(),R.mipmap.myplane);
        enemy= BitmapFactory.decodeResource(getResources(),R.mipmap.enemy);
        bullet= BitmapFactory.decodeResource(getResources(), R.mipmap.bullet);
        preparation= Bitmap.createBitmap(SCREEN_WIDTH,SCREEN_HEIGHT, Bitmap.Config.ARGB_8888);

        gameImages.add(new BackGround(backGround));
        gameImages.add(new MyPlane(myPlane));
    }

    @Override
    public void draw(Canvas canvas) {
        if(canvas!=null){

            Canvas c=new Canvas(preparation);
            count++;

            //// add enemy ship ramdomly
            //if condition means:
            //every five time add an enemy ship
            //can change it to control the speed of add new enemy ship
            if (count%5==0){
                gameImages.add(new EnemyShip(enemy));
            }

            ////draw the picture to the screen except bullet
            for (GameImageInterface image : gameImages){
                c.drawBitmap(image.getBitmap(),image.getX(),image.getY(),p);

                if (image instanceof MyPlane && count%2==0){
                    bulletImages.add(new Bullet(bullet,(MyPlane)image));
                }

            }

            ////draw the bullet image to the screen
            for (Bullet bullet : bulletImages){
                c.drawBitmap(bullet.getBitmap(),bullet.getX(),bullet.getY(),p);
            }

            ////remove the enemy ship if beat
            //some problem with that:
            //may because i use so many for loop
            //some bullet can not destroy the ship
            for (GameImageInterface image : gameImages){
                if (image instanceof EnemyShip){

                    EnemyShip selectedEnemyShip=(EnemyShip)image;
                    if (selectedEnemyShip.isBeat(bulletImages)){
                        gameImages.remove(image);
                        Log.i("REMOVE","Destroied the enemy ship!");
                        break;
                    }

                }

            }

            ////remove the enemy ship already out of the screen
            // but there is still one problem to deal with:
            // I can't remove multible bullet in one loop
            // so i use break to remove one at one time;
            for (GameImageInterface image : gameImages){
                if (image instanceof EnemyShip){

                    EnemyShip selectedEnemyShip=(EnemyShip)image;
                    if (selectedEnemyShip.ifOutOfScreen()){
                        gameImages.remove(image);
                        Log.i("REMOVE","Removed the enemy ship!");
                        break;
                    }

                }

            }

            ////remove the bullet already out of the screen
            // but there is still one problem to deal with:
            // I can't remove multible bullet in one loop
            // so i use break to remove one at one time;
            for (Bullet bullet : bulletImages){
                if (bullet.ifOutOfScreen()){
                    bulletImages.remove(bullet);
                    Log.i("REMOVE","Removed the bullet!");
                }
                break;
            }

            canvas.drawBitmap(preparation,0,0,p);
            Log.i("THREAD","Speed test!");//testing the speed of the thread

        }
    }

}
