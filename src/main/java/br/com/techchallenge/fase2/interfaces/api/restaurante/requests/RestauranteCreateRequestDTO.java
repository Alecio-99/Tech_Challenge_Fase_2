package br.com.techchallenge.fase2.interfaces.api.restaurante.requests;

public record RestauranteCreateRequestDTO(
        String nome,
        String endereco,
        String tipoCozinha,
        String horarioFuncionamento,
        Long donoId
) {}
