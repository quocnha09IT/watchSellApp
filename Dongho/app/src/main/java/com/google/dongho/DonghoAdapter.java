package com.google.dongho;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DonghoAdapter extends RecyclerView.Adapter<DonghoAdapter.ItemHolder>  {



    Context context;
    ArrayList<product> arrayProduct;

    public DonghoAdapter(Context context, ArrayList<product> arrayProduct) {
        this.context = context;
        this.arrayProduct = arrayProduct;
    }
    public interface ItemClickListener {
        void onClick(View view, int position,boolean isLongClick);
    }


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lv_dongho,null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
       product Product = arrayProduct.get(position);
        holder.product_name.setText(Product.getName());
        /*DecimalFormat decimalFormat = new DecimalFormat("###,###,###");*/
        holder.product_price.setText("price : "+ Product.getPrice()+ " vnd");
        holder.id_product.setText(Product.getId());

        Picasso.get().load(Product.getImage())
                .placeholder(R.drawable.backgroud)
                .error(R.drawable.backgroud)
                .into(holder.image_product);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
               Intent intent = new Intent(DonghoAdapter.this.context, detail_product.class);

                intent.putExtra("id_product",Product.getId());
                intent.putExtra("product_name",Product.getName());
                intent.putExtra("product_price",Product.getPrice());
                intent.putExtra("product_image",Product.getImage());
                intent.putExtra("content_product",Product.getContent());
                intent.putExtra("desc_product",Product.getDesc());

               intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
               /* Toast toast = Toast.makeText(context.getApplicationContext(), Product.getId(),Toast.LENGTH_SHORT);
                toast.show();*/
            }
        });
     /*   holder.relavite.setOnClickListener(view -> {
            Intent intent = new Intent(context, detail_product.class);
            intent.putExtra("product_name",Product.getName());
            intent.putExtra("product_price",Product.getPrice());
            intent.putExtra("id_product",Product.getId());
            intent.putExtra("image_product",Product.getImage());



            context.startActivity(intent);
        });*/
    }

    @Override
    public int getItemCount() {
        return arrayProduct.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder  implements View.OnClickListener,View.OnLongClickListener {

        public ImageView image_product;
        public TextView product_name,id_product,product_price;
        public RelativeLayout relavite;
        private ItemClickListener itemClickListener;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            image_product =  itemView.findViewById(R.id.image_product);
            product_name = (TextView) itemView.findViewById(R.id.product_name);
            id_product = (TextView) itemView.findViewById(R.id.id_product);
            product_price = (TextView) itemView.findViewById(R.id.product_price);
            relavite = itemView.findViewById(R.id.relavite);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }
        public void setItemClickListener(ItemClickListener itemClickListener)
        {
            this.itemClickListener = itemClickListener;
        }
        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),false);
        }
        @Override
        public boolean onLongClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),true);
            return true;
        }


    }

}

