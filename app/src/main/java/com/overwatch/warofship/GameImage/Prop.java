package com.overwatch.warofship.GameImage;

import android.graphics.Bitmap;

import com.overwatch.warofship.EndlessMode.EndlessModeGameView;
import com.overwatch.warofship.GameLogic.GameViewInterface;

import java.util.Random;

public class Prop implements GameImageInterface {

    private Bitmap prop;
    private float x, y;
    private Random random=new Random();
    private boolean received=false;
    private GameViewInterface currentGameView;


    public Prop(Bitmap prop,GameViewInterface currentGameView) {
        this.prop = prop;
        this.currentGameView = currentGameView;
        x = random.nextInt(currentGameView.getScreenWidth() - this.prop.getWidth());
        y = -this.prop.getHeight() - 10;
    }

    @Override
    public Bitmap getBitmap() {
        y = y + 5;
//        if(this.ifOutOfScreen()){
//            EndlessModeGameView.PROP_IMAGE.remove(this);
//            Log.i("REMOVE.Test","The prop is removed because it out of screen");
//        }
        return prop;
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
    //when the prop is received by the ship, remove it from the screen
    public void receivedbyship(){
        if(!received){
            for(GameImageInterface myShip : currentGameView.getGameImages()){
                if(myShip instanceof MyShip){
                    if (myShip.getX()>this.getX()
                            &&myShip.getY()>this.getY()
                            &&myShip.getX()<this.getX()+this.prop.getWidth()
                            &&myShip.getY()<this.getY()+this.prop.getHeight()){
                        currentGameView.getPropImages().remove(this);
                        received=true;
                        break;
                    }

                }
            }
        }
    }
    public void removeprop(){
        currentGameView.getPropImages().remove(this);
    }
}


