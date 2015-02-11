/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.model;

/**
 *
 * @author Leo
 */
public class EstadoCivil {

    private Integer cdEsatdoVicil;
    private String nmEstadoCivil;

    public EstadoCivil() {
    }

    /**
     * @return the cdEsatdoVicil
     */
    public Integer getCdEsatdoVicil() {
        return cdEsatdoVicil;
    }

    /**
     * @param cdEsatdoVicil the cdEsatdoVicil to set
     */
    public void setCdEsatdoVicil(Integer cdEsatdoVicil) {
        this.cdEsatdoVicil = cdEsatdoVicil;
    }

    /**
     * @return the nmEstadoCivil
     */
    public String getNmEstadoCivil() {
        return nmEstadoCivil;
    }

    /**
     * @param nmEstadoCivil the nmEstadoCivil to set
     */
    public void setNmEstadoCivil(String nmEstadoCivil) {
        this.nmEstadoCivil = nmEstadoCivil;
    }
}
