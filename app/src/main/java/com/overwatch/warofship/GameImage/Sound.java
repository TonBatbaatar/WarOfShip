package com.overwatch.warofship.GameImage;


import com.overwatch.warofship.GameLogic.GameViewInterface;

public class Sound extends Thread {
    public static GameViewInterface view;
    public static  int i=0;
    private boolean runningState = false;//run state of the thread

    public Sound(GameViewInterface view, int i){
        this.view=view;
        this.i=i;
    }

    //set the running state from out other part of the program
    public void setRunning(boolean run){
        runningState = run;
    }

    public void run(){
        view.getMysound().play(i,1,1,1,0,1);
    }
}
