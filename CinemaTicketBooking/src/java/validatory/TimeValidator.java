/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validatory;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author mateusz
 */
@FacesValidator("timeValidator.pl")
public class TimeValidator implements Validator
{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {
        Integer time = (Integer)value;
        if(time <= 0)
        {
            FacesMessage message = new FacesMessage("Zła wartość!");
            throw new ValidatorException(message);
        }
    }
    
}
