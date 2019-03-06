package com.example.admin.shoppingmarket.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.admin.shoppingmarket.ShoppingList;

import java.util.ArrayList;

/**
 * Created by Mayuri on 9/11/2017.
 */

public class ShoppingData extends SQLiteOpenHelper {
//Whenever we create object of this Class Database Will get Created.
    private static final String DB_NAME="SHOPPING";
    private static final int DB_VERSION=5;
    private static final String COL_ID ="ID" ;
    //private static final String TABLE_NAME ="SHOPPING_LIST" ;
    private static final String TABLE_NAME ="SHOPPING_DATA" ;
    private static final String TABLE_DATA ="SAVEDITEMS" ;
    private static final String COL_VEGE = "VEGETABLE";
    private static final String COL_IMAGE = "IMAGE";
    private static final String COL_CHECK = "TAKE";
    private static final String COL_CATEGORY ="CATEGORY";
    private static final String COL_QUANTITY ="QUANTITY";
    private static final String COL_LISTNAME = "LISTNAME";


    SQLiteDatabase db;
    int id,image;
    String name,category,quantity,listName;
    boolean check;
    boolean status;

    ShoppingList list;
    public ShoppingData(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.e("TAG","inside constructor");
        //create table in DB CREATE TABLE TABLENAME(<COL_NAME><DATATYPE>PRIMARY KEY,<COL_NAME><DATATYPE>);
       /* String CREATE_TABLE_SHOPPING="CREATE TABLE " + TABLE_DATA
                +"(" +COL_ID + " INTEGER PRIMARY KEY, "
                + COL_LIST + " TEXT,"
                + COL_ITEM + " TEXT)";*/
        String CREATE_TABLE_SHOPPING="CREATE TABLE " + TABLE_NAME
                +"(" +COL_ID + " INTEGER PRIMARY KEY,"
                + COL_VEGE + " TEXT,"
                + COL_CATEGORY + " TEXT,"
                + COL_IMAGE + " BLOB,"
                + COL_CHECK + " BOOLEAN,"
                + COL_QUANTITY + " TEXT,"
                + COL_LISTNAME + " TEXT);";
        sqLiteDatabase.execSQL(CREATE_TABLE_SHOPPING);
        Log.e("Create Table",CREATE_TABLE_SHOPPING);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.e("TAG","inside onupgrade");
        // Drop older table if existed
       sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create tables again
        onCreate(sqLiteDatabase);
    }
    public void addItem(String vege_Name,String category, int image, boolean check,String quant,String listName){
        Log.e("Item BEING Added",vege_Name);
        //int quant=1;
        db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL_VEGE,vege_Name);
        values.put(COL_CATEGORY,category);
        values.put(COL_IMAGE,image);
        values.put(COL_CHECK,check);
        values.put(COL_QUANTITY,quant);
        values.put(COL_LISTNAME,listName);
        db.insert(TABLE_NAME,null,values);

