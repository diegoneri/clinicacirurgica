/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.controller.relatorio;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dfelix3
 */
public class RelatorioEstoqueController extends RelatorioController {

    private Date dataRef;

    public RelatorioEstoqueController(Date dataReferenciaRelatorio) {
        this.dataRef = dataReferenciaRelatorio;

    }

    public static void main(String args[]) {
        RelatorioEstoqueController rel = new RelatorioEstoqueController(new Date());
        rel.imprimir();
    }

    @Override
    public void imprimir() {
        Map params = new HashMap();
        params.put("dataRefArg", dataRef);
        super.imprimir("report/estoque/Estoque.jasper", "Estoque - Relatório", params);
    }
}
