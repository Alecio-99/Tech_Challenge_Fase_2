package br.com.techchallenge.fase2.application.usecases.itemcardapio;

import br.com.techchallenge.fase2.application.exceptions.EntityNotFoundException;
import br.com.techchallenge.fase2.application.gateways.ItemCardapioGateway;
import br.com.techchallenge.fase2.domain.entities.ItemCardapio;

public class BuscarItemCardapioPorIdUseCase {

    private final ItemCardapioGateway itemCardapioGateway;

    public BuscarItemCardapioPorIdUseCase(ItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public ItemCardapio executar(Long id) {
        return itemCardapioGateway.buscarPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Item do cardápio com ID " + id + " não encontrado."));
    }
}