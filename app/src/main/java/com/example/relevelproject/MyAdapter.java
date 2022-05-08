package com.example.relevelproject;


import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    List<MainData> list;
    String category = "";

    public MyAdapter(Context context, List<MainData> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MainData busdata = list.get(position);
        Log.e("Data",busdata.getDescription());
        holder.id.setText(busdata.getID()+"");
        holder.name.setText(busdata.getName()+"");
        holder.desc.setText(busdata.getDescription()+"");
        holder.price.setText(busdata.getPrice()+"");
        holder.dis.setText(busdata.getDiscount()+"");

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog root = new Dialog(context);
                root.setContentView(R.layout.dialog_box);
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.MATCH_PARENT;
                root.getWindow().setLayout(width,height);
                root.show();

                EditText name,desc,price,discount;
                Button create;
                Spinner cat;
                final String[] category = {""};

                name = root.findViewById(R.id.addDetails_itemname);
                desc = root.findViewById(R.id.addDetails_itemdesc);
                price = root.findViewById(R.id.addDetails_itemPrice);
                discount = root.findViewById(R.id.addDetails_itemdiscount);
                category[0] = "";


                cat  = root.findViewById(R.id.addDetails_itemcategory);
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add("Cosmetics");
                arrayList.add("Makeup");
                arrayList.add("Beauty Products");
                arrayList.add("Accessories");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                        root.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arrayList
                );
                cat.setAdapter(arrayAdapter);
                final String[] finalCategory = {category[0]};
                cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        finalCategory[0] = arrayList.get(i);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });



                create = root.findViewById(R.id.addDetails_itemCreate);
                create.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, category[0],Toast.LENGTH_SHORT).show();

                        String c_name,c_desc,c_price,c_discount;
                        c_name = name.getText().toString();
                        c_desc = desc.getText().toString();
                        c_price = price.getText().toString();
                        c_discount = discount.getText().toString();

                        if(c_name.isEmpty()){
                            name.requestFocus();
                            name.setError("Enter Name");
                        }
                        if(c_price.isEmpty()){
                            price.requestFocus();
                            price.setError("Enter Name");
                        }
                        if(c_desc.isEmpty()){
                            desc.requestFocus();
                            desc.setError("Enter Name");
                        }
                        if(c_discount.isEmpty()){
                            discount.requestFocus();
                            discount.setError("Enter Name");
                        }
                        int final_price = 0;
                        int final_discount = 0;
                        try {

                            final_price = Integer.parseInt(c_price);
                        } catch (NumberFormatException e) {
                            price.requestFocus();
                            price.setError("Enter Correct Amount");
                        }
                        try {
                            final_discount = Integer.parseInt(c_discount);
                        } catch (NumberFormatException e) {
                            discount.requestFocus();
                            discount.setError("Enter Correct Amount");
                        }


                        RoomDB database;
                        database = RoomDB.getInstance(context);
                        MainData mainData = new MainData(c_name,c_discount,final_price,final_discount);
                        database.mainDao().update(busdata.getID(),c_name,c_desc,final_price,final_discount);
                        list.clear();
                        list.addAll(database.mainDao().getAll());
                        notifyDataSetChanged();
                    }
                });


                    root.dismiss();

            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainData d = list.get(holder.getAdapterPosition());
                RoomDB database;
                database = RoomDB.getInstance(context);
                database.mainDao().delete(d);
                int position = holder.getAdapterPosition();
                list.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,list.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id,name,desc,price,cat,dis;
        ImageView edit,delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.item_id);
            name = itemView.findViewById(R.id.item_name);
            desc = itemView.findViewById(R.id.item_disc);
            price = itemView.findViewById(R.id.item_price);
            dis = itemView.findViewById(R.id.item_discount);
            edit = itemView.findViewById(R.id.edit_Field);
            delete = itemView.findViewById(R.id.delete_field);
        }


    }



}
