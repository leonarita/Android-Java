package com.example.app14.model;

public class ItemDoCarrinho {

    private long id, idProduto;
    private String nome;
    private int quantidadeSelecionada;
    private double precoProduto;
    private double precoDoItemDeVenda;

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidadeSelecionada() {
        return quantidadeSelecionada;
    }

    public void setQuantidadeSelecionada(int quantidadeSelecionada) {
        this.quantidadeSelecionada = quantidadeSelecionada;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public double getPrecoUnitario() {
        return precoDoItemDeVenda;
    }

    public void setPrecoUnitario(double precoDoItemDeVenda) {
        this.precoDoItemDeVenda = precoDoItemDeVenda;
    }

    @Override
    public String toString() {
        return "ItemDoCarrinho{" +
                "id=" + id +
                ", idProduto=" + idProduto +
                ", nome='" + nome + '\'' +
                ", quantidadeSelecionada=" + quantidadeSelecionada +
                ", precoProduto=" + precoProduto +
                ", precoDoItemDeVenda=" + precoDoItemDeVenda +
                '}';
    }
}
