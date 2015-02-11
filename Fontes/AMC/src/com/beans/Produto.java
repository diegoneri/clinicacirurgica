/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

/**
 *
 * @author Leo
 */
public class Produto{
    
    private Integer cdProduto;
    private String cdFarmaceutico;
    private Fornecedor fornecedor;
    private String nmProduto;
    private String descricao;
    private String marca;
    private String cor;
    private String codigoBarras;
    private Double valorCompra;
    private Double valorVenda;
    private Integer qtdeEstoque;
    private Integer cdFornecedor;
    private String tamanho;
    
    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getCdFarmaceutico() {
        return cdFarmaceutico;
    }

    public void setCdFarmaceutico(String cdFarmaceutico) {
        this.cdFarmaceutico = cdFarmaceutico;
    }


    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }
    
    
    public Produto(){
    
    }

    /**
     * @return the cdProduto
     */
    public Integer getCdProduto() {
        return cdProduto;
    }

    /**
     * @param cdProduto the cdProduto to set
     */
    public void setCdProduto(Integer cdProduto) {
        this.cdProduto = cdProduto;
    }

    /**
     * @return the nmProduto
     */
    public String getNmProduto() {
        return nmProduto;
    }

    /**
     * @param nmProduto the nmProduto to set
     */
    public void setNmProduto(String nmProduto) {
        this.nmProduto = nmProduto;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the cor
     */
    public String getCor() {
        return cor;
    }

    /**
     * @param cor the cor to set
     */
    public void setCor(String cor) {
        this.cor = cor;
    }

    /**
     * @return the codigoBarras
     */
    public String getCodigoBarras() {
        return codigoBarras;
    }

    /**
     * @param codigoBarras the codigoBarras to set
     */
    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    /**
     * @return the valorCompra
     */
    public Double getValorCompra() {
        return valorCompra;
    }

    /**
     * @param valorCompra the valorCompra to set
     */
    public void setValorCompra(Double valorCompra) {
        this.valorCompra = valorCompra;
    }

    /**
     * @return the valorVenda
     */
    public Double getValorVenda() {
        return valorVenda;
    }

    /**
     * @param valorVenda the valorVenda to set
     */
    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }

    /**
     * @return the qtdeEstoque
     */
    public Integer getQtdeEstoque() {
        return qtdeEstoque;
    }

    /**
     * @param qtdeEstoque the qtdeEstoque to set
     */
    public void setQtdeEstoque(Integer qtdeEstoque) {
        this.qtdeEstoque = qtdeEstoque;
    }

    public Integer getCdFornecedor() {
        return cdFornecedor;
    }

    public void setCdFornecedor(Integer cdFornecedor) {
        this.cdFornecedor = cdFornecedor;
    }
    
    
    
}
