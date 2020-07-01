package com.example.app14.controller;

import com.example.app14.DAO.VendaDAO;
import com.example.app14.dbHelper.ConexaoSQLite;
import com.example.app14.model.Venda;

import java.util.List;

public class VendaCtrl {

    private final VendaDAO vendaDAO;

    public VendaCtrl (ConexaoSQLite pConexaoSQLite) {
        vendaDAO = new VendaDAO(pConexaoSQLite);
    }

    public long salvarVendaCtrl (Venda pVenda) {
        return vendaDAO.salvarVendaDAO(pVenda);
    }

    public boolean salvarItensVendaCtrl (Venda pVenda) {
        return vendaDAO.salvarItensDaVendaDAO(pVenda);
    }

    public List<Venda> listarVendasCtrl() {
        return vendaDAO.listarVendasDAO();
    }
}
