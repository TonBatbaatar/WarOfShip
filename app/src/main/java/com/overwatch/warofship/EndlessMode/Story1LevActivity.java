package com.overwatch.warofship.EndlessMode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Story1LevActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Story1LevGV view = new Story1LevGV(this);//create the Activity for the game
        setContentView(view);
    }
}
