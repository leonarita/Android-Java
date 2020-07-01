package com.example.app14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.app14.activities.ListarProdutosActivity;
import com.example.app14.activities.ProdutoActivity;
import com.example.app14.activities.VendaActivity;
import com.example.app14.activities.VendasConsolidadasActivity;
import com.example.app14.controller.ProdutoCtrl;
import com.example.app14.dbHelper.ConexaoSQLite;
import com.example.app14.model.Produto;

public class MainActivity extends AppCompatActivity {

    private Button btnCadastroProdutos, btnListarProdutos, btnVender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexaoSQLite conexaoSQLite = ConexaoSQLite.getInstancia(this);

        this.btnCadastroProdutos = (Button) findViewById(R.id.btnSalvarProduto);
        this.btnListarProdutos = (Button) findViewById(R.id.btnListarProdutos);
        this.btnVender = (Button) findViewById(R.id.btnVender);
    }

    public void cadastro(View view) {
        Intent intent = new Intent(MainActivity.this, ProdutoActivity.class);
        startActivity(intent);
    }

    public void listar(View view) {
        Intent intent = new Intent(MainActivity.this, ListarProdutosActivity.class);
        startActivity(intent);
    }

    public void vender(View view) {
        Intent intent = new Intent(MainActivity.this, VendaActivity.class);
        startActivity(intent);
    }

    public void relatar(View view) {
        Intent intent = new Intent(MainActivity.this, VendasConsolidadasActivity.class);
        startActivity(intent);
    }
}
