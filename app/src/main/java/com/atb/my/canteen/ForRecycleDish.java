package com.atb.my.canteen;

import android.content.Context;

import java.util.ArrayList;

public class ForRecycleDish {
    private String name;
    private String type;
    private String price;
    private String weight;
    private int number;
    private int picture;
    private Context context;
    private ArrayList<Integer> arrayList;

    ForRecycleDish(Context context, String name, String type, String price, String weight, ArrayList<Integer> arrayList, int picture, int number){
        this.context = context;
        this.name = name;
        this.type = type;
        this.price = price;
        this.weight = weight;
        this.picture = picture;
        this.number = number;
        this.arrayList = arrayList;
    }

    public int getNumber() {
        return number;
    }

    public int getPicture() {
        return picture;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public String getWeight() {
        return weight;
    }

    public ArrayList<Integer> getArrayList() {
        return arrayList;
    }

    public Context getContext() {
        return context;
    }
}
