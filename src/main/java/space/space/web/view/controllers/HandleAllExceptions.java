package space.space.web.view.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import space.space.errors.NemashKintiBraatException;
import space.space.errors.RegistrationFormError;

@ControllerAdvice
public class HandleAllExceptions {

    @ExceptionHandler({NemashKintiBraatException.class, RegistrationFormError.class})
    public ModelAndView handleException(Throwable exception) {
        ModelAndView modelAndView = new ModelAndView("error1");
        modelAndView.addObject("message", exception.getMessage());

        return modelAndView;
    }

}
