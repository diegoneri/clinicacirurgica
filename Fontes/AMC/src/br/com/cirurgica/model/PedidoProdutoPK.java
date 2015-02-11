/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author dfelix3
 */
@Embeddable
public class PedidoProdutoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "cd_pedido")
    private int cdPedido;
    @Basic(optional = false)
    @Column(name = "cd_produto")
    private int cdProduto;

    public PedidoProdutoPK() {
    }

    public PedidoProdutoPK(int cdPedido, int cdProduto) {
        this.cdPedido = cdPedido;
        this.cdProduto = cdProduto;
    }

    public int getCdPedido() {
        return cdPedido;
    }

    public void setCdPedido(int cdPedido) {
        this.cdPedido = cdPedido;
    }

    public int getCdProduto() {
        return cdProduto;
    }

    public void setCdProduto(int cdProduto) {
        this.cdProduto = cdProduto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) cdPedido;
        hash += (int) cdProduto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoProdutoPK)) {
            return false;
        }
        PedidoProdutoPK other = (PedidoProdutoPK) object;
        if (this.cdPedido != other.cdPedido) {
            return false;
        }
        if (this.cdProduto != other.cdProduto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cirurgica.generated.model.PedidoProdutoPK[ cdPedido=" + cdPedido + ", cdProduto=" + cdProduto + " ]";
    }
    
}
