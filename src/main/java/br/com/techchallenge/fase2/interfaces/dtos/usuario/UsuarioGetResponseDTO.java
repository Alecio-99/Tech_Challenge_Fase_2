package br.com.techchallenge.fase2.interfaces.dtos.usuario;

public record UsuarioGetResponseDTO(
        Long id,
        String nome,
        String tipoUsuarioNome
) {}
