/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.generated.model;

import java.io.Serializable;
import br.com.cirurgica.model.Fornecedor;
import br.com.cirurgica.model.Pessoa;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "cidade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cidade.findAll", query = "SELECT c FROM Cidade c"),
    @NamedQuery(name = "Cidade.findByCdCidade", query = "SELECT c FROM Cidade c WHERE c.cdCidade = :cdCidade"),
    @NamedQuery(name = "Cidade.findByNmCidade", query = "SELECT c FROM Cidade c WHERE c.nmCidade = :nmCidade"),
    @NamedQuery(name = "Cidade.findByCdEstado", query = "SELECT c FROM Cidade c WHERE c.estado = :estado")})
public class Cidade implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cd_cidade")
    private Integer cdCidade;
    private static final long serialVersionUID = 1L;
    @Column(name = "nm_cidade")
    private String nmCidade;
    @OneToMany(mappedBy = "cdCidade")
    private Collection<Fornecedor> fornecedorCollection;
    @OneToMany(mappedBy = "cdCidade")
    private Collection<Pessoa> pessoaCollection;
    @JoinColumn(name = "cd_estado", referencedColumnName = "cd_estado", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Estado estado;

    public Cidade() {
    }

    public String getNmCidade() {
        return nmCidade;
    }

    public void setNmCidade(String nmCidade) {
        this.nmCidade = nmCidade;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Fornecedor> getFornecedorCollection() {
        return fornecedorCollection;
    }

    public void setFornecedorCollection(Collection<Fornecedor> fornecedorCollection) {
        this.fornecedorCollection = fornecedorCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Pessoa> getPessoaCollection() {
        return pessoaCollection;
    }

    public void setPessoaCollection(Collection<Pessoa> pessoaCollection) {
        this.pessoaCollection = pessoaCollection;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Cidade(Integer cdCidade) {
        this.cdCidade = cdCidade;
    }

    public Integer getCdCidade() {
        return cdCidade;
    }

    public void setCdCidade(Integer cdCidade) {
        this.cdCidade = cdCidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdCidade != null ? cdCidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cidade)) {
            return false;
        }
        Cidade other = (Cidade) object;
        if ((this.cdCidade == null && other.cdCidade != null) || (this.cdCidade != null && !this.cdCidade.equals(other.cdCidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cirurgica.generated.model.Cidade[ cdCidade=" + cdCidade + " ]";
    }
    
}
