/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.view.utils.cell;

import br.com.cirurgica.model.Pedido;
import com.utilidades.Utilidades;
import java.awt.Color;
import java.awt.Component;
import java.util.EventObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

public class IntegerCellEditor extends DefaultCellEditor implements TableCellEditor {

    private JTextField totalField;
    private Pedido pedido;

    private Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public IntegerCellEditor() {
        super(new JTextField());
    }

    public void setTotalField(JTextField field) {
        this.totalField = field;
    }

    private JTextField getTotalField() {
        return this.totalField;
    }

    private JTextField getField() {
        return (JTextField) this.getComponent();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        System.out.println("getTableCellEditorComponent. Field: " + getField().getText() + ", value: " + value);
        getField().setBorder(new LineBorder(Color.black));
        if ((value != null) && (!value.equals(""))) {
            getField().setText(String.valueOf(value));
        } else {
            getField().setText("0");
        }
        return getField();
    }

    @Override
    public Object getCellEditorValue() {
        System.out.println("getCellEditorValue " + getField().getText());
        try {
            return Integer.parseInt(getField().getText());
        } catch (NumberFormatException ex) {
            Logger.getLogger(IntegerCellEditor.class.getName()).log(Level.SEVERE, null, ex);
            return getField().getText();
        }
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        System.out.println("isCellEditable " + anEvent.getSource());
        return super.isCellEditable(anEvent);
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        System.out.println("shouldSelectCell " + anEvent.getSource());
        return super.shouldSelectCell(anEvent);
    }

    @Override
    public boolean stopCellEditing() {
        System.out.println("stopCellEditing");
        String s = (String) super.getCellEditorValue();
        // Here we are dealing with the case where a user
        // has deleted the string value in a cell, possibly
        // after a failed validation. Return null, so that
        // they have the option to replace the value with
        // null or use escape to restore the original.
        // For Strings, return "" for backward compatibility.

        try {
            Integer.parseInt(s);
        } catch (Exception e) {
            getField().setBorder(new LineBorder(Color.red));
            return false;
        }
        return super.stopCellEditing();
    }

    public void totalizarValoresTabela() {
        if (getTotalField() != null) {
            getTotalField().setText(Utilidades.retornarValorFormatado(this.pedido.calcularValorTotal()));
        }
    }

    @Override
    public void cancelCellEditing() {
        System.out.println("cancelCellEditing");
        super.cancelCellEditing();
    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
        System.out.println("addCellEditorListener");
        super.addCellEditorListener(l);
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
        System.out.println("removeCellEditorListener");
        totalizarValoresTabela();
        super.removeCellEditorListener(l);
    }
}