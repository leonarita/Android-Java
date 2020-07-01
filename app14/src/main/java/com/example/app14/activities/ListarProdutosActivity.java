package com.example.app14.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.app14.R;
import com.example.app14.adapters.AdapterListaProdutos;
import com.example.app14.controller.ProdutoCtrl;
import com.example.app14.dbHelper.ConexaoSQLite;
import com.example.app14.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ListarProdutosActivity extends AppCompatActivity {

    private ListView lsvProdutos;
    private List<Produto> produtoList;
    private AdapterListaProdutos adapterListaProdutos;
    private ProdutoCtrl produtoCtrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_produtos);

        this.produtoCtrl = new ProdutoCtrl(ConexaoSQLite.getInstancia(ListarProdutosActivity.this));
        produtoList = produtoCtrl.getListaProdutosCtrl();

        this.lsvProdutos = findViewById(R.id.lsvProdutos);
        this.adapterListaProdutos = new AdapterListaProdutos(ListarProdutosActivity.this, this.produtoList);
        this.lsvProdutos.setAdapter(this.adapterListaProdutos);

        this.lsvProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                final Produto produtoSelecionado = (Produto) adapterListaProdutos.getItem(position);

                AlertDialog.Builder janelaEscolha = new AlertDialog.Builder(ListarProdutosActivity.this);

                janelaEscolha.setTitle("Escolha:");
                janelaEscolha.setMessage("O que deseja fazer com o produto selecionado?");

                janelaEscolha.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                janelaEscolha.setNegativeButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        boolean excluiu = produtoCtrl.excluirProdutoCtrl(produtoSelecionado.getId());
                        dialog.cancel();

                        if (excluiu) {
                            adapterListaProdutos.removerProduto(position);
                            Toast.makeText(ListarProdutosActivity.this, "Produto excluido com sucesso!", Toast.LENGTH_LONG);
                        }
                        else {
                            Toast.makeText(ListarProdutosActivity.this, "Erro ao excluir produto!", Toast.LENGTH_LONG);
                        }
                    }
                });

                janelaEscolha.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Bundle bundleDadosProduto = new Bundle();

                        bundleDadosProduto.putLong("id_produto", produtoSelecionado.getId());
                        bundleDadosProduto.putString("nome_produto", produtoSelecionado.getNome());
                        bundleDadosProduto.putDouble("preco_produto", produtoSelecionado.getPreco());
                        bundleDadosProduto.putInt("estoque_produto", produtoSelecionado.getQuantidadeEmEstoque());

                        Intent intent = new Intent(ListarProdutosActivity.this, EditarProdutosActivity.class);
                        intent.putExtras(bundleDadosProduto);
                        startActivity(intent);
                    }
                });

                janelaEscolha.create().show();
            }

        });
    }

    public void eventAtualizarProdutos(View view) {
        this.adapterListaProdutos.atualizar(this.produtoCtrl.getListaProdutosCtrl());
    }
}
