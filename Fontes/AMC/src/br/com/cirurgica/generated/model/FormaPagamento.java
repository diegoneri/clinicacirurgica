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
@Table(name = "formapagamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormaPagamento.findAll", query = "SELECT f FROM FormaPagamento f"),
    @NamedQuery(name = "FormaPagamento.findByCdFormaPagamento", query = "SELECT f FROM FormaPagamento f WHERE f.cdFormaPagamento = :cdFormaPagamento"),
    @NamedQuery(name = "FormaPagamento.findByNmFormaPagamento", query = "SELECT f FROM FormaPagamento f WHERE f.nmFormaPagamento = :nmFormaPagamento")})
public class FormaPagamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cd_forma_pagamento")
    private Integer cdFormaPagamento;
    @Column(name = "nm_forma_pagamento")
    private String nmFormaPagamento;
    @OneToMany(mappedBy = "cdFormaPagamento")
    private Collection<Venda> vendaCollection;

    public FormaPagamento() {
    }

    public FormaPagamento(Integer cdFormaPagamento) {
        this.cdFormaPagamento = cdFormaPagamento;
    }

    public Integer getCdFormaPagamento() {
        return cdFormaPagamento;
    }

    public void setCdFormaPagamento(Integer cdFormaPagamento) {
        this.cdFormaPagamento = cdFormaPagamento;
    }

    public String getNmFormaPagamento() {
        return nmFormaPagamento;
    }

    public void setNmFormaPagamento(String nmFormaPagamento) {
        this.nmFormaPagamento = nmFormaPagamento;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Venda> getVendaCollection() {
        return vendaCollection;
    }

    public void setVendaCollection(Collection<Venda> vendaCollection) {
        this.vendaCollection = vendaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdFormaPagamento != null ? cdFormaPagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormaPagamento)) {
            return false;
        }
        FormaPagamento other = (FormaPagamento) object;
        if ((this.cdFormaPagamento == null && other.cdFormaPagamento != null) || (this.cdFormaPagamento != null && !this.cdFormaPagamento.equals(other.cdFormaPagamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getNmFormaPagamento();
    }
    
}
