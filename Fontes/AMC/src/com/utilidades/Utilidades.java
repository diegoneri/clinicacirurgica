/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utilidades;

import br.com.cirurgica.model.Pessoa;
import br.com.cirurgica.model.Usuario;
import br.com.cirurgica.dao.CirurgicaConnectionManager;
import br.com.cirurgica.view.PrincipalView;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.freixas.jcalendar.JCalendarCombo;

/**
 *
 * @author 13b Pessoal
 */
public class Utilidades {

    int index = 0;
    Map<String, Integer> mapaEstados;
    static Map<String, Integer> mapaEstadosCivis;
    Map<String, Integer> mapaCidades;
    static String nomeLogado;
    public static Pessoa pessoaLogada = new Pessoa();
    public static Usuario usuarioLogado = new Usuario();
    private static Double valorMarkup;
    public final static Locale localBrasil = new Locale("pt","BR");

    public JComboBox popularComboCidade(String cdEstado) {
        JComboBox jComboBox1 = new JCalendarCombo();
        jComboBox1.removeAllItems();
        String sql = "select * from cidade where cd_estado = " + cdEstado;
        Connection con;
        con = CirurgicaConnectionManager.getConnection();

        mapaCidades = new HashMap<>();


        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                mapaCidades.put(rs.getString("nm_cidade"), rs.getInt("cd_cidade"));

                jComboBox1.addItem(rs.getString("nm_cidade"));
            }

            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(PrincipalView.class.getName()).log(Level.SEVERE, null, ex);
        }

        return jComboBox1;
    }

    public static JComboBox popularComboEstadoCivil() {
        JComboBox jComboBox1 = new JCalendarCombo();
        String sql = "select * from estadocivil";
        Connection con;
        con = CirurgicaConnectionManager.getConnection();


        mapaEstadosCivis = new HashMap<>();

        try {
            try (Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {

                    mapaEstadosCivis.put(rs.getString("nm_estado_civil"), rs.getInt("cd_estado_civil"));

                    jComboBox1.addItem(rs.getString("nm_estado_civil"));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PrincipalView.class.getName()).log(Level.SEVERE, null, ex);
        }

        return jComboBox1;

    }

    public static String gerarHash(String senha) throws NoSuchAlgorithmException {
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
        String senhaHash = hash.toString(16);


        return senhaHash;
    }

    public static void armazenaNomeLogado(String nome) {
        nomeLogado = nome;
    }

    public static void armazenarPessoaLogada(Pessoa pessoa) {
        pessoaLogada = pessoa;
    }

    public static String retornaNomeLogado() {
        return "Usuário Logado: " + pessoaLogada.getNmPessoa();
    }

    public static String retornarDataFormatada(String dataSemFormato) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (!(dataSemFormato == null)) {

            Date data = sdf.parse(dataSemFormato);
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");


            return formato.format(data);
        } else {

            return "";
        }

    }

    public static Double getDoubleByCurrency(String valorTexto) throws ParseException{
        NumberFormat df = DecimalFormat.getCurrencyInstance(localBrasil);
        return df.parse(valorTexto).doubleValue();
    }

    public static Float getFloatByCurrency(String valorTexto) throws ParseException{
        NumberFormat df = DecimalFormat.getCurrencyInstance(localBrasil);
        return df.parse(valorTexto).floatValue();
    }

    public static Double getDoubleByNumeric(String valorTexto) throws ParseException{
        NumberFormat df = DecimalFormat.getInstance(localBrasil);
        return df.parse(valorTexto).doubleValue();
    }

    public static Float getFloatByNumeric(String valorTexto) throws ParseException{
        NumberFormat df = DecimalFormat.getInstance(localBrasil);
        return df.parse(valorTexto).floatValue();
    }

    public static String retornarValorFormatado(Double valorNaoFormatado){
        NumberFormat df = DecimalFormat.getCurrencyInstance(localBrasil);
        return df.format(valorNaoFormatado);
    }

    public static String retornarValorFormatado(Float valorNaoFormatado){
        NumberFormat df = DecimalFormat.getCurrencyInstance(localBrasil);
        return df.format(valorNaoFormatado);
    }

    public static String retornarValorFormatado(Integer valorNaoFormatado){
        NumberFormat df = DecimalFormat.getCurrencyInstance();
        return df.format(valorNaoFormatado);
    }

    public static Double retornarMarkup() {
        if (valorMarkup == null) {

            String sql = "select * from markup";

            Connection con;
            con = CirurgicaConnectionManager.getConnection();
            valorMarkup = null;

            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    valorMarkup = Double.parseDouble(rs.getString("vl_markup"));

                }
            } catch (SQLException | NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return valorMarkup;

    }

    public static String calcularPrecoVenda(String precoCompra) {

        DecimalFormat df = new DecimalFormat("#,###.00");

        String resMarkup = "";

        resMarkup = df.format((Double.parseDouble(precoCompra.replace(",", ".")) / retornarMarkup()));

        return resMarkup;

    }
    @Deprecated
    public static void alteraMarkup(String markup) throws SQLException {
        String sql = "update markup set vl_markup = ?";

        Connection con = CirurgicaConnectionManager.getConnection();
        PreparedStatement stm;

        stm = con.prepareStatement(sql);

        stm.setString(1, markup);

        stm.executeUpdate();

        stm.close();
        con.close();

        JOptionPane.showMessageDialog(null, "Markup Alterado!!!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);

    }

    public static Usuario retornarUsuarioLogado() {
        if (usuarioLogado == null){
            usuarioLogado = new Usuario();
//            usuarioLogado.setCdUsuario(1);
            //FIXME corrigir a pessoa logada
            //usuarioLogado.setCdPessoa(1);
 //           usuarioLogado.setTipoUsuario("Administrador");
        }
        return usuarioLogado;
    }

    public static Pessoa retornarPessoaLogada() {
        return pessoaLogada;
    }
}
