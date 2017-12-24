package com.example.glebmillenium.mobile_client;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddStock extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock);
        DBConnector mDbHelper = new DBConnector(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        ArrayList<Pair<String, String>> result = DBConnector.selectFromGoods(db);

        Spinner chooseStock;
        chooseStock =  (Spinner)findViewById(R.id.chooseStock);

        List<String> list = new ArrayList<String>();
        for(int i = 0; i < result.size(); i++){
            list.add(result.get(i).second);
        }

        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter (this,
                android.R.layout.simple_spinner_item, list);
        chooseStock.setAdapter(spinnerArrayAdapter);
    }

    public void CancelClickStock(View v)
    {
        Intent intent = new Intent(this, Stocks.class);
        startActivity(intent);
    }

    public void OkClickStock(View v)
    {
        Spinner spinner = (Spinner) findViewById(R.id.chooseStock);
        String selectedStock = spinner.getSelectedItem().toString();

        TextView textview = (TextView) findViewById(R.id.countStock);
        String selectedCount = textview.getText().toString();

        DBConnector mDbHelper = new DBConnector(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        DBConnector.insertInStocks(db, selectedStock, selectedCount);

        Intent intent = new Intent(this, Stocks.class);
        startActivity(intent);
    }
}
