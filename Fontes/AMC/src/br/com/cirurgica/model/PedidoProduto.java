/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.model;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dfelix3
 */
@Entity
@Table(name = "pedidoproduto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PedidoProduto.findAll", query = "SELECT p FROM PedidoProduto p"),
    @NamedQuery(name = "PedidoProduto.findByCdPedido", query = "SELECT p FROM PedidoProduto p WHERE p.cdPedido = :cdPedido"),
    @NamedQuery(name = "PedidoProduto.findByCdProduto", query = "SELECT p FROM PedidoProduto p WHERE p.cdProduto = :cdProduto"),
    @NamedQuery(name = "PedidoProduto.findByQtdeProduto", query = "SELECT p FROM PedidoProduto p WHERE p.qtdeProduto = :qtdeProduto"),
    @NamedQuery(name = "PedidoProduto.findByVlProduto", query = "SELECT p FROM PedidoProduto p WHERE p.vlProduto = :vlProduto")})
public class PedidoProduto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cd_pedidoproduto")
    private Integer cdPedidoproduto;
    @Basic(optional = false)
    @Column(name = "qtde_produto")
    private int qtdeProduto;
    @Basic(optional = false)
    @Column(name = "vl_produto")
    private float vlProduto;
    @JoinColumn(name = "cd_produto", referencedColumnName = "cd_produto")
    @ManyToOne(optional = false)
    private Produto cdProduto;
    @JoinColumn(name = "cd_pedido", referencedColumnName = "cd_pedido")
    @ManyToOne(optional = false)
    private Pedido cdPedido;

    public PedidoProduto() {
    }

    public PedidoProduto(Integer cdPedidoproduto) {
        this.cdPedidoproduto = cdPedidoproduto;
    }

    public PedidoProduto(Integer cdPedidoproduto, int qtdeProduto, float vlProduto) {
        this.cdPedidoproduto = cdPedidoproduto;
        this.qtdeProduto = qtdeProduto;
        this.vlProduto = vlProduto;
    }

    public Integer getCdPedidoproduto() {
        return cdPedidoproduto;
    }

    public void setCdPedidoproduto(Integer cdPedidoproduto) {
        this.cdPedidoproduto = cdPedidoproduto;
    }

    public int getQtdeProduto() {
        return qtdeProduto;
    }

    public void setQtdeProduto(int qtdeProduto) {
        this.qtdeProduto = qtdeProduto;
    }

    public float getVlProduto() {
        return vlProduto;
    }

    public void setVlProduto(float vlProduto) {
        this.vlProduto = vlProduto;
    }

    public Produto getCdProduto() {
        return cdProduto;
    }

    public void setCdProduto(Produto cdProduto) {
        this.cdProduto = cdProduto;
    }

    public Pedido getCdPedido() {
        return cdPedido;
    }

    public void setCdPedido(Pedido cdPedido) {
        this.cdPedido = cdPedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdPedidoproduto != null ? cdPedidoproduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoProduto)) {
            return false;
        }
        PedidoProduto other = (PedidoProduto) object;
        if ((this.cdPedidoproduto == null && other.cdPedidoproduto != null) || (this.cdPedidoproduto != null && !this.cdPedidoproduto.equals(other.cdPedidoproduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.Pedidoproduto[ cdPedidoproduto=" + cdPedidoproduto + " ]";
    }
}