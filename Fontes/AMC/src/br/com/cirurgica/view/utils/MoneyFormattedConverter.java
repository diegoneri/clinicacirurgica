package br.com.cirurgica.view.utils;

import com.utilidades.Utilidades;
import java.text.ParseException;
import org.jdesktop.beansbinding.Converter;

/**
 * <code>Integer</code> to
 * <code>String</code> converter that returns zero when the argument cannot be
 * parsed.
 *
 * @author Jiri Vagner, Jan Stola
 */
public class MoneyFormattedConverter extends Converter<Float, String> {

    public MoneyFormattedConverter() {
    }

    @Override
    public String convertForward(Float arg) {
        return Utilidades.retornarValorFormatado(arg);
    }

    @Override
    public Float convertReverse(String arg) {
        float value;
        try {
            value = (arg == null) ? 0 : Utilidades.getFloatByNumeric(arg);
        } catch (ParseException ex) {
            try {
                value = (arg == null) ? 0 : Utilidades.getFloatByCurrency(arg);
            } catch (ParseException ex1) {
                value = 0;
            }
        }
        return value;
    }
}
