package com.overwatch.warofship.EndlessMode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class EndlessModeActivity extends AppCompatActivity {

    //?

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EndlessModeGameView view = new EndlessModeGameView(this);
        setContentView(view);
    }
}
