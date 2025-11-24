package br.com.techchallenge.fase2.application.usecases.itemcardapio;

import br.com.techchallenge.fase2.domain.entities.ItemCardapio;
import br.com.techchallenge.fase2.application.gateways.ItemCardapioGateway;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarItemCardapioPorIdUseCase {

    private final ItemCardapioGateway repository;

    public BuscarItemCardapioPorIdUseCase(ItemCardapioGateway repository) {
        this.repository = repository;
    }

    public Optional<ItemCardapio> executar(Long id) {
        // TODO: implementar busca por id
        throw new UnsupportedOperationException("Não implementado ainda");
    }
}