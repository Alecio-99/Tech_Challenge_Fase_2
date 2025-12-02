package br.com.techchallenge.fase2.application.dtos;

import java.math.BigDecimal;

public record CriarItemCardapioDTO(
        String nome,
        String descricao,
        BigDecimal preco,
        boolean somenteNoLocal,
        String fotoPath,
        Long restauranteId
) {}

