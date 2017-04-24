package com.overwatch.warofship.EndlessMode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EndlessModeActivity extends AppCompatActivity {

    //?
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EndlessModeGameView view = new EndlessModeGameView(this);
        setContentView(view);
    }
}
