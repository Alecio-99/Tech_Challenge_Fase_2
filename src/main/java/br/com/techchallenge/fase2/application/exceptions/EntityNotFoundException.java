package br.com.techchallenge.fase2.application.exceptions;

public class EntityNotFoundException extends ApplicationException {

    public EntityNotFoundException(String mensagem) {
        super(mensagem);
    }
}

