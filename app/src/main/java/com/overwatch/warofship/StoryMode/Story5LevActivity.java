package com.overwatch.warofship.StoryMode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/5/17.
 */

public class Story5LevActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Story5LevGV view = new Story5LevGV(this);//create the Activity for the game
        setContentView(view);
    }
}
