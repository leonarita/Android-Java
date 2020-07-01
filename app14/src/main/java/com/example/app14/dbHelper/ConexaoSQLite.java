package com.example.app14.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexaoSQLite extends SQLiteOpenHelper {

    private static ConexaoSQLite INSTANCIA_CONEXAO;
    private static final int VERSAO_DB = 1;
    private static final String NOME_BD = "bl_produtos_app";

    public ConexaoSQLite(@Nullable Context context) {
        super(context, NOME_BD, null, VERSAO_DB);
    }

    public static ConexaoSQLite getInstancia(Context context) {
        if (INSTANCIA_CONEXAO == null)
            INSTANCIA_CONEXAO = new ConexaoSQLite(context);
        return INSTANCIA_CONEXAO;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlTabelaProduto = "CREATE TABLE IF NOT EXISTS produto (" +
                "id INTEGER PRIMARY KEY," +
                "nome TEXT," +
                "quantidade_em_estoque INTEGER," +
                "preco REAL )";

        db.execSQL(sqlTabelaProduto);

        String sqlTabelaVenda = "CREATE TABLE IF NOT EXISTS venda (" +
                "id INTEGER PRIMARY KEY," +
                "data INTEGER )";

        db.execSQL(sqlTabelaVenda);

        String sqlTableItemDaVenda = "CREATE TABLE IF NOT EXISTS item_da_venda (" +
                "id INTEGER PRIMARY KEY," +
                "quantidade_vendida INTEGER," +
                "id_produto INTEGER," +
                "id_venda INTEGER )";

        db.execSQL(sqlTableItemDaVenda);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
