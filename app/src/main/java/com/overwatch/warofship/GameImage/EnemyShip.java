package com.overwatch.warofship.GameImage;

import android.graphics.Bitmap;

import com.overwatch.warofship.EndlessMode.EndlessModeGameView;

import java.util.Random;

/**
 * Created by Administrator on 2017/4/24.
 */

public class EnemyShip implements GameImageInterface {

    private Bitmap enemyShip1Image;
    private Random random = new Random();

    private float x;
    private float y;

    public EnemyShip(Bitmap enemyShip1Image) {
        this.enemyShip1Image=enemyShip1Image;
        x=random.nextInt(EndlessModeGameView.SCREEN_WIDTH-this.enemyShip1Image.getWidth());
        y=-this.enemyShip1Image.getHeight()-20;
    }

    @Override
    public Bitmap getBitmap() {
        y+=100;
        return enemyShip1Image;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    public boolean ifOutOfScreen(){
        if (this.y>=EndlessModeGameView.SCREEN_HEIGHT+10){
            return true;
        }else {
            return false;
        }
    }

//    public boolean isBeat(List<MyBulletImage> bulletImages){
//
//        for (MyBulletImage selectedBullet : bulletImages){
//            if (selectedBullet.getX()>this.getX()
//                    &&selectedBullet.getY()>this.getY()
//                    &&selectedBullet.getX()<this.getX()+this.enemyShip1Image.getWidth()
//                    &&selectedBullet.getY()<this.getY()+this.enemyShip1Image.getHeight()){
//                bulletImages.remove(selectedBullet);
//                return true;
//            }
//        }
//        return false;
//    }

}
