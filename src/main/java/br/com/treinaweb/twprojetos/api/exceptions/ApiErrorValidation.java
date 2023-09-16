package br.com.treinaweb.twprojetos.api.exceptions;

import java.time.LocalDateTime;
import java.util.List;

public class ApiErrorValidation extends ApiError {
    
    private List<FieldError> erros;

    public ApiErrorValidation() {

    }

    public ApiErrorValidation(int erro, String status, LocalDateTime timestamp, String mensagem,
            List<FieldError> erros) {
        super(erro, status, timestamp, mensagem);
        this.erros = erros;
    }

    public List<FieldError> getErros() {
        return erros;
    }

    public void setErros(List<FieldError> erros) {
        this.erros = erros;
    }    

}
