/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.view.pedido;

import br.com.cirurgica.dao.FornecedorDAO;
import br.com.cirurgica.model.Fornecedor;
import br.com.cirurgica.view.IView;
import br.com.cirurgica.view.venda.PesquisarCliente;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author 13b Pessoal
 */
public class PesquisarFornecedor extends javax.swing.JFrame implements IView {

    /**
     * Creates new form PesquisarCliente
     */
    private Fornecedor fornecedor;
    public PesquisarFornecedor() {

        initComponents();

        txtCnpj.setEnabled(false);
        txtNomeFantasia.setEnabled(false);

        popularTabela();



        btnAdicionar.addKeyListener(new PressEnter());
        btnPesquisar.addKeyListener(new PressEnter());
        btnVoltar.addKeyListener(new PressEnter());
        txtCnpj.addKeyListener(new PressEnter());
        txtNomeFantasia.addKeyListener(new PressEnter());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        listaFornecedores = org.jdesktop.observablecollections.ObservableCollections.observableList(new ArrayList());
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPesquisaFornecedor = new javax.swing.JTable();

        tbPesquisaFornecedor.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if(tbPesquisaFornecedor.getSelectedRow() != -1){
                    fornecedor = listaFornecedores.get(tbPesquisaFornecedor.getSelectedRow());
                    btnAdicionar.setEnabled(true);
                }else{
                    fornecedor = null;
                    btnAdicionar.setEnabled(false);
                }
            }
        });
        jPanel4 = new javax.swing.JPanel();
        btnPesquisar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        ckbCNPJ = new javax.swing.JCheckBox();
        ckbNomeFantasia = new javax.swing.JCheckBox();
        txtCnpj = new javax.swing.JTextField();
        txtNomeFantasia = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Ana Maria Cirurgica - Escolher Cliente");
        setBounds(new java.awt.Rectangle(400, 150, 0, 0));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setLayout(new java.awt.GridBagLayout());

        tbPesquisaFornecedor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbPesquisaFornecedor.setColumnSelectionAllowed(true);
        tbPesquisaFornecedor.setFocusable(false);
        tbPesquisaFornecedor.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listaFornecedores, tbPesquisaFornecedor);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nomeFantasia}"));
        columnBinding.setColumnName("Nome Fantasia");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${cnpj}"));
        columnBinding.setColumnName("CNPJ");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        tbPesquisaFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPesquisaFornecedorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbPesquisaFornecedor);
        tbPesquisaFornecedor.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tbPesquisaFornecedor.getColumnModel().getColumn(0).setPreferredWidth(300);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 507;
        gridBagConstraints.ipady = 113;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 10, 20);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnPesquisar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bt_pesquisar.png"))); // NOI18N
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel3.setText("Pesquisar");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel5.setText("CNPJ:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel9.setText("Nome Fantasia:");

        ckbCNPJ.setBackground(new java.awt.Color(206, 206, 255));
        ckbCNPJ.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ckbCNPJStateChanged(evt);
            }
        });
        ckbCNPJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbCNPJActionPerformed(evt);
            }
        });

        ckbNomeFantasia.setBackground(new java.awt.Color(206, 206, 255));
        ckbNomeFantasia.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ckbNomeFantasiaStateChanged(evt);
            }
        });
        ckbNomeFantasia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbNomeFantasiaActionPerformed(evt);
            }
        });

        txtCnpj.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtCnpj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCnpjActionPerformed(evt);
            }
        });

        txtNomeFantasia.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtNomeFantasia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeFantasiaActionPerformed(evt);
            }
        });

        btnAdicionar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bt_novo_cliente.png"))); // NOI18N
        btnAdicionar.setEnabled(false);
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnVoltar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bt_voltar.png"))); // NOI18N
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckbCNPJ)
                    .addComponent(ckbNomeFantasia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnPesquisar)
                        .addGap(1, 1, 1)
                        .addComponent(jLabel3))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(ckbCNPJ)
                            .addGap(19, 19, 19)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addComponent(ckbNomeFantasia)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdicionar)
                            .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 56;
        gridBagConstraints.insets = new java.awt.Insets(35, 20, 20, 10);
        jPanel1.add(jPanel4, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel10.setText("Escolha o Fornecedor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 10, 20);
        jPanel1.add(jLabel10, gridBagConstraints);

        getContentPane().add(jPanel1);

        bindingGroup.bind();

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-814)/2, (screenSize.height-546)/2, 814, 546);
    }// </editor-fold>//GEN-END:initComponents

    private void tbPesquisaFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPesquisaFornecedorMouseClicked
    }//GEN-LAST:event_tbPesquisaFornecedorMouseClicked

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        pesquisarFornecedor();
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void ckbCNPJStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ckbCNPJStateChanged
        if (ckbCNPJ.isSelected()) {
            ckbNomeFantasia.setSelected(false);
            txtCnpj.setEnabled(true);
        } else {
            txtCnpj.setEnabled(false);
        }
    }//GEN-LAST:event_ckbCNPJStateChanged

    private void ckbCNPJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbCNPJActionPerformed
    }//GEN-LAST:event_ckbCNPJActionPerformed

    private void ckbNomeFantasiaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ckbNomeFantasiaStateChanged
        if (ckbNomeFantasia.isSelected()) {
            ckbCNPJ.setSelected(false);
            txtNomeFantasia.setEnabled(true);
        } else {
            txtNomeFantasia.setEnabled(false);
        }
    }//GEN-LAST:event_ckbNomeFantasiaStateChanged

    private void ckbNomeFantasiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbNomeFantasiaActionPerformed
    }//GEN-LAST:event_ckbNomeFantasiaActionPerformed

    private void txtCnpjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCnpjActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCnpjActionPerformed

    private void txtNomeFantasiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeFantasiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeFantasiaActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed

        chamarCadastrarPedido();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        try {
            voltar();
        } catch (ParseException ex) {
            Logger.getLogger(PesquisarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            voltar();
        } catch (ParseException ex) {
            Logger.getLogger(PesquisarFornecedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing
    public void chamarCadastrarPedido() {

        if (tbPesquisaFornecedor.getSelectedRowCount() <= 0) {
            JOptionPane.showMessageDialog(this, "Selecione um Fornecedor para efetuar o pedido.", "Seleção Necessária", 1);

        } else {        
            CadastrarPedido cp = new CadastrarPedido(fornecedor, null);
            cp.setVisible(true);
            this.dispose();
        }

    }

    private void popularTabela() {
        FornecedorDAO dao = new FornecedorDAO();
        List<Fornecedor> listaFornecedor = dao.findAll();
        this.listaFornecedores.clear();
        if (listaFornecedor != null && listaFornecedor.size() > 0) {
            this.listaFornecedores.addAll(listaFornecedor);
        }
    }

    public void pesquisarFornecedor() {
        FornecedorDAO dao = new FornecedorDAO();

        List<Fornecedor> listaPesquisa = null;
        if (ckbCNPJ.isSelected()) {
            listaPesquisa = dao.getAllByCNPJ(txtCnpj.getText());
        } else if (ckbNomeFantasia.isSelected()) {
            listaPesquisa = dao.getAllByNomeFantasia(txtNomeFantasia.getText());
        } else {
            listaPesquisa = dao.findAll();
        }

        this.listaFornecedores.clear();
        if (listaPesquisa == null | listaPesquisa.size() <= 0) {
            JOptionPane.showMessageDialog(this, "Não foram encontrados fornecedores com o critério especificado");
        } else {
            this.listaFornecedores.addAll(listaPesquisa);
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
            java.util.logging.Logger.getLogger(PesquisarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PesquisarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PesquisarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PesquisarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PesquisarFornecedor().setVisible(true);
            }
        });
    }

    public void voltar() throws ParseException {

        GestaoPedido gp = new GestaoPedido();
        gp.setVisible(true);
        this.dispose();

    }

    @Override
    public void cancelarFechamento() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void fecharJanela() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void atribuirListeners(EventListener listener) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    class PressEnter implements KeyListener {

        public void keyPressed(KeyEvent e) {
            // TODO Auto-generated method stub  

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (txtCnpj.isFocusOwner() || txtNomeFantasia.isFocusOwner()) {
                    pesquisarFornecedor();
                } else if (txtNomeFantasia.isFocusOwner() || txtNomeFantasia.isFocusOwner()) {
                    pesquisarFornecedor();
                } else if (btnVoltar.isFocusOwner()) {
                    try {
                        voltar();
                    } catch (ParseException ex) {
                        Logger.getLogger(PesquisarCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (btnAdicionar.isFocusOwner()) {
                    chamarCadastrarPedido();

                }
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            }
        }

        public void keyReleased(KeyEvent e) {
        }

        public void keyTyped(KeyEvent e) {
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JCheckBox ckbCNPJ;
    private javax.swing.JCheckBox ckbNomeFantasia;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private java.util.List<br.com.cirurgica.model.Fornecedor> listaFornecedores;
    private javax.swing.JTable tbPesquisaFornecedor;
    private javax.swing.JTextField txtCnpj;
    private javax.swing.JTextField txtNomeFantasia;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
