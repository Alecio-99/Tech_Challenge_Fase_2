package br.com.techchallenge.fase2.interfaces.dtos.usuario;

public record UsuarioPutResponseDTO(
        Long id,
        String nome,
        String tipoUsuarioNome
) {}
