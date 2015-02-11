package br.com.cirurgica.view.utils;

import com.utilidades.Utilidades;
import java.text.NumberFormat;
import java.text.ParseException;
import org.jdesktop.beansbinding.Converter;

/**
 * <code>Float</code> to <code>String</code> converter
 * that returns zero when the argument cannot be parsed.
 * 
 * @author Diego
 */
public class MoneyConverter extends Converter<Float, String> {

    private NumberFormat formatter;
    public MoneyConverter(){
        formatter = NumberFormat.getInstance(Utilidades.localBrasil);
    }
    @Override
    public String convertForward(Float arg) {
        return formatter.format(arg);
        //return String.valueOf(arg);
    }

    @Override
    public Float convertReverse(String arg) {
        float value;
        try {
            value = (arg == null) ? 0 : formatter.parse(arg).floatValue();
        } catch (ParseException ex) {
            value = 0;
        }
        return value;
    }

}
