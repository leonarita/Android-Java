package com.example.app9.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "meuapp.db";
    private static final int VERSAO_BANCO = 6;

    public DBHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);

        Log.d("DBHelper", "Constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("DBHelper", "onCreate");

        String sqlConfig = "CREATE TABLE IF NOT EXISTS configuracoes (" +
                "_id  INTEGER PRIMARY KEY," +
                "nome VARCHAR(255)," +
                "email VARCHAR(255)" +
                ");";

        db.execSQL(sqlConfig);

        this.onUpgrade(db, 1, VERSAO_BANCO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.d("DBHelper", "onUpgrade");

        switch (oldVersion)
        {
            //Sempre usar a versão anterior como base, sem breaks nos cases
            case 1:
                Log.d("DBHelper", "atualização versão 2");

            case 2:
                Log.d("DBHelper", "atualização versão 3");

            case 3:
                Log.d("DBHelper", "atualização versão 4");

            case 4:
                Log.d("DBHelper", "atualização versão 5");

            case 5:
                String sqlClientes = "CREATE TABLE IF NOT EXISTS clientes (" +
                        "_id  INTEGER PRIMARY KEY," +
                        "nome VARCHAR(255)," +
                        "email VARCHAR(255)" +
                        ");";

                db.execSQL(sqlClientes);
        }
    }
}
