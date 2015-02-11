/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.generated.model;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dfelix3
 */
@Entity
@Table(name = "caixa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Caixa.findAll", query = "SELECT c FROM Caixa c"),
    @NamedQuery(name = "Caixa.findByCdCaixa", query = "SELECT c FROM Caixa c WHERE c.cdCaixa = :cdCaixa"),
    @NamedQuery(name = "Caixa.findByValorCaixa", query = "SELECT c FROM Caixa c WHERE c.valorCaixa = :valorCaixa"),
    @NamedQuery(name = "Caixa.findByDataLancamento", query = "SELECT c FROM Caixa c WHERE c.dataLancamento = :dataLancamento")})
public class Caixa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cd_caixa")
    private Integer cdCaixa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_caixa")
    private Float valorCaixa;
    @Column(name = "data_lancamento")
    @Temporal(TemporalType.DATE)
    private Date dataLancamento;
    @JoinColumn(name = "cd_tipo_caixa", referencedColumnName = "cd_tipo_caixa")
    @ManyToOne
    private Tipocaixa cdTipoCaixa;

    public Caixa() {
    }

    public Caixa(Integer cdCaixa) {
        this.cdCaixa = cdCaixa;
    }

    public Integer getCdCaixa() {
        return cdCaixa;
    }

    public void setCdCaixa(Integer cdCaixa) {
        this.cdCaixa = cdCaixa;
    }

    public Float getValorCaixa() {
        return valorCaixa;
    }

    public void setValorCaixa(Float valorCaixa) {
        this.valorCaixa = valorCaixa;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Tipocaixa getCdTipoCaixa() {
        return cdTipoCaixa;
    }

    public void setCdTipoCaixa(Tipocaixa cdTipoCaixa) {
        this.cdTipoCaixa = cdTipoCaixa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdCaixa != null ? cdCaixa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caixa)) {
            return false;
        }
        Caixa other = (Caixa) object;
        if ((this.cdCaixa == null && other.cdCaixa != null) || (this.cdCaixa != null && !this.cdCaixa.equals(other.cdCaixa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cirurgica.generated.model.Caixa[ cdCaixa=" + cdCaixa + " ]";
    }
    
}
