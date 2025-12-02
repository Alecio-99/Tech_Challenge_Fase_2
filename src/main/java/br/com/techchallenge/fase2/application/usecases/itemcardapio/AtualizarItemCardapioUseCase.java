package br.com.techchallenge.fase2.application.usecases.itemcardapio;

import br.com.techchallenge.fase2.application.dtos.AtualizarItemCardapioDTO;
import br.com.techchallenge.fase2.application.exceptions.EntityNotFoundException;
import br.com.techchallenge.fase2.application.gateways.ItemCardapioGateway;
import br.com.techchallenge.fase2.application.gateways.RestauranteGateway;
import br.com.techchallenge.fase2.domain.entities.ItemCardapio;
import br.com.techchallenge.fase2.domain.entities.Restaurante;

public class AtualizarItemCardapioUseCase {

    private final ItemCardapioGateway itemCardapioGateway;
    private final RestauranteGateway restauranteGateway;

    public AtualizarItemCardapioUseCase(ItemCardapioGateway itemCardapioGateway, RestauranteGateway restauranteGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
        this.restauranteGateway = restauranteGateway;
    }

    public ItemCardapio executar(AtualizarItemCardapioDTO dto) {
        itemCardapioGateway.buscarPorId(dto.id())
                .orElseThrow(() -> new EntityNotFoundException("Item do cardápio com ID " + dto.id() + " não encontrado."));

        Restaurante restaurante = restauranteGateway.buscarPorId(dto.restauranteId())
                .orElseThrow(() -> new EntityNotFoundException("Restaurante com ID " + dto.restauranteId() + " não encontrado."));

        ItemCardapio itemAtualizado = new ItemCardapio(
                dto.id(),
                dto.nome(),
                dto.descricao(),
                dto.preco(),
                dto.somenteNoLocal(),
                dto.fotoPath(),
                restaurante
        );

        return itemCardapioGateway.salvar(itemAtualizado);
    }
}