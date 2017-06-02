package com.overwatch.warofship.GameImage;

import android.graphics.Bitmap;

import com.overwatch.warofship.GameLogic.GameViewInterface;

import java.util.Random;

public class Bomb implements GameImageInterface {
    private Bitmap bomb;
    private GameViewInterface currentGameView;
    private float x,y;
    private Random random=new Random();
    private boolean received=false;

    public Bomb(Bitmap bomb,GameViewInterface currentGameView){
        this.bomb=bomb;
        this.currentGameView = currentGameView;
        x = random.nextInt(currentGameView.getScreenWidth() - this.bomb.getWidth());
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
        if (this.y >= currentGameView.getScreenHeight() + 10) {
            return true;
        } else {
            return false;
        }
    }
    public void receivedbyship(){
        if(!received){
            for(GameImageInterface myShip : currentGameView.getBombImages()){
                if(myShip instanceof MyShip){
                    if (myShip.getX()>this.getX()
                            &&myShip.getY()>this.getY()
                            &&myShip.getX()<this.getX()+this.bomb.getWidth()
                            &&myShip.getY()<this.getY()+this.bomb.getHeight()){
                        currentGameView.getBombImages().remove(this);
                        received=true;
                        break;
                    }

                }
            }
        }
    }
    public void removebomb(){
        currentGameView.getBombImages().remove(this);
    }

}
