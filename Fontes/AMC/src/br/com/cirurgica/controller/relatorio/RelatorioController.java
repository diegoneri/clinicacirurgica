/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.controller.relatorio;

import br.com.cirurgica.dao.CirurgicaConnectionManager;
import br.com.cirurgica.view.utils.ProgressBarView;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JToggleButton;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author dfelix3
 */
public abstract class RelatorioController {

    public abstract void imprimir();

    protected void imprimir(String fileName, String title, Connection conn, Map params) {
        try {
            if (fileName == null || fileName.length() < 5) {
                return;
            }

            if (title == null || title.trim().equals("")) {
                title = "Cirúrgica Ana Maria";
            }
            if (conn == null || conn.isClosed()) {
                conn = CirurgicaConnectionManager.getConnection();
            }
            if (params == null) {
                params = criarParametros();
            } else {
                incluirLocalBrasil(params);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RelatorioController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao gerar o relatório " + title);
        } catch (Exception e) {
            Logger.getLogger(RelatorioController.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro genérico " + e.getMessage());
        } finally {
            // aqui eu paro a progressbar
        }

        final String title2 = title;
        final Map params2 = params;
        final Connection conn2 = conn;
        final String fileName2 = fileName;
        Thread t = new Thread() {
            @Override
            public void run() {
                // aqui eu inicio a progressbar
                ProgressBarView rel = new ProgressBarView("Carregando relatório");
                rel.setVisible(true);

                try {
                    JasperPrint jp = JasperFillManager.fillReport(fileName2, params2, conn2);
                    buildViewer(jp, title2);
                } catch (JRException ex) {
                    Logger.getLogger(RelatorioController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Erro ao gerar o relatório " + title2);
                } catch (Exception e) {
                    Logger.getLogger(RelatorioController.class.getName()).log(Level.SEVERE, null, e);
                    JOptionPane.showMessageDialog(null, "Erro genérico " + e.getMessage());
                } finally {
                    rel.setVisible(false);
                    rel.dispose();
                }

                return;
            }
        };
        t.start();
    }

    protected void imprimir(String fileName, String title, Map params) {
        this.imprimir(fileName, title, CirurgicaConnectionManager.getConnection(), params);
    }

    protected void imprimir(String fileName, String title) {
        this.imprimir(fileName, title, CirurgicaConnectionManager.getConnection(), this.criarParametros());
    }

    public static void main(String args[]) {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMM");
        String teste = sdf.format(new Date());

        System.out.print(teste);
        //RelatorioController r = new RelatorioListaPrecoController();
        //r.imprimir("report/listapreco/ListaPreco.jasper", "Lista de Preços - Relatório", CirurgicaConnectionManager.getConnection(), r.criarParametros());
    }

    private void incluirLocalBrasil(Map params) {
        params.put("REPORT_LOCALE", new Locale("pt", "BR"));
    }

    private Map criarParametros() {
        Map params = new HashMap();
        incluirLocalBrasil(params);
        return params;
    }

    private void buildViewer(JasperPrint jp, String title) {
        JasperViewer jv = new JasperViewer(jp, false, new Locale("pt", "BR"));
        jv.setTitle(title);
        selecionarZoomLargura(extrairPanelBotoes(jv));
        maximizarTela(jv);
        jv.setVisible(true);
    }

    private void selecionarZoomLargura(JPanel pnlBotoes) {
        JToggleButton btnZoomLarguraPagina = (JToggleButton) pnlBotoes.getComponent(12);
        btnZoomLarguraPagina.setSelected(true);
        btnZoomLarguraPagina.getActionListeners()[0].actionPerformed(new ActionEvent(btnZoomLarguraPagina, 0, null));
    }

    private JPanel extrairPanelBotoes(JasperViewer jv) {
        final JRViewer jrViewer = (JRViewer) ((JPanel) ((JPanel) ((JLayeredPane) ((JRootPane) jv.getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0)).getComponent(0);
        JPanel pnlBotoes = (JPanel) jrViewer.getComponent(0);
        return pnlBotoes;
    }

    private void maximizarTela(JFrame frame) {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(d.width, d.height);
        frame.setLocation(0, 0);
        frame.setExtendedState(6);
    }
}
