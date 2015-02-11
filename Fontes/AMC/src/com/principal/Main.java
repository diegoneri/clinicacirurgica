/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.principal;

import br.com.cirurgica.dao.MyEntityManager;
import br.com.cirurgica.view.LoginView;
import br.com.cirurgica.view.utils.ProgressBarView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author 13b Pessoal
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, JRException, SQLException {
        try {
            setLogs();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
                try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        Thread t = new Thread() {
            @Override
            public void run() {
                // aqui eu inicio a progressbar  
                ProgressBarView rel = new ProgressBarView("Iniciando o programa...");
                rel.setVisible(true);
                try {
                    MyEntityManager.getEntityManagerFactory();
                } finally {
                    rel.setVisible(false);
                    rel.dispose();
                    new LoginView().setVisible(true);
                }
                return;
            }
        };
        t.start();

    }
    
    private static void setLogs() throws IOException {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy hh'h'mm'm'ss");
        String agora = format.format(date);

        File out = new File("c:/amc-logs/System.out");
        File err = new File("c:/amc-logs/System.err");
        if (!out.exists()) {
            out.mkdirs();
        }
        if (!err.exists()) {
            err.mkdirs();
        }

        System.setOut(
                new PrintStream(
                new FileOutputStream("c:/amc-logs/System.out/" + agora + ".txt", true)));

        System.setErr(
                new PrintStream(
                new FileOutputStream("c:/amc-logs/System.err/" + agora + ".txt", true)));
    }
    
}
