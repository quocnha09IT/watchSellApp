package com.google.dongho;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class daydongho extends AppCompatActivity {


    int iddh =0;
    RecyclerView recyclerproduct;
    ArrayList<product> arrayProduct;
    DonghoAdapter donghoAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daydongho);


        maxping();
        GetIdDongho();
        /* ActionToolbar();*/
        if (checkConnect.haveNetworkConnection(getApplicationContext())) {
            getDataDH();
        } else {
            checkConnect.ShowToast_Short(getApplicationContext(), "check connection!!!!");
        }



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_cart:
                Intent intent = new Intent(getApplicationContext(),Cus_cart.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void getDataDH() {



        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.getdayDH, response -> {
            if (response != null){

                String product_name = "";
                String product_price = "";
                String product_image = "";
                String id_product = "";
                String content_product = "";
                String desc_product = "";

                for (int i = 0;i<response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);



                        product_image = jsonObject.getString("Image");
                        product_name = jsonObject.getString("Name");
                        id_product = jsonObject.getString("Id");
                        product_price = jsonObject.getString("Price");
                        content_product = jsonObject.getString("Content");
                        desc_product = jsonObject.getString("Desc");
                    /*    Toast toast = Toast.makeText(getApplicationContext(),id_category,Toast.LENGTH_SHORT);
                        toast.show();*/


                        arrayProduct.add(new product(product_image, product_name, id_product, product_price,content_product,desc_product));
                        donghoAdapter.notifyDataSetChanged();




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        }, error -> {

        });

        requestQueue.add(jsonArrayRequest);

    }

    /*  private void ActionToolbar() {

     *//*setSupportActionBar(toolbarDH);*//*
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarDH.setNavigationOnClickListener(view -> {
            finish();
        });

    }*/

    private void GetIdDongho() {
        iddh = getIntent().getIntExtra("idproduct",-1);

    }

    private void maxping() {
        arrayProduct = new ArrayList<>();
        donghoAdapter = new DonghoAdapter(getApplicationContext(),arrayProduct);
        recyclerproduct = (RecyclerView) findViewById(R.id.recyclerDH);
        recyclerproduct.setHasFixedSize(true);
        recyclerproduct.setLayoutManager(new LinearLayoutManager(getApplicationContext()) );
        recyclerproduct.setAdapter(donghoAdapter);

    }
}