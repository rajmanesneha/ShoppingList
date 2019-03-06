package com.example.admin.shoppingmarket;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.shoppingmarket.database.ShoppingData;

import java.util.ArrayList;

/**
 * Created by Mayuri on 9/5/2017.
 */
public class ViewShoppingList extends Activity implements AdapterView.OnItemClickListener {

    ShoppingData shoppingDB;
    ListView lv;
    ArrayAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_shopping_list);
        setAdapter();
        lv.setOnItemClickListener(this);

    }

    private void setAdapter() {
        ArrayList<ShoppingList> itemList=new ArrayList<ShoppingList>();
        shoppingDB=new ShoppingData(this);
        itemList=shoppingDB.getListedItems();
        lv=(ListView) findViewById(R.id.listView_show);
        myAdapter= new CustomArrayAdapter(this,R.layout.list_view,itemList);
        lv.setAdapter(myAdapter);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder alertDialog = new  AlertDialog.Builder(this);
        alertDialog.setTitle("EDIT LIST");
         TextView itemSelect= (TextView)view.findViewById(R.id.show_txt);
        final String itemSelected=itemSelect.getText().toString();
                //getItemAtPosition(i);
                //getSelectedItem().toString();


        alertDialog.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"onclickDialog Edit",Toast.LENGTH_SHORT).show();
                //sHOW lISTvIEW according to your list
                Intent intent=new Intent(getApplicationContext(),EditShopProduct.class);
                intent.putExtra("itemName",itemSelected);
                startActivity(intent);
            }
        });
        alertDialog.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),itemSelected,Toast.LENGTH_SHORT).show();
                shoppingDB.deleteItemFromList(itemSelected);
                setAdapter();
            }
        });
        alertDialog.create();
        alertDialog.show();

    }
}
