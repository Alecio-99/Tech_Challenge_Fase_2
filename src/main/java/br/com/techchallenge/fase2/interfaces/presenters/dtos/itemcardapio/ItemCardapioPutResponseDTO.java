package br.com.techchallenge.fase2.interfaces.presenters.dtos.itemcardapio;

public record ItemCardapioPutResponseDTO(
        Long id,
        String nome,
        String restauranteNome
) {}
