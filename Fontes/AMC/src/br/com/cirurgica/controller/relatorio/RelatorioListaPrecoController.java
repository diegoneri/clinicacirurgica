/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.controller.relatorio;

/**
 *
 * @author dfelix3
 */
public class RelatorioListaPrecoController extends RelatorioController {

    @Override
    public void imprimir() {
        super.imprimir("report/listapreco/ListaPreco.jasper", "Lista de Preços - Relatório");
    }
    public static void main(String args[]){
        new RelatorioListaPrecoController().imprimir();
    }
}
