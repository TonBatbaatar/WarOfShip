package com.overwatch.warofship.GameMenu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by daxi on 2017/6/2.
 */

public class DbHelper  extends SQLiteOpenHelper {
    public  static final String create_record="create table record("
            +"id integer primary key autoincrement,"
            +"name text,"
            +"score integer)";
    private Context context;
    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        this.context=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_record);
        Toast.makeText(context,"create succeeded",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
