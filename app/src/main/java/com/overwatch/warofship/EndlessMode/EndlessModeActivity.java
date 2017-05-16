package com.overwatch.warofship.EndlessMode;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.overwatch.warofship.End;
import com.overwatch.warofship.Menu;

public class EndlessModeActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EndlessModeGameView view = new EndlessModeGameView(this);//create the Activity for the game
        setContentView(view);
    }


}
