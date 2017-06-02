package com.overwatch.warofship.StoryMode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Story2LevActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Story2LevGV view = new Story2LevGV(this);//create the Activity for the game
        setContentView(view);
    }
}
