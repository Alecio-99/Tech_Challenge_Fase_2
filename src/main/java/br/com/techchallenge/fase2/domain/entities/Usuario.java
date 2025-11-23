package br.com.techchallenge.fase2.domain.entities;

import br.com.techchallenge.fase2.domain.exceptions.DomainException;

public class Usuario {

    private final Long id;
    private final String nome;
    private final TipoUsuario tipoUsuario;

    public Usuario(Long id, String nome, TipoUsuario tipoUsuario) {
        validar(nome, tipoUsuario);
        this.id = id;
        this.nome = nome.trim();
        this.tipoUsuario = tipoUsuario;
    }

    private void validar(String nome, TipoUsuario tipoUsuario) {
        if (nome == null || nome.trim().isEmpty())
            throw new DomainException("Nome do usuário é obrigatório.");

        if (tipoUsuario == null)
            throw new DomainException("Usuário deve possuir um tipo de usuário.");
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
}
