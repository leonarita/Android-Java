package com.example.app10.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "meuapp.db";
    private static final int VERSAO_BANCO = 6;

    public DBHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlClientes = "CREATE TABLE IF NOT EXISTS clientes (" +
                "_id  INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome VARCHAR(255)," +
                "email VARCHAR(255)" +
                ");";

        db.execSQL(sqlClientes);

        this.onUpgrade(db, 1, VERSAO_BANCO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.d("DBHelper", "onUpgrade");
    }
}
