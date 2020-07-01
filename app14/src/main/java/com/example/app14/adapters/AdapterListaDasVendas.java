package com.example.app14.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app14.R;
import com.example.app14.activities.VendasConsolidadasActivity;
import com.example.app14.model.Produto;
import com.example.app14.model.Venda;

import java.text.SimpleDateFormat;
import java.util.List;

public class AdapterListaDasVendas extends BaseAdapter {

    private Context context;
    private List<Venda> vendaList;
    private SimpleDateFormat simpleDateFormat;

    public AdapterListaDasVendas(Context context, List<Venda> vendaList) {
        this.context = context;
        this.vendaList = vendaList;
        this.simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    @Override
    public int getCount() {
        return this.vendaList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.vendaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(this.context, R.layout.layout_minhas_vendas, null);

        TextView tvDataVenda = v.findViewById(R.id.tvDataVenda);
        TextView tvPrecoTotal = v.findViewById(R.id.tvTotalVenda);
        TextView tvQteItens = v.findViewById(R.id.tvQteItens);

        tvDataVenda.setText(this.simpleDateFormat.format(this.vendaList.get(position).getDataDaVenda()));
        tvPrecoTotal.setText(String.valueOf(this.vendaList.get(position).getTotalVenda()));
        tvQteItens.setText(String.valueOf(this.vendaList.get(position).getQteItens()));

        return v;
    }
}
