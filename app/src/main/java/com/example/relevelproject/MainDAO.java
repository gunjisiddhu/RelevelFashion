package com.example.relevelproject;


import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MainDAO {
    @Insert(onConflict = REPLACE)
    void insert(MainData mainData);


    @Delete
    void delete(MainData mainData);

    @Delete
    void deleteAll(List<MainData> mainDataList);

    @Query("SELECT * FROM table_name")
    List<MainData> getAll();

    @Query( "UPDATE table_name SET Item_Name = :sName, Item_Description = :sdescription, Item_Price = :sprice, Item_Discount = :sdiscount WHERE ID = :sID")
    void update(int sID,String sName,String sdescription,int sprice,int sdiscount);



}
