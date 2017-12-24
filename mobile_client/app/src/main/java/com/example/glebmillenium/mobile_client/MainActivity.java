package com.example.glebmillenium.mobile_client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onGoodsButtonClick(View v)
    {
        Intent intent = new Intent(this, Goods.class);
        startActivity(intent);
    }

    public void onSettingsButtonClick(View v)
    {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void onStockButtonClick(View v)
    {
        Intent intent = new Intent(this, Stocks.class);
        startActivity(intent);
    }
}
