package com.example.app14.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app14.R;
import com.example.app14.adapters.AdapterItensDoCarrinho;
import com.example.app14.controller.ProdutoCtrl;
import com.example.app14.controller.VendaCtrl;
import com.example.app14.dbHelper.ConexaoSQLite;
import com.example.app14.model.ItemDoCarrinho;
import com.example.app14.model.Produto;
import com.example.app14.model.Venda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VendaActivity extends AppCompatActivity {

    private Spinner spnProdutos;
    private List<Produto> listaProduto;
    private ProdutoCtrl produtoCtrl;
    private EditText quantidadeItem;
    private TextView tvTotalVenda;

    private ListView lsvCarrinhoCompra;
    private List<ItemDoCarrinho> listaItensDoCarrinho;
    private AdapterItensDoCarrinho adpItemDoCarrinho;

    private VendaCtrl vendaCtrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venda);

        this.vendaCtrl = new VendaCtrl(ConexaoSQLite.getInstancia(this));

        this.produtoCtrl = new ProdutoCtrl(ConexaoSQLite.getInstancia(this));
        this.listaProduto = this.produtoCtrl.getListaProdutosCtrl();

        this.spnProdutos = this.findViewById(R.id.spnProduto);

        ArrayAdapter<Produto> spnProdutoAdapter =  new ArrayAdapter<Produto>(
                this, android.R.layout.simple_spinner_item, this.listaProduto
        );

        this.spnProdutos.setAdapter(spnProdutoAdapter);

        this.quantidadeItem = this.findViewById(R.id.edtQuantidadeProduto);
        this.tvTotalVenda = this.findViewById(R.id.tvTotalVendas);

        this.lsvCarrinhoCompra = this.findViewById(R.id.lsvProdutos);
        this.listaItensDoCarrinho = new ArrayList<>();
        this.adpItemDoCarrinho = new AdapterItensDoCarrinho(VendaActivity.this, this.listaItensDoCarrinho);
        this.lsvCarrinhoCompra.setAdapter(this.adpItemDoCarrinho);

        this.lsvCarrinhoCompra.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                final ItemDoCarrinho itemDoCarrinho = (ItemDoCarrinho) adpItemDoCarrinho.getItem(position);

                AlertDialog.Builder janelaDeEscolha = new AlertDialog.Builder(VendaActivity.this);

                janelaDeEscolha.setTitle("Escolha: ");
                janelaDeEscolha.setMessage("Deseja remover o item " + itemDoCarrinho.getNome() + "?");

                janelaDeEscolha.setNegativeButton("Não", null);

                janelaDeEscolha.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean excluiu = false;

                        excluiu = adpItemDoCarrinho.removerItemDoCarrinho(position);

                        double totalVenda = calcularTotalVendas(listaItensDoCarrinho);
                        atualizarValorTotalVenda(totalVenda);

                        if (!excluiu) {
                            Toast.makeText(VendaActivity.this , "Erro ao excluir item do carrinho", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                janelaDeEscolha.create().show();
            }
        });
    }

    public void eventAddProduto(View view) {

        ItemDoCarrinho itemDoCarrinho = new ItemDoCarrinho();
        Produto produtoSelecionado = (Produto) this.spnProdutos.getSelectedItem();

        int quantidadeProduto = 0;

        if (!this.quantidadeItem.getText().toString().equals("")) {
            quantidadeProduto = Integer.parseInt(this.quantidadeItem.getText().toString());
        }
        else {
            quantidadeProduto = 1;
        }

        itemDoCarrinho.setNome(produtoSelecionado.getNome());
        itemDoCarrinho.setIdProduto(produtoSelecionado.getId());
        itemDoCarrinho.setQuantidadeSelecionada(quantidadeProduto);
        itemDoCarrinho.setPrecoProduto(produtoSelecionado.getPreco());
        itemDoCarrinho.setPrecoUnitario(itemDoCarrinho.getPrecoProduto() * itemDoCarrinho.getQuantidadeSelecionada());

        this.adpItemDoCarrinho.addItemDoCarrinho(itemDoCarrinho);

        double totalVenda = this.calcularTotalVendas(this.listaItensDoCarrinho);
        this.atualizarValorTotalVenda(totalVenda);
    }

    private double calcularTotalVendas (List<ItemDoCarrinho> pListaItensDoCarrinho) {
        double totalVendas = 0.0d;

        for (ItemDoCarrinho itemDoCarrinho : pListaItensDoCarrinho) {
            totalVendas += itemDoCarrinho.getPrecoUnitario();
            Log.d("PRODUTOS", itemDoCarrinho.toString());
        }

        return totalVendas;
    }

    private void atualizarValorTotalVenda (double pValorTotal) {
        this.tvTotalVenda.setText(String.valueOf(pValorTotal));
    }

    private Venda criarVenda () {

        Venda venda = new Venda();
        venda.setDataDaVenda(new Date());
        venda.setItemDaVenda(this.listaItensDoCarrinho);
        return venda;
    }

    private boolean salvarVenda (Venda pVenda) {
        long idVenda = (long) vendaCtrl.salvarVendaCtrl(pVenda);

        if (idVenda > 0) {
            pVenda.setId(idVenda);

            if (vendaCtrl.salvarItensVendaCtrl(pVenda)) {
                Toast.makeText(this, "Venda realizada!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Venda não pôde ser realizada, tente novamente!", Toast.LENGTH_SHORT).show();
            }

            return true;
        }
        return false;
    }

    public void eventFecharVenda(View view) {

        Venda vendaFechada = this.criarVenda();

        if (this.salvarVenda(vendaFechada)) {
            Toast.makeText(this, "Venda fechada com sucesso!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Erro ao fechar venda!", Toast.LENGTH_SHORT).show();
        }
    }
}
