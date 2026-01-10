package br.com.techchallenge.fase2.application.usecases.itemcardapio;

import br.com.techchallenge.fase2.application.gateways.ItemCardapioGateway;
import br.com.techchallenge.fase2.domain.entities.ItemCardapio;

import java.util.List;

public class ListarItensCardapioUseCase {

    private final ItemCardapioGateway itemCardapioGateway;

    public ListarItensCardapioUseCase(ItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public List<ItemCardapio> executar() {
        return itemCardapioGateway.buscarTodos();
    }
}