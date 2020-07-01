package com.example.app14.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app14.R;
import com.example.app14.controller.ProdutoCtrl;
import com.example.app14.dbHelper.ConexaoSQLite;
import com.example.app14.model.Produto;

public class EditarProdutosActivity extends AppCompatActivity {

    private EditText edtIdProduto, edtNomeProduto, edtEstoqueProduto, edtPrecoProduto;
    private Produto produto;
    private Button btnSalvarAlteracoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_produtos);

        this.edtIdProduto = (EditText) findViewById(R.id.edtIdProduto);
        this.edtNomeProduto = (EditText) findViewById(R.id.edtNomeProduto);
        this.edtEstoqueProduto = (EditText) findViewById(R.id.edtQuantidadeProduto);
        this.edtPrecoProduto = (EditText) findViewById(R.id.edtPrecoProduto);

        this.btnSalvarAlteracoes = findViewById(R.id.btnSalvarProduto);

        Bundle bundleDadosProduto = getIntent().getExtras();
        Produto produto = new Produto();

        produto.setId(bundleDadosProduto.getLong("id_produto"));
        produto.setNome(bundleDadosProduto.getString("nome_produto"));
        produto.setPreco(bundleDadosProduto.getDouble("preco_produto"));
        produto.setQuantidadeEmEstoque(bundleDadosProduto.getInt("estoque_produto"));

        this.setDadosProduto(produto);
    }

    private void setDadosProduto(Produto produto) {
        this.edtIdProduto.setText(String.valueOf(produto.getId()));
        this.edtNomeProduto.setText(produto.getNome());
        this.edtPrecoProduto.setText(String.valueOf(produto.getPreco()));
        this.edtEstoqueProduto.setText(String.valueOf(produto.getQuantidadeEmEstoque()));
    }

    private Produto getDadosProdutoDoFormulario() {

        this.produto = new Produto();

        if (!this.edtIdProduto.getText().toString().isEmpty() &&
                !this.edtNomeProduto.getText().toString().isEmpty() &&
                !this.edtEstoqueProduto.getText().toString().isEmpty() &&
                !this.edtPrecoProduto.getText().toString().isEmpty()) {

            this.produto.setId(Long.parseLong(this.edtIdProduto.getText().toString()));
            this.produto.setNome(this.edtNomeProduto.getText().toString());
            this.produto.setQuantidadeEmEstoque(Integer.parseInt(this.edtEstoqueProduto.getText().toString()));
            this.produto.setPreco(Double.parseDouble(this.edtPrecoProduto.getText().toString()));
            return produto;
        }
        else {
            return null;
        }
    }

    public void editar(View view) {
        Produto produtoAAtualizar = getDadosProdutoDoFormulario();

        if (produtoAAtualizar != null) {
            ProdutoCtrl produtoCtrl = new ProdutoCtrl(ConexaoSQLite.getInstancia(EditarProdutosActivity.this));
            boolean atualizou = produtoCtrl.atualizarProdutoCtrl(produtoAAtualizar);

            if (atualizou) {
                Toast.makeText(EditarProdutosActivity.this, "Produto salvo com sucesso!", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(EditarProdutosActivity.this, "Produto não pode ser salvo!", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(EditarProdutosActivity.this, "Todos os campos são obrigatórios!", Toast.LENGTH_LONG).show();
        }
    }
}
