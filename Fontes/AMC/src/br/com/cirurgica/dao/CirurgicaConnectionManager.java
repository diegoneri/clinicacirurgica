/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author 13b Pessoal
 */
public class CirurgicaConnectionManager {

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            return DriverManager.getConnection("jdbc:mysql://localhost:3306/bdcirurgica" , "root" , "root");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null , "Não Conectou");
            return null;

        }

    }

    public static void main(String args[]) {
        CirurgicaConnectionManager.getConnection();
    }
}
