/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.relatorio;

import br.com.cirurgica.model.Despesa;
import br.com.cirurgica.model.TipoDespesa;
import br.com.cirurgica.model.Venda;
import br.com.cirurgica.dao.CirurgicaConnectionManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author 13b Pessoal
 */
public class Imprimir {

    String teste = "";
    private static String valorTodasVendas;

//    public void imprimeComList() throws JRException, SQLException {
//
//        JasperReport jr = JasperManager.loadReport("C:\\Users\\13b Pessoal\\Documents\\NetBeansProjects\\Nova Era Enterprise\\src\\com\\relatorio\\Pessoas.jasper");
//
//        Object[] list = new Object[3];
//        list[0] = new HashMap<>().put("0", "0");
//        list[0] = new HashMap<>().put("1", "1");
//        list[0] = new HashMap<>().put("2", "2");
//        JRDataSource dataSoure = new JRMapArrayDataSource(list);
//
//        Map parametros = new HashMap();
//
//        parametros.put("0", "0");
//        parametros.put("4", "1");
//
//
//        JasperPrint impressao = JasperFillManager.fillReport(jr, parametros);
//
//
//        JasperViewer viewer = new JasperViewer(impressao, true);
//        viewer.show();
//
//
//    }
//    public void imprimeComQuery(String data1, String data2) throws JRException, SQLException {
//
//
//        JasperReport jr = JasperManager.loadReport("C:\\Users\\13b Pessoal\\Documents\\NetBeansProjects\\Nova Era Enterprise\\src\\com\\relatorio\\Fatura.jasper");
//
//
//
//        Connection con = CirurgicaConnectionManager.getConnection();
//        Statement stm = con.createStatement();
//        String query = "select cast(valor_total_venda as char) as Valor, DATE_FORMAT(data_venda , '%d/%m/%Y') as Data from venda where data_venda between '" + data1 + "' and '" + data2 + "';";
//        ResultSet rs = stm.executeQuery(query);
//
//        JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
//
//
//
//        Map parametros = new HashMap();
////        rs.next();
//        parametros.put("Valor", "0");
//        parametros.put("Data", "0");
//
//
//        JasperPrint impressao = JasperFillManager.fillReport(jr, parametros, jrRS);
//
//
//        JasperViewer viewer = new JasperViewer(impressao, true);
//        viewer.show();
//
//
//    }
    public void imprimirDespesas(String mes, String ano) throws JRException, SQLException, ParseException {
        try {
            ReportDesignerDespesa reportDesignerDespesa = new ReportDesignerDespesa();
            JasperDesign design = reportDesignerDespesa.getDesign();
            JasperReport jr = JasperCompileManager.compileReport(design);

            List<Despesa> listaDepsesa = getListDespesa(mes, ano);
            JRBeanCollectionDataSource jrBean = new JRBeanCollectionDataSource(listaDepsesa);
            Map<String, Object> parametros = new HashMap<String, Object>();
            JasperPrint impressao = JasperFillManager.fillReport(jr, parametros, jrBean);
            JasperViewer.viewReport(impressao, false);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Sem Dados para o Relatório");

        }

    }

    public void imprimirFaturamento(String data1, String data2) throws JRException, SQLException {
        setValorTodasVendas(recuperarFatura(data1, data2));

        try {
            ReportDesignerFatura reportDesignerFatura = new ReportDesignerFatura();
            JasperDesign design = reportDesignerFatura.getDesign();
            JasperReport jr = JasperCompileManager.compileReport(design);

            List<Venda> listaFatura = getListVenda(data1, data2);
            JRBeanCollectionDataSource jrBean = new JRBeanCollectionDataSource(listaFatura);
            Map<String, Object> parametros = new HashMap<String, Object>();
            JasperPrint impressao = JasperFillManager.fillReport(jr, parametros, jrBean);
            JasperViewer.viewReport(impressao, false);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Sem Dados para o Relatório");
        }



    }

    public String recuperarFatura(String data1, String data2) throws SQLException {

        Connection con = CirurgicaConnectionManager.getConnection();
        Statement stm = con.createStatement();

        String sql = "select cast(sum(valor_total_venda) as char) as Valor from venda where data_venda between '" + data1 + "' and '" + data2 + "'";
        ResultSet rs = stm.executeQuery(sql);

        rs.next();


        return rs.getString("Valor");


    }

    public List<Venda> getListVenda(String data1, String data2) throws SQLException, ParseException {


        Connection con = CirurgicaConnectionManager.getConnection();
        Statement stm = con.createStatement();
        String query = "select cast(valor_total_venda as char) as Valor, DATE_FORMAT(data_venda , '%d/%m/%Y') as Data from venda where data_venda between '" + data1 + "' and '" + data2 + "';";
        ResultSet rs = stm.executeQuery(query);


        List<Venda> lista = new LinkedList<Venda>();

        while (rs.next()) {

            lista.add(new Venda());

        }



        return lista;
    }

    public List<Despesa> getListDespesa(String mes, String ano) throws SQLException, ParseException {


        Connection con = CirurgicaConnectionManager.getConnection();
        Statement stm = con.createStatement();
        String query = "select td.cd_tipo_despesa, d.data_referente, td.nm_tipo_despesa, DATE_FORMAT(d.data_vencimento , '%d/%m/%Y') as 'vencimento', d.valor_despesa, d.isPaga from despesa d join tipodespesa td on(d.cd_tipo_despesa = td.cd_tipo_despesa) where month(d.data_referente) = '" + mes + "' and year(d.data_referente) = '" + ano + "'";
        ResultSet rs = stm.executeQuery(query);


        List<Despesa> lista = new LinkedList<Despesa>();

        while (rs.next()) {



            lista.add(new Despesa(new TipoDespesa(rs.getInt("cd_tipo_despesa"), rs.getString("nm_tipo_despesa")), rs.getString("vencimento"), ("R$ " + rs.getString("valor_despesa")), (rs.getBoolean("isPaga") == true ? "Sim" : "Não")));
        }



        return lista;
    }

    public String getTeste() {
        return teste;
    }

    public static String getValorTodasVendas() {



        return valorTodasVendas;
    }

    public static void setValorTodasVendas(String valorTodasVendas) {
        Imprimir.valorTodasVendas = valorTodasVendas;
    }
}
