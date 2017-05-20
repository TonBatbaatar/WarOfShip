package com.overwatch.warofship.EndlessMode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

import com.overwatch.warofship.GameImage.BackGround;
import com.overwatch.warofship.GameImage.Bullet;
import com.overwatch.warofship.GameImage.EnemyBossShip;
import com.overwatch.warofship.GameImage.EnemyBullet;
import com.overwatch.warofship.GameImage.EnemyShip;
import com.overwatch.warofship.GameImage.GameImageInterface;
import com.overwatch.warofship.GameImage.MyShip;
import com.overwatch.warofship.R;

import java.util.ArrayList;
import java.util.List;

public class Story3LevGV extends EndlessModeGameView implements View.OnTouchListener {
    private GameLoop gameLoop;
    private SurfaceHolder holder=null;
    private Context context;
    private sound sound;

    private Paint p=new Paint();// Paint for all draw code.

    private static int count;//controller of the speed of add a new item to the game.
    private MyShip selectedShip;//used for control the ship.

    //Class variable to store width and height fo the screen.
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;
    public static int SCORE;


    //Create Bitmap for picture to store.
    private Bitmap backGround;
    private Bitmap myShip;
    private Bitmap enemy;
    private Bitmap enemyBoss;
    private Bitmap bullet;
    private Bitmap enemyBullet;
    private Bitmap boom;
    private Bitmap preparation;



    public static SoundPool mysound;
    public static int sound_boom;
    public static int sound_shot;
    private int sound_background;

    //Class variable to store bullet images and game images.
    //the reason for using class variable is for convenience.
    //we need to use these tree variable in other classes.
    public static ArrayList<GameImageInterface> gameImages = new ArrayList();
    public static ArrayList<Bullet> PLAYER_BULLET_IMAGES = new ArrayList();
    public static ArrayList<EnemyBullet> ENEMY_BULLET_IMAGES = new ArrayList();


    public int modenumber;




