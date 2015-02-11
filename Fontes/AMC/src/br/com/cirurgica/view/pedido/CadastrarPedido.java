/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.view.pedido;

import br.com.cirurgica.controller.PedidoController;
import br.com.cirurgica.model.Produto;
import br.com.cirurgica.dao.PedidoDAO;
import br.com.cirurgica.dao.ProdutoDAO;
import br.com.cirurgica.generated.model.NotaFiscal;
import br.com.cirurgica.generated.model.TipoNotaFiscal;
import br.com.cirurgica.model.Fornecedor;
import br.com.cirurgica.model.Pedido;
import br.com.cirurgica.model.PedidoProduto;
import br.com.cirurgica.view.IView;
import com.utilidades.Utilidades;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;

/**
 *
 * @author Leo
 */
public class CadastrarPedido extends javax.swing.JFrame implements IView {

    private int alterado = 0;

    /**
     * Creates new form CadastrarPedido
     */
    private CadastrarPedido() {
        initComponents();
    }

    private void initRules(EventListener listener) {
        this.setTxtFornecedor(this.pedido.getCdFornecedor().getNmRazaoSocial());
        this.moneyCellEditor.setTotalField(txtValorPedido);
        this.moneyCellEditor.setPedido(this.pedido);
        this.moneyCellEditor.setClickCountToStart(1);
        this.integerCellEditor.setTotalField(txtValorPedido);
        this.integerCellEditor.setPedido(this.pedido);
        this.integerCellEditor.setClickCountToStart(1);
        this.atribuirListeners(listener);
    }

