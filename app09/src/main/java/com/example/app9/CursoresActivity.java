package com.example.app9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.example.app9.helpers.DBHelper;

public class CursoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursores);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("SegundaActivity", "onResume");

        DBHelper db = new DBHelper(getBaseContext());

        //Permite escrever no banco de dados
        SQLiteDatabase banco = db.getWritableDatabase();

        criarRegistros();
        selecionarRegistros();
        atualizarRegistros();
        selecionarRegistros();
        deletararRegistros();
        selecionarRegistros();
    }

    public void criarRegistros()
    {
        DBHelper db = new DBHelper(getBaseContext());
        SQLiteDatabase banco = db.getWritableDatabase();

        ContentValues ctv;

        for (int i=0; i<21; i++)
        {
            ctv = new ContentValues();

            ctv.put("nome", "Cliente " + Integer.toString(i));
            ctv.put("email", "email" + Integer.toString(i) + "@gmail.com");

            banco.insert("clientes", null, ctv);
        }
    }

    public void selecionarRegistros()
    {
        DBHelper db = new DBHelper(getBaseContext());
        SQLiteDatabase banco = db.getWritableDatabase();

        //select                                    0     1    2    (select)
        Cursor cursor = banco.rawQuery("SELECT _id, nome, email FROM clientes", null);

        if(cursor.moveToFirst())
        {
            //Há registros

            do {
                Log.d("Cursor: ", cursor.getString(1));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
    }

    public void atualizarRegistros()
    {
        DBHelper db = new DBHelper(getBaseContext());
        SQLiteDatabase banco = db.getWritableDatabase();
        ContentValues ctv;

        Cursor cursor = banco.rawQuery("SELECT _id, nome, email FROM clientes", null);

        if(cursor.moveToFirst())
        {
            do {
                ctv = new ContentValues();
                ctv.put("nome", cursor.getString(cursor.getColumnIndex("nome")) + "ALTERADO");

                banco.update("clientes", ctv, "_id = " + cursor.getString(cursor.getColumnIndex("_id")), null);

                Log.d("Cursor: ", cursor.getString(cursor.getColumnIndex("nome")));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
    }

    public void deletararRegistros()
    {
        DBHelper db = new DBHelper(getBaseContext());
        SQLiteDatabase banco = db.getWritableDatabase();
        ContentValues ctv;

        Cursor cursor = banco.rawQuery("SELECT _id, nome, email FROM clientes", null);

        if(cursor.moveToFirst())
        {
            do {
                banco.delete("clientes", "_id = " + cursor.getString(0), null);

                Log.d("Cursor: ", cursor.getString(cursor.getColumnIndex("nome")));
            }
            while (cursor.moveToNext());
        }
        else
        {
            Log.d("Cursor: ", "sem registros");
        }
        cursor.close();
    }
}
