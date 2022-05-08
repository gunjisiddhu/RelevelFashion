package com.example.relevelproject.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.relevelproject.MainData;
import com.example.relevelproject.MyAdapter;
import com.example.relevelproject.R;
import com.example.relevelproject.RoomDB;
import com.example.relevelproject.databinding.FragmentHomeBinding;

import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    RecyclerView recyclerView;
    List<MainData> mainDataList;
    RoomDB database;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        database = RoomDB.getInstance(root.getContext());
        recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mainDataList = RoomDB.getInstance(root.getContext()).mainDao().getAll();

        MyAdapter recylceAdapter = new MyAdapter(getContext(),mainDataList);
        recyclerView.setAdapter(recylceAdapter);
        recylceAdapter.notifyDataSetChanged();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}