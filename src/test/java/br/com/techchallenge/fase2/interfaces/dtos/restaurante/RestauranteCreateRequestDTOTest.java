package br.com.techchallenge.fase2.interfaces.dtos.restaurante;

public record RestauranteCreateRequestDTOTest(
        String nome,
        String endereco,
        String tipoCozinha,
        String horarioFuncionamento,
        Long donoId
) {}
