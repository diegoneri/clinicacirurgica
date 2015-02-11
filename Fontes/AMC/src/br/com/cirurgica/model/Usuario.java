/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.model;

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
import br.com.cirurgica.generated.model.Venda;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeSupport;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author dfelix3
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByCdUsuario", query = "SELECT u FROM Usuario u WHERE u.cdUsuario = :cdUsuario"),
    @NamedQuery(name = "Usuario.findByTipoUsuario", query = "SELECT u FROM Usuario u WHERE u.tipoUsuario = :tipoUsuario")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String PROP_CDUSUARIO = "PROP_CDUSUARIO";
    public static final String PROP_TIPOUSUARIO = "PROP_TIPOUSUARIO";
    public static final String PROP_CDPESSOA = "PROP_CDPESSOA";
    public static final String PROP_VENDACOLLECTION = "PROP_VENDACOLLECTION";
    public static final String PROP_LOGINCOLLECTION = "PROP_LOGINCOLLECTION";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cd_usuario")
    private Integer cdUsuario;
    @Column(name = "tipo_usuario")
    private String tipoUsuario;
    @JoinColumn(name = "cd_pessoa", referencedColumnName = "cd_pessoa")
    @ManyToOne
    private Pessoa cdPessoa;
    @OneToMany(mappedBy = "cdUsuario")
    private Collection<Venda> vendaCollection;
    
    @OneToOne(fetch= FetchType.LAZY, mappedBy = "cdUsuario", cascade= CascadeType.ALL)
    private Login login;
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    private final transient VetoableChangeSupport vetoableChangeSupport = new java.beans.VetoableChangeSupport(this);

    public Usuario() {
    }

    public Usuario(Integer cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    public Integer getCdUsuario() {
        return cdUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public Pessoa getCdPessoa() {
        return cdPessoa;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Venda> getVendaCollection() {
        return vendaCollection;
    }

    public Login getLogin() {
        return login;
    }
  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdUsuario != null ? cdUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.cdUsuario == null && other.cdUsuario != null) || (this.cdUsuario != null && !this.cdUsuario.equals(other.cdUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuário " + cdPessoa.getNmPessoa() + " ]";
    }

    public void setCdUsuario(Integer cdUsuario) throws PropertyVetoException {
        java.lang.Integer oldCdUsuario = cdUsuario;
        vetoableChangeSupport.fireVetoableChange(PROP_CDUSUARIO, oldCdUsuario, cdUsuario);
        this.cdUsuario = cdUsuario;
        propertyChangeSupport.firePropertyChange(PROP_CDUSUARIO, oldCdUsuario, cdUsuario);
    }

    public void setTipoUsuario(String tipoUsuario) throws PropertyVetoException {
        java.lang.String oldTipoUsuario = tipoUsuario;
        vetoableChangeSupport.fireVetoableChange(PROP_TIPOUSUARIO, oldTipoUsuario, tipoUsuario);
        this.tipoUsuario = tipoUsuario;
        propertyChangeSupport.firePropertyChange(PROP_TIPOUSUARIO, oldTipoUsuario, tipoUsuario);
    }

    public void setCdPessoa(Pessoa cdPessoa) throws PropertyVetoException {
        br.com.cirurgica.model.Pessoa oldCdPessoa = cdPessoa;
        vetoableChangeSupport.fireVetoableChange(PROP_CDPESSOA, oldCdPessoa, cdPessoa);
        this.cdPessoa = cdPessoa;
        propertyChangeSupport.firePropertyChange(PROP_CDPESSOA, oldCdPessoa, cdPessoa);
    }

    public void setVendaCollection(Collection<Venda> vendaCollection) throws PropertyVetoException {
        java.util.Collection<br.com.cirurgica.generated.model.Venda> oldVendaCollection = vendaCollection;
        vetoableChangeSupport.fireVetoableChange(PROP_VENDACOLLECTION, oldVendaCollection, vendaCollection);
        this.vendaCollection = vendaCollection;
        propertyChangeSupport.firePropertyChange(PROP_VENDACOLLECTION, oldVendaCollection, vendaCollection);
    }

    public void setLoginCollection(Login login) throws PropertyVetoException {
        br.com.cirurgica.model.Login oldLogin = login;
        vetoableChangeSupport.fireVetoableChange(PROP_LOGINCOLLECTION, oldLogin, login);
        this.login = login;
        propertyChangeSupport.firePropertyChange(PROP_LOGINCOLLECTION, oldLogin, login);
    }
    
}
