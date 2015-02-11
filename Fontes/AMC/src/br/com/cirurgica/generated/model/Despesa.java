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
@Table(name = "despesa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Despesa.findAll", query = "SELECT d FROM Despesa d"),
    @NamedQuery(name = "Despesa.findByCdDespesa", query = "SELECT d FROM Despesa d WHERE d.cdDespesa = :cdDespesa"),
    @NamedQuery(name = "Despesa.findByValorDespesa", query = "SELECT d FROM Despesa d WHERE d.valorDespesa = :valorDespesa"),
    @NamedQuery(name = "Despesa.findByDataVencimento", query = "SELECT d FROM Despesa d WHERE d.dataVencimento = :dataVencimento"),
    @NamedQuery(name = "Despesa.findByIsPaga", query = "SELECT d FROM Despesa d WHERE d.isPaga = :isPaga"),
    @NamedQuery(name = "Despesa.findByDataReferente", query = "SELECT d FROM Despesa d WHERE d.dataReferente = :dataReferente"),
    @NamedQuery(name = "Despesa.findByDescricao", query = "SELECT d FROM Despesa d WHERE d.descricao = :descricao")})
public class Despesa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cd_despesa")
    private Integer cdDespesa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_despesa")
    private Float valorDespesa;
    @Column(name = "data_vencimento")
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;
    @Column(name = "isPaga")
    private Boolean isPaga;
    @Basic(optional = false)
    @Column(name = "data_referente")
    @Temporal(TemporalType.DATE)
    private Date dataReferente;
    @Column(name = "descricao")
    private String descricao;
    @JoinColumn(name = "cd_tipo_despesa", referencedColumnName = "cd_tipo_despesa")
    @ManyToOne
    private TipoDespesa cdTipoDespesa;

    public Despesa() {
    }

    public Despesa(Integer cdDespesa) {
        this.cdDespesa = cdDespesa;
    }

    public Despesa(Integer cdDespesa, Date dataReferente) {
        this.cdDespesa = cdDespesa;
        this.dataReferente = dataReferente;
    }

    public Integer getCdDespesa() {
        return cdDespesa;
    }

    public void setCdDespesa(Integer cdDespesa) {
        this.cdDespesa = cdDespesa;
    }

    public Float getValorDespesa() {
        return valorDespesa;
    }

    public void setValorDespesa(Float valorDespesa) {
        this.valorDespesa = valorDespesa;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Boolean getIsPaga() {
        return isPaga;
    }

    public void setIsPaga(Boolean isPaga) {
        this.isPaga = isPaga;
    }

    public Date getDataReferente() {
        return dataReferente;
    }

    public void setDataReferente(Date dataReferente) {
        this.dataReferente = dataReferente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoDespesa getCdTipoDespesa() {
        return cdTipoDespesa;
    }

    public void setCdTipoDespesa(TipoDespesa cdTipoDespesa) {
        this.cdTipoDespesa = cdTipoDespesa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdDespesa != null ? cdDespesa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Despesa)) {
            return false;
        }
        Despesa other = (Despesa) object;
        if ((this.cdDespesa == null && other.cdDespesa != null) || (this.cdDespesa != null && !this.cdDespesa.equals(other.cdDespesa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cirurgica.generated.model.Despesa[ cdDespesa=" + cdDespesa + " ]";
    }
    
}
