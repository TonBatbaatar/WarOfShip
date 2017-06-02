package com.overwatch.warofship.GameMenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.overwatch.warofship.R;

public class Pass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pass_layout);
        Button button1=(Button) findViewById(R.id.button_20);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent=new Intent(Pass.this,Menu.class);
                startActivity(intent);
            }
        });
    }
}
