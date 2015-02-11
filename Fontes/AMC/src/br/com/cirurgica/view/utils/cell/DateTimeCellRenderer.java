/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.view.utils.cell;

import com.utilidades.Utilidades;
import java.awt.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class DateTimeCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer {

    private DateFormat formatter;

    public DateTimeCellRenderer() {
        super();
        this.formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Utilidades.localBrasil);
    }

    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected,
            final boolean hasFocus, final int row, final int column) {

        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value !=null && value instanceof Date) {
            setText(this.formatter.format(value));
        } else {
            setText("");
        }
        return c;
    }
}