package com.example.glebmillenium.mobile_client;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class AddGood extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_good);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void onCancelClick(View v)
    {
        Intent intent = new Intent(this, Goods.class);
        startActivity(intent);
    }

    public void onOkClick(View v)
    {
        DBConnector mDbHelper = new DBConnector(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        EditText editText = (EditText)findViewById(R.id.nameGood);
        DBConnector.insertInGoods(db, editText.getText().toString());
        Intent intent = new Intent(this, Goods.class);
        startActivity(intent);
    }
}
