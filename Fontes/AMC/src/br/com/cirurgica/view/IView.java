/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.view;

import java.io.Serializable;
import java.util.EventListener;

/**
 *
 * @author dfelix3
 * 
 * Interface de marcação para as views do projeto
 */
public interface IView extends Serializable{
    public void cancelarFechamento();
    public void fecharJanela();
    public void atribuirListeners(EventListener listener);
}
