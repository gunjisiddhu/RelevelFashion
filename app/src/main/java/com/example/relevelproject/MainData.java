package com.example.relevelproject;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "table_name")
public class MainData implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int ID;

    public MainData() {
    }

    public MainData(String name, String description, int price, int discount) {
        this.ID = ID;
        this.name = name;
        Description = description;
        this.price = price;
        this.discount = discount;
    }

    @ColumnInfo(name = "Item_Name")
    private String name;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @ColumnInfo(name = "Item_Description")
    private String Description;

    @ColumnInfo(name = "Item_Price")
    private int price;

    @ColumnInfo(name = "Item_Discount")
    private int discount;

}
