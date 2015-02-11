/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.model;

/**
 *
 * @author dfelix3
 */
public class NotaFiscal {
    private Integer codigoNotaFiscal;
    private String numeroNotaFiscal;
    private TipoNotaFiscal tipoNotaFiscal;

    public NotaFiscal(){
       this(0); 
    }
    
    public NotaFiscal(Integer codigoNotaFiscal){
        this.codigoNotaFiscal = codigoNotaFiscal;
    }
        
    public Integer getCodigoNotaFiscal() {
        return codigoNotaFiscal;
    }

    public void setCodigoNotaFiscal(Integer codigoNotaFiscal) {
        this.codigoNotaFiscal = codigoNotaFiscal;
    }

    public String getNumeroNotaFiscal() {
        return numeroNotaFiscal;
    }

    public void setNumeroNotaFiscal(String numeroNotaFiscal) {
        this.numeroNotaFiscal = numeroNotaFiscal;
    }

    public TipoNotaFiscal getTipoNotaFiscal() {
        return tipoNotaFiscal;
    }

    public void setTipoNotaFiscal(TipoNotaFiscal tipoNotaFiscal) {
        this.tipoNotaFiscal = tipoNotaFiscal;
    }
    
}
