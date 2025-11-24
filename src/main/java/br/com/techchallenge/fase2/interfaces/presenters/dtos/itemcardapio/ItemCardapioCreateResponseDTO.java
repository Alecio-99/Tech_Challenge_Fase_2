package br.com.techchallenge.fase2.interfaces.presenters.dtos.itemcardapio;

public record ItemCardapioCreateResponseDTO(
        Long id,
        String nome,
        String restauranteNome
) {}
