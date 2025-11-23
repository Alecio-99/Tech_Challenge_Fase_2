package br.com.techchallenge.fase2.application.usecases.itemcardapio;

import br.com.techchallenge.fase2.domain.entities.ItemCardapio;
import br.com.techchallenge.fase2.domain.repositories.ItemCardapioRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizarItemCardapioUseCase {

    private final ItemCardapioRepository repository;

    public AtualizarItemCardapioUseCase(ItemCardapioRepository repository) {
        this.repository = repository;
    }

    public ItemCardapio executar(Long id, ItemCardapio itemAtualizado) {
        // TODO: implementar atualização
        throw new UnsupportedOperationException("Não implementado ainda");
    }
}