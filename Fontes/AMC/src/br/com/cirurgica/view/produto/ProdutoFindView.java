/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.view.produto;

import br.com.cirurgica.controller.ProdutoController;
import br.com.cirurgica.model.Produto;
import br.com.cirurgica.view.IView;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author dfelix3
 */
public final class ProdutoFindView extends javax.swing.JDialog implements IView{

    /**
     * Creates new form ProdutoFindView
     */
    /*
     * Este atributo é a linha selecionada da tabela
     */
    private Produto produto;

    public ProdutoFindView(EventListener listener) {
        initComponents();
        atribuirListeners(listener);
    }

    public List<Produto> getListaProduto() {
        return this.listaProduto;
    }

    public Produto getProduto() {
        return produto;
    }

    public Produto getCriteria() {
        return produtoBean;
    }
    
    public String getOptionSelected() {
        return (buttonGroup.getSelection() != null)
                ? buttonGroup.getSelection().getActionCommand()
                : "";
    }

    @Override
    public void atribuirListeners(EventListener listener){
        ActionListener action = (ActionListener) listener;
        KeyListener key = (KeyListener) listener;
        WindowListener window = (WindowListener) listener;
        this.btnBuscaProduto.addActionListener(action);
        this.btnBuscaProduto.addKeyListener(key);
        this.btnObterProduto.addActionListener(action);
        this.btnObterProduto.addKeyListener(key);
        this.btnVoltar.addActionListener(action);
        this.btnVoltar.addKeyListener(key);
        this.addWindowListener(window);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        buttonGroup = new javax.swing.ButtonGroup();
        listaProduto = org.jdesktop.observablecollections.ObservableCollections.observableList(new ArrayList());
        produtoBean = new br.com.cirurgica.model.Produto();
        panelCampos = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        txtNomeProduto = new javax.swing.JTextField();
        txtCodigoProduto = new javax.swing.JTextField();
        lblBuscaPor = new javax.swing.JLabel();
        rdbNomeProduto = new javax.swing.JRadioButton();
        rdbCodigoProduto = new javax.swing.JRadioButton();
        btnBuscaProduto = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSelecaoProdutos = new javax.swing.JTable();
        tbSelecaoProdutos.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if(tbSelecaoProdutos.getSelectedRow() != -1){
                    produto = listaProduto.get(tbSelecaoProdutos.getSelectedRow());
                    btnObterProduto.setEnabled(true);
                }else{
                    produto = null;
                    btnObterProduto.setEnabled(false);
                }
            }
        });
        panelBotoes = new javax.swing.JPanel();
        btnObterProduto = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, txtCodigoProduto, org.jdesktop.beansbinding.ELProperty.create("${text}"), produtoBean, org.jdesktop.beansbinding.BeanProperty.create("cdFarmaceutico"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, txtNomeProduto, org.jdesktop.beansbinding.ELProperty.create("${text}"), produtoBean, org.jdesktop.beansbinding.BeanProperty.create("nome"));
        bindingGroup.addBinding(binding);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setModal(true);
        setUndecorated(true);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        panelCampos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        panelCampos.setLayout(new java.awt.GridBagLayout());

        lblTitulo.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        lblTitulo.setText("Seleção de Produto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.insets = new java.awt.Insets(10, 6, 0, 0);
        panelCampos.add(lblTitulo, gridBagConstraints);

        txtNomeProduto.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtNomeProduto.setPreferredSize(new java.awt.Dimension(6, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 304;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 6, 0, 0);
        panelCampos.add(txtNomeProduto, gridBagConstraints);

        txtCodigoProduto.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtCodigoProduto.setPreferredSize(new java.awt.Dimension(6, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 98;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 6, 0, 0);
        panelCampos.add(txtCodigoProduto, gridBagConstraints);

        lblBuscaPor.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblBuscaPor.setText("Busca por:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 0, 0);
        panelCampos.add(lblBuscaPor, gridBagConstraints);

        buttonGroup.add(rdbNomeProduto);
        rdbNomeProduto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        rdbNomeProduto.setText("Nome do Produto:");
        rdbNomeProduto.setActionCommand("P");
        rdbNomeProduto.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 10, 0, 0);
        panelCampos.add(rdbNomeProduto, gridBagConstraints);

        buttonGroup.add(rdbCodigoProduto);
        rdbCodigoProduto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        rdbCodigoProduto.setText("Código:");
        rdbCodigoProduto.setActionCommand("C");
        rdbCodigoProduto.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 0);
        panelCampos.add(rdbCodigoProduto, gridBagConstraints);

        btnBuscaProduto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnBuscaProduto.setMnemonic('P');
        btnBuscaProduto.setText("Buscar Produto");
        btnBuscaProduto.setActionCommand("ProdutoFindView.buscarProduto");
        btnBuscaProduto.setName("ProdutoFindView.buscarProduto"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        panelCampos.add(btnBuscaProduto, gridBagConstraints);

        tbSelecaoProdutos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbSelecaoProdutos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listaProduto, tbSelecaoProdutos, "");
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${cdFarmaceutico}"));
        columnBinding.setColumnName("Código");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nome}"));
        columnBinding.setColumnName("Nome");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${qtdeEstoque}"));
        columnBinding.setColumnName("Estoque");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${valorVenda}"));
        columnBinding.setColumnName("Valor Varejo");
        columnBinding.setColumnClass(Float.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${valorVendaAtacado}"));
        columnBinding.setColumnName("Valor Atacado");
        columnBinding.setColumnClass(Float.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(tbSelecaoProdutos);
        tbSelecaoProdutos.getColumnModel().getColumn(0).setResizable(false);
        tbSelecaoProdutos.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbSelecaoProdutos.getColumnModel().getColumn(1).setPreferredWidth(200);
        tbSelecaoProdutos.getColumnModel().getColumn(2).setResizable(false);
        tbSelecaoProdutos.getColumnModel().getColumn(2).setPreferredWidth(40);
        tbSelecaoProdutos.getColumnModel().getColumn(3).setCellRenderer(new br.com.cirurgica.view.utils.cell.MoneyCellRenderer());
        tbSelecaoProdutos.getColumnModel().getColumn(4).setCellRenderer(new br.com.cirurgica.view.utils.cell.MoneyCellRenderer());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 440;
        gridBagConstraints.ipady = 132;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 11, 30);
        panelCampos.add(jScrollPane1, gridBagConstraints);

        btnObterProduto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnObterProduto.setText("Obter produto selecionado");
        btnObterProduto.setActionCommand("ProdutoFindView.obterProduto");
        btnObterProduto.setEnabled(false);
        btnObterProduto.setName("ProdutoFindView.obterProduto"); // NOI18N

        btnVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bt_voltar_pequeno.png"))); // NOI18N
        btnVoltar.setActionCommand("ProdutoFindView.voltar");

        javax.swing.GroupLayout panelBotoesLayout = new javax.swing.GroupLayout(panelBotoes);
        panelBotoes.setLayout(panelBotoesLayout);
        panelBotoesLayout.setHorizontalGroup(
            panelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotoesLayout.createSequentialGroup()
                .addContainerGap(254, Short.MAX_VALUE)
                .addComponent(btnObterProduto)
                .addGap(167, 167, 167)
                .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        panelBotoesLayout.setVerticalGroup(
            panelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotoesLayout.createSequentialGroup()
                .addGroup(panelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnObterProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelCampos.add(panelBotoes, gridBagConstraints);

        getContentPane().add(panelCampos);

        bindingGroup.bind();

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-661)/2, (screenSize.height-500)/2, 661, 500);
    }// </editor-fold>//GEN-END:initComponents

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProdutoFindView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
               Produto p = new ProdutoController().find();
               System.out.println(p);
               System.exit(0);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscaProduto;
    private javax.swing.JButton btnObterProduto;
    private javax.swing.JButton btnVoltar;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscaPor;
    private javax.swing.JLabel lblTitulo;
    private java.util.List<br.com.cirurgica.model.Produto> listaProduto;
    private javax.swing.JPanel panelBotoes;
    private javax.swing.JPanel panelCampos;
    private br.com.cirurgica.model.Produto produtoBean;
    private javax.swing.JRadioButton rdbCodigoProduto;
    private javax.swing.JRadioButton rdbNomeProduto;
    private javax.swing.JTable tbSelecaoProdutos;
    private javax.swing.JTextField txtCodigoProduto;
    private javax.swing.JTextField txtNomeProduto;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    @Override
    public void cancelarFechamento() {
        
    }

    @Override
    public void fecharJanela() {
        this.dispose();
    }
}
