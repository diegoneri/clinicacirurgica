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
import br.com.cirurgica.model.Pessoa;

/**
 *
 * @author dfelix3
 */
@Entity
@Table(name = "estadocivil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estadocivil.findAll", query = "SELECT e FROM Estadocivil e"),
    @NamedQuery(name = "Estadocivil.findByCdEstadoCivil", query = "SELECT e FROM Estadocivil e WHERE e.cdEstadoCivil = :cdEstadoCivil"),
    @NamedQuery(name = "Estadocivil.findByNmEstadoCivil", query = "SELECT e FROM Estadocivil e WHERE e.nmEstadoCivil = :nmEstadoCivil")})
public class Estadocivil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cd_estado_civil")
    private Integer cdEstadoCivil;
    @Column(name = "nm_estado_civil")
    private String nmEstadoCivil;
    @OneToMany(mappedBy = "cdEstadoCivil")
    private Collection<Pessoa> pessoaCollection;

    public Estadocivil() {
    }

    public Estadocivil(Integer cdEstadoCivil) {
        this.cdEstadoCivil = cdEstadoCivil;
    }

    public Integer getCdEstadoCivil() {
        return cdEstadoCivil;
    }

    public void setCdEstadoCivil(Integer cdEstadoCivil) {
        this.cdEstadoCivil = cdEstadoCivil;
    }

    public String getNmEstadoCivil() {
        return nmEstadoCivil;
    }

    public void setNmEstadoCivil(String nmEstadoCivil) {
        this.nmEstadoCivil = nmEstadoCivil;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Pessoa> getPessoaCollection() {
        return pessoaCollection;
    }

    public void setPessoaCollection(Collection<Pessoa> pessoaCollection) {
        this.pessoaCollection = pessoaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdEstadoCivil != null ? cdEstadoCivil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estadocivil)) {
            return false;
        }
        Estadocivil other = (Estadocivil) object;
        if ((this.cdEstadoCivil == null && other.cdEstadoCivil != null) || (this.cdEstadoCivil != null && !this.cdEstadoCivil.equals(other.cdEstadoCivil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cirurgica.generated.model.Estadocivil[ cdEstadoCivil=" + cdEstadoCivil + " ]";
    }
    
}
