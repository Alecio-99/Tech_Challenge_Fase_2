package br.com.techchallenge.fase2.application.usecases.itemcardapio;

import br.com.techchallenge.fase2.domain.entities.ItemCardapio;
import br.com.techchallenge.fase2.application.gateways.ItemCardapioGateway;
import org.springframework.stereotype.Service;

@Service
public class CriarItemCardapioUseCase {

    private final ItemCardapioGateway repository;

    public CriarItemCardapioUseCase(ItemCardapioGateway repository) {
        this.repository = repository;
    }

    public ItemCardapio executar(ItemCardapio item) {
        // TODO: implementar criação
        throw new UnsupportedOperationException("Não implementado ainda");
    }
}