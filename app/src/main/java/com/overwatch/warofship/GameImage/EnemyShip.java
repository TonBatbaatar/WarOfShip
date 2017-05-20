package com.overwatch.warofship.GameImage;

import android.graphics.Bitmap;
import android.util.Log;

import com.overwatch.warofship.EndlessMode.EndlessModeGameView;
import com.overwatch.warofship.EndlessMode.sound;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyShip implements GameImageInterface {

    private EndlessModeGameView currentGameView;
    private Bitmap enemyShipImage;
    private Bitmap boomImage;
    private Random random = new Random();
    private List<Bitmap> booms=new ArrayList<>();
    private List<Bitmap> enemyship=new ArrayList<>();
    private int index=0;
    private boolean isDestroied;
    private int movedirection;



    private float x;
    private float y;


    //constructer
    public EnemyShip(Bitmap enemyShipImage,Bitmap boomImage,EndlessModeGameView gameView) {

        this.enemyShipImage=enemyShipImage;
        this.boomImage=boomImage;
        this.isDestroied=false;
//        this.movedirection=true;
        movedirection=random.nextInt(2);
        this.currentGameView=gameView;
        enemyship.add(enemyShipImage);
        //initialize the boom picture
        this.initBoomPic();

        x=random.nextInt(EndlessModeGameView.SCREEN_WIDTH-this.enemyShipImage.getWidth());
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
    public Bitmap getBitmap() {

//        Bitmap returnBitmap;
//        if (!this.isDestroied){
//            returnBitmap=enemyShipImage;
//        }else{
//            returnBitmap=booms.get(index);
//            index++;
//        }

        Bitmap bitmaps=enemyship.get(index);
        index++;

        //remove the ship if nessary
        //first if: remove when destroy and played the boom picture
        if(index==7&&isDestroied){
            currentGameView.GAME_IMAGES.remove(this);
        }
        //second if: remove if out of screen
        if(this.ifOutOfScreen()){
            currentGameView.GAME_IMAGES.remove(this);
            Log.i("REMOVE.Test","The enemy ship is removed because it out of screen");
        }

        //make sure that draw the picture every time except it removed
        if(index==enemyship.size()){
            index=0;
        }

        //control the move speed of the enemy ship by change number
        y+=25;


        return bitmaps;
    }

    public Bitmap StoryModegetBitmap(int levelnumber) {
            Bitmap bitmaps=enemyship.get(index);
            index++;

            //remove the ship if nessary
            //first if: remove when destroy and played the boom picture
            if(index==7&&isDestroied){
                currentGameView.GAME_IMAGES.remove(this);
            }
            //second if: remove if out of screen
            if(this.ifOutOfScreen()){
                currentGameView.GAME_IMAGES.remove(this);
                Log.i("REMOVE.Test","The enemy ship is removed because it out of screen");
            }

            //make sure that draw the picture every time except it removed
            if(index==enemyship.size()){
                index=0;
            }

            //control the move speed of the enemy ship by change number
            y+=16;
            if(levelnumber==2) {
                if(movedirection==0){
                x+=3;
                }
            else{
                x-=3;
                }
            if (this.x>=(currentGameView.SCREEN_WIDTH-this.enemyShipImage.getWidth())
                    ||this.x<=0){

                movedirection=1-movedirection;
            }
            }


            if(levelnumber==3) {

            if(movedirection==0){
                x+=10;
            }
            else{
                x-=10;
            }
            if (this.x>=(currentGameView.SCREEN_WIDTH-this.enemyShipImage.getWidth())
                    ||this.x<=0){

                movedirection=1-movedirection;
            }


        }




             if(levelnumber==4) {
                 Log.i("test", "level 4");
                 if(movedirection==0){
                     x+=7;
                 }
                 else{
                     x-=7;
                 }
                 if (this.x>=(currentGameView.SCREEN_WIDTH-this.enemyShipImage.getWidth())
                         ||this.x<=0){

                     movedirection=1-movedirection;
                 }


             }





//

        return bitmaps;
    }


    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    //the method to judge if the bullet is out of screen
    //true: out of screen
    //false: in screen
    public boolean ifOutOfScreen(){
        if (this.y>=currentGameView.SCREEN_HEIGHT+10){
            return true;
        }else {
            return false;
        }
    }

    public void CheckIsBeat(){
        if (!this.isDestroied){
            for (Bullet selectedBullet : currentGameView.PLAYER_BULLET_IMAGES){
                if (selectedBullet.getX()>this.getX()
                        &&selectedBullet.getY()>this.getY()
                        &&selectedBullet.getX()<this.getX()+this.enemyShipImage.getWidth()
                        &&selectedBullet.getY()<this.getY()+this.enemyShipImage.getHeight()){

                    currentGameView.PLAYER_BULLET_IMAGES.remove(selectedBullet);
                    this.removeEnmeyShip();
                    break;
                }
            }
        }
    }

    public void removeEnmeyShip(){
        enemyship=booms;
        isDestroied = true;
        currentGameView.SCORE+=50;
        new sound(sound.view,currentGameView.sound_boom).start();
        currentGameView.mysound.play(currentGameView.sound_boom,1,1,1,0,1);
    }

}