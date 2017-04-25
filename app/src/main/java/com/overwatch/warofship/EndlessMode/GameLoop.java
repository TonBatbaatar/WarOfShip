package com.overwatch.warofship.EndlessMode;

import android.graphics.Canvas;
import android.util.Log;

import com.overwatch.warofship.EndlessMode.EndlessModeGameView;

public class GameLoop extends Thread {

    private EndlessModeGameView view;
    private boolean runningState = false;

    public GameLoop(EndlessModeGameView view){
        this.view=view;
        Log.i("APP.TEST","The code run into loop class");
    }

    public void setRunning(boolean run){
        runningState = run;
    }

    @Override
    public void run() {
        while(runningState){
            Log.i("APP.TEST","The code run into run method");
            Canvas canvas = null;
            try {
                canvas = view.getHolder().lockCanvas();
                synchronized (view.getHolder()){
                    view.draw(canvas);
                }
            }finally {
                if (canvas!=null){
                    view.getHolder().unlockCanvasAndPost(canvas);
                }
            }
        }
    }

}
