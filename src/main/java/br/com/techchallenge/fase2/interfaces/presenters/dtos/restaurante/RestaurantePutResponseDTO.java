package br.com.techchallenge.fase2.interfaces.presenters.dtos.restaurante;

public record RestaurantePutResponseDTO(
        Long id,
        String nome,
        String tipoCozinha,
        String horarioFuncionamento,
        String donoNome
) {}
