package com.overwatch.warofship.GameImage;

import android.graphics.Bitmap;
import android.util.Log;

import com.overwatch.warofship.EndlessMode.EndlessModeGameView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyShip implements GameImageInterface {

    private Bitmap enemyShipImage;
    private Bitmap boomImage;
    private Random random = new Random();
    private List<Bitmap> booms=new ArrayList<>();
    private List<Bitmap> enemyship=new ArrayList<>();
    private int index=0;
    private boolean isDestroied=false;

    private float x;
    private float y;

    public EnemyShip(Bitmap enemyShipImage,Bitmap boomImage) {

        this.enemyShipImage=enemyShipImage;
        this.boomImage=boomImage;
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
        Bitmap bitmaps=enemyship.get(index);
        index++;

        //remove the ship if nessary
        //first if: remove when destroy and played the boom picture
        if(index==7&&isDestroied){
            EndlessModeGameView.gameImages.remove(this);
        }
        //second if: remove if out of screen
        if(this.ifOutOfScreen()){
            EndlessModeGameView.gameImages.remove(this);
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
        if (this.y>=EndlessModeGameView.SCREEN_HEIGHT+10){
            return true;
        }else {
            return false;
        }
    }

    public void CheckIsBeat(){
        if(!isDestroied){
            for (Bullet selectedBullet : EndlessModeGameView.PLAYER_BULLET_IMAGES){
                if (selectedBullet.getX()>this.getX()
                        &&selectedBullet.getY()>this.getY()
                        &&selectedBullet.getX()<this.getX()+this.enemyShipImage.getWidth()
                        &&selectedBullet.getY()<this.getY()+this.enemyShipImage.getHeight()){

                    EndlessModeGameView.PLAYER_BULLET_IMAGES.remove(selectedBullet);
                    enemyship=booms;
                    isDestroied= true;
                    break;
                }
            }

        }
    }

}




