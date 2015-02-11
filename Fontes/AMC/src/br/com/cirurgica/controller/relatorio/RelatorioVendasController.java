    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.controller.relatorio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dfelix3
 */
public class RelatorioVendasController extends RelatorioController {

    private Date dataInicio;
    private Date dataFim;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public RelatorioVendasController(Date dataInicio, Date dataFim) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    @Override
    public void imprimir() {
        Map params = new HashMap();
        params.put("dataInicioArg", sdf.format(dataInicio));
        params.put("dataFimArg", sdf.format(dataFim));
        super.imprimir("report/vendasintervalo/VendasIntervalo.jasper"
                , "Vendas por Período - Relatório", params);
    }

    public static void main(String args[]) {
        try {
            new RelatorioVendasController(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2010"), new Date());
        } catch (ParseException ex) {
            Logger.getLogger(RelatorioVendasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
