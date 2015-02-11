/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.generated.model;

import br.com.cirurgica.model.AbstractModel;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dfelix3
 */
@Entity
@Table(name = "markup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Markup.findAll", query = "SELECT m FROM Markup m"),
    @NamedQuery(name = "Markup.findByVlMarkup", query = "SELECT m FROM Markup m WHERE m.vlMarkup = :vlMarkup"),
    @NamedQuery(name = "Markup.update", query = "UPDATE Markup m SET m.vlMarkup = :vlMarkup")})
public class Markup extends AbstractModel implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "vl_markup")
    private Double vlMarkup;

    public Markup() {
    }
    
    public Markup(Double vlMarkup) {
        this.vlMarkup = vlMarkup;
    }

    public Double getVlMarkup() {
        return vlMarkup;
    }

    public void setVlMarkup(Double vlMarkup) {
        this.vlMarkup = vlMarkup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vlMarkup != null ? vlMarkup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Markup)) {
            return false;
        }
        Markup other = (Markup) object;
        if ((this.vlMarkup == null && other.vlMarkup != null) || (this.vlMarkup != null && !this.vlMarkup.equals(other.vlMarkup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cirurgica.generated.model.Markup[ vlMarkup=" + vlMarkup + " ]";
    }
    
}
