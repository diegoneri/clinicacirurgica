/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.model;

/**
 *
 * @author Leo
 */
public class Cidade {
    
    private Integer cdCidade;
    private String nmCidade;
    private Integer cdEstado;
    
    public Cidade(){
    
    }

    public Integer getCdCidade() {
        return cdCidade;
    }

    public Integer getCdEstado() {
        return cdEstado;
    }

    public String getNmCidade() {
        return nmCidade;
    }

    public void setCdCidade(Integer cdCidade) {
        this.cdCidade = cdCidade;
    }

    public void setCdEstado(Integer cdEstado) {
        this.cdEstado = cdEstado;
    }

    public void setNmCidade(String nmCidade) {
        this.nmCidade = nmCidade;
    }
    
    
    
    
}
