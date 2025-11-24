package br.com.techchallenge.fase2.application.gateways;

import br.com.techchallenge.fase2.domain.entities.ItemCardapio;

import java.util.List;
import java.util.Optional;

public interface ItemCardapioGateway {

    ItemCardapio salvar(ItemCardapio itemCardapio);

    Optional<ItemCardapio> buscarPorId(Long id);

    List<ItemCardapio> buscarTodos();

    void deletarPorId(Long id);

    List<ItemCardapio> buscarPorRestaurante(Long idRestaurante);
}
