package com.alexandre.comprasfirebase.entity;

public class Product {

    private String uid;
    private String nome;
    private Integer quantidade;
    private Double preco;

    public Product() {

    }

    public Product(String uid, String nome, Integer quantidade, Double preco) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preço) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Product{" +
                "uid='" + uid + '\'' +
                ", nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", preco=" + preco +
                '}';
    }
}
