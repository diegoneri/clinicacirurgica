/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.controller.relatorio;

/**
 *
 * @author dfelix3
 */
public class RelatorioContasPagarController extends RelatorioController {

    @Override
    public void imprimir() {
        super.imprimir("report/contaspagar/ContasPagar.jasper", "Contas a Pagar - Relatório");
    }
    public static void main(String args[]){
        new RelatorioContasPagarController().imprimir();
    }
}
