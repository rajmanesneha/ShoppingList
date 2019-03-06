package com.example.admin.shoppingmarket;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mayuri on 9/12/2017.
 */
public class CustomArrayAdapter extends ArrayAdapter {
    Context context;
    ArrayList<ShoppingList> itemList;
    public CustomArrayAdapter(ViewShoppingList viewShoppingList, int list_view, ArrayList<ShoppingList> itemList) {
        super(viewShoppingList,list_view);
        this.context=viewShoppingList;
        this.itemList=itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ShoppingList shoppingList=(ShoppingList) getItem(position);
        LayoutInflater inflater=LayoutInflater.from(context);
        convertView=inflater.inflate(R.layout.list_view,null);
        TextView v_txt= (TextView) convertView.findViewById(R.id.show_txt);
        ImageView v_image= (ImageView) convertView.findViewById(R.id.show_img);
        TextView v_quant= (TextView) convertView.findViewById(R.id.show_quant);

        v_txt.setText(shoppingList.getName());
        v_image.setImageResource(shoppingList.getImage());
        v_quant.setText(shoppingList.getQuantity());

        return convertView;
        //return super.getView(position, convertView, parent);
    }
}
