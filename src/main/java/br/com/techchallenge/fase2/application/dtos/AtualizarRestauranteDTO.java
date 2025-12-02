package br.com.techchallenge.fase2.application.dtos;

public record AtualizarRestauranteDTO(
        Long id,
        String nome,
        String endereco,
        String tipoCozinha,
        String horarioFuncionamento,
        Long donoId
) {}

