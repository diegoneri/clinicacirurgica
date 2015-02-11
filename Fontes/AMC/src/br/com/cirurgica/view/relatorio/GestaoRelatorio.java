/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.view.relatorio;

import br.com.cirurgica.controller.relatorio.RelatorioContasPagarController;
import br.com.cirurgica.controller.relatorio.RelatorioContasPagasController;
import br.com.cirurgica.controller.relatorio.RelatorioListaPrecoController;
import br.com.cirurgica.controller.relatorio.RelatorioSinteticoContas;
import br.com.cirurgica.view.PrincipalView;
import com.utilidades.Utilidades;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author 13b Pessoal
 */
public class GestaoRelatorio extends javax.swing.JFrame {

    /**
     * Creates new form GestaoRelatorio
     */
    public GestaoRelatorio() {
        initComponents();


        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);




        btnRelVendas.addKeyListener(new PressEnter());
        btnRelContasPagar.addKeyListener(new PressEnter());
        btnVoltar.addKeyListener(new PressEnter());


        setIconImage(new ImageIcon(getClass().getResource("/resources/ico_gestao.png")).getImage());
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

        panelComponentes = new javax.swing.JPanel();
        btnRelVendas = new javax.swing.JButton();
        lblFatura = new javax.swing.JLabel();
        btnRelContasPagar = new javax.swing.JButton();
        lblDespesa = new javax.swing.JLabel();
        btnVoltar = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        fillerVerticalEsquerda = new javax.swing.Box.Filler(new java.awt.Dimension(50, 10), new java.awt.Dimension(50, 10), new java.awt.Dimension(50, 10));
        btnRelVendasProduto = new javax.swing.JButton();
        btnContasPagas = new javax.swing.JButton();
        btnRelEstoque = new javax.swing.JButton();
        btnRelListaPrecos = new javax.swing.JButton();
        lblContasPagar = new javax.swing.JLabel();
        lblContasPagas = new javax.swing.JLabel();
        lblEstoque = new javax.swing.JLabel();
        lblListaPrecos = new javax.swing.JLabel();
        fillerVerticalDireita = new javax.swing.Box.Filler(new java.awt.Dimension(50, 10), new java.awt.Dimension(50, 10), new java.awt.Dimension(50, 10));
        fillerHorizontalSuperior = new javax.swing.Box.Filler(new java.awt.Dimension(10, 50), new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 50));
        fillerHorizontalInferior = new javax.swing.Box.Filler(new java.awt.Dimension(10, 50), new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 50));
        btnRelSintetico = new javax.swing.JButton();
        lblContasPagas1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ana Maria Cirurgica - Relatórios");
        setBounds(new java.awt.Rectangle(300, 150, 0, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        panelComponentes.setLayout(new java.awt.GridBagLayout());

        btnRelVendas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRelVendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bt_caixa.png"))); // NOI18N
        btnRelVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelVendasActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelComponentes.add(btnRelVendas, gridBagConstraints);

        lblFatura.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblFatura.setText("Vendas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panelComponentes.add(lblFatura, gridBagConstraints);

        btnRelContasPagar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRelContasPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bt_nota_fiscal.png"))); // NOI18N
        btnRelContasPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelContasPagarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelComponentes.add(btnRelContasPagar, gridBagConstraints);

        lblDespesa.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblDespesa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDespesa.setText("Vendas por Produto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panelComponentes.add(lblDespesa, gridBagConstraints);

        btnVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bt_voltar_pequeno.png"))); // NOI18N
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.insets = new java.awt.Insets(30, 30, 30, 30);
        panelComponentes.add(btnVoltar, gridBagConstraints);

        lblTitulo.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        lblTitulo.setText("Emissão de Relatórios");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.insets = new java.awt.Insets(30, 30, 30, 30);
        panelComponentes.add(lblTitulo, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        panelComponentes.add(fillerVerticalEsquerda, gridBagConstraints);

        btnRelVendasProduto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRelVendasProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bt_compras.png"))); // NOI18N
        btnRelVendasProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelVendasProdutoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelComponentes.add(btnRelVendasProduto, gridBagConstraints);

        btnContasPagas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnContasPagas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bt_nota_fiscal.png"))); // NOI18N
        btnContasPagas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContasPagasActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelComponentes.add(btnContasPagas, gridBagConstraints);

        btnRelEstoque.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRelEstoque.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bt_despesa.png"))); // NOI18N
        btnRelEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelEstoqueActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelComponentes.add(btnRelEstoque, gridBagConstraints);

        btnRelListaPrecos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRelListaPrecos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bt_pedido.png"))); // NOI18N
        btnRelListaPrecos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelListaPrecosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelComponentes.add(btnRelListaPrecos, gridBagConstraints);

        lblContasPagar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblContasPagar.setText("Contas a Pagar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panelComponentes.add(lblContasPagar, gridBagConstraints);

        lblContasPagas.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblContasPagas.setText("Contas Pagas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panelComponentes.add(lblContasPagas, gridBagConstraints);

        lblEstoque.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblEstoque.setText("Estoque");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panelComponentes.add(lblEstoque, gridBagConstraints);

        lblListaPrecos.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblListaPrecos.setText("Lista de Preços");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panelComponentes.add(lblListaPrecos, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 10;
        panelComponentes.add(fillerVerticalDireita, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        panelComponentes.add(fillerHorizontalSuperior, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 4;
        panelComponentes.add(fillerHorizontalInferior, gridBagConstraints);

        btnRelSintetico.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRelSintetico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bt_nota_fiscal.png"))); // NOI18N
        btnRelSintetico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelSinteticoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        panelComponentes.add(btnRelSintetico, gridBagConstraints);

        lblContasPagas1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblContasPagas1.setText("Sintético de Despesas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        panelComponentes.add(lblContasPagas1, gridBagConstraints);

        getContentPane().add(panelComponentes);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-946)/2, (screenSize.height-651)/2, 946, 651);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRelVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelVendasActionPerformed
        try {
            chamarRelatorioFatura();
        } catch (JRException ex) {
            Logger.getLogger(GestaoRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GestaoRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRelVendasActionPerformed

    private void btnRelContasPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelContasPagarActionPerformed
        RelatorioContasPagarController rel = new RelatorioContasPagarController();
        rel.imprimir();
    }//GEN-LAST:event_btnRelContasPagarActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        voltar();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btnRelVendasProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelVendasProdutoActionPerformed
        EmitirRelatorioVendasProduto rel = new EmitirRelatorioVendasProduto();
        rel.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRelVendasProdutoActionPerformed

    private void btnContasPagasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContasPagasActionPerformed
        RelatorioContasPagasController rel = new RelatorioContasPagasController();
        rel.imprimir();
    }//GEN-LAST:event_btnContasPagasActionPerformed

    private void btnRelEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelEstoqueActionPerformed
        EmitirRelatorioEstoque rel = new EmitirRelatorioEstoque();
        rel.setVisible(true);
        this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_btnRelEstoqueActionPerformed

    private void btnRelListaPrecosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelListaPrecosActionPerformed
        RelatorioListaPrecoController rel = new RelatorioListaPrecoController();
        rel.imprimir();// TODO add your handling code here:
    }//GEN-LAST:event_btnRelListaPrecosActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        voltar();        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void btnRelSinteticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelSinteticoActionPerformed
        RelatorioSinteticoContas rel = new RelatorioSinteticoContas();
        rel.imprimir();
    }//GEN-LAST:event_btnRelSinteticoActionPerformed

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
            java.util.logging.Logger.getLogger(GestaoRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestaoRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestaoRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestaoRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestaoRelatorio().setVisible(true);
            }
        });
    }

    public void voltar() {
        
        PrincipalView p = new PrincipalView();
        p.setVisible(true);
        p.setUsuarioLogado(Utilidades.retornaNomeLogado());
        this.dispose();

    }

    public void chamarRelatorioDespesa() {
        this.setVisible(false);
        EmitirRelatorioEstoque erd = new EmitirRelatorioEstoque();
        erd.setVisible(true);

    }

    public void chamarRelatorioFatura() throws JRException, SQLException {

        this.setVisible(false);

        EmitirRelatorioVendas erf = new EmitirRelatorioVendas();
        erf.setVisible(true);
    }

    class PressEnter implements KeyListener {

        public void keyPressed(KeyEvent e) {
            // TODO Auto-generated method stub  

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (btnRelVendas.isFocusOwner()) {
                    try {
                        chamarRelatorioFatura();
                    } catch (JRException ex) {
                        Logger.getLogger(GestaoRelatorio.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestaoRelatorio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (btnVoltar.isFocusOwner()) {
                    voltar();
                } else if (btnRelContasPagar.isFocusOwner()) {

                    chamarRelatorioDespesa();

                }
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                voltar();
            }
        }

        public void keyReleased(KeyEvent e) {
        }

        public void keyTyped(KeyEvent e) {
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContasPagas;
    private javax.swing.JButton btnRelContasPagar;
    private javax.swing.JButton btnRelEstoque;
    private javax.swing.JButton btnRelListaPrecos;
    private javax.swing.JButton btnRelSintetico;
    private javax.swing.JButton btnRelVendas;
    private javax.swing.JButton btnRelVendasProduto;
    private javax.swing.JButton btnVoltar;
    private javax.swing.Box.Filler fillerHorizontalInferior;
    private javax.swing.Box.Filler fillerHorizontalSuperior;
    private javax.swing.Box.Filler fillerVerticalDireita;
    private javax.swing.Box.Filler fillerVerticalEsquerda;
    private javax.swing.JLabel lblContasPagar;
    private javax.swing.JLabel lblContasPagas;
    private javax.swing.JLabel lblContasPagas1;
    private javax.swing.JLabel lblDespesa;
    private javax.swing.JLabel lblEstoque;
    private javax.swing.JLabel lblFatura;
    private javax.swing.JLabel lblListaPrecos;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelComponentes;
    // End of variables declaration//GEN-END:variables
}
