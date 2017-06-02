package com.overwatch.warofship.GameMenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.overwatch.warofship.StoryMode.Story1LevActivity;
import com.overwatch.warofship.StoryMode.Story2LevActivity;
import com.overwatch.warofship.StoryMode.Story3LevActivity;
import com.overwatch.warofship.StoryMode.Story4LevActivity;
import com.overwatch.warofship.StoryMode.Story5LevActivity;
import com.overwatch.warofship.R;

public class StoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_layout);

        Button button1=(Button) findViewById(R.id.button_6);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent=new Intent(StoryActivity.this,Story1LevActivity.class);
                startActivity(intent);
            }
        });

        Button button2=(Button) findViewById(R.id.button_7);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent=new Intent(StoryActivity.this,Story2LevActivity.class);
                startActivity(intent);
            }
        });
        Button button3=(Button) findViewById(R.id.button_8);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent=new Intent(StoryActivity.this,Story3LevActivity.class);
                startActivity(intent);
            }
        });
        Button button4=(Button) findViewById(R.id.button_9);
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent=new Intent(StoryActivity.this,Story4LevActivity.class);
                startActivity(intent);
            }
        });
        Button button5=(Button) findViewById(R.id.button_10);
        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent=new Intent(StoryActivity.this,Story5LevActivity.class);
                startActivity(intent);
            }
        });
    }
}
