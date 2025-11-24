package br.com.techchallenge.fase2.interfaces.presenters.dtos.restaurante;

public record RestauranteGetResponseDTO(
        Long id,
        String nome,
        String endereco,
        String tipoCozinha,
        String horarioFuncionamento,
        String donoNome
) {}
