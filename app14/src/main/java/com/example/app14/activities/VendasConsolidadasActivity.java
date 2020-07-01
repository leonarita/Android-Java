package com.example.app14.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app14.R;
import com.example.app14.adapters.AdapterItensDoCarrinho;
import com.example.app14.adapters.AdapterListaDasVendas;
import com.example.app14.controller.VendaCtrl;
import com.example.app14.dbHelper.ConexaoSQLite;
import com.example.app14.model.Venda;

import java.util.List;

public class VendasConsolidadasActivity extends AppCompatActivity {

    private ListView lsvVendas;
    private List<Venda> listaDeVendasFeitas;
    private AdapterListaDasVendas adapterListaDasVendas;
    private VendaCtrl vendaCtrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendas_consolidadas);

        this.vendaCtrl = new VendaCtrl(ConexaoSQLite.getInstancia(VendasConsolidadasActivity.this));
        this.listaDeVendasFeitas = this.vendaCtrl.listarVendasCtrl();

        this.lsvVendas = (ListView) findViewById(R.id.lsvMinhasVendas);
        this.adapterListaDasVendas = new AdapterListaDasVendas(VendasConsolidadasActivity.this, listaDeVendasFeitas);
        this.lsvVendas.setAdapter(this.adapterListaDasVendas);
    }
}
