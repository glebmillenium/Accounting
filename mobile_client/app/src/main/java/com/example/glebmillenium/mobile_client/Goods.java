package com.example.glebmillenium.mobile_client;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.net.Socket;

public class Goods extends AppCompatActivity {

    private ConnectWithRemoteServer connect;
    private SharedPreferences mSettings;
    public static final String APP_PREFERENCES = "settings";
    public static final String APP_PREFERENCES_IP_ADDRESS = "ip_address";
    public static final String APP_PREFERENCES_PORT = "port";
    static String ip;
    static String port;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        DBConnector dbConnector = new DBConnector(this, 1);
    }

    public void onAddedNewGoodButtonClick(View v)
    {

    }
}
