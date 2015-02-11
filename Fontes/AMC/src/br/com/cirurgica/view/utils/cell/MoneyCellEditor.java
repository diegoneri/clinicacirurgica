/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.view.utils.cell;

import br.com.cirurgica.model.Pedido;
import com.utilidades.Utilidades;
import java.awt.Color;
import java.awt.Component;
import java.text.ParseException;
import java.util.EventObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

public class MoneyCellEditor extends DefaultCellEditor implements TableCellEditor {
    private JTextField totalField;
    private Pedido pedido;

    private Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public MoneyCellEditor() {
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
            getField().setText(Utilidades.retornarValorFormatado((Float) value));
        } else {
            getField().setText("0,00");
        }
        return getField();
    }

    @Override
    public Object getCellEditorValue() {
        System.out.println("getCellEditorValue " + getField().getText());
        try {
            return Utilidades.getFloatByNumeric(getField().getText());
        } catch (ParseException ex) {
            try {
                return Utilidades.getFloatByCurrency(getField().getText());
            } catch (ParseException ex2) {
                Logger.getLogger(MoneyCellEditor.class.getName()).log(Level.SEVERE, null, ex2);
                return getField().getText();
            }
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

        try {
            Utilidades.getFloatByNumeric(s);
        } catch (Exception e) {
            try {
                Utilidades.getFloatByCurrency(s);
            } catch (Exception x) {
                getField().setBorder(new LineBorder(Color.red));
                return false;
            }
        }
        return super.stopCellEditing();
    }

    public void totalizarValoresTabela() {
        if (getTotalField() != null) {
            getTotalField().setText(Utilidades.retornarValorFormatado(this.getPedido().calcularValorTotal()));
        }
    }

    @Override
    public void cancelCellEditing() {
        super.cancelCellEditing();
    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
        super.addCellEditorListener(l);
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
        totalizarValoresTabela();
        super.removeCellEditorListener(l);
    }
}