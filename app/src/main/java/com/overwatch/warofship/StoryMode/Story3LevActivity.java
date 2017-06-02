package com.overwatch.warofship.StoryMode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/5/17.
 */

public class Story3LevActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Story3LevGV view = new Story3LevGV(this);//create the Activity for the game
        setContentView(view);
    }
}
