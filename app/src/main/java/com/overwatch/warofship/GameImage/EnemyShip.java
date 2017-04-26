package com.overwatch.warofship.GameImage;

import android.graphics.Bitmap;

import com.overwatch.warofship.EndlessMode.EndlessModeGameView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyShip implements GameImageInterface {

    private Bitmap enemyShip1Image;
    private Random random = new Random();
    private List<Bitmap> booms=new ArrayList<>();
    private List<Bitmap> enemyship=new ArrayList<>();
    private int index=0;
    private boolean isDestroied=false;

    private float x;
    private float y;

    public EnemyShip(Bitmap enemyShip1Image,Bitmap boom) {

        this.enemyShip1Image=enemyShip1Image;
        enemyship.add(enemyShip1Image);

        booms.add(Bitmap.createBitmap(boom,0,0,boom.getWidth()/7,boom.getHeight()));
        booms.add(Bitmap.createBitmap(boom,(boom.getWidth()/7)*1,0,boom.getWidth()/7,boom.getHeight()));
        booms.add(Bitmap.createBitmap(boom,(boom.getWidth()/7)*2,0,boom.getWidth()/7,boom.getHeight()));
        booms.add(Bitmap.createBitmap(boom,(boom.getWidth()/7)*3,0,boom.getWidth()/7,boom.getHeight()));
        booms.add(Bitmap.createBitmap(boom,(boom.getWidth()/7)*4,0,boom.getWidth()/7,boom.getHeight()));
        booms.add(Bitmap.createBitmap(boom,(boom.getWidth()/7)*5,0,boom.getWidth()/7,boom.getHeight()));
        booms.add(Bitmap.createBitmap(boom,(boom.getWidth()/7)*6,0,boom.getWidth()/7,boom.getHeight()));

        x=random.nextInt(EndlessModeGameView.SCREEN_WIDTH-this.enemyShip1Image.getWidth());
        y=-this.enemyShip1Image.getHeight()-20;
    }

    @Override
    public Bitmap getBitmap() {
        Bitmap bitmaps=enemyship.get(index);
        index++;
        if(index==7&&isDestroied){
            EndlessModeGameView.gameImages.remove(this);
        }

        if(index==enemyship.size()){
            index=0;
        }

        y+=25;
        if(this.ifOutOfScreen()){
            EndlessModeGameView.gameImages.remove(this);
        }
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

    public boolean ifOutOfScreen(){
        if (this.y>=EndlessModeGameView.SCREEN_HEIGHT+10){
            return true;
        }else {
            return false;
        }
    }

    public void isBeat(List<Bullet> bulletImages){
        if(!isDestroied){
            for (Bullet selectedBullet : bulletImages){
                if (selectedBullet.getX()>this.getX()
                        &&selectedBullet.getY()>this.getY()
                        &&selectedBullet.getX()<this.getX()+this.enemyShip1Image.getWidth()
                        &&selectedBullet.getY()<this.getY()+this.enemyShip1Image.getHeight()){

                    bulletImages.remove(selectedBullet);
                    enemyship=booms;
                    isDestroied= true;
                    break;
                }
            }

        }
    }

}




