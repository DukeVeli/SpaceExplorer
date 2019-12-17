package space.space.errors;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Registration form invalid")
public class RegistrationFormError extends RuntimeException{
    public RegistrationFormError(String message){
        super((message));

    }
}
