/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.controller.relatorio;

/**
 *
 * @author dfelix3
 */
public class RelatorioContasPagasController extends RelatorioController {

    @Override
    public void imprimir() {
        super.imprimir("report/contaspagas/ContasPagas.jasper", "Contas Pagas - Relatório");
    }
    public static void main(String args[]){
        new RelatorioContasPagasController().imprimir();
    }
}
