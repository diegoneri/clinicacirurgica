
package br.com.cirurgica.view.utils;

import org.jdesktop.beansbinding.Validator;

/**
 * 
 * @author Diego
 */
public class ValorNegativoValidator extends Validator<Integer> {

    @Override
    public Validator.Result validate(Integer arg) { 
        if (arg < 0){
            return new Result(null, "O número \'" + arg + "\' não pode ser negativo!");
        }
        return null;
    }
}
