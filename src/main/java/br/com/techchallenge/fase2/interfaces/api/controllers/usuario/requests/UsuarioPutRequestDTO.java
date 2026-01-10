package br.com.techchallenge.fase2.interfaces.api.controllers.usuario.requests;

public record UsuarioPutRequestDTO(
        String nome,
        Long tipoUsuarioId
) {}
