/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.model;

import java.util.Date;

/**
 *
 * @author 13b Pessoal
 */
public class Venda {

    private Integer cdVenda;
    private Double valorTotalVenda;
    private Date dataVenda;
    private Usuario usuario;
    private NotaFiscal nf;
    private Cliente cliente;
    private FormaPagamento formaPagamento;
    private Integer numeroParcelas;
    private Double valorDinheiro;
    private Double valorTroco;
    private Double valorParcela;
    private String cupomCartao;
    private String tipoVenda;

    public Venda(){
    }

    public NotaFiscal getNf() {
        return nf;
    }

    public void setNf(NotaFiscal nf) {
        this.nf = nf;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public Double getValorDinheiro() {
        return valorDinheiro;
    }

    public void setValorDinheiro(Double valorDinheiro) {
        this.valorDinheiro = valorDinheiro;
    }

    public Double getValorTroco() {
        return valorTroco;
    }

    public void setValorTroco(Double valorTroco) {
        this.valorTroco = valorTroco;
    }

    public Double getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(Double valorParcela) {
        this.valorParcela = valorParcela;
    }

    public String getCupomCartao() {
        return cupomCartao;
    }

    public void setCupomCartao(String cupomCartao) {
        this.cupomCartao = cupomCartao;
    }

    public String getTipoVenda() {
        return tipoVenda;
    }

    public void setTipoVenda(String tipoVenda) {
        this.tipoVenda = tipoVenda;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public Integer getCdVenda() {
        return cdVenda;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public Double getValorTotalVenda() {
        return valorTotalVenda;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setCdVenda(Integer cdVenda) {
        this.cdVenda = cdVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public void setValorTotalVenda(Double valorTotalVenda) {
        this.valorTotalVenda = valorTotalVenda;
    }

}
