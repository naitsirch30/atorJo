package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.myapplication.Payment.ActivityPayment;
import com.example.myapplication.ShoppingCart.ActivityShoppingCart;

public class activity_chilyo_topup extends AppCompatActivity {

    Intent intentSettings;
    ImageView activity_chilyo_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chilyo_topup);

        elementInit();

        /** kembali ke menu utama **/
        activity_chilyo_back = findViewById(R.id.activity_chilyo_back);
        activity_chilyo_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSettings = new Intent(activity_chilyo_topup.this, ActivityPayment.class);
                startActivity(intentSettings);
            }
        });
    }

    private void elementInit() {
    }
}
