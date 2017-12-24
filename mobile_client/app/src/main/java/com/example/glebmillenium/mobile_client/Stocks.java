package com.example.glebmillenium.mobile_client;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Stocks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        DBConnector mDbHelper = new DBConnector(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        ArrayList<Pair<String, String>> result = DBConnector.selectFromStocks(db);

        ListView messages_list;
        messages_list =  (ListView)findViewById(R.id.gridViewStocks);

        String[] from = new String[] {"id", "col_1"};
        int[] to = new int[] { R.id.id, R.id.name};

        List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();

        for(int i = 0; i < result.size(); i++){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id", "" + result.get(i).first);
            map.put("col_1", "" + result.get(i).second);
            fillMaps.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, fillMaps, R.layout.activity_item_table, from, to);
        messages_list.setAdapter(adapter);
    }

    public void onAddedNewStocksButtonClick(View view) {
        Intent intent = new Intent(this, AddStock.class);
        startActivity(intent);
    }

    public void goToMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
