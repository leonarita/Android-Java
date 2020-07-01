package com.example.app13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Inserir extends AppCompatActivity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inserir);
    }

    public void CadastrarClick(View v){
        EditText txtNome = (EditText)findViewById(R.id.txtNome);
        EditText txtEmail = (EditText)findViewById(R.id.txtEmail);

        if(txtNome.getText().toString().length() <= 0){
            txtNome.setError("Preencha o campo nome.");
            txtNome.requestFocus();
        } else if(txtEmail.getText().toString().length() <= 0){
            txtEmail.setError("Preencha o campo e-mail.");
            txtEmail.requestFocus();
        } else {
            try{
                SQLiteDatabase db = openOrCreateDatabase("clientes.db", Context.MODE_PRIVATE, null);

                ContentValues ctv = new ContentValues();
                ctv.put("nome", txtNome.getText().toString());
                ctv.put("email", txtEmail.getText().toString());

                if(db.insert("clientes", "_id", ctv) > 0){
                    Toast.makeText(getBaseContext(), "Sucesso ao cadastrar o cliente.", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getBaseContext(), "Erro ao cadastrar o cliente.", Toast.LENGTH_SHORT).show();
                }
            } catch(Exception ex){
                Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
