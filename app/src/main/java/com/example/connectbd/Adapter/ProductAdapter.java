package com.example.connectbd.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connectbd.Listener.ProductListener;
import com.example.connectbd.Model.Product;
import com.example.connectbd.R;

import org.json.JSONException;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private ArrayList<Product> productArrayList;
    private Context mContext;
    private ProductListener mListener;

    public ProductAdapter(ArrayList<Product> productArrayList, Context mContext) {
        this.productArrayList = productArrayList;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_product_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Product aProduct = productArrayList.get(position);

        holder.tv_product_name.setText(aProduct.getProductName());
        holder.tv_product_unit.setText(aProduct.getUnit());
        holder.tv_count.setText(String.valueOf(0));


        holder.btn_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mListener.addToCart(aProduct.getId(),aProduct.getQuantity(),aProduct.getPrice());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_product_image;
        private TextView tv_count,tv_product_name,tv_product_unit;
        private Button btn_price,btn_add_to_cart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            init(itemView);
        }

        private void init(View itemView) {
            iv_product_image = itemView.findViewById(R.id.product_image);
            tv_count = itemView.findViewById(R.id.tv_product_count);
            tv_product_name = itemView.findViewById(R.id.tv_product_name);
            tv_product_unit = itemView.findViewById(R.id.tv_product_unit);
            btn_price = itemView.findViewById(R.id.btn_price);
            btn_add_to_cart = itemView.findViewById(R.id.btn_add_to_cart);
        }
    }

    public void setProductItemClick(ProductListener mListener){
        if(mListener!=null){
            this.mListener = mListener;
        }
    }

}
