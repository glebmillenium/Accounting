package com.example.glebmillenium.mobile_client;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class Settings extends AppCompatActivity {
    public static final String APP_PREFERENCES = "settings";
    public static final String APP_PREFERENCES_IP_ADDRESS = "ip_address";
    public static final String APP_PREFERENCES_PORT = "port";
    private SharedPreferences mSettings;
    private EditText editTextIpServer;
    private EditText editTextPortServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        editTextIpServer = (EditText)findViewById(R.id.ipServer);
        editTextPortServer = (EditText)findViewById(R.id.portServer);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String ip = mSettings.getString(APP_PREFERENCES_IP_ADDRESS, "127.0.0.1");
        String port = mSettings.getString(APP_PREFERENCES_PORT, "6500");
        editTextIpServer.setText(ip);
        editTextPortServer.setText(port);
    }

    public void onAppliedButtonClick(View v)
    {
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_IP_ADDRESS, editTextIpServer.getText().toString());
        editor.putString(APP_PREFERENCES_PORT, editTextPortServer.getText().toString());
        editor.apply();
        finish();
    }

    public void onCloseButtonClick(View v)
    {
        finish();
    }
}
