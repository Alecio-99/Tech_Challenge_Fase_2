package br.com.techchallenge.fase2.interfaces.api.controllers.restaurante.requests;

public record RestauranteCreateRequestDTO(
        String nome,
        String endereco,
        String tipoCozinha,
        String horarioFuncionamento,
        Long donoId
) {}
