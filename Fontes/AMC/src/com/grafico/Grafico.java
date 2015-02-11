/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grafico;

import br.com.cirurgica.dao.CirurgicaConnectionManager;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 13b Pessoal
 */
public class Grafico {

    public String[] getListaDespesa(String mes, String ano) throws SQLException {

        Connection con = CirurgicaConnectionManager.getConnection();
        Statement stm = con.createStatement();
        String query = "select sum(d.valor_despesa) as Total,"
                + "(select sum(valor_despesa) from despesa where cd_tipo_despesa = 1 and month(d.data_referente) = '" + mes + "' and year(d.data_referente) = '" + ano + "') as Agua,"
                + "(select sum(valor_despesa) from despesa where cd_tipo_despesa = 2 and month(d.data_referente) = '" + mes + "' and year(d.data_referente) = '" + ano + "') as 'Energia Eletrica',"
                + "(select sum(valor_despesa) from despesa where cd_tipo_despesa = 3 and month(d.data_referente) = '" + mes + "' and year(d.data_referente) = '" + ano + "') as Telefone,"
                + "(select sum(valor_despesa) from despesa where cd_tipo_despesa = 4 and month(d.data_referente) = '" + mes + "' and year(d.data_referente) = '" + ano + "') as Imposto,"
                + "(select sum(valor_despesa) from despesa where cd_tipo_despesa = 5 and month(d.data_referente) = '" + mes + "' and year(d.data_referente) = '" + ano + "') as Compra,"
                + "(select sum(valor_despesa) from despesa where cd_tipo_despesa = 6 and month(d.data_referente) = '" + mes + "' and year(d.data_referente) = '" + ano + "') as Internet "
                + "from despesa d where month(d.data_referente) = '"+ mes + "' and year(d.data_referente) = '"+ ano +"'";
        ResultSet rs = stm.executeQuery(query);


        String[] listaDespesa = new String[6];

        rs.next();


        Double total = (rs.getString("Total") == null ? new Double(0.0) : rs.getDouble("Total"));




        Double agua = ((rs.getString("Agua") == null ? new Double(0.0) : ((rs.getDouble("Agua")) * 100) / total));
        Double energiaEletrica = ((rs.getString("Energia ELetrica") == null ? new Double(0.0) : ((rs.getDouble("Energia ELetrica")) * 100) / total));
        Double telefone = ((rs.getString("Telefone") == null ? new Double(0.0) : ((rs.getDouble("Telefone")) * 100) / total));
        Double imposto = ((rs.getString("Imposto") == null ? new Double(0.0) : ((rs.getDouble("Imposto")) * 100) / total));
        Double compra = ((rs.getString("Compra") == null ? new Double(0.0) : ((rs.getDouble("Compra")) * 100) / total));
        Double internet = ((rs.getString("Internet") == null ? new Double(0.0) : ((rs.getDouble("Internet")) * 100) / total));


        DecimalFormat df = new DecimalFormat("#,###.00");

        listaDespesa[0] = df.format((agua)).replace(",", ".");
        listaDespesa[1] = df.format((energiaEletrica)).replace(",", ".");
        listaDespesa[2] = df.format((telefone)).replace(",", ".");
        listaDespesa[3] = df.format((imposto)).replace(",", ".");
        listaDespesa[4] = df.format((compra)).replace(",", ".");
        listaDespesa[5] = df.format((internet)).replace(",", ".");




        return listaDespesa;
    }

    public static void main(String[] args) throws SQLException {


        Grafico g = new Grafico();
        Object[] o = g.getListaDespesa("09", "2012");

        for (int i = 0; i < o.length; i++) {
            System.out.println("Aqui: " + o[i]);
        }

    }
}
