/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.generated.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author dfelix3
 */
@Embeddable
public class CidadePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "cd_cidade")
    private int cdCidade;
    @Basic(optional = false)
    @Column(name = "cd_estado")
    private int cdEstado;

    public CidadePK() {
    }

    public CidadePK(int cdCidade, int cdEstado) {
        this.cdCidade = cdCidade;
        this.cdEstado = cdEstado;
    }

    public int getCdCidade() {
        return cdCidade;
    }

    public void setCdCidade(int cdCidade) {
        this.cdCidade = cdCidade;
    }

    public int getCdEstado() {
        return cdEstado;
    }

    public void setCdEstado(int cdEstado) {
        this.cdEstado = cdEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) cdCidade;
        hash += (int) cdEstado;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CidadePK)) {
            return false;
        }
        CidadePK other = (CidadePK) object;
        if (this.cdCidade != other.cdCidade) {
            return false;
        }
        if (this.cdEstado != other.cdEstado) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cirurgica.generated.model.CidadePK[ cdCidade=" + cdCidade + ", cdEstado=" + cdEstado + " ]";
    }
    
}
