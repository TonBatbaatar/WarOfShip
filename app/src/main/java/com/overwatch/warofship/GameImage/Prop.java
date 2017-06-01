package com.overwatch.warofship.GameImage;

import android.graphics.Bitmap;

import com.overwatch.warofship.EndlessMode.EndlessModeGameView;

import java.util.Random;

/**
 * Created by Administrator on 2017/5/29.
 */

public class Prop implements GameImageInterface {

    private Bitmap prop;
    private float x, y;
    private Random random=new Random();
    private boolean received=false;


    public Prop(Bitmap prop) {
        this.prop = prop;
        x = random.nextInt(EndlessModeGameView.SCREEN_WIDTH - this.prop.getWidth());
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
                            &&myShip.getX()<this.getX()+this.prop.getWidth()
                            &&myShip.getY()<this.getY()+this.prop.getHeight()){
                        EndlessModeGameView.PROP_IMAGE.remove(this);
                        received=true;
                        break;
                    }

                    }
               }
            }
        }
}


