package com.overwatch.warofship.EndlessMode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/5/17.
 */

public class Story4LevActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Story4LevGV view = new Story4LevGV(this);//create the Activity for the game
        setContentView(view);
    }
}
