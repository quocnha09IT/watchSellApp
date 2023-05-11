package com.google.dongho;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class delete_cart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_cart);

        Intent intent = getIntent();
        String cart_id1 = intent.getStringExtra("cart_id");

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server.delete, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               /* Toast.makeText(delete_cart.this,response,Toast.LENGTH_SHORT).show();*/

                Intent intent1 = new Intent(delete_cart.this,Cus_cart.class);
                startActivity(intent1);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();



                params.put("cart_id", cart_id1);


                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}