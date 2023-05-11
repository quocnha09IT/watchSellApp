package com.google.dongho;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;

    ListView  listview_category;

    DrawerLayout drawerLayout;
    ArrayList<category> arrayCategory;
    category_adapter category_adapter1;
    String NameCategory = "";
    String DescCategory = "";
    String ImageCategory = "";

    RecyclerView recyclerproduct;
    ArrayList<product> arrayProduct;
    product_adapter Product_adapter;



    EditText search;
    Button btn_search;


    public static String id_customer96;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Intent intent = getIntent();
        id_customer96 = intent.getStringExtra("id_customer");





        maxping();
        if (checkConnect.haveNetworkConnection(getApplicationContext())) {
            Actionbar();

            GetData_category();
            GetData_product();
            CathOnitemListview();

        } else {
            checkConnect.ShowToast_Short(getApplicationContext(), "check connection!!!!");
        }
        ActionbarViewFliper();



        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent1 = new Intent(MainActivity.this, search.class);
              startActivity(intent1);
            }
        });


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

    private void CathOnitemListview() {

        listview_category.setOnItemClickListener((adapterView, view, i, l) -> {
            switch (i){
                case 0:
                    if(checkConnect.haveNetworkConnection(getApplicationContext())){
                        Intent intent = new Intent(MainActivity.this,login.class);
                        intent.putExtra("idproduct",arrayCategory.get(i).getId_category());
                        startActivity(intent);
                    }else{
                        checkConnect.ShowToast_Short(getApplicationContext(),"No connect!!!");
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;

                case 1:
                    if(checkConnect.haveNetworkConnection(getApplicationContext())){
                        Intent intent = new Intent(MainActivity.this,Cus_cart   .class);
                        startActivity(intent);
                    }else{
                        checkConnect.ShowToast_Short(getApplicationContext(),"No connect!!!");
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case 2:
                    if(checkConnect.haveNetworkConnection(getApplicationContext())){
                        Intent intent = new Intent(MainActivity.this,MainActivity.class);
                      intent.putExtra("idproduct",arrayCategory.get(i).getId_category());
                        startActivity(intent);
                    }else{
                        checkConnect.ShowToast_Short(getApplicationContext(),"No connect!!!");
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case 3:
                    if(checkConnect.haveNetworkConnection(getApplicationContext())){
                        Intent intent = new Intent(MainActivity.this,hopdongho.class);
                intent.putExtra("idproduct",arrayCategory.get(i).getId_category());
                        startActivity(intent);
                    }else{
                        checkConnect.ShowToast_Short(getApplicationContext(),"No connect!!!");
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case 4:
                    if(checkConnect.haveNetworkConnection(getApplicationContext())){
                        Intent intent = new Intent(MainActivity.this,daydongho.class);
                    intent.putExtra("idproduct",arrayCategory.get(i).getId_category());
                        startActivity(intent);
                    }else{
                        checkConnect.ShowToast_Short(getApplicationContext(),"No connect!!!");
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;

                case 5:
                    if(checkConnect.haveNetworkConnection(getApplicationContext())){
                        Intent intent = new Intent(MainActivity.this,dongho.class);
                        intent.putExtra("idproduct",arrayCategory.get(i).getId_category());
                        startActivity(intent);
                    }else{
                        checkConnect.ShowToast_Short(getApplicationContext(),"No connect!!!");
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;





            }
        });
    }

    private void GetData_product() {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.product_link, response -> {
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


                        arrayProduct.add(new product(product_image,product_name,id_product,product_price,content_product,desc_product));
                        Product_adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        }, error -> {

        });

        requestQueue.add(jsonArrayRequest);
    }



    private void GetData_category() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.category_link, response -> {
            if (response != null) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        NameCategory = jsonObject.getString("NameCategory");
                        DescCategory = jsonObject.getString("IdCategory");

                        ImageCategory = jsonObject.getString("ImageCategory");
                        arrayCategory.add(new category(NameCategory, DescCategory, ImageCategory));
                        category_adapter1.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


            }

        }, error -> {
            checkConnect.ShowToast_Short(getApplicationContext(), error.toString());

        }
        );
        requestQueue.add(jsonArrayRequest);

    }




    private void ActionbarViewFliper() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("https://fptshop.com.vn/uploads/originals/2022/4/22/637862402957703328_fptshop-32.png");
        arrayList.add("https://donghothuysy.vn/upload_images/images/dong-ho-hublot-classic-fusion.jpg");
        arrayList.add("https://cdn.shopify.com/s/files/1/0195/2352/files/Rolex_datejsut_grande.png?v=1578088154");

        for (int i = 0; i < arrayList.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());

            Picasso.get().load(arrayList.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }


    private void Actionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
        toolbar.setNavigationOnClickListener(view -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });

    }


    private void maxping() {
        toolbar = findViewById(R.id.toolbartrangchinh1);
        viewFlipper = findViewById(R.id.viewlipper1);
        navigationView = findViewById(R.id.navi1);
        drawerLayout = findViewById(R.id.drawerble_layout);
        arrayCategory = new ArrayList<>();


        arrayCategory.add(0,new category("Trang Chủ","Trang chinh","https://previews.123rf.com/images/sanek13744/sanek137441607/sanek13744160700281/59665196-orange-home-icon-isolated-on-white-background.jpg"));
        arrayCategory.add(0,new category("Giỏ hàng ","Lien he","https://png.pngtree.com/png-clipart/20200701/original/pngtree-contact-our-customer-service-png-image_5402173.jpg"));
        arrayCategory.add(0,new category("Đăng nhập ","Lien he","https://banner2.cleanpng.com/20181110/srt/kisspng-computer-icons-login-scalable-vector-graphics-emai-5be7376911c6b4.4735764415418796570728.jpg"));

        category_adapter1 = new category_adapter(arrayCategory, getApplicationContext());
        listview_category = findViewById(R.id.listview_category1);
        listview_category.setAdapter(category_adapter1);

        arrayProduct = new ArrayList<>();
        Product_adapter = new product_adapter(getApplicationContext(),arrayProduct);
        recyclerproduct = (RecyclerView) findViewById(R.id.recyclerProduct);
        recyclerproduct.setHasFixedSize(true);
        recyclerproduct.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerproduct.setAdapter(Product_adapter);

        btn_search = findViewById(R.id.btn_search);
        search = findViewById(R.id.search);



    }}

  /*  private void CatchOnItemListView() {
        lvProducts.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(MainActivity.this, detail_product.class);
            TextView products_id = (TextView) findViewById(R.id.product_id);
            String product_id = (String) products_id.getText();
            intent.putExtra("products_id", product_id);

            startActivity(intent);
        });
    }

}

/*
    private void GetData(String url) {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            arrayProduct.add(new product(
                                    object.getString("Name"),
                                    object.getString("Id"),
                                    object.getString("Price")



                            ));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                  adapter.notifyDataSetChanged();

                }, error -> Toast.makeText(MainActivity.this, "Loi!!!!", Toast.LENGTH_SHORT).show()
        );
        requestQueue.add(jsonArrayRequest);
    }
}*/
