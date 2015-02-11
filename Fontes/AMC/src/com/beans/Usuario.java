/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

/**
 *
 * @author Leo
 */
public class Usuario {
    
    private Integer cdUsuario;
    private String tipoUsuario;
    private Integer cdPessoa;
    
    public Usuario(){
        this(0);
    }

    public Usuario(Integer cdUsuario){
        this.cdUsuario = cdUsuario;
    }
    /**
     * @return the cdUsuario
     */
    public Integer getCdUsuario() {
        return cdUsuario;
    }

    /**
     * @param cdUsuario the cdUsuario to set
     */
    public void setCdUsuario(Integer cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    /**
     * @return the tipoUsuario
     */
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * @param tipoUsuario the tipoUsuario to set
     */
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Integer getCdPessoa() {
        return cdPessoa;
    }

    public void setCdPessoa(Integer cdPessoa) {
        this.cdPessoa = cdPessoa;
    }
    
    
    
    
}
