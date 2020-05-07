package com.example.myapplication.ShoppingCart;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.MainServiceVendorOnlineAdapter;
import com.example.myapplication.Adapters.ShoppingCartGroupAdapter;
import com.example.myapplication.Adapters.ShoppingCartItemAdapter;
import com.example.myapplication.Api.ApiClient;
import com.example.myapplication.Api.ApiInterface;
import com.example.myapplication.Api.SharedPreferencesStore;
import com.example.myapplication.Model.ShoppingCartGroup;
import com.example.myapplication.Model.VendorMainOnline;
import com.example.myapplication.Model.VendorProduct;
import com.example.myapplication.Payment.ActivityPayment;
import com.example.myapplication.R;
import com.example.myapplication.Support.Support;
import com.example.myapplication.activity_chilyo_main;
import com.example.myapplication.activity_chilyo_rating;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityShoppingCart extends AppCompatActivity {

    Intent intentSettings;
    ImageView activity_chilyo_back;
    ImageView shopping_item_remove;
    ShoppingCartGroupAdapter groupAdapter;

    RecyclerView rv_shopping_cart_group;
    String id_user;

    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart);
        getSupportActionBar().hide();

        elementInit();

        /** mengambil id user **/
        id_user = SharedPreferencesStore.getAuthUserId(getBaseContext());
        layoutManager = new LinearLayoutManager(ActivityShoppingCart.this);
        rv_shopping_cart_group = findViewById(R.id.shopping_cart_group);
        getJson(id_user);
    }

    private void elementInit(){

        /** kembali ke menu utama **/
        activity_chilyo_back = findViewById(R.id.activity_chilyo_back);
        activity_chilyo_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSettings = new Intent(ActivityShoppingCart.this, activity_chilyo_main.class);
                startActivity(intentSettings);
            }
        });

        Button checkOut_btn = findViewById(R.id.checkOut_btn);
        checkOut_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSettings = new Intent(ActivityShoppingCart.this, ActivityPayment.class);
                startActivity(intentSettings);
            }
        });

    }

    private void getJson(String id_user){
        ApiInterface groupShopping = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<ShoppingCartGroup>> call = groupShopping.getAllShoppingCart(id_user);
        call.enqueue(new Callback<List<ShoppingCartGroup>>() {
            @Override
            public void onResponse(Call<List<ShoppingCartGroup>> call, Response<List<ShoppingCartGroup>> response) {
                generateShoppingGroup(response.body());
            }

            @Override
            public void onFailure(Call<List<ShoppingCartGroup>> call, Throwable t) {

            }
        });
    }

    private void generateShoppingGroup(List<ShoppingCartGroup> shoppingCartGroups) {
        groupAdapter = new ShoppingCartGroupAdapter(this, shoppingCartGroups);
        rv_shopping_cart_group.setLayoutManager(layoutManager);
        rv_shopping_cart_group.setAdapter(groupAdapter);
    }

    public static void refreshData(){

    }


}
