package com.example.glebmillenium.mobile_client;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by glebmillenium on 20.12.17.
 */

/**
 * DBConnector - класс, осуществляющий передачу данных
 */
class DBConnector extends SQLiteOpenHelper {
    Context mContext;

    public DBConnector(Context context){
        super(context, "accounting.db", null, 1);
        mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //проверяете какая версия сейчас и делаете апдейт
        String query = "CREATE TABLE stocks (id_stocks INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_goods INTEGER, " +
                "count INTEGER); ";
        db.execSQL(query);

        query = "INSERT INTO stocks (id_stocks, id_goods, count) " +
                "       VALUES (1, 1, 1); ";
        db.execSQL(query);

        query = "INSERT INTO stocks (id_stocks, id_goods, count) " +
                "       VALUES (2, 2, 2); ";
        db.execSQL(query);

        query = "CREATE TABLE goods (id_goods INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT); ";
        db.execSQL(query);

        query = "INSERT INTO goods (id_goods, name) " +
                "       VALUES (1, 'Яблоко'); ";
        db.execSQL(query);

        query = "INSERT INTO goods (id_goods, name) " +
                "       VALUES (2, 'Мандарин'); ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tableName");
        onCreate(db);
    }

    public static void insertInStocks(String name, String id_goods, String count)
    {

    }

    public static void insertInGoods(SQLiteDatabase db, String name)
    {
        String query = "INSERT INTO goods (name) " +
                "       VALUES ('" + name + "'); ";
        db.execSQL(query);
    }

    public static void deleteFromGoods(SQLiteDatabase db, String id)
    {
        String query = "DELETE FROM goods WHERE id='" + id + "'); ";
        db.execSQL(query);
    }

    public static ArrayList<Pair<String, String>> selectFromStocks(SQLiteDatabase db)
    {
        String[] projection = {
                "id_goods", "count"
        };

        Cursor cursor = db.query(
                "stocks",                     // The table to query
                projection,
                null,
                null,
                null,
                null,
                null);
        ArrayList result = new ArrayList<Pair<String, String>>();
        while(cursor.moveToNext()) {
            String id_goods = cursor.getString(cursor.getColumnIndexOrThrow("id_goods"));
            String goods = DBConnector.selectFromGoodsName(db, id_goods);
            String count = cursor.getString(cursor.getColumnIndexOrThrow("count"));
            result.add(new Pair<String, String>(goods, count));
        }
        cursor.close();
        return result;
    }

    public static String selectFromGoodsName(SQLiteDatabase db, String id_goods)
    {
        String[] projection = {
                "id_goods", "name"
        };
        String whereClause = "id_goods = ?";
        String[] whereArgs = new String[] {
                id_goods
        };
        Cursor cursor = db.query(
                "goods",                     // The table to query
                projection,
                whereClause,
                whereArgs,
                null,
                null,
                null);
        String result = "";
        if(cursor.moveToNext()) {
            result = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        }
        cursor.close();
        return result;
    }

    public static String selectFromGoodsId(SQLiteDatabase db, String name)
    {
        String[] projection = {
                "id_goods", "name"
        };
        String whereClause = "name = ?";
        String[] whereArgs = new String[] {
                name
        };
        Cursor cursor = db.query(
                "goods",                     // The table to query
                projection,
                whereClause,
                whereArgs,
                null,
                null,
                null);
        String result = "";
        if(cursor.moveToNext()) {
            result = cursor.getString(cursor.getColumnIndexOrThrow("id_goods"));
        }
        cursor.close();
        return result;
    }

    public static void insertInStocks(SQLiteDatabase db, String name, String count)
    {
        String id_goods = DBConnector.selectFromGoodsId(db, name);
        String query = "INSERT INTO stocks (id_goods, count) " +
                "       VALUES ('" + id_goods + "', '"  + count + "'); ";
        db.execSQL(query);
    }

    public static ArrayList<Pair<String, String>> selectFromGoods(SQLiteDatabase db)
    {
        String[] projection = {
                "id_goods", "name"
        };

        Cursor cursor = db.query(
                "goods",                     // The table to query
                projection,
                null,
                null,
                null,
                null,
                null);
        ArrayList result = new ArrayList<Pair<String, String>>();
        while(cursor.moveToNext()) {
            String id_goods = cursor.getString(cursor.getColumnIndexOrThrow("id_goods"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            result.add(new Pair<String, String>(id_goods, name));
        }
        cursor.close();
        return result;
    }

    public void insertFromGoods(String name, String id_goods, String count)
    {

    }

    public void selectFromGoods()
    {

    }
}