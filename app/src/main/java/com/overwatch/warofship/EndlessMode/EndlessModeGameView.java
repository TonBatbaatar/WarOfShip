package com.overwatch.warofship.EndlessMode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.overwatch.warofship.GameImage.BackGround;
import com.overwatch.warofship.GameImage.Bomb;
import com.overwatch.warofship.GameImage.Bullet;
import com.overwatch.warofship.GameImage.EnemyBossShip;
import com.overwatch.warofship.GameImage.EnemyBullet;
import com.overwatch.warofship.GameImage.EnemyShip;
import com.overwatch.warofship.GameImage.GameImageInterface;
import com.overwatch.warofship.GameImage.MyShip;
import com.overwatch.warofship.GameImage.Sound;
import com.overwatch.warofship.GameLogic.GameLoop;
import com.overwatch.warofship.GameLogic.GameViewInterface;
import com.overwatch.warofship.GameImage.Prop;
import com.overwatch.warofship.R;

import java.util.ArrayList;
import java.util.List;

public class EndlessModeGameView extends SurfaceView implements View.OnTouchListener,GameViewInterface {
    /**
     * variable declaration:
     * thread variables
     */
    private GameLoop gameLoop;
    private SurfaceHolder holder=null;
    private Context context;
    private Paint p;// Paint for all pictures.

    /**
     * variable declaration:
     * Bitmap to store game picture
     */
    private Bitmap backGround;
    private Bitmap myShip;
    private Bitmap enemy;
    private Bitmap enemyBoss;
    private Bitmap initialBullet;
    private Bitmap secondBullet;
    private Bitmap thirdBullet;
    private Bitmap fourthBullet;
    private Bitmap enemyBullet;
    private Bitmap boom;
    private Bitmap preparation;
    private Bitmap prop;
    private Bitmap bomb;

    /**
     * variable declaration:
     * Sound resource
     */
    private Sound Sound;
    public SoundPool mysound;
    public static int sound_boom;
    public static int sound_shot;
    private int sound_background;

    /**
     * variable declaration:
     * game info
     */
    private int SCREEN_WIDTH;// variable to store width and height fo the screen.
    private int SCREEN_HEIGHT;
    private int SCORE;
    private int count;//controller of the speed of add a new item to the game.
    private MyShip selectedShip;//used for control the ship.
    private ArrayList<GameImageInterface> gameImages;
    private ArrayList<Bullet> playerBulletImages;
    private ArrayList<EnemyBullet> enemyBulletImages;
    private ArrayList<Prop> propImages;
    private ArrayList<Bomb> bombImages;
    private int strengthenTime;
    public static int bossnumber;
    public int modenumber = 3;

