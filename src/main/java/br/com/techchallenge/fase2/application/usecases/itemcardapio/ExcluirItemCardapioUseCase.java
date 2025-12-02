package br.com.techchallenge.fase2.application.usecases.itemcardapio;

import br.com.techchallenge.fase2.application.exceptions.EntityNotFoundException;
import br.com.techchallenge.fase2.application.gateways.ItemCardapioGateway;

public class ExcluirItemCardapioUseCase {

    private final ItemCardapioGateway itemCardapioGateway;

    public ExcluirItemCardapioUseCase(ItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public void executar(Long id) {
        itemCardapioGateway.buscarPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Item do cardápio com ID " + id + " não encontrado."));
        itemCardapioGateway.deletarPorId(id);
    }
}
