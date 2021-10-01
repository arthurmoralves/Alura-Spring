package br.com.alura.crud.config.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<FormError> handle(MethodArgumentNotValidException e){
        List<FormError> formErrors = new ArrayList<>();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        fieldErrors.forEach(erro -> {
            String mensagem = messageSource.getMessage(erro, LocaleContextHolder.getLocale());
            FormError formError = new FormError(erro.getField(), mensagem);
            formErrors.add(formError);
        });

        return formErrors;
    }
}
