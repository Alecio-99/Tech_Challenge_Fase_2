package br.com.techchallenge.fase2.interfaces.dtos.itemcardapio;

public record ItemCardapioCreateResponseDTO(
        Long id,
        String nome,
        String restauranteNome
) {}
