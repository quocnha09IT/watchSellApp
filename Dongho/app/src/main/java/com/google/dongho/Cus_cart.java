package com.google.dongho;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cus_cart extends AppCompatActivity {


    TextView txtTong;
    Button btnThanhtoan,btnmuatiep,update;


    RecyclerView recyclerCart;
    cartAdapter CartAdapter ;
    ArrayList<cart> arraycart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_cart);
        maping();
        String id = getIntent().getStringExtra("cart_id");

        if (checkConnect.haveNetworkConnection(getApplicationContext())) {
            getCart();
        } else {
            checkConnect.ShowToast_Short(getApplicationContext(), "check connection!!!!");
        }


    }

    private void getCart() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.getCart, response -> {
            if (response != null){

                String name_product = "";
                String image_product = "";
                String quantity = "";
                String price = "";
                String customer_id = "";
                String product_id = "";
                String cart_id = "";


                    try {
                        int i;
                        for (i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);

                            cart_id = jsonObject.getString("cart_id");
                            customer_id = jsonObject.getString("customer_id");
                            product_id = jsonObject.getString("product_id");
                            name_product = jsonObject.getString("product_name");
                            quantity = jsonObject.getString("quantity");
                            price = jsonObject.getString("price");
                            image_product = jsonObject.getString("image");
                            arraycart.add(new cart( name_product,  image_product,  quantity,  price,  customer_id,  product_id,  cart_id));
                            CartAdapter.notifyDataSetChanged();
                        }
                /*    Toast toast = Toast.makeText(getApplicationContext(),id_category,Toast.LENGTH_SHORT);
                    toast.show();*/



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, error -> Toast.makeText(getApplicationContext(),"loii",Toast.LENGTH_SHORT).show());

        requestQueue.add(jsonArrayRequest);
    }

 /*   private void EventUI() {
        long tongtien =0;
        for (int i = 0;i< MainActivity.arrayCart.size();i++ ){
            tongtien += MainActivity.arrayCart.get(i).getPrice_product();

        }
        txtTong.setText(tongtien + "Đ");
    }*/

  /*  private void checkData() {
        if (MainActivity.arrayCart.size() <= 0 ){
            CartAdapter.notifyDataSetChanged();
            txtTB.setVisibility(View.VISIBLE);
            lvCart.setVisibility(View.INVISIBLE);
        }else{
            CartAdapter.notifyDataSetChanged();
            txtTB.setVisibility(View.INVISIBLE);
            lvCart.setVisibility(View.VISIBLE);

        }
    }*/



    private void maping() {



        btnThanhtoan = findViewById(R.id.btThanhtoan);


        arraycart = new ArrayList<>();

        CartAdapter = new cartAdapter(getApplicationContext(),arraycart);

        recyclerCart = (RecyclerView) findViewById(R.id.recycler_cart);
        recyclerCart.setHasFixedSize(true);
        recyclerCart.setLayoutManager(new LinearLayoutManager(getApplicationContext()) );
        recyclerCart.setAdapter(CartAdapter);
    }

    public void delete(String cart_id){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server.delete, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("dc")){
                    Toast.makeText(Cus_cart.this,"delete success !!",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String , String > paragams = new HashMap<>();
                paragams.put("cart_id",cart_id);
                return paragams;
            }
        };
        requestQueue.add(stringRequest);

    }
}