package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;

import com.example.myapplication.Adapters.MainServiceVendorOnlineAdapter;
import com.example.myapplication.Api.ApiClient;
import com.example.myapplication.Api.ApiInterface;
import com.example.myapplication.Api.SharedPreferencesStore;
import com.example.myapplication.Model.ShoppingCart;
import com.example.myapplication.Model.VendorMainOnline;
import com.example.myapplication.ShoppingCart.ActivityShoppingCart;
import com.example.myapplication.Support.CustomProgressBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_chilyo_main extends AppCompatActivity {

    RecyclerView recyclerView;
    MainServiceVendorOnlineAdapter adapter;
    List<VendorMainOnline> vendorMainOnlineList;
    Intent intentSettings;
    Context context;
    CustomProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chilyo_main);
        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.main_service_vendor_view);

        /** Simulasi login - Hapus ini hen klo smo pake nni punya **/
        SharedPreferencesStore.setAuthUserId("2020001", getBaseContext());
        String data = SharedPreferencesStore.getAuthUserId(getBaseContext());
        Log.d("GetData", data);
        elementInit();
        getJson();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getJson();
    }

    private void elementInit(){

        // inisialisasi progress bar
        progress = new CustomProgressBar();

        // pergi ke halaman shopping cart
        ImageView shopping_cart_button = findViewById(R.id.activity_chilyo_cart);
        shopping_cart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSettings = new Intent(activity_chilyo_main.this, ActivityShoppingCart.class);
                startActivity(intentSettings);
            }
        });

        // go to history (sementara) ke rating
        ImageView rating_button = findViewById(R.id.activity_chilyo_history);
        rating_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSettings = new Intent(activity_chilyo_main.this, activity_chilyo_rating.class);
                startActivity(intentSettings);
            }
        });
    }
    /*Method to generate List of data using RecyclerView with custom adapter*/

    private void getJson(){
        //progress.show(this, "Loading");
        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<VendorMainOnline>> call = service.getOnlineVendors("180");
        call.enqueue(new Callback<List<VendorMainOnline>>() {
            @Override
            public void onResponse(Call<List<VendorMainOnline>> call, Response<List<VendorMainOnline>> response) {
                generateDataList(response.body());
                //progress.getDialog().dismiss();
            }

            @Override
            public void onFailure(Call<List<VendorMainOnline>> call, Throwable t) {
                Log.d("GetData", t.toString());
            }
        });
    }

    private void generateDataList(List<VendorMainOnline> vendorMainOnline) {
        adapter = new MainServiceVendorOnlineAdapter(this, vendorMainOnline);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity_chilyo_main.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}
