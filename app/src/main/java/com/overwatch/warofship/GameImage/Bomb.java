package com.overwatch.warofship.GameImage;

import android.graphics.Bitmap;

import com.overwatch.warofship.EndlessMode.EndlessModeGameView;

import java.util.Random;

/**
 * Created by Administrator on 2017/6/1.
 */

public class Bomb implements GameImageInterface {
    private Bitmap bomb;
    private float x,y;
    private Random random=new Random();
    private boolean received=false;

    public Bomb(Bitmap bomb){
        this.bomb=bomb;
        x = random.nextInt(EndlessModeGameView.SCREEN_WIDTH - this.bomb.getWidth());
        y = -this.bomb.getHeight() - 10;
    }
    @Override
    public Bitmap getBitmap() {
        y=y+10;
        return bomb;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }
    public boolean ifOutOfScreen() {
        if (this.y >= EndlessModeGameView.SCREEN_HEIGHT + 10) {
            return true;
        } else {
            return false;
        }
    }
    public void receivedbyship(){
        if(!received){
            for(GameImageInterface myShip : EndlessModeGameView.GAME_IMAGES){
                if(myShip instanceof MyShip){
                    if (myShip.getX()>this.getX()
                            &&myShip.getY()>this.getY()
                            &&myShip.getX()<this.getX()+this.bomb.getWidth()
                            &&myShip.getY()<this.getY()+this.bomb.getHeight()){
                        EndlessModeGameView.BOMB_IMAGE.remove(this);
                        received=true;
                        break;
                    }

                }
            }
        }
    }
    public void removebomb(){
        EndlessModeGameView.BOMB_IMAGE.remove(this);
    }

}
