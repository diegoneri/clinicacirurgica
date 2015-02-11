package br.com.cirurgica.view.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.jdesktop.beansbinding.Converter;

/**
 * <code>Integer</code> to <code>String</code> converter
 * that returns zero when the argument cannot be parsed.
 * 
 * @author Jiri Vagner, Jan Stola
 */
public class DateConverter extends Converter<Date, String> {

    private DateFormat formatter;
    public DateConverter(){
        this.formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", new Locale("pt","BR"));
    }
    
    @Override
    public String convertForward(Date arg) {
        return this.formatter.format(arg);
    }

    @Override
    public Date convertReverse(String arg) {
        Date value;
        try {
            value = formatter.parse(arg);
        } catch (ParseException ex) {
            value = new Date();
        }
        return value;
    }

}
