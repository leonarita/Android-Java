package com.example.app14.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.app14.dbHelper.ConexaoSQLite;
import com.example.app14.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private final ConexaoSQLite conexaoSQLite;

    public ProdutoDAO(ConexaoSQLite c) {
        this.conexaoSQLite = c;
    }

    public long salvarProdutoDAO (Produto pProduto) {

        SQLiteDatabase db = conexaoSQLite.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();

            values.put("id", pProduto.getId());
            values.put("nome", pProduto.getNome());
            values.put("quantidade_em_estoque", pProduto.getQuantidadeEmEstoque());
            values.put("preco", pProduto.getPreco());

            long idProdutoInserido = db.insert("produto", null, values);
            return idProdutoInserido;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (db != null)
                db.close();
        }

        return 0;
    }

    public List<Produto> getListaProdutosDAO() {

        List<Produto> listaProduto = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor;

        String query = "SELECT * FROM produto;";

        try {

            db = this.conexaoSQLite.getReadableDatabase();
            cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {

                Produto produtoTemporario = null;

                do {

                    produtoTemporario = new Produto();

                    produtoTemporario.setId(cursor.getLong(0));
                    produtoTemporario.setNome(cursor.getString(1));
                    produtoTemporario.setQuantidadeEmEstoque(cursor.getInt(2));
                    produtoTemporario.setPreco(cursor.getDouble(3));

                    listaProduto.add(produtoTemporario);
                }
                while (cursor.moveToNext());
            }
        }
        catch (Exception e) {

            Log.d("ERRO LISTA PRODUTOS", "Erro ao retornar produtos");
            return null;
        }
        finally {
            if (db != null)
                db.close();
        }

        return listaProduto;
    }

    public boolean excluirProdutoDAO (long pIdProduto) {
        SQLiteDatabase db = null;

        try {
            db = this.conexaoSQLite.getWritableDatabase();
            db.delete("produto", "id=?", new String[] {String.valueOf(pIdProduto)});
        }
        catch (Exception e) {
            Log.d("PRODUTODAO", "Não foi possível deletar produto!");
            return false;
        }
        finally {
            if (db != null)
                db.close();
        }
        return true;
    }

    public boolean atualizarProdutoDAO (Produto pProduto) {
        SQLiteDatabase db = null;

        ContentValues produtoAtributos = new ContentValues();
        produtoAtributos.put("nome", pProduto.getNome());
        produtoAtributos.put("quantidade_em_estoque", pProduto.getQuantidadeEmEstoque());
        produtoAtributos.put("preco", pProduto.getPreco());

        try {
            db = this.conexaoSQLite.getWritableDatabase();
            int atualizou = db.update("produto", produtoAtributos, "id=?", new String[] {String.valueOf(pProduto.getId())});

            if (atualizou>0)
                return true;
        }
        catch (Exception e) {
            Log.d("PRODUTODAO", "Não foi possível atualizar produto!");
            return false;
        }
        finally {
            if (db != null)
                db.close();
        }
        return false;
    }
}
