/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.view.cliente;

import br.com.cirurgica.dao.CirurgicaConnectionManager;
import br.com.cirurgica.view.PrincipalView;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.freixas.jcalendar.*;

/**
 *
 * @author Leo
 */
public class CadastrarCliente extends javax.swing.JFrame {

    Map<String, Integer> mapaEstados;
    Map<String, Integer> mapaEstadosCivis;
    Map<String, Integer> mapaCidades;
//    Map<String, Integer> mapaMeses;

    /**
     * Creates new form CadastrarCliente
     */
    public CadastrarCliente() {
        initComponents();
        popularComboEstado();
        popularComboEstadoCivil();





        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);

        btnAdicionar.addKeyListener(new PressEnter());
        btnVoltar.addKeyListener(new PressEnter());
        btnLimpar.addKeyListener(new PressEnter());
        txtNome.addKeyListener(new PressEnter());
        txtEndereco.addKeyListener(new PressEnter());
        txtNumero.addKeyListener(new PressEnter());
        txtComplemento.addKeyListener(new PressEnter());
        txtBairro.addKeyListener(new PressEnter());
        txtCep.addKeyListener(new PressEnter());
        txtCelUm.addKeyListener(new PressEnter());
        txtTelUm.addKeyListener(new PressEnter());
        txtEmail.addKeyListener(new PressEnter());
        txtTelDois.addKeyListener(new PressEnter());
        txtRG.addKeyListener(new PressEnter());
        txtCPF.addKeyListener(new PressEnter());
        txtCelDois.addKeyListener(new PressEnter());
        cbDataNascimento.addKeyListener(new PressEnter());
        cbCidade.addKeyListener(new PressEnter());
        cbEstado.addKeyListener(new PressEnter());
        cbEstadoCivil.addKeyListener(new PressEnter());
        rdbMasculino.addKeyListener(new PressEnter());
        rdbFeminino.addKeyListener(new PressEnter());

