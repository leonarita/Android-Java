package com.example.app14.controller;

import com.example.app14.DAO.ProdutoDAO;
import com.example.app14.dbHelper.ConexaoSQLite;
import com.example.app14.model.Produto;

import java.util.List;

public class ProdutoCtrl {

    private final ProdutoDAO produtoDAO;

    public ProdutoCtrl (ConexaoSQLite pConexao) {
        produtoDAO = new ProdutoDAO(pConexao);
    }

    public long salvarProdutoCtrl (Produto pProduto) {
        return this.produtoDAO.salvarProdutoDAO(pProduto);
    }

    public List<Produto> getListaProdutosCtrl() {
        return this.produtoDAO.getListaProdutosDAO();
    }

    public boolean excluirProdutoCtrl(long pIdProduto) {
        return this.produtoDAO.excluirProdutoDAO(pIdProduto);
    }

    public boolean atualizarProdutoCtrl(Produto pProduto) {
        return this.produtoDAO.atualizarProdutoDAO(pProduto);
    }
}
