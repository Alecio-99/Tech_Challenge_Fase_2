package br.com.techchallenge.fase2.interfaces.dtos.usuario;

public record UsuarioCreateResponseDTO(
        Long id,
        String nome,
        String tipoUsuarioNome
) {}
