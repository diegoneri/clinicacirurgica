/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.generated.model;

import br.com.cirurgica.model.Produto;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author dfelix3
 */
@Embeddable
public class VendaProdutoPK implements Serializable {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cd_vendaproduto")
    private Integer cdVendaProduto;
    @ManyToOne(optional = false)
    private Venda cdVenda;
    @ManyToOne(optional = false)
    private Produto cdProduto;

    public VendaProdutoPK() {
    }

    public VendaProdutoPK(Venda cdVenda, Produto cdProduto) {
        this.cdVenda = cdVenda;
        this.cdProduto = cdProduto;
    }

    public Venda getCdVenda() {
        return cdVenda;
    }

    public void setCdVenda(Venda cdVenda) {
        this.cdVenda = cdVenda;
    }

    public Produto getCdProduto() {
        return cdProduto;
    }

    public void setCdProduto(Produto cdProduto) {
        this.cdProduto = cdProduto;
    }

    public Integer getCdVendaProduto() {
        return cdVendaProduto;
    }

    public void setCdVendaProduto(Integer cdVendaProduto) {
        this.cdVendaProduto = cdVendaProduto;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.cdVenda);
        hash = 23 * hash + Objects.hashCode(this.cdProduto);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VendaProdutoPK other = (VendaProdutoPK) obj;
        if (!Objects.equals(this.cdVenda, other.cdVenda)) {
            return false;
        }
        if (!Objects.equals(this.cdProduto, other.cdProduto)) {
            return false;
        }
        return true;
    }


}
