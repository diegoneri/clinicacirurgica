/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

/**
 *
 * @author Leo
 */
public class Login {
    
   private Integer cdLogin;
   private String nmLogin;
   private String nmSenha;
   private Integer cdUsuario;
    
    public Login(){
    
    }

    /**
     * @return the cdLogin
     */
    public Integer getCdLogin() {
        return cdLogin;
    }

    /**
     * @param cdLogin the cdLogin to set
     */
    public void setCdLogin(Integer cdLogin) {
        this.cdLogin = cdLogin;
    }

    /**
     * @return the nmLogin
     */
    public String getNmLogin() {
        return nmLogin;
    }

    /**
     * @param nmLogin the nmLogin to set
     */
    public void setNmLogin(String nmLogin) {
        this.nmLogin = nmLogin;
    }

    public void setNmSenha(String nmSenha) {
        this.nmSenha = nmSenha;
    }

    public String getNmSenha() {
        return nmSenha;
    }

    public void setCdUsuario(Integer cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    public Integer getCdUsuario() {
        return cdUsuario;
    }

}
