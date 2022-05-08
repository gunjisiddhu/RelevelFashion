package com.example.relevelproject;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.sql.Blob;

@Entity(tableName = "table_name")
public class MainData implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int ID;

    public MainData( String name, String description, int price, int discount) {
        this.ID = ID;
        this.name = name;
        Description = description;
        this.price = price;
        this.discount = discount;
    }

    @ColumnInfo(name = "Item Name")
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

    @ColumnInfo(name = "Item Description")
    private String Description;

    @ColumnInfo(name = "Item Price")
    private int price;

    @ColumnInfo(name = "Item Discount")
    private int discount;

}
