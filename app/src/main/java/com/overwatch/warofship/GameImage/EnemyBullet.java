package com.overwatch.warofship.GameImage;

import android.graphics.Bitmap;

import com.overwatch.warofship.EndlessMode.EndlessModeGameView;

public class EnemyBullet implements GameImageInterface {

    private Bitmap myBulletImage;
    private EnemyBossShip bossShip;

    private float x;
    private float y;

    public EnemyBullet(Bitmap myBulletImage, EnemyBossShip bossShip) {

        this.myBulletImage = myBulletImage;
        this.bossShip = bossShip;

        x=bossShip.getX()+bossShip.getWidth()/2-10;
        y=bossShip.getY()+70;//-myBulletImage.getHeight();
    }

    @Override
    public Bitmap getBitmap() {
        y-=125;
        return myBulletImage;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
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
        if(y>= EndlessModeGameView.SCREEN_HEIGHT){
            return true;
        }else{
            return false;
        }
    }
}
