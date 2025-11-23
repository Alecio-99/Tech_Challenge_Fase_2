package br.com.techchallenge.fase2.domain.entities;

import br.com.techchallenge.fase2.domain.exceptions.DomainException;

public class TipoUsuario {

    private final Long id;
    private final String nomeTipo;

    public TipoUsuario(Long id, String nomeTipo) {
        validar(nomeTipo);
        this.id = id;
        this.nomeTipo = nomeTipo.trim();
    }

    private void validar(String nomeTipo) {
        if (nomeTipo == null || nomeTipo.trim().isEmpty()) {
            throw new DomainException("Nome do tipo de usuário é obrigatório.");
        }
    }

    public Long getId() {
        return id;
    }

    public String getNomeTipo() {
        return nomeTipo;
    }
}
