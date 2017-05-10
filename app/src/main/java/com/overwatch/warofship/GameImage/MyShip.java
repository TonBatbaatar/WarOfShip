package com.overwatch.warofship.GameImage;

import android.graphics.Bitmap;

import com.overwatch.warofship.EndlessMode.EndlessModeGameView;

import java.util.ArrayList;
import java.util.List;

public class
MyShip implements GameImageInterface {

    private Bitmap myShipImage;
    private Bitmap boomImage;
    private List<Bitmap> booms=new ArrayList<>();
    private List<Bitmap> myShipImages=new ArrayList<>();
    private int index;
    private boolean isDestroyed=false;

    private float x;
    private float y;
    private float width;
    private float height;

    public MyShip(Bitmap myShipImage, Bitmap boomImage){
        this.myShipImage=myShipImage;
        this.boomImage=boomImage;
        myShipImages.add(myShipImage);
        this.index=0;

        this.initBoomPic();//initialize the boom pictures

        //initialize the width and height
        width=myShipImage.getWidth();
        height=myShipImage.getHeight();
        //initialize the location
        x=(EndlessModeGameView.SCREEN_WIDTH-width)/2;
        y=EndlessModeGameView.SCREEN_HEIGHT-height-10;
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
        Bitmap MyShipPre=myShipImages.get(index);
        index++;

        //if ship destroyed and played all boom pictures we remove my ship
        if(index==7&&isDestroyed){
            EndlessModeGameView.gameImages.remove(this);
        }
        //make sure the we can draw every time
        if(index==myShipImages.size()){
            index=0;
        }

        return MyShipPre;
    }

    @Override
    //getter and setter method of location
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

    public boolean ifPlaneSelected(float x, float y){
        if (x>this.getX()
                &&y>this.getY()
                &&x<this.getX()+this.myShipImage.getWidth()
                &&y<this.getY()+this.myShipImage.getHeight()){
            return true;
        }else {
            return false;
        }
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void isBeat(List<GameImageInterface> EnemyImages,List<EnemyBullet> bulletImages){
        if(!isDestroyed){
            for (GameImageInterface enemyship :EnemyImages){
                if(enemyship instanceof EnemyShip){
                    if (enemyship.getX()>this.getX()
                            &&enemyship.getY()>this.getY()
                            &&enemyship.getX()<this.getX()+this.myShipImage.getWidth()
                            &&enemyship.getY()<this.getY()+this.myShipImage.getHeight()){
                       ((EnemyShip) enemyship).CheckIsBeat();
                        myShipImages=booms;
                        isDestroyed= true;
                        break;
                    }
                }

            }
            for(GameImageInterface enemybullet :bulletImages){
                if(enemybullet instanceof EnemyBullet){
                    if(enemybullet.getX()>this.getX()
                            &&enemybullet.getY()>this.getY()
                            &&enemybullet.getX()<this.getX()+this.myShipImage.getWidth()
                            &&enemybullet.getY()<this.getY()+this.myShipImage.getHeight()){
                        ((EnemyBullet)enemybullet).CheckIsBeat(this);
                        myShipImages=booms;
                        isDestroyed= true;
                        break;

                    }
                }
            }

        }
    }

}

