package com.fisioEspacio.errorhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@RestController
public class ErrorHandler {
    
	@ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleAllExceptions(Exception exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        System.out.println(exception);
        return modelAndView;
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleResponseStatusException(ResponseStatusException exception) {
    	System.out.println(exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("errorMessage", exception.getReason());
        System.out.println(exception);
        return modelAndView;
    }
    
}
