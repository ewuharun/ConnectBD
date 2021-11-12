package com.example.connectbd.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.connectbd.Activity.MainActivity;
import com.example.connectbd.Activity.ProductActivity;
import com.example.connectbd.Adapter.CategoryAdapter;
import com.example.connectbd.Listener.CategoryListener;
import com.example.connectbd.Model.Category;
import com.example.connectbd.R;
import com.example.connectbd.Utils.CommonPickers;
import com.example.connectbd.Utils.NetworkUtils;
import com.example.connectbd.ViewModel.CategoryViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment{

    private RecyclerView rv_category;
    private Category category;
    private CategoryViewModel categoryViewModel;
    private CategoryAdapter adapter;
    private ArrayList<Category> categoryArrayList;
    private boolean isVisible = false;

    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance() {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        initView(view);
        initVariable();

        getAllCategoryData();




        return view;
    }

    private void getAllCategoryData() {

        Toast.makeText(requireActivity(), "method", Toast.LENGTH_SHORT).show();

        if (NetworkUtils.getConnectivityStatus(requireActivity()) >= 1) {

            final ProgressDialog dialog = CommonPickers.progressDialog("", "Loading Data...", requireActivity());


            categoryViewModel.getMutableLiveData().observe(requireActivity(), new Observer<List<Category>>() {
                @Override
                public void onChanged(List<Category> categories) {
                    dialog.dismiss();
                    categoryArrayList = (ArrayList<Category>) categories;
                    populateRecyclearView(categoryArrayList);

                }
            });
        } else {
            Toast.makeText(requireActivity(), "Internet is not available!", Toast.LENGTH_SHORT).show();
        }


    }

    private void populateRecyclearView(ArrayList<Category> categoryArrayList) {
        adapter = new CategoryAdapter(categoryArrayList, requireActivity());
        rv_category.setLayoutManager(new LinearLayoutManager(requireActivity()));
        rv_category.setAdapter(adapter);

        adapter.clickArrowButton(new CategoryListener() {
            @Override
            public void setOnClickListener(int category_id) {
                Toast.makeText(requireActivity(), String.valueOf(category_id), Toast.LENGTH_SHORT).show();

                Log.e("CategoryId",String.valueOf(category_id));

                Intent intent = new Intent(requireActivity(), ProductActivity.class);
                intent.putExtra("category_id",String.valueOf(category_id));
                startActivity(intent);

            }
        });


    }

    private void initVariable() {
        category = new Category();
        categoryArrayList = new ArrayList<>();
        isVisible = false;
        categoryViewModel = new ViewModelProvider(requireActivity()).get(CategoryViewModel.class);
    }

    private void initView(View view) {
        rv_category = view.findViewById(R.id.rv_category);
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getView()!=null){
            Toast.makeText(requireActivity(), "category", Toast.LENGTH_SHORT).show();
            getAllCategoryData();
        }
    }


}