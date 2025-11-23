package br.com.techchallenge.fase2.application.usecases.itemcardapio;

import br.com.techchallenge.fase2.domain.entities.ItemCardapio;
import br.com.techchallenge.fase2.domain.repositories.ItemCardapioRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarItemCardapioUseCase {

    private final ItemCardapioRepository repository;

    public CriarItemCardapioUseCase(ItemCardapioRepository repository) {
        this.repository = repository;
    }

    public ItemCardapio executar(ItemCardapio item) {
        // TODO: implementar criação
        throw new UnsupportedOperationException("Não implementado ainda");
    }
}