package com.overwatch.warofship.EndlessMode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/5/17.
 */

public class Story2LevActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Story2LevGV view = new Story2LevGV(this);//create the Activity for the game
        setContentView(view);
    }
}
