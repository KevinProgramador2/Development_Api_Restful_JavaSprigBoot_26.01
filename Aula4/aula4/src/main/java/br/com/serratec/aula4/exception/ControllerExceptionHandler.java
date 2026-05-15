package br.com.serratec.aula4.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//classe do spring que vai tratar os erros
@RestControllerAdvice // Quando eu anoto uma classe com essa anotacao ela vai ser resposavel por
                      // capturar todos os erros e manipulo por aqui
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> erros = new ArrayList<>();

        for (FieldError erro : ex.getBindingResult().getFieldErrors()) {
            erros.add(erro.getField() + ":" + erro.getDefaultMessage());
        }

        ErroResposta erroResposta = new ErroResposta(status.value(), "Existem erros na requisição!",
                LocalDateTime.now(), erros);
        return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
    }

    // ======TRATAMENTO DE JSON COM NOME ERRADO============
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ErroResposta erroResposta = new ErroResposta(status.value(), "Existem erros na requisição!" + ex.getMessage(),
                LocalDateTime.now(), null);

        return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
    }

}