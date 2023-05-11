package com.google.dongho;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class register extends AppCompatActivity {




    Button register;
    EditText name_customer1,password_customer1,email_customer1,phone_customer1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        register = (Button) findViewById(R.id.register);
        name_customer1 = (EditText) findViewById(R.id.name_customer);
        password_customer1 = (EditText)  findViewById(R.id.password_customer);
        email_customer1 = (EditText)  findViewById(R.id.email_customer1);
        phone_customer1 = (EditText)  findViewById(R.id.phone_customer);


        register.setOnClickListener(view -> {
            String name_customer2 = name_customer1.getText().toString().trim();
            String password_customer2 = password_customer1.getText().toString().trim();
            String email_customer2 = email_customer1.getText().toString().trim();
            String phone_customer2 = phone_customer1.getText().toString().trim();
            if (name_customer2.isEmpty() || password_customer2.isEmpty() || email_customer2.isEmpty() || phone_customer2.isEmpty()){
                Toast.makeText(register.this," ko dc de trong",Toast.LENGTH_SHORT).show();
            }else {
              /*  Toast toast = Toast.makeText(getApplicationContext(), password_customer1.getText().toString().trim() , Toast.LENGTH_SHORT);
                toast.show();*/
                Register();
            }
        });

    }



  private void Register() {

      RequestQueue requestQueue = Volley.newRequestQueue(register.this);
      StringRequest stringRequest = new StringRequest(Request.Method.POST, server.register, new Response.Listener<String>() {

          @Override
          public void onResponse(String response) {
             if (response.trim().equals("ddc")) {

                  Toast.makeText(register.this, "them thanh cong,", Toast.LENGTH_SHORT).show();
                 startActivity(new Intent(register.this, login.class));

              }else{
                  Toast.makeText(register.this,"looix theem", Toast.LENGTH_SHORT).show();
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

              params.put("name" , name_customer1.getText().toString().trim());
              params.put("email" , email_customer1.getText().toString().trim());
              params.put("password" , password_customer1.getText().toString().trim());
              params.put("phone" , phone_customer1.getText().toString().trim());



              return params;
          }
      };

      requestQueue.add(stringRequest);

    /*  StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
          if (response.trim().equals("ddc")) {

              Toast.makeText(register.this, "them thanh cong,", Toast.LENGTH_SHORT).show();
              startActivity(new Intent(register.this, login.class));

          }else{
              Toast.makeText(register.this,"looix theem", Toast.LENGTH_SHORT).show();
          }

      }, error -> {

          Log.d("AAA", " loi\n" + error.toString());

      }) {



          @Override
          protected Map<String, String> getParams() {
              Map<String, String> params = new HashMap<>();

              params.put("name" , name_customer1.getText().toString().trim());
              params.put("email" , email_customer1.getText().toString().trim());
              params.put("password" , password_customer1.getText().toString().trim());
              params.put("phone" , phone_customer1.getText().toString().trim());


              return params;
          }
      };
      requestQueue.add(stringRequest);*/


    }

}
