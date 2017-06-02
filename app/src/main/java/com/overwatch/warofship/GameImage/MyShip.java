package com.overwatch.warofship.GameImage;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.util.Log;

import com.overwatch.warofship.EndlessMode.EndlessModeGameView;
import com.overwatch.warofship.GameMenu.DbHelper;
import com.overwatch.warofship.GameMenu.End;
import com.overwatch.warofship.GameLogic.GameViewInterface;

import java.util.ArrayList;
import java.util.List;

public  class MyShip implements GameImageInterface {

    /**
     * variable declaration
     */
    private Bitmap myShipImage;
    private Bitmap boomImage;
    private GameViewInterface currentGameView;
    private List<Bitmap> booms=new ArrayList<>();
    private List<Bitmap> myShipImages=new ArrayList<>();
    private int index;
    private boolean isDestroyed;
    private DbHelper dbHelper;
    //represents the level of the bullet
    public  static int levelofbullet;

    private float x;
    private float y;
    private float width;
    private float height;
    private Context context;
    private int score;
    /**
     * constructor of MyShip
     * @param myShipImage
     *          bitmap stored play ship image
     * @param boomImage
     *          bitmap store boom picture
     * @param context
     * @param gameVeiw
     *          game view use my ship
     */
    public MyShip(Bitmap myShipImage, Bitmap boomImage, Context context, GameViewInterface gameVeiw,int score){
        this.myShipImage=myShipImage;
        this.boomImage=boomImage;
        this.currentGameView = gameVeiw;
        myShipImages.add(myShipImage);
        this.index=0;
        this.isDestroyed=false;
        levelofbullet=1;
        this.context=context;
        this.score=score;
        this.initBoomPic();//initialize the boom pictures

        //initialize the width and height
        width=myShipImage.getWidth();
        height=myShipImage.getHeight();
        //initialize the location
        x=(currentGameView.getScreenWidth()-width)/2;
        y=currentGameView.getScreenHeight();

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
            currentGameView.getGameImages().remove(this);
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
            for (GameImageInterface image : currentGameView.getGameImages()){
                if(image instanceof EnemyShip||image instanceof Barrier||image instanceof EnemyBossShip){
                    if((this.getX()>image.getX()&&this.getX()<image.getX()+width&&this.getY()>image.getY()&&this.getY()<image.getY()+height)
                            ||(image.getX()>this.getX()&&image.getX()<this.x+width&&image.getY()>this.y&&image.getY()<this.y+height)){
                        if(image instanceof EnemyShip){
                            ((EnemyShip) image).removeEnmeyShip();
                        }





                        this.removePlayership();
                        return true;
                    }
                }

            }

            //check if player ship beat by enemy bullet
            for(GameImageInterface enemybullet :currentGameView.getEnemyBulletImages()){
                if(enemybullet instanceof EnemyBullet){
                    if((this.getX()>enemybullet.getX()&&this.getX()<enemybullet.getX()+width&&this.getY()>enemybullet.getY()&&this.getY()<enemybullet.getY()+height)
                            ||(enemybullet.getX()>this.getX()&&enemybullet.getX()<this.x+width&&enemybullet.getY()>this.y&&enemybullet.getY()<this.y+height)){

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

    public void receiveprop(){
        for(GameImageInterface prop : currentGameView.getPropImages()){
            if(prop instanceof Prop){
                if((this.getX()>prop.getX()&&this.getX()<prop.getX()+width&&this.getY()>prop.getY()&&this.getY()<prop.getY()+height)
                        ||(prop.getX()>this.getX()&&prop.getX()<this.x+width&&prop.getY()>this.y&&prop.getY()<this.y+height)){
                    ((Prop) prop).removeprop();
                    currentGameView.setStrengthenTime(1);

                    levelofbullet += 1;
                    Log.i("weapon strengthen","levelofbullet");
                    if(levelofbullet>=4){
                        levelofbullet=4;
                    }
                    break;
                }
            }
        }
    }
    public void receivebomb(){
        for(GameImageInterface bomb : currentGameView.getBombImages()){
            if(bomb instanceof Bomb){
                if((this.getX()>bomb.getX()&&this.getX()<bomb.getX()+width&&this.getY()>bomb.getY()&&this.getY()<bomb.getY()+height)
                        ||(bomb.getX()>this.getX()&&bomb.getX()<this.x+width&&bomb.getY()>this.y&&bomb.getY()<this.y+height)){
                    for(GameImageInterface enemyship : currentGameView.getGameImages()){
                        if(enemyship instanceof EnemyShip){
                            ((EnemyShip) enemyship).removeEnmeyShip();
                        }
                    }
                    ((Bomb) bomb).removebomb();
                    break;
                }
            }
        }
    }

    public void removePlayership(){
        myShipImages=booms;
        isDestroyed= true;
        dbHelper=new DbHelper(context,"record.db",null,1);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name","user");
        values.put("score",currentGameView.getSCORE());
        db.insert("record",null,values);
        values.clear();
        Intent intent=new Intent(context,End.class);
        context.startActivity(intent);
    }

}

