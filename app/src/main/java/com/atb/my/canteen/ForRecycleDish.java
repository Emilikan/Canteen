package com.atb.my.canteen;

import android.content.Context;

public class ForRecycleDish {
    /**
     * класс данных о блюде (нужен для RecycleView)
     */

    private String name;
    private String type;
    private String price;
    private String weight;
    private int number;
    private String picture;
    private Context context;
    private String allPath;
    private String calorie;
    private String mTrients;
    private Boolean isThisMain;

    ForRecycleDish(Context context, String name, String type, String price, String weight, String allPath, String calorie, String mTrients, String picture, int number, Boolean isThisMain){
        this.context = context;
        this.name = name;
        this.isThisMain = isThisMain;
        this.type = type;
        this.price = price + "руб";
        this.weight = weight;
        this.picture = picture;
        this.number = number;
        this.allPath = allPath;
        this.calorie = calorie;
        this.mTrients = mTrients;
    }

    public Boolean getThisMain() {
        return isThisMain;
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

    public Context getContext() {
        return context;
    }
}
