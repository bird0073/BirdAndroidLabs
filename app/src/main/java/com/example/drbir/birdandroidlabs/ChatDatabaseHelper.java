package com.example.drbir.birdandroidlabs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by drbir on 3/7/2018.
 */

public class ChatDatabaseHelper extends SQLiteOpenHelper {

    protected static final String ACTIVITY_NAME = "ChatDatabaseHelper";

    public static String DATABASE_NAME = "Messages.db";
    static int VERSION_NUM = 2;
    public static final String TABLE_NAME = "dbChatTable";
    public static final String KEY_MESSAGE = "MESSAGE";
    public static final String KEY_ID = "ID";

    public ChatDatabaseHelper (Context ctx){
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(ACTIVITY_NAME, "Calling onCreate");
       db.execSQL("CREATE TABLE " + TABLE_NAME + "( " + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
               + KEY_MESSAGE + " text);");
    }


    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer){
        Log.i(ACTIVITY_NAME, "Calling onUpgrade, oldVersion= " + oldVer + " newVersion= " +newVer);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void onOpen(SQLiteDatabase db) //always gets called
    {
        Log.i("DATABASE", "Database opened");
    }

    public void onDowngrade(SQLiteDatabase db, int oldVer, int newVer){

    }

}
