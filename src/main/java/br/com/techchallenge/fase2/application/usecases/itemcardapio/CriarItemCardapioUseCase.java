package br.com.techchallenge.fase2.application.usecases.itemcardapio;

import br.com.techchallenge.fase2.application.dtos.CriarItemCardapioDTO;
import br.com.techchallenge.fase2.application.exceptions.EntityAlreadyExistsException;
import br.com.techchallenge.fase2.application.exceptions.EntityNotFoundException;
import br.com.techchallenge.fase2.application.gateways.ItemCardapioGateway;
import br.com.techchallenge.fase2.application.gateways.RestauranteGateway;
import br.com.techchallenge.fase2.domain.entities.ItemCardapio;
import br.com.techchallenge.fase2.domain.entities.Restaurante;

public class CriarItemCardapioUseCase {

    private final ItemCardapioGateway itemCardapioGateway;
    private final RestauranteGateway restauranteGateway;

    public CriarItemCardapioUseCase(ItemCardapioGateway itemCardapioGateway, RestauranteGateway restauranteGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
        this.restauranteGateway = restauranteGateway;
    }

    public ItemCardapio executar(CriarItemCardapioDTO dto) {
        ItemCardapio itemExistente = itemCardapioGateway.buscarPoNome(dto.nome().trim());
        if (itemExistente != null) {
            throw new EntityAlreadyExistsException("Item do cardápio com nome '" + dto.nome() + "' já existe.");
        }

        Restaurante restaurante = restauranteGateway.buscarPorId(dto.restauranteId())
                .orElseThrow(() -> new EntityNotFoundException("Restaurante com ID " + dto.restauranteId() + " não encontrado."));

        ItemCardapio novoItemCardapio = new ItemCardapio(
                null,
                dto.nome(),
                dto.descricao(),
                dto.preco(),
                dto.somenteNoLocal(),
                dto.fotoPath(),
                restaurante
        );

        return itemCardapioGateway.salvar(novoItemCardapio);
    }
}
