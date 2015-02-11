/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.generated.model;

import br.com.cirurgica.model.Pessoa;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author dfelix3
 */
@Entity
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByCdCliente", query = "SELECT c FROM Cliente c WHERE c.cdCliente = :cdCliente"),
    @NamedQuery(name = "Cliente.findByDataCadastro", query = "SELECT c FROM Cliente c WHERE c.dataCadastro = :dataCadastro"),
    @NamedQuery(name = "Cliente.findByCdPessoa", query = "SELECT c FROM Cliente c WHERE c.cdPessoa = :cdPessoa"),
    @NamedQuery(name = "Cliente.findByDataUltimaCompra", query = "SELECT c FROM Cliente c WHERE c.dataUltimaCompra = :dataUltimaCompra")})
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cd_cliente")
    private Integer cdCliente;
    @Column(name = "data_cadastro")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    @JoinColumn(name = "cd_pessoa", referencedColumnName = "cd_pessoa")
    @ManyToOne
    private Pessoa cdPessoa;
    @Column(name = "data_ultima_compra")
    @Temporal(TemporalType.DATE)
    private Date dataUltimaCompra;
    @OneToMany(mappedBy = "cdCliente")
    private Collection<Venda> vendaCollection;

    public Cliente() {
    }

    public Cliente(Integer cdCliente) {
        this.cdCliente = cdCliente;
    }

    public Integer getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(Integer cdCliente) {
        this.cdCliente = cdCliente;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Pessoa getCdPessoa() {
        return cdPessoa;
    }

    public void setCdPessoa(Pessoa cdPessoa) {
        this.cdPessoa = cdPessoa;
    }

    public Date getDataUltimaCompra() {
        return dataUltimaCompra;
    }

    public void setDataUltimaCompra(Date dataUltimaCompra) {
        this.dataUltimaCompra = dataUltimaCompra;
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
        hash += (cdCliente != null ? cdCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.cdCliente == null && other.cdCliente != null) || (this.cdCliente != null && !this.cdCliente.equals(other.cdCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + cdCliente + "] - " + this.getCdPessoa().getNmPessoa();
    }
    
}
