package com.google.dongho;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class search_adapter extends RecyclerView.Adapter<search_adapter.ItemHolder>  {
    Context context;
    ArrayList<search_jv> arrayProduct;

    public search_adapter(Context context, ArrayList<search_jv> arrayProduct) {
        this.context = context;
        this.arrayProduct = arrayProduct;
    }
    @NonNull
    @Override
    public search_adapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search,null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull search_adapter.ItemHolder holder, int position) {
        search_jv Search_jv = arrayProduct.get(position);
        holder.product_name.setText(Search_jv.getName_product());
        /*DecimalFormat decimalFormat = new DecimalFormat("###,###,###");*/
        holder.product_price.setText("price : "+ Search_jv.getPrice_product()+ " vnd");


        Picasso.get().load(Search_jv.getImage_product())
                .placeholder(R.drawable.backgroud)
                .error(R.drawable.backgroud)
                .into(holder.image_product);

    }

    @Override
    public int getItemCount() {
        return arrayProduct.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView image_product;
        public TextView product_name,product_price;


        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            image_product =  itemView.findViewById(R.id.image_product);
            product_name = (TextView) itemView.findViewById(R.id.product_name);

            product_price = (TextView) itemView.findViewById(R.id.product_price);



        }
    }
}
