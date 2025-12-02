package br.com.techchallenge.fase2.application.exceptions;

public class EntityAlreadyExistsException extends ApplicationException {

    public EntityAlreadyExistsException(String mensagem) {
        super(mensagem);
    }
}

