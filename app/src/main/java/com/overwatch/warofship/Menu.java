package com.overwatch.warofship;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.overwatch.warofship.EndlessMode.EndlessModeActivity;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        Button button1=(Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent=new Intent(Menu.this,EndlessModeActivity.class);
                startActivity(intent);
            }
        });
        Button button2=(Button) findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent=new Intent(Menu.this,StoryActivity.class);
                startActivity(intent);
            }
        });
    }

}