    //Inclusão
    public CadastrarPedido(Fornecedor fornecedor, EventListener listener) {
        this();
        try {
            BeanUtils.copyProperties(this.pedido.getCdFornecedor(), fornecedor);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(CadastrarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.initRules(listener);
    }
    //Alterar

    public CadastrarPedido(Pedido pedido, EventListener listener) {
        this();
        if (pedido != null) {
            if (pedido.getCdNotaFiscal() == null) {
                pedido.setCdNotaFiscal(new NotaFiscal());
            }
            try {
                BeanUtils.copyProperties(this.pedido, pedido);

            } catch (IllegalAccessException | InvocationTargetException ex) {
                Logger.getLogger(CadastrarPedido.class.getName()).log(Level.SEVERE, null, ex);
            } catch(ConversionException ex){
                Logger.getLogger(CadastrarPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            throw new RuntimeException("Pedido informado para alteração é inválido");
        }
        this.alterado = 1;
        if (this.pedido.getCdNotaFiscal() != null && this.pedido.getCdNotaFiscal().getNumeroNotaFiscal() != null) {
            this.txtNotaFiscal.setText(this.pedido.getCdNotaFiscal().getNumeroNotaFiscal());
        }
        this.ckPago.setSelected(this.pedido.getIsPago());
        this.ckRecebido.setSelected(this.pedido.getIsRecebido());
        this.cbDataPedido.setDate(this.pedido.getDataPedido());
        this.listaPedidoProduto.addAll(this.pedido.getPedidoProdutoCollection());
        this.txtValorPedido.setText(Utilidades.retornarValorFormatado(this.pedido.calcularValorTotal()));

        //Alterando os labels e comportamento dos campos
        this.panelPesquisa.setEnabled(false);
        this.txtProduto.setEnabled(false);
        this.btnProcurar.setEnabled(false);
        this.btnAdicionar.setEnabled(false);
        this.btnRemover.setEnabled(false);
        this.tableProdutos.setEnabled(false);
        this.initRules(listener);
    }

    public void limparCampos() {
//        jTextArea1.setText(null);
        txtValorPedido.setText(null);
        txtNotaFiscal.setText(null);
        ckRecebido.setSelected(false);
        ckPago.setSelected(false);

        cbDataPedido.setDate(new Date());

    }

    public void adicionarAoCarrinho() {
        Produto p = new Produto();
        PedidoProduto pp = new PedidoProduto();

        String texto = this.txtProduto.getText();
        if (texto.trim().equals("")) {
        } else {
            ProdutoDAO dao = new ProdutoDAO();
            Produto ppq = dao.getByCodigoFarmaceutico(texto);
            if (ppq != null) {
                p = ppq;
            } else {
                return;
            }
        }

        pp.setCdProduto(p);
        pp.setQtdeProduto(1);
        pp.setVlProduto(new Float(p.getValorCompra().floatValue()));
        pp.setCdPedido(pedido);
        this.pedido.getPedidoProdutoCollection().add(pp);

    }

    public void cadastrarPedido() {

        if (this.pedido.getPedidoProdutoCollection().size() < 0) {
            JOptionPane.showMessageDialog(this, "Adicionar um item ao pedido!");
        } else {
            PedidoDAO dao = new PedidoDAO();
            try {
                dao.incluir(pedido);
            } catch (Exception ex) {
                Logger.getLogger(CadastrarPedido.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Pedido cadastrado com sucesso!");
            System.out.println(this.pedido.toString());
        }
    }

    public void setTxtValorPedido(String texto) {
        txtValorPedido.setText(texto);
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

        pedido = new br.com.cirurgica.model.Pedido();
        textFieldPrecoCusto = new javax.swing.JTextField();
        produtoPesquisa = new br.com.cirurgica.model.Produto();
        pedidoProdutoSelecionado = new br.com.cirurgica.model.PedidoProduto();
        listaPedidoProduto = org.jdesktop.observablecollections.ObservableCollections.observableList(new ArrayList());
        moneyCellRenderer = new br.com.cirurgica.view.utils.cell.MoneyCellRenderer();
        moneyCellEditor = new br.com.cirurgica.view.utils.cell.MoneyCellEditor();
        dateConverter = new br.com.cirurgica.view.utils.DateConverter();
        moneyConverter = new br.com.cirurgica.view.utils.MoneyConverter();
        moneyFormattedConverter = new br.com.cirurgica.view.utils.MoneyFormattedConverter();
        integerCellEditor = new br.com.cirurgica.view.utils.cell.IntegerCellEditor();
        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panelValorPedido = new javax.swing.JPanel();
        txtValorPedido = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        ckRecebido = new javax.swing.JCheckBox();
        ckPago = new javax.swing.JCheckBox();
        txtNotaFiscal = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProdutos = new javax.swing.JTable();
        tableProdutos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (tableProdutos.getSelectedRow() != -1) {
                    List<PedidoProduto> lista = (List<PedidoProduto>) pedido.getPedidoProdutoCollection();
                    pedidoProdutoSelecionado = lista.get(tableProdutos.getSelectedRow());
                    btnRemover.setEnabled(true);
                } else {
                    pedidoProdutoSelecionado = null;
                    btnRemover.setEnabled(false);
                }
            }
        });
        jLabel10 = new javax.swing.JLabel();
        txtFornecedor = new javax.swing.JTextField();
        btnRemover = new javax.swing.JButton();
        panelPesquisa = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtProduto = new javax.swing.JTextField();
        btnProcurar = new javax.swing.JButton();
        btnAdicionar = new javax.swing.JButton();
        painelBotoes = new javax.swing.JPanel();
        lblConfirmar = new javax.swing.JLabel();
        lblCancelar = new javax.swing.JLabel();
        btnCadastrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        cbDataPedido = new com.toedter.calendar.JDateChooser();

        pedido.setCdFornecedor(new Fornecedor());
        pedido.setCdNotaFiscal(new br.com.cirurgica.generated.model.NotaFiscal(new TipoNotaFiscal(1)));
        pedido.setIsPago(Boolean.FALSE);
        pedido.setIsRecebido(Boolean.FALSE);
        pedido.setPedidoProdutoCollection(listaPedidoProduto);
        pedido.setValorPedido(new java.lang.Float(0.0F));

        textFieldPrecoCusto.setText("jTextField1");
        textFieldPrecoCusto.setAlignmentX(0.0F);
        textFieldPrecoCusto.setAlignmentY(0.0F);
        textFieldPrecoCusto.setBorder(null);
        textFieldPrecoCusto.setMargin(new java.awt.Insets(0, 0, 0, 0));

        moneyCellRenderer.setText("moneyCellRenderer1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Ana Maria Cirurgica - Cadastrar Pedido");
        setBounds(new java.awt.Rectangle(300, 10, 0, 0));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        lblTitulo.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        lblTitulo.setText("Cadastro de Pedido");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(lblTitulo, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel2.setText("Data do Pedido:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 10, 0, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel3.setText("Produtos do pedido:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 0);
        jPanel1.add(jLabel3, gridBagConstraints);

        panelValorPedido.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelValorPedido.setMinimumSize(new java.awt.Dimension(400, 44));

        txtValorPedido.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtValorPedido.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtValorPedido.setEnabled(false);
        txtValorPedido.setPreferredSize(new java.awt.Dimension(6, 24));

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, pedido, org.jdesktop.beansbinding.ELProperty.create("${valorPedido}"), txtValorPedido, org.jdesktop.beansbinding.BeanProperty.create("text"), "valorTotalPedidoBinding");
        binding.setConverter(moneyFormattedConverter);
        bindingGroup.addBinding(binding);

        txtValorPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorPedidoActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel7.setText("Valor do Pedido:");

        ckRecebido.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        ckRecebido.setText("Recebido");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, pedido, org.jdesktop.beansbinding.ELProperty.create("${isRecebido}"), ckRecebido, org.jdesktop.beansbinding.BeanProperty.create("selected"));
        binding.setSourceNullValue(false);
        bindingGroup.addBinding(binding);

        ckPago.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        ckPago.setText("Pago");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, pedido, org.jdesktop.beansbinding.ELProperty.create("${isPago}"), ckPago, org.jdesktop.beansbinding.BeanProperty.create("selected"), "pagoBinding");
        binding.setSourceNullValue(false);
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout panelValorPedidoLayout = new javax.swing.GroupLayout(panelValorPedido);
        panelValorPedido.setLayout(panelValorPedidoLayout);
        panelValorPedidoLayout.setHorizontalGroup(
            panelValorPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelValorPedidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtValorPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addComponent(ckPago, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(ckRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );
        panelValorPedidoLayout.setVerticalGroup(
            panelValorPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelValorPedidoLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(panelValorPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValorPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(ckPago, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.ipady = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        jPanel1.add(panelValorPedido, gridBagConstraints);

        txtNotaFiscal.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, pedido, org.jdesktop.beansbinding.ELProperty.create("${cdNotaFiscal.numeroNotaFiscal}"), txtNotaFiscal, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        jPanel1.add(txtNotaFiscal, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel1.setText("N° Nota Fiscal:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 10, 0, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        tableProdutos.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tableProdutos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${pedidoProdutoCollection}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, pedido, eLProperty, tableProdutos);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${cdProduto.cdFarmaceutico}"));
        columnBinding.setColumnName("Código");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${cdProduto}"));
        columnBinding.setColumnName("Nome");
        columnBinding.setColumnClass(br.com.cirurgica.model.Produto.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${qtdeProduto}"));
        columnBinding.setColumnName("Quantidade");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${vlProduto}"));
        columnBinding.setColumnName("Preço de Custo");
        columnBinding.setColumnClass(Float.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${cdProduto.valorVendaAtacado}"));
        columnBinding.setColumnName("Atacado");
        columnBinding.setColumnClass(Float.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${cdProduto.valorVenda}"));
        columnBinding.setColumnName("Varejo");
        columnBinding.setColumnClass(Float.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(tableProdutos);
        tableProdutos.getColumnModel().getColumn(0).setResizable(false);
        tableProdutos.getColumnModel().getColumn(1).setResizable(false);
        tableProdutos.getColumnModel().getColumn(2).setCellEditor(integerCellEditor);
        tableProdutos.getColumnModel().getColumn(3).setCellEditor(moneyCellEditor);
        tableProdutos.getColumnModel().getColumn(3).setCellRenderer(moneyCellRenderer);
        tableProdutos.getColumnModel().getColumn(4).setCellEditor(moneyCellEditor);
        tableProdutos.getColumnModel().getColumn(4).setCellRenderer(moneyCellRenderer);
        tableProdutos.getColumnModel().getColumn(5).setCellEditor(moneyCellEditor);
        tableProdutos.getColumnModel().getColumn(5).setCellRenderer(moneyCellRenderer);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 500;
        gridBagConstraints.ipady = 144;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(7, 10, 0, 0);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel10.setText("Fornecedor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 10, 0, 0);
        jPanel1.add(jLabel10, gridBagConstraints);

        txtFornecedor.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtFornecedor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtFornecedor.setEnabled(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, pedido, org.jdesktop.beansbinding.ELProperty.create("${cdFornecedor.nmRazaoSocial}"), txtFornecedor, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        jPanel1.add(txtFornecedor, gridBagConstraints);

        btnRemover.setText("Remover selecionado");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 0, 0);
        jPanel1.add(btnRemover, gridBagConstraints);

        panelPesquisa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelPesquisa.setMinimumSize(new java.awt.Dimension(400, 44));
        panelPesquisa.setLayout(new java.awt.GridBagLayout());

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel11.setText("Produto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelPesquisa.add(jLabel11, gridBagConstraints);

        txtProduto.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, produtoPesquisa, org.jdesktop.beansbinding.ELProperty.create("${cdFarmaceutico}"), txtProduto, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelPesquisa.add(txtProduto, gridBagConstraints);

        btnProcurar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnProcurar.setText("Procurar");
        btnProcurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcurarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelPesquisa.add(btnProcurar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        jPanel1.add(panelPesquisa, gridBagConstraints);

        btnAdicionar.setText("Adicionar ao Pedido");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(btnAdicionar, gridBagConstraints);

        painelBotoes.setLayout(new java.awt.GridBagLayout());

        lblConfirmar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblConfirmar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblConfirmar.setText("SALVAR");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        painelBotoes.add(lblConfirmar, gridBagConstraints);

        lblCancelar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblCancelar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCancelar.setText("FECHAR");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 8;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        painelBotoes.add(lblCancelar, gridBagConstraints);

        btnCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bt_novo_cliente.png"))); // NOI18N
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 17;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        painelBotoes.add(btnCadastrar, gridBagConstraints);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bt_voltar.png"))); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 17;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        painelBotoes.add(btnCancelar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(painelBotoes, gridBagConstraints);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, pedido, org.jdesktop.beansbinding.ELProperty.create("${dataPedido}"), cbDataPedido, org.jdesktop.beansbinding.BeanProperty.create("date"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 140;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        jPanel1.add(cbDataPedido, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(40, 40, 40, 40);
        getContentPane().add(jPanel1, gridBagConstraints);

        bindingGroup.bind();

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-916)/2, (screenSize.height-578)/2, 916, 578);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        if (alterado == 0) {
            cadastrarPedido();
        } else {
            alterarPedido();
        }

    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        chamarGestaoPedido();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtValorPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorPedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorPedidoActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        adicionarAoCarrinho();
        setTxtValorPedido(Utilidades.retornarValorFormatado(this.pedido.calcularValorTotal()));
        reiniciarProduto();
        habilitarCadastro();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void reiniciarProduto() {
        txtProduto.requestFocus();
    }

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        if (tableProdutos.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione um Produto para poder remover.", "Seleção Necessária", 1);
        } else {
            removerDoCarrinho();
            setTxtValorPedido(Utilidades.retornarValorFormatado(this.pedido.calcularValorTotal()));
            txtProduto.requestFocus();
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    }//GEN-LAST:event_formWindowClosing

    public Pedido getPedido() {
        return this.pedido;
    }
    private void btnProcurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcurarActionPerformed
        produtoPesquisa = new PedidoController().findProduto(getPedido().getCdFornecedor());
        if (produtoPesquisa == null) {
            this.txtProduto.setText("");
        } else {
            this.txtProduto.setText(produtoPesquisa.getCdFarmaceutico());
        }
    }//GEN-LAST:event_btnProcurarActionPerformed

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
            java.util.logging.Logger.getLogger(CadastrarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastrarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastrarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastrarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastrarPedido((Pedido) null, null).setVisible(true);
            }
        });
    }

    public void removerDoCarrinho() {
        try {
            int resp =
                    JOptionPane.showConfirmDialog(this, "Deseja excluir o item selecionado?", "Excluindo item", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resp == JOptionPane.YES_OPTION) {
                ((List<PedidoProduto>) this.pedido.getPedidoProdutoCollection()).remove(pedidoProdutoSelecionado);
                JOptionPane.showMessageDialog(this, "Item do pedido excluído com sucesso!", "Exclusão de pedido", JOptionPane.INFORMATION_MESSAGE);
                txtProduto.requestFocus();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "O pedido item não foi excluído!", "Exclusão de pedido", JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(GestaoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void cancelarFechamento() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void fecharJanela() {
        this.dispose();
    }

    @Override
    public final void atribuirListeners(EventListener listener) {
        btnCadastrar.addKeyListener(new PressEnter());
        btnCancelar.addKeyListener(new PressEnter());

        txtValorPedido.addKeyListener(new PressEnter());
        txtNotaFiscal.addKeyListener(new PressEnter());
        ckRecebido.addKeyListener(new PressEnter());
        ckPago.addKeyListener(new PressEnter());
        cbDataPedido.addKeyListener(new PressEnter());
        txtFornecedor.addKeyListener(new PressEnter());
    }

    private void setTxtFornecedor(String nmRazaoSocial) {
        this.txtFornecedor.setText(nmRazaoSocial);
    }

    private void habilitarCadastro() {
    }

    private void alterarPedido() {
        PedidoDAO dao = new PedidoDAO();
        try {
            if (pedido.getCdNotaFiscal() != null){
                pedido.getCdNotaFiscal().setCdTipoNotaFiscal(new TipoNotaFiscal(1));
            }
            dao.alterar(pedido);
        } catch (Exception ex) {
            Logger.getLogger(CadastrarPedido.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        JOptionPane.showMessageDialog(this, "Pedido alterado com sucesso!");
        System.out.println(this.pedido.toString());
    }

    class PressEnter implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {
            // TODO Auto-generated method stub

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (btnCadastrar.isFocusOwner()) {
                    cadastrarPedido();
                } else if (btnCancelar.isFocusOwner()) {
                    chamarGestaoPedido();
                } else if (ckRecebido.isFocusOwner()) {
                    if (ckRecebido.isSelected()) {
                        ckRecebido.setSelected(false);
                    } else {
                        ckRecebido.setSelected(true);
                    }
                } else if (ckPago.isFocusOwner()) {
                    if (ckPago.isSelected() == true) {
                        ckPago.setSelected(false);
                    } else {
                        ckPago.setSelected(true);
                    }
                }
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                chamarGestaoPedido();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }
    }

    private void chamarGestaoPedido() {
        new GestaoPedido().setVisible(true);
        this.dispose();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnProcurar;
    private javax.swing.JButton btnRemover;
    private com.toedter.calendar.JDateChooser cbDataPedido;
    private javax.swing.JCheckBox ckPago;
    private javax.swing.JCheckBox ckRecebido;
    private br.com.cirurgica.view.utils.DateConverter dateConverter;
    private br.com.cirurgica.view.utils.cell.IntegerCellEditor integerCellEditor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCancelar;
    private javax.swing.JLabel lblConfirmar;
    private javax.swing.JLabel lblTitulo;
    private java.util.List<br.com.cirurgica.model.PedidoProduto> listaPedidoProduto;
    private br.com.cirurgica.view.utils.cell.MoneyCellEditor moneyCellEditor;
    private br.com.cirurgica.view.utils.cell.MoneyCellRenderer moneyCellRenderer;
    private br.com.cirurgica.view.utils.MoneyConverter moneyConverter;
    private br.com.cirurgica.view.utils.MoneyFormattedConverter moneyFormattedConverter;
    private javax.swing.JPanel painelBotoes;
    private javax.swing.JPanel panelPesquisa;
    private javax.swing.JPanel panelValorPedido;
    private br.com.cirurgica.model.Pedido pedido;
    private br.com.cirurgica.model.PedidoProduto pedidoProdutoSelecionado;
    private br.com.cirurgica.model.Produto produtoPesquisa;
    private javax.swing.JTable tableProdutos;
    private javax.swing.JTextField textFieldPrecoCusto;
    private javax.swing.JTextField txtFornecedor;
    private javax.swing.JTextField txtNotaFiscal;
    private javax.swing.JTextField txtProduto;
    private javax.swing.JTextField txtValorPedido;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
