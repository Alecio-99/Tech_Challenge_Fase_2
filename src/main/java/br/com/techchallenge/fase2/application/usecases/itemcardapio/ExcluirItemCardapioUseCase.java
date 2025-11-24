package br.com.techchallenge.fase2.application.usecases.itemcardapio;

import br.com.techchallenge.fase2.application.gateways.ItemCardapioGateway;
import org.springframework.stereotype.Service;

@Service
public class ExcluirItemCardapioUseCase {

    private final ItemCardapioGateway repository;

    public ExcluirItemCardapioUseCase(ItemCardapioGateway repository) {
        this.repository = repository;
    }

    public void executar(Long id) {
        // TODO: implementar exclusão
        throw new UnsupportedOperationException("Não implementado ainda");
    }
}
