/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.view.cliente;

import br.com.cirurgica.dao.CirurgicaConnectionManager;
import br.com.cirurgica.view.PrincipalView;
import com.utilidades.Utilidades;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 13b Pessoal
 */
public class GestaoCliente extends javax.swing.JFrame {
//

    static String RGAlteracao;

    /**
     * Creates new form GestaoCliente
     */
    public GestaoCliente() throws ParseException {
        initComponents();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);


        btnAdicionar.addKeyListener(new PressEnter());
        btnPesquisar.addKeyListener(new PressEnter());
        btnVoltar.addKeyListener(new PressEnter());
        btnAlterar.addKeyListener(new PressEnter());
        btnExcluir.addKeyListener(new PressEnter());
        txtPesquisa.addKeyListener(new PressEnter());

        setIconImage(new ImageIcon(getClass().getResource("/resources/ico_gestao.png")).getImage());


        popularTabela();
        tbGestaoClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);



    }

    public void popularTabela() throws ParseException {

        String sql = "select max(v.data_venda)as ultimaCompra, p.RG, c.cd_cliente, p.nm_pessoa, p.email from cliente c inner join pessoa p on(c.cd_pessoa = p.cd_pessoa) left join venda v on(v.cd_cliente = c.cd_cliente) group by c.cd_cliente";
        Connection con;
        con = CirurgicaConnectionManager.getConnection();

        DefaultTableModel dataModel = (DefaultTableModel) tbGestaoClientes.getModel();

        dataModel.setNumRows(0);


        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                Object[] linha = {rs.getString("RG"), rs.getString("nm_pessoa"), rs.getString("email"), Utilidades.retornarDataFormatada(rs.getString("ultimaCompra"))};

                dataModel.addRow(linha);
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

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbGestaoClientes = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnAdicionar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnPesquisar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnVoltar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnAlterar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnExcluir = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ana Maria Cirurgica - Clientes");
        setBounds(new java.awt.Rectangle(400, 200, 0, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel1.setText("Gestão de Clientes");

        tbGestaoClientes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbGestaoClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RG", "Nome", "Email", "Ultima Compra"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbGestaoClientes.setFocusable(false);
        tbGestaoClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbGestaoClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbGestaoClientes);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAdicionar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bt_novo_cliente.png"))); // NOI18N
        btnAdicionar.setPreferredSize(new java.awt.Dimension(90, 60));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });
        btnAdicionar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnAdicionarKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ADICIONAR");

        btnPesquisar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bt_pesquisar.png"))); // NOI18N
        btnPesquisar.setPreferredSize(new java.awt.Dimension(90, 60));
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("PESQUISAR");

        btnVoltar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bt_voltar.png"))); // NOI18N
        btnVoltar.setPreferredSize(new java.awt.Dimension(90, 60));
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel4.setText("VOLTAR(ESC)");

        txtPesquisa.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel5.setText("Nome do Cliente:");

        btnAlterar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bt_alterar.png"))); // NOI18N
        btnAlterar.setPreferredSize(new java.awt.Dimension(90, 60));
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("ALTERAR");

        btnExcluir.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bt_excluir.png"))); // NOI18N
        btnExcluir.setPreferredSize(new java.awt.Dimension(90, 60));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("EXCLUIR");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(12, 12, 12)
                        .add(btnAdicionar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 59, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel2)))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jLabel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(btnAlterar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 59, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(btnExcluir, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 59, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 59, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(btnPesquisar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 59, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel5)
                            .add(txtPesquisa, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 195, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jLabel3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, btnVoltar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel4))
                .add(139, 139, 139))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(btnVoltar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(btnAlterar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(btnAdicionar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(btnExcluir, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(btnPesquisar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .add(7, 7, 7)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel2)
                            .add(jLabel6)
                            .add(jLabel7)
                            .add(jLabel3)
                            .add(jLabel4)))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(11, 11, 11)
                        .add(jLabel5)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(txtPesquisa, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jLabel1)
                    .add(jScrollPane1)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 615, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(10, 10, 10)
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 163, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-651)/2, (screenSize.height-374)/2, 651, 374);
    }// </editor-fold>//GEN-END:initComponents

    private void tbGestaoClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbGestaoClientesMouseClicked
    }//GEN-LAST:event_tbGestaoClientesMouseClicked

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        chamarCadastrarCliente();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnAdicionarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAdicionarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdicionarKeyPressed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        chamarPesquisarCliente();
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        chamarVoltar();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        chamarVisualizarCliente();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        try {
            chamarExcluirCliente();
        } catch (ParseException ex) {
            Logger.getLogger(GestaoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        chamarVoltar();        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    public void metodoPesquisar() {
        DefaultTableModel dataModel = (DefaultTableModel) tbGestaoClientes.getModel();

        dataModel.setNumRows(0);
        String sql = "select max(v.data_venda)as ultimaCompra, p.RG, c.cd_cliente, p.nm_pessoa, p.email from cliente c left join pessoa p on(c.cd_pessoa = p.cd_pessoa) left join venda v on(v.cd_cliente = c.cd_cliente) where p.nm_pessoa like '" + txtPesquisa.getText() + "%' group by c.cd_cliente ";

        Connection con;
        con = CirurgicaConnectionManager.getConnection();


        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                Object[] linha = {rs.getString("RG"), rs.getString("nm_pessoa"), rs.getString("email"), Utilidades.retornarDataFormatada(rs.getString("ultimaCompra"))};

                dataModel.addRow(linha);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void excluirCliente() throws ParseException {
        if (tbGestaoClientes.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Selecione um Cliente para poder excluir.", "Seleção Necessária", 1);
        } else {
            if (JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir este cadastro? (Pressione ESC para Cancelar)", "Confirmação", 2, 3) == 0) {
                Connection con = CirurgicaConnectionManager.getConnection();
                PreparedStatement stm;


                String sql = "select cd_pessoa from pessoa where RG = '" + tbGestaoClientes.getModel().getValueAt(tbGestaoClientes.getSelectedRow(), 0).toString() + "'";
                String cdPessoa = "";
                try {
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        cdPessoa = rs.getString("cd_pessoa");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


                sql = "delete from cliente where cd_pessoa = " + cdPessoa;


                try {
                    stm = con.prepareStatement(sql);
                    stm.executeUpdate();

                    stm.close();
                    con.close();


                    JOptionPane.showMessageDialog(this, "CADASTRO EXCLUIDO COM SUCESSO!!!", "Remoção", 1);

                } catch (SQLException ex) {
                    Logger.getLogger(GestaoCliente.class.getName()).log(Level.SEVERE, null, ex);
                }

            }


        }

        popularTabela();


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
            java.util.logging.Logger.getLogger(GestaoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestaoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestaoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestaoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new GestaoCliente().setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(GestaoCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    class PressEnter implements KeyListener {

        public void keyPressed(KeyEvent e) {
            // TODO Auto-generated method stub

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (btnAdicionar.isFocusOwner()) {
                    chamarCadastrarCliente();
                } else if (btnPesquisar.isFocusOwner()) {
                    chamarPesquisarCliente();
                } else if (btnVoltar.isFocusOwner()) {
                    chamarVoltar();
                } else if (btnAlterar.isFocusOwner()) {
                    chamarVisualizarCliente();
                } else if (btnExcluir.isFocusOwner()) {
                    try {
                        chamarExcluirCliente();
                    } catch (ParseException ex) {
                        Logger.getLogger(GestaoCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (txtPesquisa.isFocusOwner()) {
                    chamarPesquisarCliente();
                }
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

                chamarVoltar();
            }
        }

        public void keyReleased(KeyEvent e) {
        }

        public void keyTyped(KeyEvent e) {
        }
    }

    public void chamarCadastrarCliente() {
        this.setVisible(false);
        CadastrarCliente cc = new CadastrarCliente();
        cc.setVisible(true);
    }

    public void chamarVisualizarCliente() {
        if (tbGestaoClientes.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Selecione um Cliente para Visualizar e/ou Alterar seus dados.", "Seleção Necessária", 1);
        } else {
            RGAlteracao = tbGestaoClientes.getModel().getValueAt(tbGestaoClientes.getSelectedRow(), 0).toString();

            AlterarCliente ac = new AlterarCliente();
            ac.setVisible(true);
            this.dispose();
        }

    }

    public void chamarExcluirCliente() throws ParseException {
        excluirCliente();
    }

    public void chamarPesquisarCliente() {
        metodoPesquisar();

    }

    public void chamarVoltar() {

        PrincipalView p = new PrincipalView();
        p.setVisible(true);
        p.setUsuarioLogado(Utilidades.retornaNomeLogado());
        this.dispose();

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbGestaoClientes;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}
