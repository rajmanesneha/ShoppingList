package com.example.admin.shoppingmarket;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.shoppingmarket.database.ShopList;
import com.example.admin.shoppingmarket.database.ShoppingData;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    String[] vegetables;
    Context context;
    int[] imageId;
    ShoppingData db = new ShoppingData(context);
    ArrayList<ShoppingList> itemList;
    ArrayList<String> shoppinglist = new ArrayList<String>();
    String checked;

    private static LayoutInflater inflater = null;

    /*  public CustomAdapter(CreateShoppingList createShoppingList, String[] vegetables, int[] imageids) {
          // TODO Auto-generated constructor stub
          this.vegetables=vegetables;
          this.context=createShoppingList;
          this.imageId=imageids;
          inflater = ( LayoutInflater )context.
                  getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      }*/
    public CustomAdapter(CreateShoppingList createShoppingList, int custom_list_view, ArrayList<ShoppingList> itemList) {
        // TODO Auto-generated constructor stub
        super(createShoppingList, custom_list_view);
        this.context = createShoppingList;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return itemList.size();
    }

    @Override
    public ShoppingList getItem(int position) {
        // TODO Auto-generated method stub
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //if(convertView!=null){
        final ShoppingList shoppingList = (ShoppingList) getItem(position);
        final TextView v_txt;
        ImageView v_image;
        final CheckBox v_check;

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.custom_list_view, null);
        Log.i("TAG", "ConvertView:" + convertView);
        v_txt = (TextView) convertView.findViewById(R.id.vege_txt);
        v_image = (ImageView) convertView.findViewById(R.id.vege_img);
        v_check = (CheckBox) convertView.findViewById(R.id.vege_check);

        convertView.setTag(new listHolder(v_txt, v_check));

        v_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*CheckBox c= (CheckBox) view;
                ShoppingList shoppingL= (ShoppingList) c.getTag();
                Log.e("ONCLICKITEM LISTITEM",view.toString()+" "+shoppingL+" "+c.getTag());
                shoppingL.setSelected(c.isSelected());*/
                v_check.setSelected(shoppingList.isSelected());
                Log.e("isSelected", String.valueOf(shoppingList.isSelected()));
            }
        });

        v_txt.setText(shoppingList.getName());
        v_image.setImageResource(shoppingList.getImage());
        //v_check.setChecked(shoppingList.isSelected());

        v_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ShoppingData shoppingDB=new ShoppingData(context);
                //ShopList shoplistDB=new ShopList(context);
                Log.e("CHECKED ITEM IS", String.valueOf(v_txt.getText()));
                checked=String.valueOf(v_txt.getText());
                //v_check.setSelected(shoppingList.isSelected());
                v_check.setSelected(b);
                Log.e("isSelected", String.valueOf(shoppingList.isSelected()));
                shoppinglist.add(checked);
               // shoplistDB.updateList(shoppinglist);
                shoppingDB.updateList(String.valueOf(v_txt.getText()),b);
            }
        });
        //}
        return convertView;
        //return super.getView(position, convertView, parent);
    }


    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        ArrayList<ShoppingList> ListItems;
        ListItems = getAllItems();

    }

    public ArrayList<ShoppingList> getAllItems() {
        return itemList;
    }

}

class listHolder {
    private CheckBox v_check;
    private TextView v_txt;


    public listHolder(TextView v_txt, CheckBox v_check) {
        this.v_check=v_check;
        this.v_txt=v_txt;

    }
    public CheckBox getV_check() {
        return v_check;
    }

    public void setV_check(CheckBox v_check) {
        this.v_check = v_check;
    }

    public TextView getV_txt() {
        return v_txt;
    }

    public void setV_txt(TextView v_txt) {
        this.v_txt = v_txt;
    }

}

/*public class Holder
    {
        TextView tv;
        ImageView img;
        CheckBox check;
    }*/
    /*@Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        boolean checked=false;
        rowView = inflater.inflate(R.layout.custom_list_view, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);
        holder.check= (CheckBox) rowView.findViewById(R.id.checkBox);
        holder.tv.setText(vegetables[position]);
        holder.img.setImageResource(imageId[position]);
        //rowView.setonS
     *//*   if(holder.check.isChecked()){
            checked=true;
            holder.check.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "You Checked "+vegetables[position], Toast.LENGTH_LONG).show();
                    ShoppingList item=new ShoppingList("Amaranthus",R.drawable.amaranthus,false);
                    itemList.add(item);
                }
            });
        }else
            checked=false;
*//*

  *//*      rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Select "+vegetables[position], Toast.LENGTH_LONG).show();
                //db=new ShoppingData(context);
               // db.addItem("ShopList",vegetables[position]);
            }
        });*//*
        return rowView;
    }*/

//}