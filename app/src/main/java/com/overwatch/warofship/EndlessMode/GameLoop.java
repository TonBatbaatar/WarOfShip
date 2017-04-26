package com.overwatch.warofship.EndlessMode;

import android.graphics.Canvas;
import android.util.Log;

public class GameLoop extends Thread {

    private EndlessModeGameView view;
    private boolean runningState = false;//run state of the thread

    public GameLoop(EndlessModeGameView view){
        this.view=view;
    }

    //set the running state from out other part of the program
    public void setRunning(boolean run){
        runningState = run;
    }

    @Override
    //Actual part of running the thread
    public void run() {
        while(runningState){
            Canvas canvas = null;
            try {
                canvas = view.getHolder().lockCanvas();
                synchronized (view.getHolder()){
                    view.draw(canvas);//draw the preparation Bitmap to the screen
                    Thread.sleep(20);//control the speed of the thread
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (canvas!=null){
                    view.getHolder().unlockCanvasAndPost(canvas);
                }
            }
        }
    }

}
