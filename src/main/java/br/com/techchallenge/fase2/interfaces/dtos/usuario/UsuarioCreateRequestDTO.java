package br.com.techchallenge.fase2.interfaces.dtos.usuario;

public record UsuarioCreateRequestDTO(
        String nome,
        Long tipoUsuarioId
) {}
