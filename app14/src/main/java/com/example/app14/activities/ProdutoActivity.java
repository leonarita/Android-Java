package com.example.app14.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app14.R;
import com.example.app14.controller.ProdutoCtrl;
import com.example.app14.dbHelper.ConexaoSQLite;
import com.example.app14.model.Produto;

public class ProdutoActivity extends AppCompatActivity {

    private EditText edtIdProduto, edtNomeProduto, edtQuantidadeProduto, edtPrecoProduto;
    private Button btnSalvarProduto;
    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        edtIdProduto = (EditText) findViewById(R.id.edtIdProduto);
        edtNomeProduto = (EditText) findViewById(R.id.edtNomeProduto);
        edtQuantidadeProduto = (EditText) findViewById(R.id.edtQuantidadeProduto);
        edtPrecoProduto = (EditText) findViewById(R.id.edtPrecoProduto);
        btnSalvarProduto = (Button) findViewById(R.id.btnSalvarProduto);

        this.clickNoBotaoSalvarListener();
    }

    private void clickNoBotaoSalvarListener() {

        this.btnSalvarProduto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Produto produtoACadastrar = getDadosProdutoDoFormulario();

                if (produtoACadastrar != null) {
                    ProdutoCtrl produtoCtrl = new ProdutoCtrl(ConexaoSQLite.getInstancia(ProdutoActivity.this));
                    long id = produtoCtrl.salvarProdutoCtrl(produtoACadastrar);

                    if (id>0) {
                        Toast.makeText(ProdutoActivity.this, "Produto salvo com sucesso!", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(ProdutoActivity.this, "Produto não pode ser salvo!", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(ProdutoActivity.this, "Todos os campos são obrigatórios!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private Produto getDadosProdutoDoFormulario() {

        this.produto = new Produto();

        if (!this.edtIdProduto.getText().toString().isEmpty() &&
                !this.edtNomeProduto.getText().toString().isEmpty() &&
                !this.edtQuantidadeProduto.getText().toString().isEmpty() &&
                !this.edtPrecoProduto.getText().toString().isEmpty()) {

            this.produto.setId(Long.parseLong(this.edtIdProduto.getText().toString()));
            this.produto.setNome(this.edtNomeProduto.getText().toString());
            this.produto.setQuantidadeEmEstoque(Integer.parseInt(this.edtQuantidadeProduto.getText().toString()));
            this.produto.setPreco(Double.parseDouble(this.edtPrecoProduto.getText().toString()));
            return produto;
        }
        else {
            return null;
        }
    }
}
