package com.overwatch.warofship.GameMenu;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.overwatch.warofship.*;

public class Record_Activity extends AppCompatActivity {
    private DbHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setContentView(R.layout.record_layout);
        dbhelper=new DbHelper(this,"record.db",null,1);
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        Cursor cursor=db.query("record",null,null,null,null,null,"score desc","5");
        TextView textView=(TextView)findViewById(R.id.tv1);
        textView.setText(textView.getText()+"\n");
        textView.setText(textView.getText()+"\n");
        textView.setText(textView.getText()+"\n");
        textView.setText(textView.getText()+"\n");
        textView.setText(textView.getText()+"\n");
        textView.setText(textView.getText()+"\n");
        textView.setText(textView.getText()+"\n");
        textView.setText(textView.getText()+"\n");
        textView.setText(textView.getText()+"\n");
        textView.setText(textView.getText()+"                  "+"NAME"+"         ");
        textView.setText(textView.getText()+"SCORE\n");

        if(cursor.moveToFirst()){
            int number=1;
            do{

                String name=cursor.getString(cursor.getColumnIndex("name"));
                int score=cursor.getInt(cursor.getColumnIndex("score"));
                textView.setText(textView.getText()+"         "+Integer.toString(number)+"     ");
                textView.setText(textView.getText()+"    "+name+"          ");
                textView.setText(textView.getText()+Integer.toString(score)+"\n");
                number++;
            }while(cursor.moveToNext());
        }
        cursor.close();
    }
}
