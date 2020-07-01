package com.example.app9;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import com.example.app9.helpers.DBHelper;

//Para criar um layout para table, basta criar um Android Resource Directory, Ratio, Long e escolher a largura
//Ele pode não aparecer inicialmente por estar vazio

//Para utilizar Banco de Dados, usar SharedPreferences (framework nativo)

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botao = findViewById(R.id.btnPrincipal);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), SegundaActivity.class));
            }
        });

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        sp.edit().putBoolean("esta_logado", false).apply();

        if (sp.getBoolean("esta_logado", false))
        {

        }

        DBHelper db = new DBHelper(getBaseContext());

        //Permite ler o banco de dados
        db.getReadableDatabase();

        startActivity(new Intent(getBaseContext(), CursoresActivity.class));
    }
/*      --> adicionar nos atributor
    public void abrirSegundaActivity (View view) {

        // Iniciar uma activity com a intenção de pegar o contexto para iniciar a classe
        startActivity(new Intent(getBaseContext(), SegundaActivity.class));
    }
*/
}
