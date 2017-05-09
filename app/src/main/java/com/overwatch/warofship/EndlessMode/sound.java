package com.overwatch.warofship.EndlessMode;

/**
 * Created by Administrator on 2017/5/9.
 */

public class sound extends Thread {
    public static EndlessModeGameView view;
    private boolean runningState = false;//run state of the thread
     static  int i=0;

    public sound(EndlessModeGameView view,int i){
        this.view=view;
        this.i=i;
    }

    //set the running state from out other part of the program
    public void setRunning(boolean run){
        runningState = run;
    }

    public void run(){
        EndlessModeGameView.mysound.play(i,1,1,1,0,1);

    }
}