        db.close();
        Log.e("Item Added",vege_Name);


    }


   /*  public void addItem(String ListName,String item){
         db=getWritableDatabase();
         ContentValues values=new ContentValues();
         values.put(COL_LIST,ListName);
         values.put(COL_ITEM,item);
         db.insert(TABLE_NAME,null,values);
         db.close();
         Log.e("Item Added",ListName);
     }*/



    public ArrayList<ShoppingList> getallItems(){
        String selectQuery;
        ArrayList<ShoppingList> List=new ArrayList<>();
        db=getReadableDatabase();
        selectQuery= "SELECT * FROM " +  TABLE_NAME + ";";
        Cursor cursor=db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do {
                id=cursor.getInt(0);
                name=cursor.getString(1);
                category=cursor.getString(2);
                image=cursor.getInt(3);
                quantity=cursor.getString(4);
                listName=cursor.getString(5);
                //check=cursor.i(3);
                Log.e("CURSOR ITEMS",name+" "+category+" "+quantity);
                ShoppingList list=new ShoppingList(id,name,category,image,false,quantity,listName);
                List.add(list); //Fetch dat afrom Internal Storage to RAM
            }while (cursor.moveToNext());
        }
        db.close();
        return List;
    }

    public ArrayList<ShoppingList> getListedItems() {
        String selectQuery;
        ArrayList<ShoppingList> List=new ArrayList<>();
        db=getReadableDatabase();
        selectQuery= "SELECT * FROM " +  TABLE_NAME + " WHERE "+COL_CHECK +"=1 "+";";
        Cursor cursor=db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do {
                id=cursor.getInt(0);
                name=cursor.getString(1);
                category=cursor.getString(2);
                image=cursor.getInt(3);
                quantity=cursor.getString(4);
                listName=cursor.getString(5);
                //check=cursor.i(3);
                Log.e("CURSORListed ITEMS",name+" "+category +" "+quantity);
                ShoppingList list=new ShoppingList(id,name,category,image,false,quantity,listName);
                List.add(list); //Fetch dat afrom Internal Storage to RAM
            }while (cursor.moveToNext());
        }
        db.close();
        return List;
    }

    public void updateList(String  id, boolean ischecked) {
        //status=true; //ADD ITEMS IN TO CREATEDlIST
        db=getWritableDatabase();
        //CHECK WHETHER IT IS ALREADY PRESENT IN LIST OR NOT
        ContentValues value=new ContentValues();
        Log.e("ISCHECKED", String.valueOf(ischecked)+ "  "+id);
        value.put(COL_CHECK, ischecked);
        int rowsUpdated=db.update(TABLE_NAME,value,COL_VEGE + "=?",new String[]{id});//String.valueOf(id)
        Log.i("updateList", "Status UPDATED IN DATABASE: " + rowsUpdated);
        db.close();
    }

    public ArrayList<ShoppingList> getSpinCateItems(String spn_Category) {
        String selectQuery;
        ArrayList<ShoppingList> List=new ArrayList<>();
        db=getReadableDatabase();
        Log.e("CATEGORY",spn_Category);
       // selectQuery= "SELECT * FROM " +  TABLE_NAME + " WHERE "+COL_CATEGORY+"=\'"+spn_Category +"\'"+";";
        selectQuery= "SELECT * FROM " +  TABLE_NAME + " WHERE "+COL_CATEGORY+" in (\'"+spn_Category +"\'"+");";

        Cursor cursor=db.rawQuery(selectQuery,null);
        Log.e("SPINCSATE COUNT",selectQuery+" "+ String.valueOf(cursor.getCount()));
        if (cursor.moveToFirst()){
            do {
                id=cursor.getInt(0);
                name=cursor.getString(1);
                category=cursor.getString(2);
                image=cursor.getInt(3);
                quantity=cursor.getString(4);
                listName=cursor.getString(5);
                //check=cursor.i(3);
                Log.e("CURSORSPINCATE ITEMS",name+" "+category +" "+quantity);
                list=new ShoppingList(id,name,category,image,false,quantity,listName);
                List.add(list); //Fetch dat afrom Internal Storage to RAM
            }while (cursor.moveToNext());
        }
        db.close();
        return List;
    }

    public ArrayList<ShoppingList> searchItem(String search) {
        String selectQuery;
        ArrayList<ShoppingList> List=new ArrayList<>();
        db=getReadableDatabase();
        Log.e("SEARCH",search);
        selectQuery= "SELECT * FROM " +  TABLE_NAME + " WHERE "+COL_VEGE+" LIKE \'"+search +"%"+"\'"+";";

        Cursor cursor=db.rawQuery(selectQuery,null);
        Log.e("SPINCSATE COUNT",selectQuery+" "+ String.valueOf(cursor.getCount()));
        if (cursor.moveToFirst()){
            do {
                id=cursor.getInt(0);
                name=cursor.getString(1);
                //category=cursor.getString(2);
                image=cursor.getInt(3);
                quantity=cursor.getString(4);
                listName=cursor.getString(5);
                //check=cursor.get(3);
                Log.e("CURSORSPINCATE ITEMS",name+" "+category);
                list=new ShoppingList(id,name,category,image,false,quantity,listName);
                List.add(list); //Fetch dat afrom Internal Storage to RAM
            }while (cursor.moveToNext());
        }
        db.close();
        return List;
    }

    public void deleteAll() {
        String deleteQuery;
        db=getReadableDatabase();
        deleteQuery="DELETE FROM "+TABLE_NAME + ";";
        db.execSQL(deleteQuery);
    }

    public void updateQuantity(String itemSelected,String quantity) {
        Log.e("UPDATE QUANTITY",itemSelected+" "+ quantity);
        db=getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put(COL_QUANTITY, quantity);
        //value.put(COL_VEGE, itemSelected);
        int rowsUpdated=db.update(TABLE_NAME,value,COL_VEGE + "=?",new String[]{itemSelected});//String.valueOf(id)
        Log.i("updateQuantity", "quantity UPDATED IN DATABASE: " + rowsUpdated);
        db.close();
        getListedItems();
    }

    public void deleteItemFromList(String itemSelected) {
    //UNCHECK SELECTED ITEM ..SO THAT IT WILL GET DELETED
        Log.i("TAG", "inside deleteItemFromList"+ itemSelected);
        db=getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put(COL_CHECK,Boolean.FALSE);
        int rowsUpdated=db.update(TABLE_NAME,value,COL_VEGE + "=?",new String[]{itemSelected});//String.valueOf(itemSelectd)
        Log.i("TAG", "Status UPDATED IN DATABASE: " + rowsUpdated);
        db.close();
    }

    /*public ArrayList<ShoppingList> updateShoppingList() {

    }*/
}
