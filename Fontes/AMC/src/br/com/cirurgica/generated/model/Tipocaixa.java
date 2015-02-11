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
@Table(name = "tipocaixa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipocaixa.findAll", query = "SELECT t FROM Tipocaixa t"),
    @NamedQuery(name = "Tipocaixa.findByCdTipoCaixa", query = "SELECT t FROM Tipocaixa t WHERE t.cdTipoCaixa = :cdTipoCaixa"),
    @NamedQuery(name = "Tipocaixa.findByNmTipoCaixa", query = "SELECT t FROM Tipocaixa t WHERE t.nmTipoCaixa = :nmTipoCaixa")})
public class Tipocaixa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cd_tipo_caixa")
    private Integer cdTipoCaixa;
    @Column(name = "nm_tipo_caixa")
    private String nmTipoCaixa;
    @OneToMany(mappedBy = "cdTipoCaixa")
    private Collection<Caixa> caixaCollection;

    public Tipocaixa() {
    }

    public Tipocaixa(Integer cdTipoCaixa) {
        this.cdTipoCaixa = cdTipoCaixa;
    }

    public Integer getCdTipoCaixa() {
        return cdTipoCaixa;
    }

    public void setCdTipoCaixa(Integer cdTipoCaixa) {
        this.cdTipoCaixa = cdTipoCaixa;
    }

    public String getNmTipoCaixa() {
        return nmTipoCaixa;
    }

    public void setNmTipoCaixa(String nmTipoCaixa) {
        this.nmTipoCaixa = nmTipoCaixa;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Caixa> getCaixaCollection() {
        return caixaCollection;
    }

    public void setCaixaCollection(Collection<Caixa> caixaCollection) {
        this.caixaCollection = caixaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdTipoCaixa != null ? cdTipoCaixa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipocaixa)) {
            return false;
        }
        Tipocaixa other = (Tipocaixa) object;
        if ((this.cdTipoCaixa == null && other.cdTipoCaixa != null) || (this.cdTipoCaixa != null && !this.cdTipoCaixa.equals(other.cdTipoCaixa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cirurgica.generated.model.Tipocaixa[ cdTipoCaixa=" + cdTipoCaixa + " ]";
    }
    
}
