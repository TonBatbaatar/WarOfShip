package com.overwatch.warofship.GameImage;

import android.graphics.Bitmap;

import com.overwatch.warofship.EndlessMode.EndlessModeGameView;

public class EnemyBullet implements GameImageInterface {

    private Bitmap myBulletImage;

    private float x;
    private float y;

    public EnemyBullet(Bitmap myBulletImage, EnemyBossShip bossShip) {

        this.myBulletImage = myBulletImage;

        x=bossShip.getX()+bossShip.getWidth()/2-10;
        y=bossShip.getY()+10+bossShip.getHeight();
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
        if(y>= EndlessModeGameView.SCREEN_HEIGHT){
            return true;
        }else{
            return false;
        }
    }
}
