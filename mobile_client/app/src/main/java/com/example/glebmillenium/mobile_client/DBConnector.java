package com.example.glebmillenium.mobile_client;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by glebmillenium on 20.12.17.
 */

/**
 * DBConnector - класс, осуществляющий передачу данных
 */
class DBConnector extends SQLiteOpenHelper {
    Context mContext;

    public DBConnector(Context context, int dbVer){
        super(context, "accounting.db", null, dbVer);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //проверяете какая версия сейчас и делаете апдейт
        String query = "CREATE TABLE stocks (id_stock INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "id_goods INTEGER, " +
                "count INTEGER); ";
        db.execSQL(query);

        query = "INSERT INTO stocks (id_stock, name, id_goods, count) " +
                "       VALUES (1, 'test', 1, 1); ";
        db.execSQL(query);

        query = "CREATE TABLE goods (id_goods INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT); ";
        db.execSQL(query);

        query = "INSERT INTO goods (id_goods, name) " +
                "       VALUES (1, 'test'); ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tableName");
        onCreate(db);
    }

    public void insertFromStocks(String name, String id_goods, String count)
    {

    }

    public void selectFromStocks()
    {
        String query = "SELECT name FROM stocks;";
        db.execSQL(query);
    }

    public void insertFromGoods(String name, String id_goods, String count)
    {

    }

    public void selectFromGoods()
    {

    }
}