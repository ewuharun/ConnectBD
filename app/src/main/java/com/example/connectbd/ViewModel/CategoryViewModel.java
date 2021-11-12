package com.example.connectbd.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.connectbd.Model.Category;
import com.example.connectbd.Repository.CategoryRepository;
import com.google.gson.Gson;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {
    public MutableLiveData<List<Category>> mutableLiveData = new MutableLiveData<>();
    private Application application;
    private CategoryRepository categoryRepository;

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        categoryRepository = new CategoryRepository();
    }


    public MutableLiveData<List<Category>> getMutableLiveData() {
        return categoryRepository.getAllCategoryData();
    }
}
