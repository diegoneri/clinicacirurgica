/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.view.utils;

/**
 *
 * @author dfelix3
 */
public enum Acao {

    INCLUIR("incluir", "incluído"), ALTERAR("alterar", "alterado");
    private String action;
    private String pastAction;

    private Acao(String action, String pastAction) {
        this.action = action;
        this.pastAction = pastAction;
    }

    public String getAction() {
        return this.action;
    }

    public String getPastAction() {
        return this.pastAction;
    }
}
