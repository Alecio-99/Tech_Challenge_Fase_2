package br.com.techchallenge.fase2.interfaces.dtos.restaurante;

public record RestauranteCreateResponseDTO(
        Long id,
        String nome,
        String tipoCozinha,
        String horarioFuncionamento,
        String donoNome
) {}
