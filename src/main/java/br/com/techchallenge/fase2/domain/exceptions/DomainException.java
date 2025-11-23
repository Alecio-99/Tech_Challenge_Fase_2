package br.com.techchallenge.fase2.domain.exceptions;

public class DomainException extends RuntimeException {

    public DomainException(String mensagem) {
        super(mensagem);
    }

}
