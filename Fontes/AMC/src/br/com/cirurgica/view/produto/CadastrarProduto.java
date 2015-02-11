/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.view.produto;

import br.com.cirurgica.controller.ProdutoController;
import br.com.cirurgica.model.Fornecedor;
import br.com.cirurgica.model.FornecedorProduto;
import br.com.cirurgica.model.Produto;
import br.com.cirurgica.view.IView;
import br.com.cirurgica.view.utils.Acao;
import br.com.cirurgica.view.utils.LoggingBindingListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author Leo, Diego
 */
public final class CadastrarProduto extends javax.swing.JFrame implements IView {

    @Override
    public void cancelarFechamento() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void fecharJanela() {
        this.dispose();
    }

    @Override
    public void atribuirListeners(EventListener listener) {
        ActionListener action = (ActionListener) listener;
        KeyListener key = (KeyListener) listener;
        WindowListener window = (WindowListener) listener;

        btnAdicionar.addActionListener(action);
        btnAdicionar.addKeyListener(key);

        btnCancelar.addActionListener(action);
        btnCancelar.addKeyListener(key);

        this.addWindowListener(window);
    }
    private Acao acao;

    /**
     * Creates new form CadastrarProduto
     */
    public CadastrarProduto(Produto p, EventListener listener, List<Fornecedor> fornecedores, List<FornecedorProduto> fornecedoresExistentes) {
        this.produtoTela = p;
        this.listaFornecedores = ObservableCollections.observableList(fornecedores);
        initComponents();

        if (p != null) {
            initAlterar(fornecedoresExistentes);
        } else {
            initIncluir();
        }
        
        atribuirListeners(listener);
        bindingGroup.addBindingListener(new LoggingBindingListener(validationMsgLabel));
    }

    public List<Fornecedor> getListaFornecedores() {
        return this.listaFornecedores;
    }

    private void initAlterar(List<FornecedorProduto> fornecedoresExistentes) {
        this.acao = Acao.ALTERAR;
        this.lblAcaoCadastro.setText("ALTERAR PRODUTO");
        this.lblTitulo.setText("Alteração de Produto");
        this.setTitle("Ana Maria Cirurgica - Alterar Produto");
        popularItensSelecionados(fornecedoresExistentes);
    }

    private void initIncluir() {
        this.acao = Acao.INCLUIR;
        this.lblAcaoCadastro.setText("CADASTRAR PRODUTO");
        this.lblTitulo.setText("Cadastro de Produto");
        this.setTitle("Ana Maria Cirurgica - Cadastrar Produto");
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

        produtoTela = produtoTela == null ? new Produto() : produtoTela;
        listaFornecedores = listaFornecedores == null ? ObservableCollections.observableList(new ArrayList<Fornecedor>()) : listaFornecedores;
        moneyConverter = new br.com.cirurgica.view.utils.MoneyConverter();
        integerConverter = new br.com.cirurgica.view.utils.IntegerConverter();
        listaTamanhos = org.jdesktop.observablecollections.ObservableCollections.observableList(new ArrayList<String>());
        numberValidator = new br.com.cirurgica.view.utils.ValorNegativoValidator();
        moneyValidator = new br.com.cirurgica.view.utils.MoneyValidator();
        fornecedorProdutoConverter = new br.com.cirurgica.view.utils.FornecedorProdutoConverter();
        panelTela = new javax.swing.JPanel();
        painelCampos = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNomeProduto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaDescricao = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMarcaProduto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCor = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCodigoBarras = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtEstoque = new javax.swing.JTextField();
        panelPrecos = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtValorCusto = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtValorAtacado = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtValorVarejo = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtCodigoProduto = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        cbTamanho = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListFornecedores = new javax.swing.JList<br.com.cirurgica.model.Fornecedor>();
        jLabel11 = new javax.swing.JLabel();
        validationMsgLabel = new javax.swing.JLabel();
        painelBotoes = new javax.swing.JPanel();
        btnAdicionar = new javax.swing.JButton();
        lblAcaoCadastro = new javax.swing.JLabel();
        lblCancelar = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jListFornecedores, org.jdesktop.beansbinding.ELProperty.create("${selectedElements}"), produtoTela, org.jdesktop.beansbinding.BeanProperty.create("fornecedorCollection"), "VincularListfornecedores");
        binding.setConverter(fornecedorProdutoConverter);
        bindingGroup.addBinding(binding);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Ana Maria Cirurgica - Cadastrar Produto");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setFocusCycleRoot(false);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        painelCampos.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20)));

