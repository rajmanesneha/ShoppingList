package com.example.admin.shoppingmarket.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by VinayakR on 10/4/2017.
 */

public class ShopList extends SQLiteOpenHelper {

    private static final String TABLE_LIST = "SHOPLIST";
    private static final String COL_LISTNAME = "LISTNAME";
    private static final String COL_DATE = "DATE";
    private static final String COL_LISTITEM = "ITEMNAME";
    private static final String COL_LISTID = "LISTID";

    SQLiteDatabase db;
    private static final String DB_NAME="SHOPPINGLIST";
    private static final int DB_VERSION=1;



    public ShopList(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_TABLE_SHOPLIST = "CREATE TABLE " + TABLE_LIST
                + "(" + COL_LISTID + " INTEGER PRIMARY KEY, "
                + COL_LISTNAME + " TEXT,"
                + COL_LISTITEM + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_TABLE_SHOPLIST);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST);
        // Create tables again
        onCreate(sqLiteDatabase);
    }


    public void createList(String listName) {
        db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL_LISTNAME,listName);
        db.insert(TABLE_LIST,null,values);
        db.close();
        Log.e("LIST CREATED ",listName);
    }

    public void updateList(ArrayList<String> s) {
        db=getWritableDatabase();
        ContentValues values=new ContentValues();
       // values.put(COL_LISTNAME,listName);
        db.insert(TABLE_LIST,null,values);
        db.close();
        //Log.e("LIST CREATED ",listName);
    }
}