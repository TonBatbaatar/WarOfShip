package com.overwatch.warofship.GameImage;

import android.graphics.Bitmap;
import android.util.Log;

import com.overwatch.warofship.EndlessMode.EndlessModeGameView;

import java.util.ArrayList;
import java.util.List;

public class EnemyBossShip implements GameImageInterface {

    private Bitmap enemyBossShipImage;
    private List<Bitmap> booms=new ArrayList<>();
    private List<Bitmap> enemyship=new ArrayList<>();
    private int index=0;
    private int HP=0;
    private boolean isDestroied=false;
    private boolean moveDirection=true;

    private float x;
    private float y;
    private float width;
    private float height;

    public EnemyBossShip(Bitmap enemyBossShipImage, Bitmap boom) {
        this.enemyBossShipImage=enemyBossShipImage;
        enemyship.add(enemyBossShipImage);

        booms.add(Bitmap.createBitmap(boom,0,0,boom.getWidth()/7,boom.getHeight()));
        booms.add(Bitmap.createBitmap(boom,(boom.getWidth()/7)*1,0,boom.getWidth()/7,boom.getHeight()));
        booms.add(Bitmap.createBitmap(boom,(boom.getWidth()/7)*2,0,boom.getWidth()/7,boom.getHeight()));
        booms.add(Bitmap.createBitmap(boom,(boom.getWidth()/7)*3,0,boom.getWidth()/7,boom.getHeight()));
        booms.add(Bitmap.createBitmap(boom,(boom.getWidth()/7)*4,0,boom.getWidth()/7,boom.getHeight()));
        booms.add(Bitmap.createBitmap(boom,(boom.getWidth()/7)*5,0,boom.getWidth()/7,boom.getHeight()));
        booms.add(Bitmap.createBitmap(boom,(boom.getWidth()/7)*6,0,boom.getWidth()/7,boom.getHeight()));

        //x=(EndlessModeGameView.SCREEN_WIDTH+this.enemyBossShipImage.getWidth())/2;
        x=0;
        y=-enemyBossShipImage.getHeight();
        width=enemyBossShipImage.getWidth();
        height=enemyBossShipImage.getHeight();
    }

    @Override
    public Bitmap getBitmap() {
        Bitmap selectedImage=enemyship.get(index);
        index++;
        if(index==7&&isDestroied){
            EndlessModeGameView.gameImages.remove(this);
        }

        if(index==enemyship.size()){
            index=0;
        }

        if(this.ifOutOfScreen()){
            EndlessModeGameView.gameImages.remove(this);
        }

        this.moveVerticalAuto();
        this.moveHorizontalAuto();
        return selectedImage;
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
                        &&selectedBullet.getX()<this.getX()+this.enemyBossShipImage.getWidth()
                        &&selectedBullet.getY()<this.getY()+this.enemyBossShipImage.getHeight()){

                    bulletImages.remove(selectedBullet);
                    HP++;
                    if(HP>4){
                        enemyship=booms;
                        isDestroied= true;
                    }
                    break;
                }
            }

        }
    }

    private void moveHorizontalAuto(){
        if (moveDirection){
            x=x+20;
        }else {
            x=x-20;
        }

        if (this.x>=(EndlessModeGameView.SCREEN_WIDTH-this.enemyBossShipImage.getWidth())
                ||this.x<=0){

            moveDirection=!moveDirection;
        }
    }

    private void moveVerticalAuto(){
       if (y<=0){
           y=y+20;
       }
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
