package br.com.treinaweb.twprojetos.api.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionManager extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleEntityNotFoundException(EntityNotFoundException exception) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ApiError apiError = new ApiError(
            httpStatus.value(), 
            httpStatus.getReasonPhrase(), 
            LocalDateTime.now(), 
            exception.getLocalizedMessage()
        );

        return new ResponseEntity<>(apiError, httpStatus);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        List<FieldError> erros = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            FieldError campoErro = new FieldError(
                fieldError.getField(),
                fieldError.getDefaultMessage()
            );

            erros.add(campoErro);
        });

        ApiErrorValidation validacaoApiErro = new ApiErrorValidation(
            status.value(),
            status.getReasonPhrase(),
            LocalDateTime.now(),
            "Houveram erros de validação",
            erros
        );

        return new ResponseEntity<>(validacaoApiErro, status);
	}

}
