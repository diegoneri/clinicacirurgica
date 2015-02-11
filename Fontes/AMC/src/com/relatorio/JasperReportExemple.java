/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.relatorio;

/**
 *
 * @author 13b Pessoal
 */
import br.com.cirurgica.dao.CirurgicaConnectionManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class JasperReportExemple {

    public JasperReportExemple() {
    }

    public void gerar(String layout) throws JRException, SQLException, ClassNotFoundException {
        //gerando o jasper design
        JasperDesign desenho = JRXmlLoader.load(layout);
//        JasperDesign desenho = new JasperDesign();

        //compila o relatório
        JasperReport relatorio = JasperCompileManager.compileReport(desenho);

        //estabelece conexão

        Connection con = CirurgicaConnectionManager.getConnection();
        Statement stm = con.createStatement();
        String query = "select nm_pessoa from pessoa";
        ResultSet rs = stm.executeQuery(query);

        //implementação da interface JRDataSource para DataSource ResultSet
        JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);

        //executa o relatório
        Map parametros = new HashMap();
        parametros.put("nota", new Double(10));
        JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, jrRS);
        JasperExportManager.exportReportToPdfFile(impressao, "Lista de Pessoas.pdf");

        //exibe o resultado
        JasperViewer viewer = new JasperViewer(impressao, true);
        viewer.show();
    }

//    public static void main(String[] args) {
//        try {
//            new JasperReportExemple().gerar("report.jrxml");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
