/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.model;

/**
 *
 * @author dfelix3
 */
public class Telefone {
    private Long codigo;
    private String codigoDdi;
    private String codigoDdd;
    private String numero;
    private String ramal;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getCodigoDdi() {
        return codigoDdi;
    }

    public void setCodigoDdi(String codigoDdi) {
        this.codigoDdi = codigoDdi;
    }

    public String getCodigoDdd() {
        return codigoDdd;
    }

    public void setCodigoDdd(String codigoDdd) {
        this.codigoDdd = codigoDdd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }
    
}
