/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.model;

/**
 *
 * @author Leo
 */
public class Estado {
    
    private Integer cdEstado;
    private String nmEstado;
    private String uf;
    private Integer cdPais;
    
    public Estado(){
    
    }

    public void setNmEstado(String nmEstado) {
        this.nmEstado = nmEstado;
    }

    public void setCdPais(Integer cdPais) {
        this.cdPais = cdPais;
    }

    public void setCdEstado(Integer cdEstado) {
        this.cdEstado = cdEstado;
    }

    public String getUf() {
        return uf;
    }

    public String getNmEstado() {
        return nmEstado;
    }

    public Integer getCdPais() {
        return cdPais;
    }

    public Integer getCdEstado() {
        return cdEstado;
    }


    public void setUf(String uf) {
        this.uf = uf;
    }
    
    
    
    
    
     
}