        lblTitulo.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Cadastro de Produto");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel2.setText("Nome do Produto:");

        txtNomeProduto.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtNomeProduto.setPreferredSize(new java.awt.Dimension(6, 24));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, produtoTela, org.jdesktop.beansbinding.ELProperty.create("${nome}"), txtNomeProduto, org.jdesktop.beansbinding.BeanProperty.create("text"), "nomeProdutoBinding");
        bindingGroup.addBinding(binding);

        txaDescricao.setColumns(20);
        txaDescricao.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txaDescricao.setRows(5);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, produtoTela, org.jdesktop.beansbinding.ELProperty.create("${descricao}"), txaDescricao, org.jdesktop.beansbinding.BeanProperty.create("text"), "descricaoBinding");
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(txaDescricao);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel3.setText("Descrição:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel4.setText("Marca:");

        txtMarcaProduto.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtMarcaProduto.setPreferredSize(new java.awt.Dimension(6, 24));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, produtoTela, org.jdesktop.beansbinding.ELProperty.create("${marca}"), txtMarcaProduto, org.jdesktop.beansbinding.BeanProperty.create("text"), "marcaProdutoBinding");
        bindingGroup.addBinding(binding);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel5.setText("Cor:");

        txtCor.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtCor.setPreferredSize(new java.awt.Dimension(6, 24));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, produtoTela, org.jdesktop.beansbinding.ELProperty.create("${cor}"), txtCor, org.jdesktop.beansbinding.BeanProperty.create("text"), "corBinding");
        bindingGroup.addBinding(binding);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel6.setText("Código de Barras:");

        txtCodigoBarras.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtCodigoBarras.setPreferredSize(new java.awt.Dimension(6, 24));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, produtoTela, org.jdesktop.beansbinding.ELProperty.create("${codigoBarras}"), txtCodigoBarras, org.jdesktop.beansbinding.BeanProperty.create("text"), "codigoBarrasBinding");
        bindingGroup.addBinding(binding);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel8.setText("Em estoque:");

        txtEstoque.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtEstoque.setPreferredSize(new java.awt.Dimension(6, 24));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, produtoTela, org.jdesktop.beansbinding.ELProperty.create("${qtdeEstoque}"), txtEstoque, org.jdesktop.beansbinding.BeanProperty.create("text_ON_ACTION_OR_FOCUS_LOST"), "Estoque");
        binding.setConverter(integerConverter);
        binding.setValidator(numberValidator);
        bindingGroup.addBinding(binding);

        panelPrecos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelPrecos.setLayout(new java.awt.GridBagLayout());

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel13.setText("R$");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        panelPrecos.add(jLabel13, gridBagConstraints);

        txtValorCusto.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtValorCusto.setPreferredSize(new java.awt.Dimension(6, 24));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, produtoTela, org.jdesktop.beansbinding.ELProperty.create("${valorCompra}"), txtValorCusto, org.jdesktop.beansbinding.BeanProperty.create("text_ON_ACTION_OR_FOCUS_LOST"), "valorCustoBinding");
        binding.setConverter(moneyConverter);
        binding.setValidator(moneyValidator);
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 94;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        panelPrecos.add(txtValorCusto, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel7.setText("Valor de Custo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 26;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        panelPrecos.add(jLabel7, gridBagConstraints);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Ex. : 45,99");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 46;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 4, 0);
        panelPrecos.add(jLabel14, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Ex. : 89,50");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 46;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 4, 0);
        panelPrecos.add(jLabel12, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel1.setText("R$");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        panelPrecos.add(jLabel1, gridBagConstraints);

        txtValorAtacado.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtValorAtacado.setPreferredSize(new java.awt.Dimension(6, 24));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, produtoTela, org.jdesktop.beansbinding.ELProperty.create("${valorVendaAtacado}"), txtValorAtacado, org.jdesktop.beansbinding.BeanProperty.create("text_ON_ACTION_OR_FOCUS_LOST"), "valorAtacadoBinding");
        binding.setConverter(moneyConverter);
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 94;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        panelPrecos.add(txtValorAtacado, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel10.setText("Valor de Atacado:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        panelPrecos.add(jLabel10, gridBagConstraints);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel19.setText("R$");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        panelPrecos.add(jLabel19, gridBagConstraints);

        txtValorVarejo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtValorVarejo.setPreferredSize(new java.awt.Dimension(6, 24));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, produtoTela, org.jdesktop.beansbinding.ELProperty.create("${valorVenda}"), txtValorVarejo, org.jdesktop.beansbinding.BeanProperty.create("text_ON_ACTION_OR_FOCUS_LOST"), "valorVarejoBinding");
        binding.setConverter(moneyConverter);
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 94;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        panelPrecos.add(txtValorVarejo, gridBagConstraints);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Ex. : 89,50");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 46;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 4, 0);
        panelPrecos.add(jLabel20, gridBagConstraints);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel21.setText("Valor de Varejo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        panelPrecos.add(jLabel21, gridBagConstraints);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel18.setText("Fornecedor:");

        txtCodigoProduto.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtCodigoProduto.setPreferredSize(new java.awt.Dimension(6, 24));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, produtoTela, org.jdesktop.beansbinding.ELProperty.create("${cdFarmaceutico}"), txtCodigoProduto, org.jdesktop.beansbinding.BeanProperty.create("text"), "codigoProdutoBinding");
        bindingGroup.addBinding(binding);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel22.setText("Tamanho:");

        cbTamanho.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cbTamanho.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PP", "P", "M", "G", "GG", "GGG", "GGGG" }));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, produtoTela, org.jdesktop.beansbinding.ELProperty.create("${tamanho}"), cbTamanho, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"), "tamanhoBinding");
        bindingGroup.addBinding(binding);

        org.jdesktop.swingbinding.JListBinding jListBinding = org.jdesktop.swingbinding.SwingBindings.createJListBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listaFornecedores, jListFornecedores, "");
        bindingGroup.addBinding(jListBinding);

        jScrollPane2.setViewportView(jListFornecedores);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel11.setText("Código:");

        painelBotoes.setLayout(new java.awt.GridBagLayout());

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bt_novo_cliente.png"))); // NOI18N
        btnAdicionar.setActionCommand("CadastroProduto.salvar");
        btnAdicionar.setName("CadastroProduto.salvar"); // NOI18N
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        painelBotoes.add(btnAdicionar, gridBagConstraints);

        lblAcaoCadastro.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblAcaoCadastro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAcaoCadastro.setText("CADASTRAR PRODUTO");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(1, 10, 10, 10);
        painelBotoes.add(lblAcaoCadastro, gridBagConstraints);

        lblCancelar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblCancelar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCancelar.setText("CANCELAR");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 8;
        gridBagConstraints.insets = new java.awt.Insets(1, 10, 10, 10);
        painelBotoes.add(lblCancelar, gridBagConstraints);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bt_voltar.png"))); // NOI18N
        btnCancelar.setActionCommand("CadastroProduto.cancelar");
        btnCancelar.setName("CadastroProduto.cancelar"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        painelBotoes.add(btnCancelar, gridBagConstraints);

        javax.swing.GroupLayout painelCamposLayout = new javax.swing.GroupLayout(painelCampos);
        painelCampos.setLayout(painelCamposLayout);
        painelCamposLayout.setHorizontalGroup(
            painelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelCamposLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(painelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(painelCamposLayout.createSequentialGroup()
                        .addGroup(painelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(painelCamposLayout.createSequentialGroup()
                                .addGroup(painelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(painelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMarcaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(validationMsgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane1)
                            .addGroup(painelCamposLayout.createSequentialGroup()
                                .addGroup(painelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(painelCamposLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(90, 90, 90)
                                        .addComponent(jLabel22))
                                    .addGroup(painelCamposLayout.createSequentialGroup()
                                        .addComponent(txtCor, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(cbTamanho, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(16, 16, 16)
                                .addGroup(painelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addGroup(painelCamposLayout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(txtEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addGroup(painelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel6)
                            .addComponent(txtCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(painelBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelCamposLayout.createSequentialGroup()
                        .addComponent(panelPrecos, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelCamposLayout.setVerticalGroup(
            painelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelCamposLayout.createSequentialGroup()
                .addComponent(lblTitulo)
                .addGap(43, 43, 43)
                .addGroup(painelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(3, 3, 3)
                .addGroup(painelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(painelCamposLayout.createSequentialGroup()
                        .addComponent(txtNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(painelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMarcaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(validationMsgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelCamposLayout.createSequentialGroup()
                            .addGroup(painelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel22))
                            .addGap(6, 6, 6)
                            .addGroup(painelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtCor, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelCamposLayout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(6, 6, 6)
                            .addComponent(txtCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(1, 1, 1)))
                    .addGroup(painelCamposLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(6, 6, 6)
                        .addComponent(txtEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37)
                .addComponent(panelPrecos, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(painelBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelTelaLayout = new javax.swing.GroupLayout(panelTela);
        panelTela.setLayout(panelTelaLayout);
        panelTelaLayout.setHorizontalGroup(
            panelTelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTelaLayout.createSequentialGroup()
                .addComponent(painelCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(178, 178, 178))
        );
        panelTelaLayout.setVerticalGroup(
            panelTelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelCampos, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
        );

        getContentPane().add(panelTela);

        bindingGroup.bind();

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-753)/2, (screenSize.height-629)/2, 753, 629);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdicionarActionPerformed

    public Boolean validarCampos() {
        return (txtNomeProduto.getText().equals("")
                || txtCodigoProduto.getText().equals("") || txtMarcaProduto.getText().equals("")
                || txaDescricao.getText().equals("") || txtValorCusto.getText().equals("")
                || txtValorAtacado.getText().equals("") || txtValorVarejo.getText().equals("")
                || txtCor.getText().equals("") || txtEstoque.getText().equals("")
                || txtCodigoBarras.getText().equals("")
                //TESTAR
                || produtoTela.getFornecedorCollection().size() <= 0
                || cbTamanho.getSelectedItem() == null);
    }

    public void limparCampos() {
        for (FornecedorProduto fp : this.produtoTela.getFornecedorCollection()) {
            System.out.println(fp);
        }
    }

    public Acao getAcao() {
        return this.acao;
    }

    public Produto getProdutoTela() {
        return this.produtoTela;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.cotxtProdutotorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastrarProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ProdutoController p = new ProdutoController().init();
                p.register(null);
                //new CadastrarProduto(null).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox cbTamanho;
    private br.com.cirurgica.view.utils.FornecedorProdutoConverter fornecedorProdutoConverter;
    private br.com.cirurgica.view.utils.IntegerConverter integerConverter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<br.com.cirurgica.model.Fornecedor> jListFornecedores;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAcaoCadastro;
    private javax.swing.JLabel lblCancelar;
    private javax.swing.JLabel lblTitulo;
    private java.util.List<Fornecedor> listaFornecedores;
    private java.util.List<String> listaTamanhos;
    private br.com.cirurgica.view.utils.MoneyConverter moneyConverter;
    private br.com.cirurgica.view.utils.MoneyValidator moneyValidator;
    private br.com.cirurgica.view.utils.ValorNegativoValidator numberValidator;
    private javax.swing.JPanel painelBotoes;
    private javax.swing.JPanel painelCampos;
    private javax.swing.JPanel panelPrecos;
    private javax.swing.JPanel panelTela;
    private br.com.cirurgica.model.Produto produtoTela;
    private javax.swing.JTextArea txaDescricao;
    private javax.swing.JTextField txtCodigoBarras;
    private javax.swing.JTextField txtCodigoProduto;
    private javax.swing.JTextField txtCor;
    private javax.swing.JTextField txtEstoque;
    private javax.swing.JTextField txtMarcaProduto;
    private javax.swing.JTextField txtNomeProduto;
    private javax.swing.JTextField txtValorAtacado;
    private javax.swing.JTextField txtValorCusto;
    private javax.swing.JTextField txtValorVarejo;
    private javax.swing.JLabel validationMsgLabel;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    private void popularItensSelecionados(List<FornecedorProduto> fornecedorCollection) {
        for (int i = 0; i < fornecedorCollection.size(); i++) {
            FornecedorProduto fp = fornecedorCollection.get(i);
            int index = this.listaFornecedores.indexOf(fp.getFornecedor());
            if (index != -1) {
                jListFornecedores.addSelectionInterval(index, index);
            }
        }
    }
}