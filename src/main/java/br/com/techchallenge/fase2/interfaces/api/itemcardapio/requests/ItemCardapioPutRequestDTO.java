package br.com.techchallenge.fase2.interfaces.api.itemcardapio.requests;

import java.math.BigDecimal;

public record ItemCardapioPutRequestDTO(
        String nome,
        String descricao,
        BigDecimal preco,
        boolean somenteNoLocal,
        String fotoPath,
        Long restauranteId
) {}
