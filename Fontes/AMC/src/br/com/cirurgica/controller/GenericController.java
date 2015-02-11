/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.controller;

import br.com.cirurgica.view.IView;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;

/**
 *
 * @author dfelix3
 */
//TODO Finalizar GenericController
public abstract class GenericController<M> implements ActionListener, KeyListener, WindowListener {
    protected static final String DESEJA_FECHAR_A_TELA_ATUAL = "Deseja fechar a tela atual?";
    protected static final String SAÍDA_DE_TELA = "Saída de tela";

    /**
     * Retorna a tela de Gestão manipulada pelo Controller em questão
     * @return view
     */
    public abstract IView getView();

    /**
     * Retorna a tela de busca manipulada pelo Controller em questão, quando
     * a mesma for implementada. Implementação de View deve ser um JDialog
     * @return view
     */
    public abstract IView getFindView();

    /**
     * Retorna a tela de cadastro manipulada pelo Controller em questão, quando
     * a mesma for implementada.
     * @return view
     */
    public abstract IView getRegisterView();

    /**
     * Inicializa as operações do controller em questão
     * @return controller
     */
    protected abstract GenericController init();

    /**
     * Inicializa a operação de cadastro / alteração de um modelo
     * @param model <b>NULL<b/> --> cadastro, senão é alteração
     */
    public abstract void register(M model);
    /**
     * Este método deverá ter em sua implementação a chamada a um JDialog de
     * pesquisa onde a thread irá aguardar a thread gerada pela janela até que
     * a mesma seja finalizada.
     * @return resultado da pesquisa
     */
    public abstract M find();

    @Override
    public void actionPerformed(ActionEvent e) {
        evaluateAction(e.getActionCommand());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("keyTyped " + e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                evaluateAction(e.getComponent().getName());
                break;
            case KeyEvent.VK_ESCAPE:
                evaluateEscape(e.getComponent().getName());
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("keyReleased " + e);
    }
    /**
     * Método abstrato que deve ser sobrescrito pela implementação.
     * Deve implementar as ações dos botões da(s) tela(s) manipuladas pelo
     * controller de acordo com as ações.
     * @param action: valor de acordo com o listener que gera a ação:
     * - ActionPerformed: actionCommand
     * - KeyPressed: component.name
     */
    protected abstract void evaluateAction(String action);

    /**
     * Método abstrato que deve ser sobrescrito pela implementação.
     * Deve implementar as ações da tecla escape (VK_ESCAPE) da(s) tela(s)
     * manipuladas pelo controller.
     * @param action: valor de acordo com o listener que gera a ação:
     * - KeyPressed: component.name
     */
    protected abstract void evaluateEscape(String action);

    /**
     * Cria um evento de WindowClosing com a tela de cadastro
     * Passando o evento ao método que fecha a janela, simula a ação de um
     * componente idêntica ao fechamento da tela.
     * @para view Tela para reinício / fechamento
     */
    protected void closeWindowSimulation(IView view) {
        WindowEvent evt = new WindowEvent((Window)view, WindowEvent.WINDOW_CLOSING);
        this.windowClosing(evt);
    }

    @Override
    public void windowOpened(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Fechando tela: " + e.getSource().toString());
        fecharJanela(e);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void windowIconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void windowActivated(WindowEvent e) {
        this.ativarJanela(e);
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        this.desativarJanela(e);
    }

    public abstract void ativarJanela(WindowEvent e);
    public abstract void desativarJanela(WindowEvent e);

    /**
     *
     *
     */
    protected void fecharJanela(WindowEvent e){
        int resp =
                JOptionPane.showConfirmDialog((Component) getView(), DESEJA_FECHAR_A_TELA_ATUAL, SAÍDA_DE_TELA, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (resp == JOptionPane.CANCEL_OPTION) {
            getView().cancelarFechamento();
        } else {
            getView().fecharJanela();
        }
    }
}