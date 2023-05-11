package com.google.dongho;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class detail_product extends AppCompatActivity {

    ImageView image_product;
    TextView name_product, id, price_product, desc_product, content_product;
    Button add,muatiep;
    Spinner spinner;




    int sl = 0;
    int newprice1 = 0;
    String  sl1 ="";
    String newprice2="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);


        id = findViewById(R.id.product_id);
        name_product = findViewById(R.id.name_product);
        price_product = findViewById(R.id.price_product);
        desc_product = findViewById(R.id.desc);
        content_product = findViewById(R.id.content);
        image_product = findViewById(R.id.product_image);
        add = findViewById(R.id.addcart);
        spinner = findViewById(R.id.spinner);





        id.setText("STT: " + getIntent().getStringExtra("id_product"));
        name_product.setText("Tên: " + getIntent().getStringExtra("product_name"));
        price_product.setText("Giá: " + getIntent().getStringExtra("product_price"));
        desc_product.setText(getIntent().getStringExtra("desc_product"));
        content_product.setText(getIntent().getStringExtra("content_product"));

        Picasso.get().load(getIntent().getStringExtra("product_image")).into(image_product);



        eventSnipnner();

        Intent intent = getIntent();
        String  message = intent.getStringExtra("product_price");
        int mess1 = Integer.parseInt(message);


        muatiep = findViewById(R.id.muatiep);
        muatiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(detail_product.this,MainActivity.class);
                startActivity(intent1);
            }
        });

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                      sl = Integer.parseInt(spinner.getSelectedItem().toString());
                      newprice1 = sl * mess1;
                      sl1 = String.valueOf(sl);
                      newprice2 = String.valueOf(newprice1);
                      EventButton();

                }
            });
        }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_cart:
                Intent intent = new Intent(getApplicationContext(), Cus_cart.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void eventSnipnner() {
        Integer[] sl = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, sl);

        spinner.setAdapter(arrayAdapter);


    }

    private void EventButton() {

        RequestQueue requestQueue = Volley.newRequestQueue(detail_product.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server.addcart, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("ddc")) {

                    Toast.makeText(detail_product.this, "them thanh cong,", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(detail_product.this, MainActivity.class));

                }else{
                    Toast.makeText(detail_product.this,"looix theem", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("AAA", " loi\n" + error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();



                params.put("id_customer" ,MainActivity.id_customer96);
                params.put("id_product" , getIntent().getStringExtra("id_product"));
                params.put("name_product" , getIntent().getStringExtra("product_name"));
                params.put("quality" , sl1);
                params.put("price" , newprice2);
                params.put("image" , getIntent().getStringExtra("product_image"));

                return params;

            }
        };
        requestQueue.add(stringRequest);

    }
}


//                   if (MainActivity.arrayCart.size() > 0){
//                       int sl  = Integer.parseInt(spinner.getSelectedItem().toString());
//                       boolean exit = false;
//                       for (int  i = 0 ;i < MainActivity.arrayCart.size();i++){
//                           if (MainActivity.arrayCart.get(i).getId() == id1){
//                               MainActivity.arrayCart.get(i).setSl(MainActivity.arrayCart.get(i).getSl() + sl);
//                               if (MainActivity.arrayCart.get(i).getSl() >= 10){
//                                   MainActivity.arrayCart.get(i).setSl(10);
//                               }
//                               MainActivity.arrayCart.get(i).setPrice_product((int) (price_product1 * MainActivity.arrayCart.get(i).getSl()));
//                               exit = true;
//                           }
//
//                       }
//                       if (exit == false){
////                           sl = Integer.parseInt(spinner.getSelectedItem().toString());
////                           int newprice1 = sl * message;
//                           MainActivity.arrayCart.add(new cart(id1,name_product1,image_product1,1,10000));
//                       }
//
//                    }else {
                      /* int sl = Integer.parseInt(spinner.getSelectedItem().toString());
                        int newprice1 = sl * message;
                        MainActivity.arrayCart.add(new cart(id1,name_product1,image_product1,sl,newprice1));*/
//                 }

                  /*  Toast toast = Toast.makeText(getApplicationContext(), String.valueOf(MainActivity.arrayCart.get(0).getSl()) ,Toast.LENGTH_SHORT );
                   toast.show();*/
                /*   Intent intent = new Intent(getApplicationContext(),Cus_cart.class);
                   startActivity(intent);*/
