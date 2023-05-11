package com.google.dongho;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class category_adapter extends BaseAdapter {
    ArrayList<category> arrayListcategory;
    Context context;

    public category_adapter(ArrayList<category> arrayListcategory, Context context) {
        this.arrayListcategory = arrayListcategory;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListcategory.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListcategory.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        TextView name_category;
        ImageView image_category;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_category_listview,null);
            viewHolder.name_category = view.findViewById(R.id.name_category);
            viewHolder.image_category = view.findViewById(R.id.image_category);
            view.setTag(viewHolder);

        }else{
            viewHolder =  (ViewHolder) view.getTag();

        }
        category category1 = (category) getItem(i);
        viewHolder.name_category.setText(category1.getName_category());
        Picasso.get().load(category1.getImage_category())
                .placeholder(R.drawable.backgroud)
                .error(R.drawable.backgroud)
                .into(viewHolder.image_category);
        return view;
    }
}
