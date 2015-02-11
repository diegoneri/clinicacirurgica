/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.model;

import java.util.Date;

/**
 *
 * @author Leo
 */
public class Despesa {

    private Integer cdDespesa;
    private Double valorDespesa;
    private Date dataVencimento;
    private Boolean isPaga;
    private Integer cdTipoDespesa;
    private Date dataReferente;
    private TipoDespesa tipoDespesa;
    private String dataVencimentoString;
    private String valorDespesaString;
    private String isPagaString;

    public Despesa(TipoDespesa tipoDespesa, String dataVencimentoString, String valorDespesaString, String isPagaString) {

        this.tipoDespesa = tipoDespesa;
        this.dataVencimentoString = dataVencimentoString;
        this.isPagaString = isPagaString;
        this.valorDespesaString = valorDespesaString;

    }

    public Integer getCdDespesa() {
        return cdDespesa;
    }

    public void setCdDespesa(Integer cdDespesa) {
        this.cdDespesa = cdDespesa;
    }

    public String getDataVencimentoString() {
        return dataVencimentoString;
    }

    public void setDataVencimentoString(String dataVencimentoString) {
        this.dataVencimentoString = dataVencimentoString;
    }

 

    public Double getValorDespesa() {
        return valorDespesa;
    }

    public void setValorDespesa(Double valorDespesa) {
        this.valorDespesa = valorDespesa;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Boolean getIsPaga() {
        return isPaga;
    }

    public void setIsPaga(Boolean isPaga) {
        this.isPaga = isPaga;
    }

    public Integer getCdTipoDespesa() {
        return cdTipoDespesa;
    }

    public void setCdTipoDespesa(Integer cdTipoDespesa) {
        this.cdTipoDespesa = cdTipoDespesa;
    }

    public Date getDataReferente() {
        return dataReferente;
    }

    public void setDataReferente(Date dataReferente) {
        this.dataReferente = dataReferente;
    }

    public TipoDespesa getTipoDespesa() {
        return tipoDespesa;
    }

    public void setTipoDespesa(TipoDespesa tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }

    public String getValorDespesaString() {
        return valorDespesaString;
    }

    public void setValorDespesaString(String valorDespesaString) {
        this.valorDespesaString = valorDespesaString;
    }

    public String getIsPagaString() {
        return isPagaString;
    }

    public void setIsPagaString(String isPagaString) {
        this.isPagaString = isPagaString;
    }
    
    
}
