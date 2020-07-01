package com.example.app14.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.app14.R;
import com.example.app14.model.ItemDoCarrinho;
import com.example.app14.model.Produto;

import java.util.List;

public class AdapterItensDoCarrinho extends BaseAdapter {

    private Context context;
    private List<ItemDoCarrinho> itensDoCarrinho;

    public AdapterItensDoCarrinho(Context context, List<ItemDoCarrinho> produtoList) {
        this.context = context;
        this.itensDoCarrinho = produtoList;
    }

    @Override
    public int getCount() {
        return this.itensDoCarrinho.size();
    }

    @Override
    public Object getItem(int position) {
        return this.itensDoCarrinho.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public boolean removerItemDoCarrinho(int position) {

        this.itensDoCarrinho.remove(position);
        notifyDataSetChanged();
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(this.context, R.layout.layout_carrinho_produtos, null);

        TextView tvNomeProduto = v.findViewById(R.id.tvNomeProduto);
        TextView tvPrecoProduto = v.findViewById(R.id.tvPrecoProduto);
        TextView tvQuantidadeSelecionada = v.findViewById(R.id.tvQteProduto);
        TextView tvValorItem = v.findViewById(R.id.tvValorTotalItem);

        tvNomeProduto.setText(this.itensDoCarrinho.get(position).getNome());
        tvPrecoProduto.setText(String.valueOf(this.itensDoCarrinho.get(position).getPrecoProduto()));
        tvQuantidadeSelecionada.setText(String.valueOf(this.itensDoCarrinho.get(position).getQuantidadeSelecionada()));
        tvValorItem.setText(String.valueOf(this.itensDoCarrinho.get(position).getPrecoUnitario()));

        return v;
    }

    public void addItemDoCarrinho(ItemDoCarrinho pItemDoCarrinho) {
        this.itensDoCarrinho.add(pItemDoCarrinho);
        this.notifyDataSetChanged();
    }

    public void atualizar(List<ItemDoCarrinho> pItemDoCarrinho) {
        this.itensDoCarrinho.clear();
        this.itensDoCarrinho = pItemDoCarrinho;
        this.notifyDataSetChanged();
    }
}

