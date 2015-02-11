/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.generated.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author dfelix3
 */
@Entity
@Table(name = "tiponotafiscal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoNotaFiscal.findAll", query = "SELECT t FROM TipoNotaFiscal t"),
    @NamedQuery(name = "TipoNotaFiscal.findByCdTipoNotaFiscal", query = "SELECT t FROM TipoNotaFiscal t WHERE t.cdTipoNotaFiscal = :cdTipoNotaFiscal"),
    @NamedQuery(name = "TipoNotaFiscal.findByNmTipoNotaFiscal", query = "SELECT t FROM TipoNotaFiscal t WHERE t.nmTipoNotaFiscal = :nmTipoNotaFiscal")})
public class TipoNotaFiscal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cd_tipo_nota_fiscal")
    private Integer cdTipoNotaFiscal;
    @Column(name = "nm_tipo_nota_fiscal")
    private String nmTipoNotaFiscal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cdTipoNotaFiscal")
    private Collection<NotaFiscal> notaFiscalCollection;

    public TipoNotaFiscal() {
    }

    public TipoNotaFiscal(Integer cdTipoNotaFiscal) {
        this.cdTipoNotaFiscal = cdTipoNotaFiscal;
    }

    public Integer getCdTipoNotaFiscal() {
        return cdTipoNotaFiscal;
    }

    public void setCdTipoNotaFiscal(Integer cdTipoNotaFiscal) {
        this.cdTipoNotaFiscal = cdTipoNotaFiscal;
    }

    public String getNmTipoNotaFiscal() {
        return nmTipoNotaFiscal;
    }

    public void setNmTipoNotaFiscal(String nmTipoNotaFiscal) {
        this.nmTipoNotaFiscal = nmTipoNotaFiscal;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<NotaFiscal> getNotaFiscalCollection() {
        return notaFiscalCollection;
    }

    public void setNotaFiscalCollection(Collection<NotaFiscal> notaFiscalCollection) {
        this.notaFiscalCollection = notaFiscalCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdTipoNotaFiscal != null ? cdTipoNotaFiscal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoNotaFiscal)) {
            return false;
        }
        TipoNotaFiscal other = (TipoNotaFiscal) object;
        if ((this.cdTipoNotaFiscal == null && other.cdTipoNotaFiscal != null) || (this.cdTipoNotaFiscal != null && !this.cdTipoNotaFiscal.equals(other.cdTipoNotaFiscal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cirurgica.generated.model.TipoNotaFiscal[ cdTipoNotaFiscal=" + cdTipoNotaFiscal + " ]";
    }
    
}
