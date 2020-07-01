package com.example.app13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListarActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar);
    }

    public void onResume(){
        super.onResume();

        SQLiteDatabase db = openOrCreateDatabase("clientes.db", Context.MODE_PRIVATE, null);

        // Tabela de clientes
        StringBuilder sqlClientes = new StringBuilder();
        sqlClientes.append("CREATE TABLE IF NOT EXISTS clientes (");
        sqlClientes.append("_id INTEGER PRIMARY KEY, ");
        sqlClientes.append("nome VARCHAR(30), ");
        sqlClientes.append("email VARCHAR(30));");
        db.execSQL(sqlClientes.toString());

        Cursor cursor = db.rawQuery("SELECT * FROM clientes", null);

        String[] from = {"_id", "nome"};
        int[] to = {R.id.txvID, R.id.txvNome};

        android.widget.SimpleCursorAdapter ad = new android.widget.SimpleCursorAdapter(getBaseContext(),
                R.layout.listar_model, cursor, from, to);

        ListView ltwDados = (ListView)findViewById(R.id.ltwDados);

        ltwDados.setAdapter(ad);


        ltwDados.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapter, View view,
                                    int position, long id) {

                SQLiteCursor c = (SQLiteCursor) adapter.getAdapter().getItem(position);

                Intent it = new Intent(getBaseContext(), Editar.class);
                it.putExtra("id", c.getInt(0));
                startActivity(it);
            }
        });

        db.close();
    }
}
