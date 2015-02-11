/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.generated.model;

import br.com.cirurgica.model.Pedido;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
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
@Table(name = "notafiscal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NotaFiscal.findAll", query = "SELECT n FROM NotaFiscal n"),
    @NamedQuery(name = "NotaFiscal.findByCdNotaFiscal", query = "SELECT n FROM NotaFiscal n WHERE n.cdNotaFiscal = :cdNotaFiscal"),
    @NamedQuery(name = "NotaFiscal.findByNumeroNotaFiscal", query = "SELECT n FROM NotaFiscal n WHERE n.numeroNotaFiscal = :numeroNotaFiscal"),
    @NamedQuery(name = "NotaFiscal.findByNumeroTipoNotaFiscal"
             , query = "SELECT n FROM NotaFiscal n WHERE n.numeroNotaFiscal = :numeroNotaFiscal AND n.cdTipoNotaFiscal = :tipoNotaFiscal")})
public class NotaFiscal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cd_nota_fiscal")
    private Integer cdNotaFiscal;
    @Column(name = "numero_nota_fiscal")
    private String numeroNotaFiscal;
    @JoinColumn(name = "cd_tipo_nota_fiscal", referencedColumnName = "cd_tipo_nota_fiscal")
    @ManyToOne(optional = false)
    private TipoNotaFiscal cdTipoNotaFiscal;
    @OneToMany(mappedBy = "cdNotaFiscal")
    private Collection<Venda> vendaCollection;
    @OneToMany(mappedBy = "cdNotaFiscal")
    private Collection<Pedido> pedidoCollection;

    protected transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);    
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
    
    public NotaFiscal() {
        this.numeroNotaFiscal = "";
    }

    public NotaFiscal(Integer cdNotaFiscal) {
        this();
        this.cdNotaFiscal = cdNotaFiscal;
    }
    public NotaFiscal(TipoNotaFiscal tipo) {
        this();
        this.cdTipoNotaFiscal = tipo;
    }    
    
    public NotaFiscal(Integer cdNotaFiscal, TipoNotaFiscal tipo) {
        this(cdNotaFiscal);
        this.cdTipoNotaFiscal = tipo;
    }    
    
    public Integer getCdNotaFiscal() {
        return cdNotaFiscal;
    }

    public String getNumeroNotaFiscal() {
        return numeroNotaFiscal;
    }

    public TipoNotaFiscal getCdTipoNotaFiscal() {
        return cdTipoNotaFiscal;
    }
    
    public void setCdNotaFiscal(Integer cdNotaFiscal) {
        Integer oldCdNotaFiscal = this.cdNotaFiscal;
        this.cdNotaFiscal = cdNotaFiscal;
        propertyChangeSupport.firePropertyChange("cdNotaFiscal", oldCdNotaFiscal, cdNotaFiscal);
    }

    public void setNumeroNotaFiscal(String numeroNotaFiscal) {
        String oldNumeroNotaFiscal = this.numeroNotaFiscal;
        this.numeroNotaFiscal = numeroNotaFiscal;
        propertyChangeSupport.firePropertyChange("numeroNotaFiscal", oldNumeroNotaFiscal, numeroNotaFiscal);
    }


    public void setCdTipoNotaFiscal(TipoNotaFiscal cdTipoNotaFiscal) {
        TipoNotaFiscal oldCdTipoNotaFiscal = this.cdTipoNotaFiscal;
        this.cdTipoNotaFiscal = cdTipoNotaFiscal;
        propertyChangeSupport.firePropertyChange("cdTipoNotaFiscal", oldCdTipoNotaFiscal, cdTipoNotaFiscal);
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Venda> getVendaCollection() {
        return vendaCollection;
    }

    public void setVendaCollection(Collection<Venda> vendaCollection) {
        this.vendaCollection = vendaCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Pedido> getPedidoCollection() {
        return pedidoCollection;
    }

    public void setPedidoCollection(Collection<Pedido> pedidoCollection) {
        this.pedidoCollection = pedidoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdNotaFiscal != null ? cdNotaFiscal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotaFiscal)) {
            return false;
        }
        NotaFiscal other = (NotaFiscal) object;
        if ((this.cdNotaFiscal == null && other.cdNotaFiscal != null) || (this.cdNotaFiscal != null && !this.cdNotaFiscal.equals(other.cdNotaFiscal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "NotaFiscal[ numeroNotaFiscal=" + numeroNotaFiscal + ", tipo=" + cdTipoNotaFiscal +" ]";
    }
    
}