    //Constructor of the endless mode game view.
    public Story3LevGV(Context context){

        super(context);
        gameLoop = new GameLoop(this);//initialize the new loop for the endless mode.
        sound = new sound(this,sound.i);
        this.setOnTouchListener(this);//add the touch listener.
        holder=getHolder();
        this.context=context;
        modenumber=3;
        //Main part of run the game.
        //Game start from here.
        holder.addCallback(
                new SurfaceHolder.Callback(){
                    @Override
                    //The thread begins from here
                    //Game loop activated when the surface created.
                    public void surfaceCreated(SurfaceHolder holder) {
                        gameLoop.setRunning(true);//Set the run state to run
                        gameLoop.start();//Start the thread
                    }

                    @Override
                    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                        EndlessModeGameView.SCREEN_WIDTH = width;//initialize the class variable when surface changed
                        EndlessModeGameView.SCREEN_HEIGHT = height;
                        init();//initialize all of the pictures in the class
                    }

                    @Override
                    public void surfaceDestroyed(SurfaceHolder holder) {
                        gameLoop.setRunning(false);//set the run state of the game loop to stop
                    }
                });
        this.count=0;//initialize the speed controller
        this.SCORE=0;
    }

    ////Method for initialize the game images:
    //initialize the bitmaps with the images.
    //this method is used in constructor
    private void init(){

        //Preparation Bitmap is used for make the draw part more fluently
        preparation= Bitmap.createBitmap(SCREEN_WIDTH,SCREEN_HEIGHT, Bitmap.Config.ARGB_8888);

        backGround= BitmapFactory.decodeResource(getResources(), R.mipmap.sea);
        myShip= BitmapFactory.decodeResource(getResources(),R.mipmap.playership);
        enemy= BitmapFactory.decodeResource(getResources(),R.mipmap.enemyship);
        enemyBoss=BitmapFactory.decodeResource(getResources(),R.mipmap.enemybossship);
        bullet= BitmapFactory.decodeResource(getResources(), R.mipmap.bullet);
        enemyBullet= BitmapFactory.decodeResource(getResources(), R.mipmap.boosbullet);
        boom=BitmapFactory.decodeResource(getResources(),R.mipmap.boom);


        gameImages.add(new BackGround(backGround));//add bitmap to list
        gameImages.add(new MyShip(myShip,boom,context));


        mysound=new SoundPool(10, AudioManager.STREAM_SYSTEM,0);
        sound_boom=mysound.load(getContext(),R.raw.boom,1);
        sound_shot=mysound.load(getContext(),R.raw.shot,1);
    }





    @Override
    //the method of draw the bitmap to the screen
    //this method is used in Game loop
    public void draw(Canvas canvas) {

        if(canvas!=null){

            Canvas preparationCanvas = new Canvas(preparation);//create a new canvas to draw preparation Bitmap
            count++;//Speed controller to be updated


            if (count%15==0){
                SCORE+=10;
            }


            //// Add enemy ship randomly.
            //if condition means that :
            //every 15 time --> add an basic enemy ship
            //every 150 time --> add an boss enemy ship
            if (count%15==0){
                gameImages.add(new EnemyShip(enemy,boom,this));//every five times we add an enemy ship
            }
            if (count%150==0){
                gameImages.add(new EnemyBossShip(enemyBoss,boom,5));//every 150 times we add an enemy ship
            }



            //// Draw game images
            // For loop --> draw every bitmap in the gameImages list
            for (GameImageInterface image : (List<GameImageInterface>)gameImages.clone()){

                //following draw method --> draw every bitmaps to preparation canvas
                if(image instanceof EnemyShip){
                    preparationCanvas.drawBitmap(((EnemyShip) image).StoryModegetBitmap(modenumber),image.getX(),image.getY(),p);
                }
                preparationCanvas.drawBitmap(image.getBitmap(),image.getX(),image.getY(),p);


                //Add the bullet
                //change new bullet inserting speed here
                if (image instanceof MyShip && count%10==0){
                    PLAYER_BULLET_IMAGES.add(new Bullet(bullet,(MyShip)image));
                    new sound(sound.view,sound_shot).start();
                    EndlessModeGameView.mysound.play(sound_shot,1,1,1,0,1);
                } else if (image instanceof EnemyBossShip && count%25==0){
                    ENEMY_BULLET_IMAGES.add(new EnemyBullet(enemyBullet,(EnemyBossShip)image));
                }


                //// Destroy ships
                // Destroy when --> crash with ship
                // Destroy when --> beat by bullet
                if (image instanceof MyShip){
                    ((MyShip) image).checkIsBeat();
                } else if (image instanceof EnemyShip){
                    ((EnemyShip) image).CheckIsBeat();
                } else if (image instanceof EnemyBossShip){
                    ((EnemyBossShip) image).checkIsBeat();
                }
            }


            ////Draw player bullet
            //remove the bullet already out of the screen
            for (Bullet bullet : PLAYER_BULLET_IMAGES){
                if(bullet.ifOutOfScreen()){
                    PLAYER_BULLET_IMAGES.remove(bullet);
                    Log.i("REMOVE","Removed the player bullet!");
                }else {
                    preparationCanvas.drawBitmap(bullet.getBitmap(), bullet.getX(), bullet.getY(), p);
                }
            }


            ////Draw enemy bullet
            //remove the bullet already out of the screen
            for (EnemyBullet bullet : ENEMY_BULLET_IMAGES){
                if(bullet.ifOutOfScreen()){
                    ENEMY_BULLET_IMAGES.remove(bullet);
                    Log.i("REMOVE","Removed the enemy bullet!");
                }else{
                    preparationCanvas.drawBitmap(bullet.getBitmap(),bullet.getX(),bullet.getY(),p);
                }
            }


            //// Draw the preparation Bitmap to screen.
            canvas.drawBitmap(preparation,0,0,p);
        }
    }

    @Override
    //Following method is code for touch listener
    public boolean onTouch(View v, MotionEvent event) {

        if (event.getAction()==MotionEvent.ACTION_DOWN){
            for (GameImageInterface image: gameImages){
                if (image instanceof MyShip){
                    //this if method is select the ship when we touch on it
                    if (((MyShip) image).ifPlaneSelected(event.getX(),event.getY())){
                        selectedShip=(MyShip)image;
                    }else {
                        selectedShip = null;
                    }
                    break;
                }
            }
        } else if (event.getAction()==MotionEvent.ACTION_MOVE){

            //this if method is actual part of draw at location we moved
            if (selectedShip!=null){
                float newX=event.getX()-(selectedShip.getWidth()/2);
                float newY=event.getY()-(selectedShip.getHeight()/2);
                selectedShip.setX(newX);
                selectedShip.setY(newY);
            }

        } else if (event.getAction()==MotionEvent.ACTION_UP){
            selectedShip=null;//release the ship
        }
        return true;
    }
}
