package com.overwatch.warofship.GameImage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.overwatch.warofship.GameLogic.GameViewInterface;

import java.util.ArrayList;
import java.util.List;

public class EnemyBossShip implements GameImageInterface {

    /**
     * variable declaration
     */
    private Bitmap enemyBossShipImage;
    private Bitmap enemyBossShipWithHP;
    private Bitmap boomImage;
    private GameViewInterface currentGameview;
    private List<Bitmap> booms;
    private List<Bitmap> enemyship;
    private int index;
    private Paint p;
    private int maxHP;
    private int HP;
    private boolean isDestroyed;
    private boolean moveDirection;
    private float x;
    private float y;
    private float width;
    private float height;

    /**
     * constructor of EnemyBossShip
     * @param enemyBossShipImage
     *          bitmap stored boss ship picture
     * @param boomImage
     *          bitmap store boom picture
     * @param maxHp
     *          value of max HP of boss
     * @param currentGameView
     *          current game view
     */
    public EnemyBossShip(Bitmap enemyBossShipImage,Bitmap boomImage, int maxHp, GameViewInterface currentGameView){
        this.enemyBossShipImage=enemyBossShipImage;
        this.enemyBossShipWithHP=Bitmap.createBitmap(enemyBossShipImage.getWidth(),enemyBossShipImage.getHeight()+30,
                Bitmap.Config.ARGB_8888);
        this.boomImage=boomImage;
        this.currentGameview = currentGameView;
        this.booms=new ArrayList<>();
        this.enemyship=new ArrayList<>();
        this.index=0;
        this.p = new Paint();
        this.maxHP=maxHp;
        this.HP=maxHP;
        this.isDestroyed=false;
        this.moveDirection=true;
        this.x=0;
        this.y=-enemyBossShipImage.getHeight();
        this.width=enemyBossShipImage.getWidth();
        this.height=enemyBossShipImage.getHeight();

        this.initBoomPic();// initialize the boom pictures
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
        if (!isDestroyed){
            Canvas canvas=new Canvas(enemyBossShipWithHP);

            /**
             * draw the HP bar to boss bitmap
             * add the bar to existing boss ship bitmap
             */
            p.setColor(Color.GREEN);
            canvas.drawRect(new Rect(0,0,enemyBossShipWithHP.getWidth(),16),p);
            p.setColor(Color.RED);
            canvas.drawRect(new Rect(2,2,((enemyBossShipWithHP.getWidth()-2)/this.maxHP)*HP,14),p);
            canvas.drawBitmap(enemyBossShipImage,
                    new Rect(0,0,enemyBossShipImage.getWidth(),enemyBossShipImage.getHeight()),
                    new Rect(0,30,enemyBossShipImage.getWidth(),enemyBossShipImage.getHeight()+30),
                    p);

            /**
             * add boss ship picture to list
             * list is needed to add boom picture
             */
            this.enemyship=new ArrayList<>();
            enemyship.add(enemyBossShipWithHP);
        }

        Bitmap selectedImage=enemyship.get(index);
        index++;

        /**
         * remove the ship
         * when finish the boom picture
         */
        if(index==7&&isDestroyed){
            currentGameview.getGameImages().remove(this);
        }

        /**
         * logic make sure boss ship draw
         * until it is destroyed
         */
        if(index==enemyship.size()){
            index=0;
        }

        // move the boss ship auto
        this.moveVerticalAuto();
        this.moveHorizontalAuto();

        return selectedImage;
    }

    @Override
    // getter of the location
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }

    /**
     * check if the boss ship is beat
     * remove the ship if beat
     * no return type
     */
    public void checkIsBeat(){
        if (!this.isDestroyed){
            for (Bullet selectedBullet : currentGameview.getPlayerBulletImages()){
                if (selectedBullet.getX()>this.getX()
                        &&selectedBullet.getY()>this.getY()
                        &&selectedBullet.getX()<this.getX()+this.enemyBossShipImage.getWidth()
                        &&selectedBullet.getY()<this.getY()+this.enemyBossShipImage.getHeight()){

                    currentGameview.getPlayerBulletImages().remove(selectedBullet);
                    currentGameview.setBossnumber(currentGameview.getBossnumber()-150);


                    HP--;
                    if(HP==0){
                        this.removeEBossShip();
                    }
                    break;
                }
            }
        }

    }

    /**
     * remove the boss ship
     * actually:
     * change the boss ship list to boom list
     * add the score
     */
    public void removeEBossShip(){
        enemyship=booms;
        isDestroyed= true;

        currentGameview.setSCORE(currentGameview.getSCORE() + 150);
    }

    /**
     * move horizontal
     */
    private void moveHorizontalAuto(){
        if (moveDirection){
            x=x+5;
        }else {
            x=x-5;
        }

        if (this.x>=(currentGameview.getScreenWidth() - this.enemyBossShipImage.getWidth())
                ||this.x<=0){

            moveDirection=!moveDirection;
        }
    }

    /**
     * move vertical
     */
    private void moveVerticalAuto(){
       if (y<=0){
           y=y+5;
       }
    }

    /**
     * getter of width
     * getter of height
     * @return
     *          width
     *          height
     */
    public float getWidth() {
        return width;
    }
    public float getHeight() {
        return height;
    }
    public int getHP(){
        return HP;
    }

}
