package com.example.glebmillenium.mobile_client;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ItemTable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_table);
    }

    public void onAddedNewGoodButtonClick(View v)
    {
        Intent intent = new Intent(this, AddGood.class);
        startActivity(intent);
    }

    public void onDeleteGoodButtonClick(View view) {
        TextView tv = (TextView)findViewById(R.id.id);
        DBConnector mDbHelper = new DBConnector(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        DBConnector.deleteFromGoods(db, tv.getText().toString());
    }
}
