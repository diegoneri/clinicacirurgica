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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "estado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estado.findAll", query = "SELECT e FROM Estado e"),
    @NamedQuery(name = "Estado.findByCdEstado", query = "SELECT e FROM Estado e WHERE e.cdEstado = :cdEstado"),
    @NamedQuery(name = "Estado.findByNmEstado", query = "SELECT e FROM Estado e WHERE e.nmEstado = :nmEstado"),
    @NamedQuery(name = "Estado.findByUf", query = "SELECT e FROM Estado e WHERE e.uf = :uf")})
public class Estado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cd_estado")
    private Integer cdEstado;
    @Column(name = "nm_estado")
    private String nmEstado;
    @Column(name = "UF")
    private String uf;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado")
    private Collection<Cidade> cidadeCollection;
    @JoinColumn(name = "cd_pais", referencedColumnName = "cd_pais")
    @ManyToOne
    private Pais cdPais;

    public Estado() {
    }

    public Estado(Integer cdEstado) {
        this.cdEstado = cdEstado;
    }

    public Integer getCdEstado() {
        return cdEstado;
    }

    public void setCdEstado(Integer cdEstado) {
        this.cdEstado = cdEstado;
    }

    public String getNmEstado() {
        return nmEstado;
    }

    public void setNmEstado(String nmEstado) {
        this.nmEstado = nmEstado;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Cidade> getCidadeCollection() {
        return cidadeCollection;
    }

    public void setCidadeCollection(Collection<Cidade> cidadeCollection) {
        this.cidadeCollection = cidadeCollection;
    }

    public Pais getCdPais() {
        return cdPais;
    }

    public void setCdPais(Pais cdPais) {
        this.cdPais = cdPais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdEstado != null ? cdEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estado)) {
            return false;
        }
        Estado other = (Estado) object;
        if ((this.cdEstado == null && other.cdEstado != null) || (this.cdEstado != null && !this.cdEstado.equals(other.cdEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cirurgica.generated.model.Estado[ cdEstado=" + cdEstado + " ]";
    }
    
}
