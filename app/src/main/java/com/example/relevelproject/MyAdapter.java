package com.example.relevelproject;


import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    List<MainData> list;
    OnTouchListener onTouchListener;

    public MyAdapter(Context context, List<MainData> list) {
        this.context = context;
        this.list = list;
        this.onTouchListener = onTouchListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(view, onTouchListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MainData busdata = list.get(position);
        holder.id.setText(busdata.getID());
        holder.name.setText(busdata.getName());
        holder.desc.setText(busdata.getDescription());
        holder.price.setText(busdata.getPrice());
        holder.dis.setText(busdata.getDiscount());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView id,name,desc,price,cat,dis;
        OnTouchListener onTouchListener;

        public MyViewHolder(@NonNull View itemView, OnTouchListener onTouchListener) {
            super(itemView);

            id = itemView.findViewById(R.id.item_id);
            name = itemView.findViewById(R.id.item_name);
            desc = itemView.findViewById(R.id.item_disc);
            price = itemView.findViewById(R.id.item_price);
            dis = itemView.findViewById(R.id.item_discount);
            itemView.setOnClickListener(this);
            this.onTouchListener = onTouchListener;
        }

        @Override
        public void onClick(View v) {
            onTouchListener.onTouchClick(getAdapterPosition());
        }
    }

    public interface OnTouchListener {
        void onTouchClick(int position);
    }


}
