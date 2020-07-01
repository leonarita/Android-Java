package com.example.app13;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Editar extends AppCompatActivity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar);

        Intent it = getIntent();

        int id = it.getIntExtra("id", 0);

        SQLiteDatabase db = openOrCreateDatabase("clientes.db",
                Context.MODE_PRIVATE, null);

        Cursor cursor = db.rawQuery("SELECT * FROM clientes WHERE _id = ?",
                new String[] { String.valueOf(id) });

        if (cursor.moveToFirst()) {
            EditText txtNome = (EditText) findViewById(R.id.txtNome);
            EditText txtEmail = (EditText) findViewById(R.id.txtEmail);

            txtNome.setText(cursor.getString(1));
            txtEmail.setText(cursor.getString(2));
        }
    }

    public void AtualizarClick(View v) {
        EditText txtNome = (EditText) findViewById(R.id.txtNome);
        EditText txtEmail = (EditText) findViewById(R.id.txtEmail);

        if (txtNome.getText().toString().length() <= 0) {
            txtNome.setError("Preencha o campo nome.");
            txtNome.requestFocus();
        } else if (txtEmail.getText().toString().length() <= 0) {
            txtEmail.setError("Preencha o campo e-mail.");
            txtEmail.requestFocus();
        } else {
            try {
                SQLiteDatabase db = openOrCreateDatabase("clientes.db",
                        Context.MODE_PRIVATE, null);

                ContentValues ctv = new ContentValues();
                ctv.put("nome", txtNome.getText().toString());
                ctv.put("email", txtEmail.getText().toString());

                Intent it = getIntent();

                int id = it.getIntExtra("id", 0);

                if (db.update("clientes", ctv, "_id=?",
                        new String[] { String.valueOf(id) }) > 0) {
                    Toast.makeText(getBaseContext(),
                            "Sucesso ao atualizar o cliente.",
                            Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getBaseContext(),
                            "Erro ao atualizar o cliente.", Toast.LENGTH_SHORT)
                            .show();
                }
            } catch (Exception ex) {
                Toast.makeText(getBaseContext(), ex.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void ApagarClick(View v) {
        try {
            final SQLiteDatabase db = openOrCreateDatabase("clientes.db",
                    Context.MODE_PRIVATE, null);

            Intent it = getIntent();

            final int id = it.getIntExtra("id", 0);


            Builder msg = new Builder(Editar.this);
            msg.setMessage("Deseja apagar este clientes?");
            msg.setNegativeButton("Não", null);
            msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    if (db.delete("clientes", "_id=?",
                            new String[] { String.valueOf(id) }) > 0) {
                        Toast.makeText(getBaseContext(),
                                "Sucesso ao apagar o cliente.", Toast.LENGTH_SHORT)
                                .show();
                        finish();
                    } else {
                        Toast.makeText(getBaseContext(), "Erro ao apagar o cliente.",
                                Toast.LENGTH_SHORT).show();
                    }

                }
            });

            msg.show();

        } catch (Exception ex) {
            Toast.makeText(getBaseContext(), ex.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }

    }
}
