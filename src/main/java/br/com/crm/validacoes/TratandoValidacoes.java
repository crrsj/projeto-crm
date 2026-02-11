package br.com.crm.validacoes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TratandoValidacoes {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?>tratandoValidacoes(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(ValidarCampos::new).toList());
    }
}
