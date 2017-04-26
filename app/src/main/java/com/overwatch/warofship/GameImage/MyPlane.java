package com.overwatch.warofship.GameImage;

/**
 * Created by daxi on 2017/4/25.
 */

import android.graphics.Bitmap;

import com.overwatch.warofship.EndlessMode.EndlessModeGameView;

import java.util.ArrayList;
import java.util.List;

public class
MyPlane implements GameImageInterface {

    private Bitmap myPlaneImage;
    private Bitmap newBitmap = null;
    private List<Bitmap> booms=new ArrayList<>();
    private List<Bitmap> myplane=new ArrayList<>();
    private int index=0;
    private boolean isDestroyed=false;

    private float x;
    private float y;
    private float width;
    private float height;

    public MyPlane(Bitmap mp,Bitmap boom){
        this.myPlaneImage=mp;
        myplane.add(myPlaneImage);
//        newBitmap=Bitmap.createBitmap(EndlessModeGameView.SCREEN_HEIGHT,EndlessModeGameView.SCREEN_WIDTH,
//                Bitmap.Config.ARGB_8888);
        booms.add(Bitmap.createBitmap(boom,0,0,boom.getWidth()/7,boom.getHeight()));
        booms.add(Bitmap.createBitmap(boom,(boom.getWidth()/7)*1,0,boom.getWidth()/7,boom.getHeight()));
        booms.add(Bitmap.createBitmap(boom,(boom.getWidth()/7)*2,0,boom.getWidth()/7,boom.getHeight()));
        booms.add(Bitmap.createBitmap(boom,(boom.getWidth()/7)*3,0,boom.getWidth()/7,boom.getHeight()));
        booms.add(Bitmap.createBitmap(boom,(boom.getWidth()/7)*4,0,boom.getWidth()/7,boom.getHeight()));
        booms.add(Bitmap.createBitmap(boom,(boom.getWidth()/7)*5,0,boom.getWidth()/7,boom.getHeight()));
        booms.add(Bitmap.createBitmap(boom,(boom.getWidth()/7)*6,0,boom.getWidth()/7,boom.getHeight()));

        width=myPlaneImage.getWidth();
        height=myPlaneImage.getHeight();
        x=(EndlessModeGameView.SCREEN_WIDTH-width)/2;
        y=EndlessModeGameView.SCREEN_HEIGHT-height-10;

    }

    @Override
    public Bitmap getBitmap() {
        Bitmap bitmaps=myplane.get(index);
        index++;
        if(index==7&&isDestroyed){
            EndlessModeGameView.gameImages.remove(this);
        }

        if(index==myplane.size()){
            index=0;
        }

//        Canvas canvas=new Canvas(newBitmap);
//        Paint paint=new Paint();
//
//        canvas.drawBitmap(this.myPlaneImage,
//                new Rect(0,0,this.myPlaneImage.getWidth(),this.myPlaneImage.getHeight()),
//                new Rect(0,0,this.myPlaneImage.getWidth(),this.myPlaneImage.getHeight()),
//                paint);

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

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public boolean ifPlaneSelected(float x, float y){
        if (x>this.getX()
                &&y>this.getY()
                &&x<this.getX()+this.myPlaneImage.getWidth()
                &&y<this.getY()+this.myPlaneImage.getHeight()){
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
    public void isBeat(List<GameImageInterface> EnemyImages,List<Bullet> bulletImages){
        if(!isDestroyed){
            for (GameImageInterface enemyship :EnemyImages){
                if(enemyship instanceof EnemyShip){
                    if (enemyship.getX()>this.getX()
                            &&enemyship.getY()>this.getY()
                            &&enemyship.getX()<this.getX()+this.myPlaneImage.getWidth()
                            &&enemyship.getY()<this.getY()+this.myPlaneImage.getHeight()){
                       ((EnemyShip) enemyship).isBeat(bulletImages);
                        myplane=booms;
                        isDestroyed= true;
                        break;
                    }
                }

            }

        }
    }

}

