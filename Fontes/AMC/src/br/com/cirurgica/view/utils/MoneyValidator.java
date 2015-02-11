
package br.com.cirurgica.view.utils;

import org.jdesktop.beansbinding.Validator;

/**
 * Validator that ensures that given value is between 1 and 199.
 * 
 * @author Diego
 */
public class MoneyValidator extends Validator<Float> {

    @Override
    public Validator.Result validate(Float arg) { 
        if (arg < 0){
            return new Result(null, "O número \'" + arg + "\' não é válido!");
        }
        return null;
    }
}
