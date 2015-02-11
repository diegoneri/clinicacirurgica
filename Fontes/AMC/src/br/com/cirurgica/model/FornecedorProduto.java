/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dfelix3
 */
@Entity
@Table(name="fornecedorproduto", catalog="bdcirurgica")
@NamedQueries({
    @NamedQuery(name = "FornecedorProduto.findAll", query = "SELECT f FROM FornecedorProduto f"),
    @NamedQuery(name = "FornecedorProduto.findByCdFornecedor", query = "SELECT f FROM FornecedorProduto f WHERE f.id.cdFornecedor = :cdFornecedor"),
    @NamedQuery(name = "FornecedorProduto.findByCdProduto", query = "SELECT f FROM FornecedorProduto f WHERE f.id.cdProduto = :cdProduto"),
    @NamedQuery(name = "FornecedorProduto.findByDataInclusao", query = "SELECT f FROM FornecedorProduto f WHERE f.dataInclusao = :dataInclusao")})
public class FornecedorProduto implements Serializable {
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "cdFornecedor"
                       , column = 
                                @Column(name = "cd_fornecedor", nullable = false)
                          )
      , @AttributeOverride(name = "cdProduto"
                       , column =
                                @Column(name = "cd_produto", nullable = false)
                           )
                        })    
    private FornecedorProdutoPK id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cd_fornecedor", nullable = false, insertable = false, updatable = false)    
    private Fornecedor fornecedor;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cd_produto", nullable = false, insertable = false, updatable = false)    
    private Produto produto;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_inclusao", nullable = false, length = 19)
    private Date dataInclusao;

    public FornecedorProduto(FornecedorProdutoPK id, Fornecedor fornecedor, Produto produto, Date dataInclusao) {
        this.id = id;
        this.fornecedor = fornecedor;
        this.produto = produto;
        this.dataInclusao = dataInclusao;
    }

    public FornecedorProduto() {
        this(null,null,null,null);
    }  
    
    public FornecedorProdutoPK getId() {
        return id;
    }

    public void setId(FornecedorProdutoPK id) {
        this.id = id;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        final FornecedorProduto other = (FornecedorProduto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return fornecedor.getNmRazaoSocial();
    }

    public void generatePK() throws NullPointerException{
        if (this.fornecedor != null && this.produto != null){
            FornecedorProdutoPK pk = this.getId()!= null ? 
                                     this.getId() : new FornecedorProdutoPK();
            pk.setCdFornecedor(this.fornecedor.getCdFornecedor());
            pk.setCdProduto(this.produto.getCdProduto());
            this.setId(pk);
        }else{
            throw new NullPointerException("Produto ou Fornecedor inválido!");
        }
        
    }
    
}
