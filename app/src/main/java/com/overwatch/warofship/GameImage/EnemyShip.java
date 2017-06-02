package com.overwatch.warofship.GameImage;

import android.graphics.Bitmap;
import android.util.Log;

import com.overwatch.warofship.GameLogic.GameViewInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyShip implements GameImageInterface {

    /**
     * variable declaration
     */
    private GameViewInterface currentGameView;
    private Bitmap enemyShipImage;
    private Bitmap boomImage;
    private Random random;
    private List<Bitmap> booms;
    private List<Bitmap> enemyShipList;
    private int index;
    private boolean isDestroyed;
    private int moveDirection;
    private float x;
    private float y;


    /**
     * constructor of EnemyShip
     * @param enemyShipImage
     *          bitmap stored enemy ship picture
     * @param boomImage
     *          bitmap stored boss picture
     * @param gameView
     *          game view use enemy ship
     */
    public EnemyShip(Bitmap enemyShipImage,Bitmap boomImage,GameViewInterface gameView) {
        this.currentGameView=gameView;
        this.enemyShipImage=enemyShipImage;
        this.boomImage=boomImage;
        this.random = new Random();
        this.booms=new ArrayList<>();
        this.enemyShipList =new ArrayList<>();
        this.index=0;
        this.isDestroyed=false;
        this.moveDirection=random.nextInt(2);// to achieve move right or left ramdom

        enemyShipList.add(enemyShipImage);// add the ship bitmap to list
        this.initBoomPic();// initialize the boom picture

        x=random.nextInt(currentGameView.getScreenWidth()-this.enemyShipImage.getWidth());
        y=-this.enemyShipImage.getHeight()-20;
    }

    //initialize the boom pictures;
    private void initBoomPic(){
        booms.add(Bitmap.createBitmap(boomImage,0,0,boomImage.getWidth()/7,boomImage.getHeight()));
        booms.add(Bitmap.createBitmap(boomImage,(boomImage.getWidth()/7)*1,0,boomImage.getWidth()/7,boomImage.getHeight()));
        booms.add(Bitmap.createBitmap(boomImage,(boomImage.getWidth()/7)*2,0,boomImage.getWidth()/7,boomImage.getHeight()));
        booms.add(Bitmap.createBitmap(boomImage,(boomImage.getWidth()/7)*3,0,boomImage.getWidth()/7,boomImage.getHeight()));
        booms.add(Bitmap.createBitmap(boomImage,(boomImage.getWidth()/7)*4,0,boomImage.getWidth()/7,boomImage.getHeight()));
        booms.add(Bitmap.createBitmap(boomImage,(boomImage.getWidth()/7)*5,0,boomImage.getWidth()/7,boomImage.getHeight()));
        booms.add(Bitmap.createBitmap(boomImage,(boomImage.getWidth()/7)*6,0,boomImage.getWidth()/7,boomImage.getHeight()));
    }

    @Override
    /**
     * getter of bitmap
     */
    public Bitmap getBitmap() {

//        Bitmap returnBitmap;
//        if (!this.isDestroied){
//            returnBitmap=enemyShipImage;
//        }else{
//            returnBitmap=booms.get(index);
//            index++;
//        }

        Bitmap returnBitmap=enemyShipList.get(index);
        index++;

        /**
         * remove the ship
         * when destroyed and finish boom picture
         * when out of screen
         * move speed control here
         */
        if(index==7&&isDestroyed){
            currentGameView.getGameImages().remove(this);
        }
        if(this.ifOutOfScreen()){
            currentGameView.getGameImages().remove(this);
            Log.i("REMOVE.Test","The enemy ship is removed because it out of screen");
        }

        /**
         * logic make sure enemy ship draw
         * until it is destroyed
         */
        if(index==enemyShipList.size()){
            index=0;
        }

        y+=25;// speed of enmey ship
        return returnBitmap;
    }

    /**
     * getter of stroy mode
     * @param levelNumber
     *          level number of game view
     * @return
     */
    public Bitmap StoryModeGetBitmap(int levelNumber) {
            Bitmap returnBitmap = enemyShipList.get(index);
            index++;

            //remove the ship if nessary
            //first if: remove when destroy and played the boom picture
            if(index==7&&isDestroyed){
                currentGameView.getGameImages().remove(this);
            }
            //second if: remove if out of screen
            if(this.ifOutOfScreen()){
                currentGameView.getGameImages().remove(this);
                Log.i("REMOVE.Test","The enemy ship is removed because it out of screen");
            }

            //make sure that draw the picture every time except it removed
            if(index==enemyShipList.size()){
                index=0;
            }

            //control the move speed of the enemy ship by change number
            y+=16;
            if(levelNumber==2) {
                if(moveDirection==0){
                x+=3;
                }
            else{
                x-=3;
                }
            if (this.x>=(currentGameView.getScreenWidth()-this.enemyShipImage.getWidth())
                    ||this.x<=0){

                moveDirection=1-moveDirection;
            }
            }


            if(levelNumber==3) {

            if(moveDirection==0){
                x+=10;
            }
            else{
                x-=10;
            }
            if (this.x>=(currentGameView.getScreenWidth()-this.enemyShipImage.getWidth())
                    ||this.x<=0){

                moveDirection=1-moveDirection;
            }


        }

             if(levelNumber==4) {
                 Log.i("test", "level 4");
                 if(moveDirection==0){
                     x+=7;
                 }
                 else{
                     x-=7;
                 }
                 if (this.x>=(currentGameView.getScreenWidth()-this.enemyShipImage.getWidth())
                         ||this.x<=0){

                     moveDirection=1-moveDirection;
                 }


             }

        return returnBitmap;
    }


    @Override
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }

    //the method to judge if the bullet is out of screen
    //true: out of screen
    //false: in screen
    public boolean ifOutOfScreen(){
        if (this.y>=currentGameView.getScreenHeight()+10){
            return true;
        }else {
            return false;
        }
    }

    public void CheckIsBeat(){
        if (!this.isDestroyed){
            for (Bullet selectedBullet : currentGameView.getPlayerBulletImages()){
                if (selectedBullet.getX()>this.getX()
                        &&selectedBullet.getY()>this.getY()
                        &&selectedBullet.getX()<this.getX()+this.enemyShipImage.getWidth()
                        &&selectedBullet.getY()<this.getY()+this.enemyShipImage.getHeight()){

                    currentGameView.getPlayerBulletImages().remove(selectedBullet);
                    this.removeEnmeyShip();
                    break;
                }
            }
        }
    }

    public void removeEnmeyShip(){
        enemyShipList=booms;
        isDestroyed = true;
        currentGameView.setSCORE(currentGameView.getSCORE() + 50);
//        new sound(sound.view,currentGameView.sound_boom).start();
//        currentGameView.getMysound().play(currentGameView.sound_boom,1,1,1,0,1);
    }

}