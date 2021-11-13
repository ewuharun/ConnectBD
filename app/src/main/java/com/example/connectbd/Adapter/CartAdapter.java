package com.example.connectbd.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connectbd.Listener.IncrementDecrementClick;
import com.example.connectbd.Model.Product;
import com.example.connectbd.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private ArrayList<Product> cartList;
    private Context mContext;
    private IncrementDecrementClick mListener;

    public CartAdapter(ArrayList<Product> cartList, Context mContext) {
        this.cartList = cartList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_cart_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Product aProduct = cartList.get(position);

        holder.tvProductName.setText("Product Name : "+aProduct.getProductName());
        holder.tvPrice.setText("Price : "+String.valueOf(aProduct.getPrice()));
        holder.tvUnit.setText("Unit : "+aProduct.getUnit());
        holder.tvTotal.setText("Total : "+aProduct.getPrice());

        holder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String presentCounter = holder.tvCounter.getText().toString().trim();
                int counter = Integer.parseInt(presentCounter);
                holder.tvCounter.setText(String.valueOf(counter+1));
                holder.tvTotal.setText("Total : "+String.valueOf(aProduct.getPrice()*(counter+1))+" TK");
            }
        });


        holder.ivRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String presentCounter = holder.tvCounter.getText().toString().trim();
                int counter = Integer.parseInt(presentCounter);
                if(counter>1){
                    holder.tvCounter.setText(String.valueOf(counter-1));

                    holder.tvTotal.setText("Total : "+String.valueOf(aProduct.getPrice()*(counter+1))+" TK");
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvProductName,tvUnit,tvPrice,tvTotal,tvCounter;
        private ImageView ivRemove,ivAdd;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            init(itemView);
        }

        private void init(View itemView) {
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvUnit = itemView.findViewById(R.id.tvUnit);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvTotal = itemView.findViewById(R.id.tvTotal);
            tvCounter = itemView.findViewById(R.id.tvCounter);
            ivRemove = itemView.findViewById(R.id.ivRemove);
            ivAdd = itemView.findViewById(R.id.ivAdd);
        }
    }

    void setIncrementDecrementClick(IncrementDecrementClick mListener){
        if(mListener!=null){
            this.mListener = mListener;
        }
    }
}
