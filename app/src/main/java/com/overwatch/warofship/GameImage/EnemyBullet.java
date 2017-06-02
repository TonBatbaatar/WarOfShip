package com.overwatch.warofship.GameImage;

import android.graphics.Bitmap;

import com.overwatch.warofship.GameLogic.GameViewInterface;

public class EnemyBullet implements GameImageInterface {

    private Bitmap myBulletImage;
    private GameViewInterface currentGameview;

    private float x;
    private float y;

    private boolean hit;

    public EnemyBullet(Bitmap myBulletImage, EnemyBossShip bossShip , GameViewInterface currentGameview) {

        this.myBulletImage = myBulletImage;

        this.currentGameview = currentGameview;

        x=bossShip.getX()+bossShip.getWidth()/2-10;
        y=bossShip.getY()+10+bossShip.getHeight();

        this.hit=false;
    }

    @Override
    public Bitmap getBitmap() {
        y+=15;
        return myBulletImage;
    }

    @Override
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }

    public boolean ifOutOfScreen(){
        if(y>= currentGameview.getScreenHeight()){
            return true;
        }else{
            return false;
        }
    }

    public void removeEnemyBullet(){
        this.hit=true;
        currentGameview.getEnemyBulletImages().remove(this);
    }

}
