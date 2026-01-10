package br.com.techchallenge.fase2.interfaces.api.controllers.usuario.requests;

public record UsuarioCreateRequestDTO(
        String nome,
        Long tipoUsuarioId
) {}
