package com.example.admin.shoppingmarket;

/**
 * Created by Mayuri on 9/11/2017.
 */

public class ShoppingList {

    String name = null,category=null,quantity,listName;
    int image = 0,id;
    boolean selected = false;


    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {

        return category;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public ShoppingList(int id, String name, String category, int image, boolean selected, String quantity,String listName) {
        super();
        this.id=id;
        this.name = name;
        this.image = image;
        this.selected = selected;
        this.category=category;
        this.quantity=quantity;
        this.listName=listName;
    }

    public String getName() {
        return name;
    }
    public void setName(String code) {
        this.name = name;
    }
    public int getImage() {
        return image;
    }
    public void setImage(int image) {
        this.image = image;
    }

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
