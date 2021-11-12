package com.example.connectbd.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connectbd.Listener.CategoryListener;
import com.example.connectbd.Model.Category;
import com.example.connectbd.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private ArrayList<Category> categoryArrayList;
    private Context mContext;
    private CategoryListener mListener;

    public CategoryAdapter(ArrayList<Category> categoryArrayList, Context mContext) {
        this.categoryArrayList = categoryArrayList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_category_list,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Category aCategory = categoryArrayList.get(position);

        holder.category_name_tv.setText(String.valueOf(aCategory.getCategoryName()));
        holder.total_count_tv.setText(String.valueOf(aCategory.getTotalItem()+" Items"));

        holder.right_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "clicked", Toast.LENGTH_SHORT).show();
                mListener.setOnClickListener(aCategory.getId());
            }
        });


    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView total_count_tv,category_name_tv;
        private ImageView right_iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }


        private void initView(View itemView) {
            total_count_tv = itemView.findViewById(R.id.tv_category_count);
            category_name_tv = itemView.findViewById(R.id.tv_category_name);
            right_iv = itemView.findViewById(R.id.right_iv);
        }
    }

    public void clickArrowButton(CategoryListener mListener){
        if(mListener!=null){
            this.mListener = mListener;
        }
    }


}
