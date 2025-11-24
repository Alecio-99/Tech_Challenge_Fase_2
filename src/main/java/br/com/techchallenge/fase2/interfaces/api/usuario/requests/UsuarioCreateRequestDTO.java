package br.com.techchallenge.fase2.interfaces.api.usuario.requests;

public record UsuarioCreateRequestDTO(
        String nome,
        Long tipoUsuarioId
) {}
