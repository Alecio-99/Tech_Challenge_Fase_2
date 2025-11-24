package br.com.techchallenge.fase2.interfaces.api.usuario.requests;

public record UsuarioPutRequestDTO(
        String nome,
        Long tipoUsuarioId
) {}
