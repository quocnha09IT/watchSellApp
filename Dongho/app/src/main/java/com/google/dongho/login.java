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

public class login extends AppCompatActivity {
    EditText email1, password1;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email1 = findViewById(R.id.email);
        password1 = findViewById(R.id.password);
        login = findViewById(R.id.login);


        /*http://192.168.1.10/test/login.php*/


        Button tv = findViewById(R.id.intent_register);
        tv.setOnClickListener(view -> {
            Intent intent1 = new Intent(login.this, register.class);
            startActivity(intent1);

        });

        login.setOnClickListener(view -> {
            String email = email1.getText().toString().trim();
            String password = password1.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(login.this, " ko dc de trong", Toast.LENGTH_SHORT).show();
            } else {
              /*  Toast toast = Toast.makeText(getApplicationContext(), password_customer1.getText().toString().trim() , Toast.LENGTH_SHORT);
                toast.show();*/
                Register();
            }
        });

    }

    private void Register() {

        RequestQueue requestQueue = Volley.newRequestQueue(login.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server.login, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                if (response.trim().equals("ko")) {
                    Toast.makeText(login.this, "Tai khoan khong chinh xac!!", Toast.LENGTH_SHORT).show();


                } else {
                   String id =  response.trim();
                    Toast.makeText(login.this, "Login susscces !!!", Toast.LENGTH_SHORT).show();
                   /* startActivity(new Intent(login.this, MainActivity.class));*/
                    Intent intent = new Intent(login.this, MainActivity.class);
                    intent.putExtra("id_customer", id);
                    startActivity(intent);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("AAA", " loi\n" + error.toString());

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();


                params.put("email", email1.getText().toString().trim());
                params.put("password", password1.getText().toString().trim());


                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}