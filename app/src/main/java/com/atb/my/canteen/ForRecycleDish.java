package com.atb.my.canteen;

import android.content.Context;

import java.util.ArrayList;

public class ForRecycleDish {
    private String name;
    private String type;
    private String price;
    private String weight;
    private int number;
    private String picture;
    private Context context;
    private String allPath;
    private ArrayList<Integer> arrayList;
    private String calorie;
    private String mTrients;

    ForRecycleDish(Context context, String name, String type, String price, String weight, String allPath, String calorie, String mTrients, ArrayList<Integer> arrayList, String picture, int number){
        this.context = context;
        this.name = name;
        this.type = type;
        this.price = price;
        this.weight = weight;
        this.picture = picture;
        this.number = number;
        this.arrayList = arrayList;
        this.allPath = allPath;
        this.calorie = calorie;
        this.mTrients = mTrients;
    }

    public int getNumber() {
        return number;
    }

    public String getPicture() {
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

    public String getCalorie() {
        return calorie;
    }

    public String getmTrients() {
        return mTrients;
    }

    public String getAllPath() {
        return allPath;
    }

    public ArrayList<Integer> getArrayList() {
        return arrayList;
    }

    public Context getContext() {
        return context;
    }
}
