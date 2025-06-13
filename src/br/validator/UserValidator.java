package br.validator;

import br.excepetion.ValidatorException;
import br.model.UserModel;

public class UserValidator {
    private UserValidator(){}
    //exception verify custmater 
    public static void verifyModel(final UserModel model) throws ValidatorException{
        if(stringIsBlank(model.getName())) 
        throw new ValidatorException("enter a valid name");
        if (model.getName().length() <=1 )
        throw new ValidatorException("the name must have at least 2 characters");
        if(stringIsBlank(model.getEmail())) 
        throw new ValidatorException("enter a valid email");
        if(!model.getEmail().contains("@") || (!model.getEmail().contains(".")))
        throw new ValidatorException("Enter a valid email");
    }

    private static boolean stringIsBlank(final String value){
        return value == null || value.isBlank();
    }   

}