    /**
     * constructor of EndlessModeGameView
     * @param context
     */
    public EndlessModeGameView(Context context){
        super(context);

        this.gameLoop = new GameLoop(this);//initialize the new loop for the endless mode.
        this.holder = getHolder();
        this.context = context;
        this.p = new Paint();
        this.setOnTouchListener(this);//add the touch listener.
        this.holder.addCallback(
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
                        SCREEN_WIDTH = width;//initialize the class variable when surface changed
                        SCREEN_HEIGHT = height;
                        init();//initialize all of the pictures in the class
                    }

                    @Override
                    public void surfaceDestroyed(SurfaceHolder holder) {
                        gameLoop.setRunning(false);//set the run state of the game loop to stop
                    }
                });

        Sound = new Sound(this, Sound.i);

        this.count = 0;//initialize the speed controller
        this.bossnumber = 0;//control the number of boss ship
        this.SCORE = 0;
        this.gameImages = new ArrayList();
        this.playerBulletImages = new ArrayList();
        this.enemyBulletImages = new ArrayList();
        this.propImages =new ArrayList<>();
        this.bombImages = new ArrayList<>();
        this.strengthenTime=0;

    }

    /**
     * Method for initialize the game images and sounds:
     * insert the picture to bitmap
     * insert the music to Sound
     */
    private void init(){

        //Preparation Bitmap is used for make the draw part more fluently
        preparation = Bitmap.createBitmap(SCREEN_WIDTH,SCREEN_HEIGHT, Bitmap.Config.ARGB_8888);

        backGround= BitmapFactory.decodeResource(getResources(), R.mipmap.sea);
        myShip= BitmapFactory.decodeResource(getResources(), R.mipmap.playership);
        enemy= BitmapFactory.decodeResource(getResources(),R.mipmap.enemyship);
        enemyBoss=BitmapFactory.decodeResource(getResources(),R.mipmap.enemybossship);
        initialBullet= BitmapFactory.decodeResource(getResources(), R.mipmap.bullet);
        secondBullet= BitmapFactory.decodeResource(getResources(), R.mipmap.bullet2);
        thirdBullet= BitmapFactory.decodeResource(getResources(), R.mipmap.bullet3);
        fourthBullet= BitmapFactory.decodeResource(getResources(), R.mipmap.bullet4);
        enemyBullet= BitmapFactory.decodeResource(getResources(), R.mipmap.boosbullet);
        prop=BitmapFactory.decodeResource(getResources(),R.mipmap.bullet);
        bomb=BitmapFactory.decodeResource(getResources(),R.mipmap.bullet4);
        boom = BitmapFactory.decodeResource(getResources(),R.mipmap.boom);

        gameImages.add(new BackGround(backGround,this));//add bitmap to list
        gameImages.add(new MyShip(myShip,boom,context,this));

        mysound = new SoundPool(10, AudioManager.STREAM_SYSTEM,0);
        sound_boom = mysound.load(getContext(),R.raw.boom,1);
        sound_shot = mysound.load(getContext(),R.raw.shot,1);
    }

    @Override
    /**
     * the method of draw use in thread(Game loop)
     * to draw the bitmap to screen
     */
    public void draw(Canvas canvas) {

        if(canvas!=null){

            Canvas preparationCanvas = new Canvas(preparation);//create a new canvas to draw preparation Bitmap
            count++;//Speed controller to be updated
            bossnumber++;
            strengthenTime++;
            if (count%15==0){
                SCORE+=5;// the score increase by time player survive
            }

            /**
             * Add enemy ship randomly:
             * every 15 time --> add an basic enemy ship
             * every 150 time --> add an boss enemy ship
             * every 150 time --> add a prop
             */
            if (count%15==0){
                gameImages.add(new EnemyShip(enemy,boom,this));//every five times we add an enemy ship
            }
            if (bossnumber%150==0&&bossnumber<=600){
                gameImages.add(new EnemyBossShip(enemyBoss,boom,5,this));//every 150 times we add an enemy ship
            }
            if(count%150==0){
                propImages.add(new Prop(prop,this));
            }
            if(count%200==0){
                bombImages.add(new Bomb(bomb,this));
            }

            /**
             * Draw game images
             * draw every bitmap in the gameImages list
             * draw to preparation canvas
             */
            for (GameImageInterface image : (List<GameImageInterface>)gameImages.clone()){

                preparationCanvas.drawBitmap(image.getBitmap(),image.getX(),image.getY(),p);// draw code

                /**
                 * Add bullet
                 * player and enemy bullet all here
                 * bullet adding speed control here
                 */
                if (image instanceof MyShip && count%10==0){
                    playerBulletImages.add(new Bullet(initialBullet,(MyShip)image,secondBullet,thirdBullet,fourthBullet));
                    new Sound(Sound.view,sound_shot).start();
                    mysound.play(sound_shot,1,1,1,0,1);
                } else if (image instanceof EnemyBossShip && count%25==0){
                    enemyBulletImages.add(new EnemyBullet(enemyBullet,(EnemyBossShip)image, this));
                }

                /**
                 * 1.check destroy
                 * 2.remove ships
                 * 3.receive reward
                 * destroy when crash with ship
                 * destroy when beat by bullet
                 */
                if (image instanceof MyShip){
                    if (count<15) {
                        ((MyShip) image).moveintoscreen();
                    }
                    ((MyShip) image).checkIsBeat();
                    ((MyShip) image).receiveprop();
                    ((MyShip) image).receivebomb();
                } else if (image instanceof EnemyShip){
                    ((EnemyShip) image).CheckIsBeat();
                } else if (image instanceof EnemyBossShip){
                    ((EnemyBossShip) image).checkIsBeat();
                }
            }

            /**
             * draw player bullet
             * remove the bullet
             * remove when bullet out of screen
             */
            for (Bullet bullet : playerBulletImages){
                if(bullet.ifOutOfScreen()){
//                    PLAYER_BULLET_IMAGES.remove(bullet);
                    Log.i("REMOVE","Removed the player bullet!");
                }else {
                    preparationCanvas.drawBitmap(bullet.getBitmap(), bullet.getX(), bullet.getY(), p);
                }
            }

            /**
             * draw enemy bullet
             * remove the bullet
             * remove when bullet out of screen
             */
            for (EnemyBullet bullet : enemyBulletImages){
                if(bullet.ifOutOfScreen()){
//                    ENEMY_BULLET_IMAGES.remove(bullet);
                    Log.i("REMOVE","Removed the enemy bullet!");
                }else{
                    preparationCanvas.drawBitmap(bullet.getBitmap(),bullet.getX(),bullet.getY(),p);
                }
            }

            /**
             * Draw props
             * remove the prop already out of the screen
             */
            for(Prop prop : propImages){
                if(prop.ifOutOfScreen()){
                    Log.i("REMOVE","Removed the prop!");
                }else{
                    preparationCanvas.drawBitmap(prop.getBitmap(),prop.getX(),prop.getY(),p);
                }
            }
            for(Bomb bomb : bombImages){
                if(bomb.ifOutOfScreen()){
                    Log.i("REMOVE","Removed the prop!");
                }else{
                    preparationCanvas.drawBitmap(bomb.getBitmap(),bomb.getX(),bomb.getY(),p);
                }
            }

            /**
             * discard reward after amount of time
             * change discard time
             */
            if(strengthenTime%150==0){
                MyShip.levelofbullet=1;
            }


            /**
             * draw score board
             * set the type of text here
             * size or color or style
             */
            Paint textp=new Paint();
            String scoreBoard = "SCORE: <" + this.SCORE + " >";
            textp.setColor(Color.RED);
            textp.setTextSize(22);
            preparationCanvas.drawText(scoreBoard, 10,20,textp);


            // Draw the preparation Bitmap to screen.
            canvas.drawBitmap(preparation,0,0,p);
        }
    }

    @Override
    /**
     * on touch listener to control player ship
     */
    public boolean onTouch(View v, MotionEvent event) {

        if (event.getAction()==MotionEvent.ACTION_DOWN){
            for (GameImageInterface image: gameImages){
                if (image instanceof MyShip){
                    //this if method is select the ship when we touch on it
                    if (((MyShip) image).ifPlaneSelected(event.getX(),event.getY())){
                        if (count>15) {
                            selectedShip = (MyShip) image;
                        }
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

    /**
     * useful getters
     * @return
     *          return the variable needed
     */
    public SoundPool getMysound() {
        return mysound;
    }
    public int getScreenWidth() {
        return SCREEN_WIDTH;
    }
    public int getScreenHeight() {
        return SCREEN_HEIGHT;
    }
    public ArrayList<GameImageInterface> getGameImages() {
        return gameImages;
    }
    public ArrayList<Bullet> getPlayerBulletImages() {
        return playerBulletImages;
    }
    public int getSCORE() {
        return SCORE;
    }
    public void setSCORE(int SCORE) {
        this.SCORE = SCORE;
    }
    public ArrayList<EnemyBullet> getEnemyBulletImages() {
        return enemyBulletImages;
    }

    @Override
    public ArrayList<Prop> getPropImages() {
        return null;
    }

    @Override
    public ArrayList<Bomb> getBombImages() {
        return null;
    }

    public int getStrengthenTime() {
        return strengthenTime;
    }

    public void setStrengthenTime(int strengthenTime) {
        strengthenTime = strengthenTime;
    }
}
