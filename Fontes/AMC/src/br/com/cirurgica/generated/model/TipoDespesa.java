/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.generated.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "tipodespesa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDespesa.findAll", query = "SELECT t FROM TipoDespesa t"),
    @NamedQuery(name = "TipoDespesa.findByCdTipoDespesa", query = "SELECT t FROM TipoDespesa t WHERE t.cdTipoDespesa = :cdTipoDespesa"),
    @NamedQuery(name = "TipoDespesa.findByNmTipoDespesa", query = "SELECT t FROM TipoDespesa t WHERE t.nmTipoDespesa = :nmTipoDespesa")})
public class TipoDespesa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cd_tipo_despesa")
    private Integer cdTipoDespesa;
    @Column(name = "nm_tipo_despesa")
    private String nmTipoDespesa;
    @OneToMany(mappedBy = "cdTipoDespesa")
    private Collection<Despesa> despesaCollection;

    public TipoDespesa() {
    }

    public TipoDespesa(Integer cdTipoDespesa) {
        this.cdTipoDespesa = cdTipoDespesa;
    }

    public Integer getCdTipoDespesa() {
        return cdTipoDespesa;
    }

    public void setCdTipoDespesa(Integer cdTipoDespesa) {
        this.cdTipoDespesa = cdTipoDespesa;
    }

    public String getNmTipoDespesa() {
        return nmTipoDespesa;
    }

    public void setNmTipoDespesa(String nmTipoDespesa) {
        this.nmTipoDespesa = nmTipoDespesa;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Despesa> getDespesaCollection() {
        return despesaCollection;
    }

    public void setDespesaCollection(Collection<Despesa> despesaCollection) {
        this.despesaCollection = despesaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdTipoDespesa != null ? cdTipoDespesa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDespesa)) {
            return false;
        }
        TipoDespesa other = (TipoDespesa) object;
        if ((this.cdTipoDespesa == null && other.cdTipoDespesa != null) || (this.cdTipoDespesa != null && !this.cdTipoDespesa.equals(other.cdTipoDespesa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cirurgica.generated.model.TipoDespesa[ cdTipoDespesa=" + cdTipoDespesa + " ]";
    }
    
}
