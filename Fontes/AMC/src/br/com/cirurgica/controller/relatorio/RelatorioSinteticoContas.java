/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.controller.relatorio;

/**
 *
 * @author dfelix3
 */
public class RelatorioSinteticoContas extends RelatorioController {

    @Override
    public void imprimir() {
        super.imprimir("report/sinteticocontas/SinteticoContas.jasper", "Sintético de Despesas - Relatório");
    }
    public static void main(String args[]){
        new RelatorioSinteticoContas().imprimir();
    }
}
