/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.model;

/**
 *
 * @author 13b Pessoal
 */
public class TipoDespesa {

    public TipoDespesa(Integer cdTipoDespesa, String nmTipoDespesa) {

        this.cdTipoDespesa = cdTipoDespesa;
        this.nmTipoDespesa = nmTipoDespesa;
    }
    private Integer cdTipoDespesa;
    private String nmTipoDespesa;

    public Integer getCdTipoDespesa() {
        return cdTipoDespesa;
    }

    public void setCdTipoDespesa(Integer cdTipoDespesa) {
        this.cdTipoDespesa = cdTipoDespesa;
    }

    public String getNmTipoDespesa() {
        return nmTipoDespesa;
    }

    public void setNmTipoDespesa(String nmTipoDespesa) {
        this.nmTipoDespesa = nmTipoDespesa;
    }
}
