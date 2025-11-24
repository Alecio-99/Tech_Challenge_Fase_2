package br.com.techchallenge.fase2.application.usecases.itemcardapio;

import br.com.techchallenge.fase2.domain.entities.ItemCardapio;
import br.com.techchallenge.fase2.application.gateways.ItemCardapioGateway;
import org.springframework.stereotype.Service;

@Service
public class AtualizarItemCardapioUseCase {

    private final ItemCardapioGateway repository;

    public AtualizarItemCardapioUseCase(ItemCardapioGateway repository) {
        this.repository = repository;
    }

    public ItemCardapio executar(Long id, ItemCardapio itemAtualizado) {
        // TODO: implementar atualização
        throw new UnsupportedOperationException("Não implementado ainda");
    }
}