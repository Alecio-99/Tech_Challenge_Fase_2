package br.com.techchallenge.fase2.interfaces.dtos.usuario;

public record UsuarioPutRequestDTO(
        String nome,
        Long tipoUsuarioId
) {}
