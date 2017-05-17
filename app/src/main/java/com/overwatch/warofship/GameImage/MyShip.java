package com.overwatch.warofship.GameImage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import com.overwatch.warofship.End;
import com.overwatch.warofship.EndlessMode.EndlessModeGameView;

import java.util.ArrayList;
import java.util.List;

public  class MyShip implements GameImageInterface {

    private Bitmap myShipImage;
    private Bitmap boomImage;
    private List<Bitmap> booms=new ArrayList<>();
    private List<Bitmap> myShipImages=new ArrayList<>();
    private int index;
    private boolean isDestroyed;

    private float x;
    private float y;
    private float width;
    private float height;
    private Context context;
    public MyShip(Bitmap myShipImage, Bitmap boomImage, Context context){
        this.myShipImage=myShipImage;
        this.boomImage=boomImage;
        myShipImages.add(myShipImage);
        this.index=0;
        this.isDestroyed=false;
        this.context=context;
        this.initBoomPic();//initialize the boom pictures

        //initialize the width and height
        width=myShipImage.getWidth();
        height=myShipImage.getHeight();
        //initialize the location
        x=(EndlessModeGameView.SCREEN_WIDTH-width)/2;
        y=EndlessModeGameView.SCREEN_HEIGHT;

    }

    //initialize the boom pictures;
    private void initBoomPic(){
        booms.add(Bitmap.createBitmap(boomImage,0,0,boomImage.getWidth()/7,boomImage.getHeight()));
        booms.add(Bitmap.createBitmap(boomImage,(boomImage.getWidth()/7),0,boomImage.getWidth()/7,boomImage.getHeight()));
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
            EndlessModeGameView.GAME_IMAGES.remove(this);
            Intent intent=new Intent(context,End.class);
            context.startActivity(intent);
        }
        //make sure the we can draw every time
        if(index==myShipImages.size()){
            index=0;
        }

        return MyShipPre;
    }

    @Override
    //// Getter and setter method of location
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


    //// Check if plane selected by player
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

    public void moveintoscreen(){
            y=y-10;
    }


    //// Getter of width and height
    public float getWidth() {
        return width;
    }
    public float getHeight() {
        return height;
    }


    ////Check if the player ship is beat
    //boolean return type is just for stop the loop
    public boolean checkIsBeat(){
        if (!this.isDestroyed){
            //check if player ship crash with enemy ship
            for (GameImageInterface image : EndlessModeGameView.GAME_IMAGES){
                if(image instanceof EnemyShip){
                    if (image.getX()>this.getX()
                            &&image.getY()>this.getY()
                            &&image.getX()<this.getX()+this.myShipImage.getWidth()
                            &&image.getY()<this.getY()+this.myShipImage.getHeight()){

                        ((EnemyShip) image).removeEnmeyShip();
                        this.removePlayership();
                        return true;
                    }
                }

            }

            //check if player ship beat by enemy bullet
            for(GameImageInterface enemybullet :EndlessModeGameView.ENEMY_BULLET_IMAGES){
                if(enemybullet instanceof EnemyBullet){
                    if(enemybullet.getX()>this.getX()
                            &&enemybullet.getY()>this.getY()
                            &&enemybullet.getX()<this.getX()+this.myShipImage.getWidth()
                            &&enemybullet.getY()<this.getY()+this.myShipImage.getHeight()){

                        ((EnemyBullet)enemybullet).removeEnemyBullet();
                        this.removePlayership();
                        return true;
                    }
                }
            }
        }else if (this.isDestroyed){
          return true;
        }
        return false;
    }

    public void removePlayership(){
        myShipImages=booms;
        isDestroyed= true;
    }

}

