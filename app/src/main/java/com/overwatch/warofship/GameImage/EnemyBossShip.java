package com.overwatch.warofship.GameImage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.overwatch.warofship.EndlessMode.EndlessModeGameView;

import java.util.ArrayList;
import java.util.List;

public class EnemyBossShip implements GameImageInterface {

    private Bitmap enemyBossShipImage;
    private Bitmap enemyBossShipWithHP;
    private Bitmap boomImage;
    private List<Bitmap> booms;
    private List<Bitmap> enemyship;
    private int index=0;
    private int maxHP;
    private int HP;
    private boolean isDestroied=false;
    private boolean moveDirection=true;

    private float x;
    private float y;
    private float width;
    private float height;

    public EnemyBossShip(Bitmap enemyBossShipImage, Bitmap boomImage, int maxHp){
        this.enemyBossShipImage=enemyBossShipImage;
        this.enemyBossShipWithHP=Bitmap.createBitmap(enemyBossShipImage.getWidth(),enemyBossShipImage.getHeight()+30,
                Bitmap.Config.ARGB_8888);
        this.booms=new ArrayList<>();
        this.enemyship=new ArrayList<>();
        this.boomImage=boomImage;
        this.initBoomPic();

        x=0;
        y=-enemyBossShipImage.getHeight();
        width=enemyBossShipImage.getWidth();
        height=enemyBossShipImage.getHeight();

        this.maxHP=maxHp;
        this.HP=maxHP;


    }


    @Override
    public Bitmap getBitmap() {

        if (!isDestroied){
            Canvas canvas=new Canvas(enemyBossShipWithHP);
            Paint p = new Paint();

            p.setColor(Color.GREEN);
            canvas.drawRect(new Rect(0,0,enemyBossShipWithHP.getWidth(),16),p);
            p.setColor(Color.RED);
            canvas.drawRect(new Rect(2,2,((enemyBossShipWithHP.getWidth()-2)/this.maxHP)*HP,14),p);

            canvas.drawBitmap(enemyBossShipImage,
                    new Rect(0,0,enemyBossShipImage.getWidth(),enemyBossShipImage.getHeight()),
                    new Rect(0,30,enemyBossShipImage.getWidth(),enemyBossShipImage.getHeight()+30),
                    p);
            this.enemyship=new ArrayList<>();
            enemyship.add(enemyBossShipWithHP);
        }

        Bitmap selectedImage=enemyship.get(index);
        index++;
        if(index==7&&isDestroied){
            EndlessModeGameView.GAME_IMAGES.remove(this);
        }

        if(index==enemyship.size()){
            index=0;
        }

        if(this.ifOutOfScreen()){
            EndlessModeGameView.GAME_IMAGES.remove(this);
        }

        this.moveVerticalAuto();
        this.moveHorizontalAuto();
        return selectedImage;
    }

    @Override
    public float getX() {
        return x;
    }
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

    public void checkIsBeat(){
        if (!this.isDestroied){
            for (Bullet selectedBullet : EndlessModeGameView.PLAYER_BULLET_IMAGES){
                if (selectedBullet.getX()>this.getX()
                        &&selectedBullet.getY()>this.getY()
                        &&selectedBullet.getX()<this.getX()+this.enemyBossShipImage.getWidth()
                        &&selectedBullet.getY()<this.getY()+this.enemyBossShipImage.getHeight()){

                    EndlessModeGameView.PLAYER_BULLET_IMAGES.remove(selectedBullet);

                    HP--;
                    if(HP==0){
                        this.removeEBossShip();
                    }
                    break;
                }
            }
        }

    }

    public void removeEBossShip(){
        enemyship=booms;
        isDestroied= true;
        EndlessModeGameView.SCORE+=150;
    }

    private void moveHorizontalAuto(){
        if (moveDirection){
            x=x+5;
        }else {
            x=x-5;
        }

        if (this.x>=(EndlessModeGameView.SCREEN_WIDTH-this.enemyBossShipImage.getWidth())
                ||this.x<=0){

            moveDirection=!moveDirection;
        }
    }

    private void moveVerticalAuto(){
       if (y<=0){
           y=y+5;
       }
    }

    public float getWidth() {
        return width;
    }
    public float getHeight() {
        return height;
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

}
