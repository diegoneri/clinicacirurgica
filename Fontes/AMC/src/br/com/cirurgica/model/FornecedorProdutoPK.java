/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author dfelix3
 */
@Embeddable
public class FornecedorProdutoPK implements Serializable {
    @Column(name = "cd_fornecedor", nullable = false)
    private Integer cdFornecedor;
    @Column(name = "cd_produto", nullable = false)
    private Integer cdProduto;

    public FornecedorProdutoPK() {
        this(-1,-1);
    }

    public FornecedorProdutoPK(Integer cdFornecedor, Integer cdProduto) {
        this.cdFornecedor = cdFornecedor;
        this.cdProduto = cdProduto;
    }

    public FornecedorProdutoPK(Integer cdFornecedor) {
        this(cdFornecedor, 0);
    }
   
    public Integer getCdFornecedor() {
        return cdFornecedor;
    }

    public void setCdFornecedor(Integer cdFornecedor) {
        this.cdFornecedor = cdFornecedor;
    }

    public Integer getCdProduto() {
        return cdProduto;
    }

    public void setCdProduto(Integer cdProduto) {
        this.cdProduto = cdProduto;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.cdFornecedor);
        hash = 97 * hash + Objects.hashCode(this.cdProduto);
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
        final FornecedorProdutoPK other = (FornecedorProdutoPK) obj;
        if (!Objects.equals(this.cdFornecedor, other.cdFornecedor)) {
            return false;
        }
        if (!Objects.equals(this.cdProduto, other.cdProduto)) {
            return false;
        }
        return true;
    }
    
}