        rdbMasculino.setSelected(true);


    }

    public void popularComboCidade(String cdEstado) {
        cbCidade.removeAllItems();
        String sql = "select * from cidade where cd_estado = " + cdEstado;
        Connection con;
        con = CirurgicaConnectionManager.getConnection();

        mapaCidades = new HashMap<>();


        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                mapaCidades.put(rs.getString("nm_cidade"), rs.getInt("cd_cidade"));

                cbCidade.addItem(rs.getString("nm_cidade"));
            }

            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(PrincipalView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void popularComboEstadoCivil() {
        String sql = "select * from estadocivil";
        Connection con;
        con = CirurgicaConnectionManager.getConnection();


        mapaEstadosCivis = new HashMap<>();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                mapaEstadosCivis.put(rs.getString("nm_estado_civil"), rs.getInt("cd_estado_civil"));

                cbEstadoCivil.addItem(rs.getString("nm_estado_civil"));
            }

            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(PrincipalView.class.getName()).log(Level.SEVERE, null, ex);
        }



    }

    public void cadastrarPessoa() {


        if (txtNome.getText().equals("") || txtRG.getText().equals("") || txtCPF.getText().equals("")
                || txtEndereco.getText().equals("") || txtNumero.getText().equals("") || txtComplemento.getText().equals("")
                || txtBairro.getText().equals("") || txtCep.getText().equals("") || txtTelUm.getText().equals("") || txtTelDois.getText().equals("")
                || txtCelUm.getText().equals("") || txtCelDois.getText().equals("") || cbEstado.getSelectedItem().equals(null) || cbCidade.getSelectedItem().equals(null)
                || cbDataNascimento.getSelectedItem().equals(null) || cbEstadoCivil.getSelectedItem().equals(null)){

           JOptionPane.showMessageDialog(this, "Preencha todos os campos antes de cadastrar um Cliente!");

        }else{
        String sql = "insert into pessoa(nm_pessoa, RG, CPF, data_nascimento, sexo, nm_endereco, "
                + "numero_endereco, bairro, complemento, CEP, cd_cidade, cd_estado_civil, telefone_fixo1, "
                + "telefone_fixo2, telefone_celular1, telefone_celular2, email) "
                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";


        Connection con = CirurgicaConnectionManager.getConnection();
        PreparedStatement stm;


        try {
            stm = con.prepareStatement(sql);
            stm.setString(1, txtNome.getText());
            stm.setString(2, txtRG.getText());
            stm.setString(3, txtCPF.getText());

            String dataNascimento = new SimpleDateFormat("yyyy-MM-dd").format(cbDataNascimento.getDate());

            //jComboBox5.getSelectedItem().toString() + "-" + mapaMeses.get(jComboBox4.getSelectedItem().toString()) + "-" + jComboBox3.getSelectedItem().toString();
            stm.setString(4, dataNascimento);

            if (rdbMasculino.isSelected()) {
                stm.setString(5, "M");
            } else {
                stm.setString(5, "F");
            }

            stm.setString(6, txtEndereco.getText());
            stm.setString(7, txtNumero.getText());
            stm.setString(8, txtBairro.getText());
            stm.setString(9, txtComplemento.getText());
            stm.setString(10, txtCep.getText());
            stm.setString(11, mapaCidades.get(cbCidade.getSelectedItem().toString()).toString());
            stm.setString(12, mapaEstadosCivis.get(cbEstadoCivil.getSelectedItem().toString()).toString());
            stm.setString(13, txtTelUm.getText());
            stm.setString(14, txtTelDois.getText());
            stm.setString(15, txtCelUm.getText());
            stm.setString(16, txtCelDois.getText());
            stm.setString(17, txtEmail.getText());

            stm.executeUpdate();


            stm.close();
            con.close();

            JOptionPane.showMessageDialog(this, "CADASTRO EFETUADO COM SUCESSO!!!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);



            this.setVisible(false);
            GestaoCliente gc = new GestaoCliente();
            gc.setVisible(true);
           //  this.dispose();

        } catch (Exception e) {

            e.printStackTrace();
        }

        }
    }

//    public void cadastrarCliente() throws SQLException {
//
//        String sql = "insert into cliente(data_cadastro, ultima_compra, cd_pessoa) values (?,?,?)";
//
//
//        Connection con = CirurgicaConnectionManager.getConnection();
//        PreparedStatement stm = con.prepareStatement(sql);
//        try {
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("select cd_pessoa from pessoa where cd_pessoa = (select max(cd_pessoa) from pessoa)");
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Calendar c = Calendar.getInstance();
//
//
//            while (rs.next()) {
//                stm.setString(1, sdf.format(c.getTime()));
//                stm.setString(2, null);
//                System.out.println("AQUI : " + rs.getString("cd_pessoa"));
//                stm.setString(3, rs.getString("cd_pessoa"));
//                stm.execute();
//
//            }
//            stm.close();
//            con.close();
//            stmt.close();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(PrincipalView.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//
//
//
//
//    }

    public void popularComboEstado() {
        String sql = "select * from estado";
        Connection con;
        con = CirurgicaConnectionManager.getConnection();


        mapaEstados = new HashMap<>();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                mapaEstados.put(rs.getString("uf"), rs.getInt("cd_estado"));

                cbEstado.addItem(rs.getString("uf"));
            }

            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(PrincipalView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        txtNumero = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtComplemento = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        cbCidade = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbEstado = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        txtCep = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtTelUm = new javax.swing.JTextField();
        txtCelUm = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtTelDois = new javax.swing.JTextField();
        txtCelDois = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        rdbMasculino = new javax.swing.JRadioButton();
        rdbFeminino = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        cbDataNascimento = new org.freixas.jcalendar.JCalendarCombo();
        jLabel24 = new javax.swing.JLabel();
        cbEstadoCivil = new javax.swing.JComboBox();
        btnAdicionar = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        txtRG = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtCPF = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ana Maria Cirurgica - Cadastrar Cliente");
        setBounds(new java.awt.Rectangle(300, 10, 0, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel2.setText("Nome:");

        txtNome.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtNome.setPreferredSize(new java.awt.Dimension(6, 24));
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel3.setText("Endereço:");

        txtEndereco.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtEndereco.setPreferredSize(new java.awt.Dimension(6, 24));
        txtEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEnderecoActionPerformed(evt);
            }
        });

        txtNumero.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtNumero.setPreferredSize(new java.awt.Dimension(6, 24));
        txtNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel4.setText("Numero:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel5.setText("Complemento:");

        txtComplemento.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtComplemento.setPreferredSize(new java.awt.Dimension(6, 24));
        txtComplemento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtComplementoActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel6.setText("Bairro:");

        txtBairro.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtBairro.setPreferredSize(new java.awt.Dimension(6, 24));
        txtBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBairroActionPerformed(evt);
            }
        });

        cbCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCidadeActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel7.setText("Estado:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel8.setText("Cidade:");

        cbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEstadoActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel10.setText("CEP:");

        txtCep.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtCep.setPreferredSize(new java.awt.Dimension(6, 24));
        txtCep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCepActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Telefones"));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel14.setText("Tel 2:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel15.setText("Tel 1:");

        txtTelUm.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtTelUm.setPreferredSize(new java.awt.Dimension(6, 24));
        txtTelUm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelUmActionPerformed(evt);
            }
        });

        txtCelUm.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtCelUm.setPreferredSize(new java.awt.Dimension(6, 24));
        txtCelUm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCelUmActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel12.setText("Cel 2:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel11.setText("Cel 1:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel16.setText("Email:");

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtEmail.setPreferredSize(new java.awt.Dimension(6, 24));
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        txtTelDois.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtTelDois.setPreferredSize(new java.awt.Dimension(6, 24));
        txtTelDois.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelDoisActionPerformed(evt);
            }
        });

        txtCelDois.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtCelDois.setPreferredSize(new java.awt.Dimension(6, 24));
        txtCelDois.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCelDoisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCelUm, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(txtTelUm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel14)
                                .addComponent(txtTelDois, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                .addComponent(txtCelDois, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel12))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelUm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelDois, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCelUm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCelDois, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addGap(6, 6, 6)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Adicionais"));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel22.setText("Sexo:");

        rdbMasculino.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup1.add(rdbMasculino);
        rdbMasculino.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        rdbMasculino.setText("Masculino");
        rdbMasculino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbMasculinoActionPerformed(evt);
            }
        });

        rdbFeminino.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup1.add(rdbFeminino);
        rdbFeminino.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        rdbFeminino.setText("Feminino");
        rdbFeminino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbFemininoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel1.setText("Data de Nascimento:");

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel4.add(cbDataNascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 26, 196, -1));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel24.setText("Estado Civil:");

        cbEstadoCivil.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel22)
                        .addGap(6, 6, 6)
                        .addComponent(rdbMasculino)
                        .addGap(7, 7, 7)
                        .addComponent(rdbFeminino))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel24)
                        .addGap(11, 11, 11)
                        .addComponent(cbEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel22))
                    .addComponent(rdbMasculino)
                    .addComponent(rdbFeminino))
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel24))
                    .addComponent(cbEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bt_novo_cliente.png"))); // NOI18N
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bt_voltar.png"))); // NOI18N
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bt_clear.png"))); // NOI18N
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel19.setText("RG:");

        txtRG.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtRG.setPreferredSize(new java.awt.Dimension(6, 24));
        txtRG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRGActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel21.setText("CPF:");

        txtCPF.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtCPF.setPreferredSize(new java.awt.Dimension(6, 24));
        txtCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPFActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("ADICIONAR CLIENTE");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("LIMPAR CAMPOS");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("CANCELAR CADASTRO");

        jLabel20.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel20.setText("Cadastro de Clientes");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(134, 134, 134)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel8)
                                        .addGap(175, 175, 175)
                                        .addComponent(jLabel10))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txtRG, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel4)
                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                    .addComponent(txtNumero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel5)
                                                        .addGap(140, 140, 140))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                        .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(6, 6, 6))))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnVoltar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(101, 101, 101))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(257, 257, 257)
                        .addComponent(jLabel19)
                        .addGap(130, 130, 130)
                        .addComponent(jLabel21)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel19)
                    .addComponent(jLabel21))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17)
                                .addGap(14, 14, 14)
                                .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 758, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-774)/2, (screenSize.height-492)/2, 774, 492);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void txtEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnderecoActionPerformed

    private void txtNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroActionPerformed

    private void txtComplementoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtComplementoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtComplementoActionPerformed

    private void txtBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBairroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBairroActionPerformed

    private void cbCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCidadeActionPerformed

    private void cbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEstadoActionPerformed
        //        if (index != 0) {
            //
            //            JOptionPane.showMessageDialog(null, "Ow, para de mudar sapoha!!");
            //
            //        }
        //        index++;
        String item = cbEstado.getSelectedItem().toString();
        popularComboCidade(mapaEstados.get(item).toString());
    }//GEN-LAST:event_cbEstadoActionPerformed

    private void txtCepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCepActionPerformed

    private void txtTelUmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelUmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelUmActionPerformed

    private void txtCelUmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCelUmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCelUmActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtTelDoisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelDoisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelDoisActionPerformed

    private void txtCelDoisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCelDoisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCelDoisActionPerformed

    private void rdbMasculinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbMasculinoActionPerformed
        //        jRadioButton2.setSelected(false);
    }//GEN-LAST:event_rdbMasculinoActionPerformed

    private void rdbFemininoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbFemininoActionPerformed
        //        jRadioButton1.setSelected(false);
    }//GEN-LAST:event_rdbFemininoActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        cadastrarPessoa();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        try {
            voltar();
        } catch (ParseException ex) {
            Logger.getLogger(CadastrarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limparCampos();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void txtRGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRGActionPerformed

    private void txtCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPFActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            voltar();
        } catch (ParseException ex) {
            Logger.getLogger(CadastrarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

    public void limparCampos() {


        this.txtNome.setText(null);
        this.txtEndereco.setText(null);
        this.txtNumero.setText(null);
        this.txtComplemento.setText(null);
        this.txtBairro.setText(null);
        this.txtCep.setText(null);
        this.txtEmail.setText(null);
        this.txtTelDois.setText(null);
        this.txtRG.setText(null);
        this.txtCPF.setText(null);
        this.txtCelDois.setText(null);
        this.txtCelUm.setText(null);
        this.txtTelUm.setText(null);
        this.cbCidade.setSelectedIndex(0);
        this.cbEstado.setSelectedIndex(0);
        this.cbEstadoCivil.setSelectedIndex(0);


    }

    public void voltar() throws ParseException {

        if (JOptionPane.showConfirmDialog(this, "Tem certeza que deseja cancelar o cadastro? (Pressione ESC para Cancelar)", "Confirmação", 2, 3) == 0) {


            GestaoCliente gc = new GestaoCliente();
            gc.setVisible(true);
            this.dispose();


        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastrarCliente().setVisible(true);
            }
        });
    }

    class PressEnter implements KeyListener {

        public void keyPressed(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (btnAdicionar.isFocusOwner()) {
                    cadastrarPessoa();
                } else if (btnVoltar.isFocusOwner()) {
                    try {
                        voltar();
                    } catch (ParseException ex) {
                        Logger.getLogger(CadastrarCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (btnLimpar.isFocusOwner()) {
                    limparCampos();
                }
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                try {
                    voltar();
                } catch (ParseException ex) {
                    Logger.getLogger(CadastrarCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        public void keyReleased(KeyEvent e) {
        }

        public void keyTyped(KeyEvent e) {

        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cbCidade;
    private org.freixas.jcalendar.JCalendarCombo cbDataNascimento;
    private javax.swing.JComboBox cbEstado;
    private javax.swing.JComboBox cbEstadoCivil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton rdbFeminino;
    private javax.swing.JRadioButton rdbMasculino;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCPF;
    private javax.swing.JTextField txtCelDois;
    private javax.swing.JTextField txtCelUm;
    private javax.swing.JTextField txtCep;
    private javax.swing.JTextField txtComplemento;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtRG;
    private javax.swing.JTextField txtTelDois;
    private javax.swing.JTextField txtTelUm;
    // End of variables declaration//GEN-END:variables
}
