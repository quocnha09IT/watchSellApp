package com.google.dongho;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.ItemHolder> {
    Context context;
    ArrayList<cart> arrayCart;

    public cartAdapter(Context context, ArrayList<cart> arrayCart) {
        this.context = context;
        this.arrayCart = arrayCart;
    }
    public interface ItemClickListener {
        void onClick(View view, int position,boolean isLongClick);
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        cart Cart = arrayCart.get(position);
        holder.name_product.setText(Cart.getName_product());
        /*DecimalFormat decimalFormat = new DecimalFormat("###,###,###");*/
        holder.price.setText("price : " + Cart.getPrice() + " vnd");
        holder.quantity.setText("Quantity: "+Cart.getQuantity());

        Picasso.get().load(Cart.getImage_product())
                .placeholder(R.drawable.backgroud)
                .error(R.drawable.backgroud)
                .into(holder.image_product);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                /*context.delete(Cart.getCart_id());*/
             /*   Toast.makeText(context,Cart.getCart_id(), Toast.LENGTH_SHORT).show();*/

  Intent intent = new Intent(cartAdapter.this.context, delete_cart.class);
                intent.putExtra("cart_id",Cart.getCart_id());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);


            }
        });


    }
    private void delete(String name){
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setMessage("you want to delete ??" + name);
       /* dialog.setPositiveButton("yes!!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.setPositiveButton("no!!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
*/
    }
    @Override
    public int getItemCount() {
        return arrayCart.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder  implements View.OnClickListener,View.OnLongClickListener{

        public ImageView image_product,delete;
        public TextView name_product, quantity, price;
        private ItemClickListener itemClickListener;



        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            name_product = itemView.findViewById(R.id.name_cart);
            image_product = itemView.findViewById(R.id.image_cart);
            quantity = (TextView) itemView.findViewById(R.id.quantity);
            price = (TextView) itemView.findViewById(R.id.price_cart);
            delete = itemView.findViewById(R.id.delete);
            delete.setOnClickListener(this);
            delete.setOnLongClickListener(this);


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
