package com.example.glebmillenium.mobile_client;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

public class Goods extends AppCompatActivity {

    private ConnectWithRemoteServer connect;
    private SharedPreferences mSettings;
    public static final String APP_PREFERENCES = "settings";
    public static final String APP_PREFERENCES_IP_ADDRESS = "ip_address";
    public static final String APP_PREFERENCES_PORT = "port";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String ip = mSettings.getString(APP_PREFERENCES_IP_ADDRESS, "127.0.0.1");
        String port = mSettings.getString(APP_PREFERENCES_PORT, "6500");
        TextView tvId = (TextView) findViewById(R.id.textView);
        try {
            connect = new ConnectWithRemoteServer(ip, Integer.parseInt(port));
            connect.run(0);
            tvId.setText("1");
        } catch (IOException e) {
            System.out.println("Ошибка в соединении. " + e.getMessage());
            tvId.setText("0" + e.getMessage());
        }
    }
}
