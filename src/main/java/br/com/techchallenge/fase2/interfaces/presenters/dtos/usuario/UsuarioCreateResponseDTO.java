package br.com.techchallenge.fase2.interfaces.presenters.dtos.usuario;

public record UsuarioCreateResponseDTO(
        Long id,
        String nome,
        String tipoUsuarioNome
) {}
