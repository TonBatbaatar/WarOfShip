package com.overwatch.warofship.EndlessMode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.overwatch.warofship.GameImage.BackGround;
import com.overwatch.warofship.GameImage.Bullet;
import com.overwatch.warofship.GameImage.EnemyBossShip;
import com.overwatch.warofship.GameImage.EnemyShip;
import com.overwatch.warofship.GameImage.GameImageInterface;
import com.overwatch.warofship.GameImage.MyPlane;
import com.overwatch.warofship.R;

import java.util.ArrayList;
import java.util.List;

public class EndlessModeGameView extends SurfaceView implements View.OnTouchListener {

    private GameLoop gameLoop;
    private SurfaceHolder holder=null;

    private Paint p=new Paint();

    private static int count;
    private MyPlane selectedPlane;

    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;


    //Create Bitmap for picture to store
    private Bitmap backGround;
    private Bitmap myPlane;
    private Bitmap enemy;
    private Bitmap enemyBoss;
    private Bitmap bullet;
    private Bitmap boom;
    private Bitmap preparation;

    public static ArrayList<GameImageInterface> gameImages = new ArrayList();
    public static ArrayList<Bullet> bulletImages = new ArrayList();

    public EndlessModeGameView(Context context){

        super(context);
        gameLoop = new GameLoop(this);
        this.setOnTouchListener(this);
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
                        init();
                    }

                    @Override
                    public void surfaceDestroyed(SurfaceHolder holder) {
                        gameLoop.setRunning(false);
                    }
                });
        this.count=0;
    }

    ////Method for initialize the game images:
    //initialize the bitmaps with the images.
    private void init(){

        preparation= Bitmap.createBitmap(SCREEN_WIDTH,SCREEN_HEIGHT, Bitmap.Config.ARGB_8888);

        backGround= BitmapFactory.decodeResource(getResources(), R.mipmap.background);
        myPlane= BitmapFactory.decodeResource(getResources(),R.mipmap.myplane);
        enemy= BitmapFactory.decodeResource(getResources(),R.mipmap.enemy);
        bullet= BitmapFactory.decodeResource(getResources(), R.mipmap.bullet);
        boom=BitmapFactory.decodeResource(getResources(),R.mipmap.boom);
        enemyBoss=BitmapFactory.decodeResource(getResources(),R.mipmap.enemyboss);

        gameImages.add(new BackGround(backGround));
        gameImages.add(new MyPlane(myPlane));
    }

    @Override
    public void draw(Canvas canvas) {

        if(canvas!=null){

            Canvas c=new Canvas(preparation);
            count++;

            //// add enemy ship randomly
            //if condition means:
            //every five time add an enemy ship
            //can change it to control the speed of add new enemy ship
            if (count%5==0){
                gameImages.add(new EnemyShip(enemy,boom));
            }
            if (count%50==0){
                gameImages.add(new EnemyBossShip(enemyBoss,boom));
            }

            ////draw the picture to the screen except bullet
            for (GameImageInterface image : (List<GameImageInterface>)gameImages.clone()){
                if (image instanceof EnemyShip){
                    ((EnemyShip) image).isBeat(bulletImages);
                    Log.i("REMOVE","Destroied the enemy ship!");

                }
                c.drawBitmap(image.getBitmap(),image.getX(),image.getY(),p);

                if (image instanceof MyPlane && count%4==0){
                    bulletImages.add(new Bullet(bullet,(MyPlane)image));
                }

            }

            ////draw the bullet image to the screen
            for (Bullet bullet : bulletImages){
                c.drawBitmap(bullet.getBitmap(),bullet.getX(),bullet.getY(),p);
            }

            ////remove the bullet already out of the screen
            // but there is still one problem to deal with:
            // I can't remove multi bullet in one loop
            // so i use break to remove one at one time;
            for (Bullet bullet : bulletImages){
                if (bullet.ifOutOfScreen()){
                    bulletImages.remove(bullet);
                    Log.i("REMOVE","Removed the bullet!");
                }
                break;
            }

            canvas.drawBitmap(preparation,0,0,p);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (event.getAction()==MotionEvent.ACTION_DOWN){

            for (GameImageInterface image: gameImages){

                if (image instanceof MyPlane){

                    if (((MyPlane) image).ifPlaneSelected(event.getX(),event.getY())){
                        selectedPlane=(MyPlane)image;
                    }else {
                        selectedPlane = null;
                    }
                    break;
                }
            }

        } else if (event.getAction()==MotionEvent.ACTION_MOVE){

            if (selectedPlane!=null){
                float newX=event.getX()-(selectedPlane.getWidth()/2);
                float newY=event.getY()-(selectedPlane.getHeight()/2);
                selectedPlane.setX(newX);
                selectedPlane.setY(newY);
            }

        } else if (event.getAction()==MotionEvent.ACTION_UP){

            selectedPlane=null;

        }

        return true;
    }



}
