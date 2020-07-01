package com.example.app14.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.app14.activities.VendaActivity;
import com.example.app14.dbHelper.ConexaoSQLite;
import com.example.app14.model.ItemDoCarrinho;
import com.example.app14.model.Venda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VendaDAO {

    private final ConexaoSQLite conexaoSQLite;

    public VendaDAO (ConexaoSQLite conexaoSQLite) {
        this.conexaoSQLite = conexaoSQLite;
    }

    public long salvarVendaDAO (Venda pVenda) {

        SQLiteDatabase db = conexaoSQLite.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();

            values.put("data", pVenda.getDataDaVenda().getTime());

            long idVendaInserido = db.insert("venda", null, values);
            Log.d("RETORNADO", String.valueOf(idVendaInserido));
            return idVendaInserido;

        }
        catch (Exception e) {
            Log.d("RETORNADO", "EXCEÇÃO");
            e.printStackTrace();
            return 0;
        }
        finally {
            if (db != null)
                db.close();
        }
    }

    public boolean salvarItensDaVendaDAO (Venda pVenda) {

        SQLiteDatabase db = conexaoSQLite.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM venda", null);

        db = conexaoSQLite.getWritableDatabase();

        try {
            ContentValues values = null;

            for (ItemDoCarrinho itemVenda : pVenda.getItemDaVenda()) {

                values = new ContentValues();

                values.put("quantidade_vendida", itemVenda.getQuantidadeSelecionada());
                values.put("id_produto", itemVenda.getIdProduto());
                values.put("id_venda", c.getCount());

                db.insert("item_da_venda", null, values);
            }

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (db != null)
                db.close();
        }

        return false;
    }

    public List<Venda> listarVendasDAO() {

        List<Venda> listaVendas = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor;

        String query = "SELECT venda.id, venda.data, SUM(produto.preco*item_da_venda.quantidade_vendida), COUNT(produto.id*item_da_venda.quantidade_vendida)" +
                "FROM venda INNER JOIN item_da_venda ON (venda.id = item_da_venda.id_venda)" +
                "INNER JOIN produto ON (item_da_venda.id_produto = produto.id)" +
                "GROUP BY venda.id;";

        try {
            db = this.conexaoSQLite.getReadableDatabase();
            cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                Venda vendaTemp = null;

                do {
                    vendaTemp = new Venda();

                    vendaTemp.setId(cursor.getLong(0));
                    vendaTemp.setDataDaVenda(new Date(cursor.getLong(1)));
                    vendaTemp.setTotalVenda(cursor.getDouble(2));
                    vendaTemp.setQteItens(cursor.getInt(3));

                    listaVendas.add(vendaTemp);
                }
                while (cursor.moveToNext());
            }
        }
        catch (Exception e) {
            Log.d("ERRO VENDAS", "Erro ao retornar as vendas");
            return null;
        }

        Log.d("DADOS BUSCADOS", listaVendas.toString());
        return listaVendas;
    }
}
