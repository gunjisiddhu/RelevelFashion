package com.example.relevelproject.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.relevelproject.MainData;
import com.example.relevelproject.R;
import com.example.relevelproject.RoomDB;
import com.example.relevelproject.databinding.FragmentDashboardBinding;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    EditText name,desc,price,discount;
    Button create;
    Spinner cat;
    String category;

    List<MainData> dataList = new ArrayList<>();
    RoomDB database;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        name = root.findViewById(R.id.addDetails_itemname);
        desc = root.findViewById(R.id.addDetails_itemdesc);
        price = root.findViewById(R.id.addDetails_itemPrice);
        discount = root.findViewById(R.id.addDetails_itemdiscount);
        category = "";


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
        cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category = arrayList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        create = root.findViewById(R.id.addDetails_itemCreate);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),category,Toast.LENGTH_SHORT).show();

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


                database = RoomDB.getInstance(getContext());
                MainData mainData = new MainData(c_name,c_desc,final_price,final_discount);
                database.mainDao().insert(mainData);

            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}