package com.overwatch.warofship.GameLogic;

import android.graphics.Canvas;

public class GameLoop extends Thread {

    /**
     * variable declaration:
     * GameViewInterface view --> let system know which view to draw
     * boolean runningState --> running state of thread
     */
    private GameViewInterface view;
    private boolean runningState;

    /**
     * constructor of loop
     * @param view
     *          parameter to initialize current view
     */
    public GameLoop(GameViewInterface view){
        this.view=view;
        this.runningState = false;
    }

    /**
     * setter of the runningState
     * @param run
     *          set the runningState to param run
     */
    public void setRunning(boolean run){
        runningState = run;
    }

    @Override
    /**
     * override of run method of thread
     * game view draw here
     */
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
