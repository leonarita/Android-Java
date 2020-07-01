package com.example.app14.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.app14.R;
import com.example.app14.model.Produto;

import java.util.List;

public class AdapterListaProdutos extends BaseAdapter {

    private Context context;
    private List<Produto> produtoList;

    public AdapterListaProdutos(Context context, List<Produto> produtoList) {
        this.context = context;
        this.produtoList = produtoList;
    }

    @Override
    public int getCount() {
        return this.produtoList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.produtoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void removerProduto(int position) {

        this.produtoList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(this.context, R.layout.layout_produto, null);

        TextView tvNomeProduto = v.findViewById(R.id.tvNomeProduto);
        TextView tvEstoqueProduto = v.findViewById(R.id.tvEstoqueProduto);
        TextView tvPrecoProduto = v.findViewById(R.id.tvPrecoProduto);

        tvNomeProduto.setText(this.produtoList.get(position).getNome());
        tvEstoqueProduto.setText(String.valueOf(this.produtoList.get(position).getQuantidadeEmEstoque()));
        tvPrecoProduto.setText(String.valueOf(this.produtoList.get(position).getPreco()));

        return v;
    }

    public void atualizar(List<Produto> pProdutos) {
        this.produtoList.clear();
        this.produtoList = pProdutos;
        this.notifyDataSetChanged();
    }
}

