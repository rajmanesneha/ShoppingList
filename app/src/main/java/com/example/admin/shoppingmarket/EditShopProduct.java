package com.example.admin.shoppingmarket;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.shoppingmarket.database.ShoppingData;

import java.util.ArrayList;

/**
 * Created by Mayuri on 9/5/2017.
 */

public class EditShopProduct  extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener {

    ShoppingData shoppingDB;
    ListView lv;
    ArrayAdapter myAdapter;
    EditText editedName,editedQuant;
    String name, quantitiy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_product);
        String itemName=getIntent().getStringExtra("itemName");
        EditText edtName= (EditText) findViewById(R.id.editItemEtxt);
        edtName.setText(itemName);
        shoppingDB=new ShoppingData(this);
        shoppingDB.getWritableDatabase();
        Button editButton= (Button) findViewById(R.id.button_editItem);
        editedName= (EditText) findViewById(R.id.editItemEtxt);
        editedQuant= (EditText) findViewById(R.id.edtQuantity);
         name=editedName.getText().toString();
         quantitiy=editedQuant.getText().toString();
        editButton.setOnClickListener(this);/*new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shoppingDB.updateQuantity(name, quantitiy);
            }
        });*/
        /*ArrayList<ShoppingList> itemList=new ArrayList<ShoppingList>();
        shoppingDB=new ShoppingData(this);
        itemList=shoppingDB.getListedItems();
        lv=(ListView) findViewById(R.id.listView_show);

        lv.setAdapter(myAdapter);*/
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onClick(View view) {
        editedQuant= (EditText) findViewById(R.id.edtQuantity);
        quantitiy=editedQuant.getText().toString();
        editedName= (EditText) findViewById(R.id.editItemEtxt);
        name=editedName.getText().toString();
        Log.e("TAG",name + " Q="+ quantitiy);
        shoppingDB.updateQuantity(name, quantitiy);

    }
}