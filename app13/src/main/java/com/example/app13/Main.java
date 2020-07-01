package com.example.app13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main extends AppCompatActivity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void ListarClientesClick(View v){
        Intent it = new Intent(getBaseContext(), ListarActivity.class);
        startActivity(it);
    }

    public void CadastrarClienteClick(View v){
        Intent it = new Intent(getBaseContext(), Inserir.class);
        startActivity(it);
    }
}
