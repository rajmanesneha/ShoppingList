package com.example.admin.shoppingmarket;


import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.admin.shoppingmarket.database.ShopList;
import com.example.admin.shoppingmarket.database.ShoppingData;

import java.text.ParsePosition;
import java.util.ArrayList;

/**
 * Created by Mayuri on 18-08-2017.
 */

public class CreateShoppingList extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener {
    ListView lv;
    CustomAdapter myAdapter;
    ShoppingData shoppingDB;
    private ArrayList<ShoppingList> shoppingListData;
    ShopList shopList;
    EditText listName;
    Button createList;
    Spinner spn;
    ArrayList<ShoppingList> itemList,searchitemList;
    String cate_Selected;
    SearchView search;
    Button buttonOk;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_create_shopping_list);
        search= (SearchView) findViewById(R.id.search);
        listName= (EditText) findViewById(R.id.editTxt_List);
        buttonOk= (Button) findViewById(R.id.button_ok);
        listView= (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ShoppingList shoppingList=myAdapter.getItem(i);
                if(shoppingList.isSelected()) {
                Log.e("Selected item= ",shoppingList.getName().toString());
                }
                Toast.makeText(getApplicationContext(),"Item clicked= "+ shoppingList.getName()+ shoppingList.isSelected(),Toast.LENGTH_SHORT).show();
            }
        });
        buttonOk.setOnClickListener(this);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.e("SEARCH","search item"+s);
                displayItems(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.e("onQueryTextChange",s);
                displayItems(s);// pass selected spin cate
                return false;
            }
        });
        spn = (Spinner) findViewById(R.id.spinnerShopplingList);
        String[] Shop_list = {"FRUITS", "VEGETABLES", "LEAFY VEGETABLES","DAIRY PRODUCT"};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Shop_list);
        spn.setAdapter(adapter);
        String spn_Category=spn.getSelectedItem().toString();
        Log.e("SPINNER",spn_Category);
        displayItemList(spn_Category);
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("SPINNER sELECTEd",adapterView.getSelectedItem().toString());
                cate_Selected= adapterView.getSelectedItem().toString();
                displayItemList(cate_Selected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    @Override
    public void onClick(View view) {
        //onClickof ButtonOK
        String list_Name=listName.getText().toString();
        shopList =new ShopList(getApplicationContext()); //this will call constructor and will create database
        shopList.createList(list_Name);
    }

    private void displayItems(String s) {
        ArrayList<ShoppingList> searchitemList=new ArrayList<ShoppingList>();

        shoppingDB=new ShoppingData(this);
        searchitemList=shoppingDB.searchItem(s);
        Log.e("ITEMS",searchitemList.toString());
        Log.e("ITEMSELECTED BY SPINNER", String.valueOf(searchitemList.size()));
        lv=(ListView) findViewById(R.id.listView);
        //myAdapter= new CustomAdapter(this,R.layout.custom_list_view,allItemList);
        myAdapter= new CustomAdapter(this,R.layout.custom_list_view,searchitemList);
        lv.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }

    private void displayItemList(String spn_Category) {
        ArrayList<ShoppingList> itemList=new ArrayList<ShoppingList>();
        ArrayList<ShoppingList> allItemList=new ArrayList<ShoppingList>();

        shoppingDB=new ShoppingData(this);
        allItemList=shoppingDB.getallItems();
        Log.e("ALLITEMS",allItemList.toString());
        itemList=shoppingDB.getSpinCateItems(spn_Category);
        Log.e("ITEMS",itemList.toString());
        Log.e("ITEMSELECTED BY SPINNER", String.valueOf(itemList.size()));
        lv=(ListView) findViewById(R.id.listView);
        //myAdapter= new CustomAdapter(this,R.layout.custom_list_view,allItemList);
        myAdapter= new CustomAdapter(this,R.layout.custom_list_view,itemList);
        lv.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("TAGGGGGGGGGGGGG","iTEM SELECTED");
                Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);
                cursor.getPosition();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Log.e("ITEM SELECTED", String.valueOf(id)+"  "+item.isChecked());

        //noinspection SimplifiableIfStatement
        // if (id == R.id.action_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ShoppingList shoppingList=shoppingListData.get(i);
        Toast.makeText(getApplicationContext(),"Item clicked= "+ shoppingList.getName(),Toast.LENGTH_SHORT).show();

    }


}
