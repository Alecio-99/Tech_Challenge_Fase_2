package br.com.techchallenge.fase2.application.usecases.itemcardapio;

import br.com.techchallenge.fase2.domain.repositories.ItemCardapioRepository;
import org.springframework.stereotype.Service;

@Service
public class ExcluirItemCardapioUseCase {

    private final ItemCardapioRepository repository;

    public ExcluirItemCardapioUseCase(ItemCardapioRepository repository) {
        this.repository = repository;
    }

    public void executar(Long id) {
        // TODO: implementar exclusão
        throw new UnsupportedOperationException("Não implementado ainda");
    }
}
