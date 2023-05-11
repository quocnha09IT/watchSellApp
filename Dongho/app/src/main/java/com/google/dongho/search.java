package com.google.dongho;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.HashMap;
import java.util.Map;


public class search extends AppCompatActivity {

    Button btn_search;
    EditText search;
    TextView show_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);



        btn_search = findViewById(R.id.btn_search);
        search = findViewById(R.id.search);
        show_search = findViewById(R.id.show_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search_product();
            }
        });


    }
    private void search_product() {

        RequestQueue requestQueue = Volley.newRequestQueue(search.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server.search, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               if (response.trim() != "") {
                   show_search.setText(response.trim());

                  /* Toast toast = Toast.makeText(getApplicationContext(),response.trim(),Toast.LENGTH_SHORT);
                   toast.show();*/

         /*    Toast.makeText(getApplicationContext(),response.trim(),Toast.LENGTH_SHORT).show();
                            String product_name = "";
                            String product_price = "";
                            String product_image = "";


                            for (int i = 0;i<response.length();i++){

                                product_image = response.trim();
                                product_name = response.trim();
                                product_price = response.trim();

                       Toast toast = Toast.makeText(getApplicationContext(),product_name,Toast.LENGTH_SHORT);
                        toast.show();


                                arraySearch.add(new search_jv(product_image, product_name, product_price));
                                Search_adapter.notifyDataSetChanged();


                            }*/

                }else{
                    Toast.makeText(search.this,"khong co san pham nhu vay!!!", Toast.LENGTH_SHORT).show();
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



                params.put("product_name" ,search.getText().toString().trim());

                return params;

            }
        };
        requestQueue.add(stringRequest);

    }


    private void get_search() {

   String url ="http://192.168.1.10/php/search.php";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, response -> {
            if (response != null){

                String product_name = "";
                String product_price = "";
                String product_image = "";


                for (int i = 0;i<response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        product_image = jsonObject.getString("Image");
                        product_name = jsonObject.getString("Name");
                        product_price = jsonObject.getString("Price");

                       /*Toast toast = Toast.makeText(getApplicationContext(),product_name,Toast.LENGTH_SHORT);
                        toast.show();
*/





                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        }, error -> {

        });

        requestQueue.add(jsonArrayRequest);
    }
}