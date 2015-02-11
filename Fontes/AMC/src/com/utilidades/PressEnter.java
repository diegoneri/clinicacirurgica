/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utilidades;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author 13b Pessoal
 */
public class PressEnter implements KeyListener {

    String classe;

    public PressEnter(String classe) {

        this.classe = classe;

    }
    public PressEnter(){
    
    
    }

    @Override
    public void keyPressed(KeyEvent e) {


        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//            realizarLogin();
        }
    }

    public void keyPressed(KeyEvent e, String j) {
        System.out.println("Cassilds!");
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            //realizarLogin();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // JOptionPane.showMessageDialog(null, "2: " + KeyEvent.getKeyText(e.getKeyCode()));
        // TODO Auto-generated method stub  
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub  
        // JOptionPane.showMessageDialog(null, "3: " + KeyEvent.getKeyText(e.getKeyCode()));
    }
}
