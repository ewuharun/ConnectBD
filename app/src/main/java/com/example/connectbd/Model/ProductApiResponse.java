package com.example.connectbd.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductApiResponse {
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("products")
    @Expose
    private List<Product> products = null;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
