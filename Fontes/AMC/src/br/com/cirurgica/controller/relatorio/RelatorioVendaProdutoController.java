/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.controller.relatorio;

import br.com.cirurgica.model.Produto;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dfelix3
 */
public class RelatorioVendaProdutoController extends RelatorioController {

    private Produto produto;

    public RelatorioVendaProdutoController(Produto produto) {
        this.produto = produto;
    }

    public static void main(String args[]) {
        Produto p = new Produto();
        p.setCdProduto(1);
        p.setNome("Pneu");
        new RelatorioVendaProdutoController(p);
    }

    @Override
    public void imprimir() {
        Map params = new HashMap();
        params.put("codigoProduto", produto.getCdProduto());
        super.imprimir("report/vendasproduto/VendasProduto.jasper", "Vendas Por Produto (" + produto.getNome() + ") - Relatório", params);
    }
}
