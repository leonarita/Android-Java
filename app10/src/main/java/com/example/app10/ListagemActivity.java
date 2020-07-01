package com.example.app10;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.app10.helpers.DBHelper;

public class ListagemActivity extends AppCompatActivity {

    protected Cursor mCursor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);
    }

    @Override
    protected void onResume() {
        super.onResume();

        DBHelper db = new DBHelper(getBaseContext());
        SQLiteDatabase banco = db.getWritableDatabase();

        mCursor = banco.rawQuery("SELECT _id, nome, email FROM clientes", null);

        String[] from = { "nome", "email" };
        int[] to = { R.id.txvNomeCliente, R.id.txvEmailCliente };

        MeuAdapter adapter = new MeuAdapter(getBaseContext(), R.layout.lista_clientes, mCursor, from, to, 0);
        ListView ltvClientes = (ListView) findViewById(R.id.ltvClientes);
        ltvClientes.setAdapter(adapter);

        ltvClientes.setOnItemClickListener((adapterView, view, position, id) -> {
            Cursor cursor = (Cursor) adapterView.getAdapter().getItem(position);
            Intent intent = new Intent(getBaseContext(), ListagemActivity.class);
            intent.putExtra("ID", id);
            startActivity(intent);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        mCursor.close();
    }

    public class MeuAdapter extends SimpleCursorAdapter {

        public MeuAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
            super(context, layout, c, from, to, flags);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            super.bindView(view, context, cursor);

            TextView txvEmail = (TextView) view.findViewById(R.id.txvEmailCliente);

            if (cursor.getLong(0) < 8)
                txvEmail.setTextColor(Color.RED);
            else
                txvEmail.setTextColor(Color.BLUE);
        }
    }
}
