/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author dfelix3
 */
@Entity
@Table(name = "login")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l"),
    @NamedQuery(name = "Login.findByCdLogin", query = "SELECT l FROM Login l WHERE l.cdLogin = :cdLogin"),
    @NamedQuery(name = "Login.findByNmLogin", query = "SELECT l FROM Login l WHERE l.nmLogin = :nmLogin"),
    @NamedQuery(name = "Login.findByNmLoginSenha", query = "SELECT l FROM Login l WHERE l.nmLogin = :nmLogin AND l.nmSenha = :nmSenha"),
    @NamedQuery(name = "Login.findByNmSenha", query = "SELECT l FROM Login l WHERE l.nmSenha = :nmSenha")})
public class Login extends AbstractModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cd_login")
    private Integer cdLogin;
    @Column(name = "nm_login")
    private String nmLogin;
    @Column(name = "nm_senha")
    private String nmSenha;
    @OneToOne(fetch= FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Usuario cdUsuario;

    public Login() {
    }

    public Login(Integer cdLogin) {
        this.cdLogin = cdLogin;
    }

    public Integer getCdLogin() {
        return cdLogin;
    }

    public void setCdLogin(Integer cdLogin) {
        this.cdLogin = cdLogin;
    }

    public String getNmLogin() {
        return nmLogin;
    }

    public void setNmLogin(String nmLogin) {
        this.nmLogin = nmLogin;
    }

    public String getNmSenha() {
        return nmSenha;
    }

    public void setNmSenha(String nmSenha) {
        this.nmSenha = nmSenha;
    }

    public Usuario getCdUsuario() {
        return cdUsuario;
    }

    public void setCdUsuario(Usuario cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdLogin != null ? cdLogin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Login)) {
            return false;
        }
        Login other = (Login) object;
        if ((this.cdLogin == null && other.cdLogin != null) || (this.cdLogin != null && !this.cdLogin.equals(other.cdLogin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cirurgica.generated.model.Login[ cdLogin=" + cdLogin + " ]";
    }
    
}
