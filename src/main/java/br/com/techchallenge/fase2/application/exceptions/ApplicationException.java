package br.com.techchallenge.fase2.application.exceptions;

public class ApplicationException extends RuntimeException {

    public ApplicationException(String mensagem) {
        super(mensagem);
    }

    public ApplicationException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}



