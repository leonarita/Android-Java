package com.example.app10;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.app10.helpers.DBHelper;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText nome, email, id;
    DBHelper db;
    SQLiteDatabase banco;
    ContentValues ctv;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(getBaseContext());

        Button listar = (Button) findViewById(R.id.btnListar);

        id = findViewById(R.id.txtId);
        nome = findViewById(R.id.txtNome);
        email = findViewById(R.id.txtEmail);

        result = findViewById(R.id.txtResultado);
        result.setText("VENCEDOR:");

        listar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), ListagemActivity.class));
            }
        });
    }

    public void buscarCliente(View view) {

        if (!id.getText().toString().isEmpty()) {

            db = new DBHelper(getBaseContext());
            banco = db.getReadableDatabase();

            Cursor cursor = banco.rawQuery("SELECT _id, nome, email FROM clientes WHERE _id=" + id.getText().toString(), null);
            cursor.moveToFirst();

            if (!cursor.getString(1).isEmpty()) {
                nome.setText(cursor.getString(1));
                email.setText(cursor.getString(2));
            }

            cursor.close();
        }
    }

    public void inserirCliente(View view) {

        if (verificarCredenciais()) {
            banco = db.getWritableDatabase();

            nome = findViewById(R.id.txtNome);
            email = findViewById(R.id.txtEmail);

            ctv = new ContentValues();

            ctv.put("nome", nome.getText().toString());
            ctv.put("email", email.getText().toString());

            banco.insert("clientes", null, ctv);

            nome.setText("");
            email.setText("");
        }

    }

    public void atualizarCliente(View view) {

        if (verificarCredenciais() && !id.getText().toString().isEmpty()) {

            db = new DBHelper(getBaseContext());
            banco = db.getWritableDatabase();

            ctv = new ContentValues();
            ctv.put("nome", nome.getText().toString());
            ctv.put("email", email.getText().toString());

            id = findViewById(R.id.txtId);

            banco.update("clientes", ctv, "_id = " + id.getText().toString(), null);

            nome.setText("");
            email.setText("");
        }
    }

    public void excluirCliente(View view) {

        if (!id.getText().toString().isEmpty()) {
            db = new DBHelper(getBaseContext());
            banco = db.getWritableDatabase();

            banco.delete("clientes", "_id = " + id.getText().toString(), null);

            id.setText("");
        }
    }

    public boolean verificarCredenciais() {

        if (email.getText().toString().isEmpty() || email.getText().toString().isEmpty() ||
                !email.getText().toString().contains("@"))
            return false;
        return true;
    }
    public void sortearCliente(View view) {

        db = new DBHelper(getBaseContext());
        banco = db.getWritableDatabase();

        Random gerador = new Random();

        Cursor cursor = banco.rawQuery("SELECT _id, nome, email FROM clientes", null);

        cursor.move(gerador.nextInt(cursor.getCount())+1);

        result.setText(cursor.getString(1).toString().toUpperCase());
    }
}
