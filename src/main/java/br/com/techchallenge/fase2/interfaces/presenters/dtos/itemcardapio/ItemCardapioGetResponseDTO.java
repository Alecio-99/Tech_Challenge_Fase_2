package br.com.techchallenge.fase2.interfaces.presenters.dtos.itemcardapio;

import java.math.BigDecimal;

public record ItemCardapioGetResponseDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        boolean somenteNoLocal,
        String fotoPath,
        String restauranteNome
) {}
