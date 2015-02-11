/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.model;

/**
 *
 * @author 13b Pessoal
 */
public class VendaProduto {
    
    private Integer cdProduto;
    private Integer cdVenda;
    private Integer qtdeProduto;
    
    public VendaProduto(){
    
    
    }

    public Integer getCdProduto() {
        return cdProduto;
    }

    public Integer getCdVenda() {
        return cdVenda;
    }

    public Integer getQtdeProduto() {
        return qtdeProduto;
    }

    public void setCdProduto(Integer cdProduto) {
        this.cdProduto = cdProduto;
    }

    public void setQtdeProduto(Integer qtdeProduto) {
        this.qtdeProduto = qtdeProduto;
    }

    public void setCdVenda(Integer cdVenda) {
        this.cdVenda = cdVenda;
    }

           
    
    
}
