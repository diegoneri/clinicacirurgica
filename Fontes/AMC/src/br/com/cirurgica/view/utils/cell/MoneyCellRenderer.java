/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cirurgica.view.utils.cell;

import java.awt.Component;  
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JTable;  
import javax.swing.table.DefaultTableCellRenderer;  
import javax.swing.table.TableCellRenderer;  
  
public class MoneyCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer {  
  
    private NumberFormat formatter;  
    
  
    public MoneyCellRenderer() {  
        super();  
        this.formatter = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
    }  
  
    @Override  
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected,  
            final boolean hasFocus, final int row, final int column) {  
  
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);  
        if ((value != null) && (!value.equals(""))) {  
            setText(this.formatter.format(value));  
        } else {  
            setText("");  
        }  
        return c;  
    }  
}