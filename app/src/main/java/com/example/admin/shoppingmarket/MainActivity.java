package com.example.admin.shoppingmarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.admin.shoppingmarket.database.ShoppingData;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn1, btn2, btn3, btn4;
    Spinner spn;
    ShoppingData shoppingDB;
    private ArrayList<ShoppingList> list;

    public static String[] vegetable_Name = {"Brinjal","Cabbage","Ladies Finger","Carrot","Green Chillies","Tomato","Potato","Capsicum","Onion", "Drumstick","Green Onion",
            "Cauliflower","Sweet Potato","RidgeGourd","Raddish","Pumpkin","Mushroom","Green Peas","French Beans","Cucumber","Red Chilli",
            "Bottle Gourd","Bitter Gourd","Beetroot",
            "Amaranthus","Curry Leaves","Fenugreek Leaves","Red Sorral","Spinach","Coriander","Mint Leaves",
            "Apple","Banana", "Black Current","Orange","Mango","Custered Apple","Cherry","Pineapple","Pomogranate","Grapes","Dates","Coconut",
            "Watermelon","Strawberry","Sapodilla","Pear","Peach","Papaya","Melon","Lemon","Jack Fruit","Jamun","Gooseberry","Guava","Kiwi",
            "Milk","Butter","Cheese","Ghee","Kulfi","Cream","Choclates","Paneer","Curd","Khoa",
    };
    public static int[] imageids = {R.drawable.brinjal,R.drawable.cabbage,R.drawable.ladiesfinger, R.drawable.carrot, R.drawable.greenchillies,R.drawable.tomato,
            R.drawable.potato,R.drawable.capsicum,R.drawable.onion,R.drawable.drumstick,R.drawable.greenonion,R.drawable.cauliflower,
            R.drawable.sweetpotato,R.drawable.ridgegourd,R.drawable.raddish,R.drawable.pumpkin,R.drawable.pumpkin,R.drawable.greenpeas,
            R.drawable.frenchbeans,R.drawable.cucumber,R.drawable.redchilli,R.drawable.bottlegourd,R.drawable.bittergourd,R.drawable.beetroot,
            R.drawable.amaranthus,R.drawable.curryleaves, R.drawable.fenugreekleaves,R.drawable.redsorral,R.drawable.spinach,R.drawable.coriander,
            R.drawable.mintleaves,
            R.drawable.apple,R.drawable.banana,R.drawable.blackcurrent,R.drawable.orange,R.drawable.mango,R.drawable.custeredapple,R.drawable.cherry,
            R.drawable.pineapple,R.drawable.pomegranate,R.drawable.grapes, R.drawable.dates,R.drawable.coconut,R.drawable.watermelon,R.drawable.strawberry,
            R.drawable.sapodilla,R.drawable.pear, R.drawable.peach, R.drawable.papaya,R.drawable.melon,R.drawable.lemon,R.drawable.jackfruit,R.drawable.jamun,
            R.drawable.gooseberry,R.drawable.guava,R.drawable.kiwi,
            R.drawable.milk,R.drawable.butter,R.drawable.cheese, R.drawable.ghee,R.drawable.kulfi, R.drawable.cream, R.drawable.choclates, R.drawable.paneer,
            R.drawable.curd,R.drawable.khoa,};


    public static String[] category={"VEGETABLES","VEGETABLES","VEGETABLES","VEGETABLES","VEGETABLES","VEGETABLES","VEGETABLES","VEGETABLES","VEGETABLES","VEGETABLES","VEGETABLES","VEGETABLES",
            "VEGETABLES","VEGETABLES","VEGETABLES","VEGETABLES","VEGETABLES","VEGETABLES","VEGETABLES","VEGETABLES","VEGETABLES","VEGETABLES","VEGETABLES","VEGETABLES",
            "LEAFY VEGETABLES","LEAFY VEGETABLES","LEAFY VEGETABLES","LEAFY VEGETABLES",
            "LEAFY VEGETABLES","LEAFY VEGETABLES","LEAFY VEGETABLES",
            "FRUITS","FRUITS","FRUITS","FRUITS","FRUITS","FRUITS","FRUITS","FRUITS","FRUITS","FRUITS","FRUITS","FRUITS","FRUITS","FRUITS","FRUITS","FRUITS",
            "FRUITS","FRUITS","FRUITS","FRUITS","FRUITS","FRUITS","FRUITS","FRUITS","FRUITS",
            "DAIRY PRODUCT","DAIRY PRODUCT","DAIRY PRODUCT","DAIRY PRODUCT","DAIRY PRODUCT","DAIRY PRODUCT","DAIRY PRODUCT","DAIRY PRODUCT","DAIRY PRODUCT","DAIRY PRODUCT"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.buttonCreateList);
        btn2 = (Button) findViewById(R.id.buttonViewList);
        btn3 = (Button) findViewById(R.id.buttonAddProduct);
       // btn4 = (Button) findViewById(R.id.buttonEditProduct);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        //btn4.setOnClickListener(this);

        shoppingDB =new ShoppingData(getApplicationContext()); //this will call constructor and will create database
        //shoppingDB.addItem("ShopList",R.drawable.amaranthus,false);
        String vege_name;
        String vege_cate;
        int vege_img;
        Log.e("COUNT", vegetable_Name.length +"and "+ imageids.length+"and "+category.length);
        for(int k=0;k<vegetable_Name.length;k++){
            vege_name=vegetable_Name[k];
            vege_img=imageids[k];
            vege_cate=category[k];
            Log.e("AddItem",vege_name+" "+vege_cate+" "+vege_img);
            //TEMPORARY TO AVOID DUPLICATE DATA.
           //shoppingDB.deleteAll();
            //Log.e("DELETE","OLD RECORDS DELETED FROM DB");
            //LISTNAME WILL BE NULL INITIALLY AND WILL UPDATE ACCORDINGLY
          //shoppingDB.addItem(vege_name,vege_cate,vege_img,false,"1",null);

        }
        Log.e("TAG","Values Inserted in DB");


        //String[] FoodMarket = {"About FoodMarket", "Rate the App", "Invite Friend"};
        //ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, FoodMarket);
        //spn.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonCreateList:
                Toast.makeText(this,"ButtonPressed",Toast.LENGTH_SHORT).show();
                Intent i1 = new Intent(this, CreateShoppingList.class);
                startActivity(i1);
                break;
            case R.id.buttonViewList:
                Toast.makeText(this,"buttonViewList Pressed",Toast.LENGTH_SHORT).show();
                Intent i2 = new Intent(this, ViewShoppingList.class);
                startActivity(i2);
                break;

            case R.id.buttonAddProduct:
                Toast.makeText(this,"buttonAddProduct Pressed",Toast.LENGTH_SHORT).show();
                Intent i3 = new Intent(this, AddProduct.class);
                startActivity(i3);
                break;
            /*case R.id.buttonEditProduct:
                Toast.makeText(this,"buttonEditProduct Pressed",Toast.LENGTH_SHORT).show();
                Intent i4 = new Intent(this, EditShopProduct.class);
                startActivity(i4);
                break;*/

        }
    }

}

