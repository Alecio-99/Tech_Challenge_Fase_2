package br.com.techchallenge.fase2.application.usecases.itemcardapio;

import br.com.techchallenge.fase2.domain.entities.ItemCardapio;
import br.com.techchallenge.fase2.domain.repositories.ItemCardapioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarItensCardapioUseCase {

    private final ItemCardapioRepository repository;

    public ListarItensCardapioUseCase(ItemCardapioRepository repository) {
        this.repository = repository;
    }

    public List<ItemCardapio> executar() {
        // TODO: implementar listagem
        throw new UnsupportedOperationException("Não implementado ainda");
    }
}