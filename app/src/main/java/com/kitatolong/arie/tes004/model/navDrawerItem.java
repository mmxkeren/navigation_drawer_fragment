package com.kitatolong.arie.tes004.model;

/**
 * Created by Arie on 2/24/2016.
 */
public class navDrawerItem {
    private String title;
    private int icon;
    private String count = "0";
    // bolean to set visibility of the counter
    private boolean isCounterVisible = false;

    public navDrawerItem(){}

    public navDrawerItem(String title, int icon){
        this.title=title;
        this.icon=icon;
    }

    public navDrawerItem(String title, int icon, boolean isCounterVisible, String count){
        this.title=title;
        this.icon=icon;
        this.isCounterVisible=isCounterVisible;
        this.count=count;
    }

    public String getTitle(){
        return this.title;
    }

    public int getIcon(){
        return this.icon;
    }

    public String getCount(){
        return this.count;
    }

    public boolean getCountervisibility(){
        return this.isCounterVisible;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public void setIcon(int icon){
        this.icon=icon;
    }

    public void setCount(String count){
        this.count=count;
    }

    public void setCounterVisible(boolean isCounterVisible){
        this.isCounterVisible=isCounterVisible;
    }
}
