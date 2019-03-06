package com.example.admin.shoppingmarket;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.admin.shoppingmarket.database.ShoppingData;

/**
 * Created by Mayuri on 9/5/2017.
 */

public class AddProduct extends Activity implements AdapterView.OnItemClickListener {
    ShoppingData shoppingDB;
    Button add;
    EditText itemToAdd,category;
    Spinner spnCate;
    String spn_Category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);
         add= (Button) findViewById(R.id.button_addItem);
         itemToAdd= (EditText) findViewById(R.id.addItemTxt);
        //category= (EditText) findViewById(R.id.addItemCate);

        spnCate = (Spinner) findViewById(R.id.spinnerCategory);
        String[] Shop_list = {"FRUITS", "VEGETABLES", "LEAFY VEGETABLES","DAIRY PRODUCT"};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Shop_list);
        spnCate.setAdapter(adapter);
        //final String spn_Category=spnCate.getSelectedItem().toString();
        //Log.e("SPINNERCategory",spn_Category);
        spnCate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("SPINNER sELECTEd",adapterView.getSelectedItem().toString());
                spn_Category= adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spn_Category="FRUITS";
            }
            // final String addItem=itemToAdd.getText().toString();
        //Log.e("TAG",addItem);
        });
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shoppingDB =new ShoppingData(getApplicationContext()); //this will call constructor and will create database
                    shoppingDB.addItem(itemToAdd.getText().toString(),spn_Category,R.drawable.amaranthus,false,"1",null);
                    Log.e("TAG",itemToAdd.getText().toString() +" "+ spn_Category +"Inserted in DB");

                }
            });

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
