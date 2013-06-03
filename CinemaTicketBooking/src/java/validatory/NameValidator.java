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
@FacesValidator("nameValidator.pl")
public class NameValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {
        String name = value.toString();
        FacesMessage message = new FacesMessage();
        boolean passed = true;
        if(name.length() < 2)
        {
            message.setDetail("Za krótka nazwa");
            passed = false;
        }
        if(name.length() > 50)
        {
            message.setDetail("Zbyt długa nazwa");
            passed = false;
        }
        if(!passed)
            throw new ValidatorException(message);
    }
    
}
