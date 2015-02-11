/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.generated.model;

import java.io.Serializable;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dfelix3
 */
@Entity
@Table(name = "vendaproduto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VendaProduto.findAll", query = "SELECT v FROM VendaProduto v"),
    @NamedQuery(name = "VendaProduto.findByCdVenda", query = "SELECT v FROM VendaProduto v WHERE v.vendaProdutoPK.cdVenda = :cdVenda"),
    @NamedQuery(name = "VendaProduto.findByVlDescontoProduto", query = "SELECT v FROM VendaProduto v WHERE v.vlDescontoProduto = :vlDescontoProduto"),
    @NamedQuery(name = "VendaProduto.findByCdProduto", query = "SELECT v FROM VendaProduto v WHERE v.vendaProdutoPK.cdProduto = :cdProduto"),
    @NamedQuery(name = "VendaProduto.findByQtdeProduto", query = "SELECT v FROM VendaProduto v WHERE v.qtdeProduto = :qtdeProduto"),
    @NamedQuery(name = "VendaProduto.findByVlVendaProduto", query = "SELECT v FROM VendaProduto v WHERE v.vlVendaProduto = :vlVendaProduto")})

@AssociationOverrides({
            @AssociationOverride(name = "vendaProdutoPK.cdVendaProduto",
			joinColumns = @JoinColumn(name = "cd_vendaProduto")),
		@AssociationOverride(name = "vendaProdutoPK.cdVenda",
			joinColumns = @JoinColumn(name = "cd_venda")),
		@AssociationOverride(name = "vendaProdutoPK.cdProduto",
			joinColumns = @JoinColumn(name = "cd_produto")) })
public class VendaProduto implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VendaProdutoPK vendaProdutoPK = new VendaProdutoPK();
    @Basic(optional = false)
    @Column(name = "vl_desconto_produto")
    private float vlDescontoProduto;
    @Column(name = "qtde_produto")
    private Integer qtdeProduto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "vl_venda_produto")
    private Float vlVendaProduto;

    public VendaProduto() {
    }

    public VendaProduto(VendaProdutoPK vendaProdutoPK) {
        this.vendaProdutoPK = vendaProdutoPK;
    }

    public VendaProduto(VendaProdutoPK vendaProdutoPK, float vlDescontoProduto) {
        this.vendaProdutoPK = vendaProdutoPK;
        this.vlDescontoProduto = vlDescontoProduto;
    }

    public VendaProdutoPK getVendaProdutoPK() {
        return vendaProdutoPK;
    }

    public void setVendaProdutoPK(VendaProdutoPK vendaProdutoPK) {
        this.vendaProdutoPK = vendaProdutoPK;
    }

    public float getVlDescontoProduto() {
        return vlDescontoProduto;
    }

    public void setVlDescontoProduto(float vlDescontoProduto) {
        this.vlDescontoProduto = vlDescontoProduto;
    }

    public Integer getQtdeProduto() {
        return qtdeProduto;
    }

    public void setQtdeProduto(Integer qtdeProduto) {
        this.qtdeProduto = qtdeProduto;
    }

    public Float getVlVendaProduto() {
        return vlVendaProduto;
    }

    public void setVlVendaProduto(Float vlVendaProduto) {
        this.vlVendaProduto = vlVendaProduto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vendaProdutoPK != null ? vendaProdutoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VendaProduto)) {
            return false;
        }
        VendaProduto other = (VendaProduto) object;
        if ((this.vendaProdutoPK == null && other.vendaProdutoPK != null) || (this.vendaProdutoPK != null && !this.vendaProdutoPK.equals(other.vendaProdutoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cirurgica.generated.model.VendaProduto[ vendaProdutoPK=" + vendaProdutoPK + " ]";
    }
    public Float getTotalItem(){
        return this.qtdeProduto * (this.vlVendaProduto - this.vlDescontoProduto);
    }
}
