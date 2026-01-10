package br.com.techchallenge.fase2.application.usecases.itemcardapio;

import br.com.techchallenge.fase2.application.dtos.AtualizarItemCardapioDTO;
import br.com.techchallenge.fase2.application.exceptions.EntityNotFoundException;
import br.com.techchallenge.fase2.application.gateways.ItemCardapioGateway;
import br.com.techchallenge.fase2.application.gateways.RestauranteGateway;
import br.com.techchallenge.fase2.domain.entities.ItemCardapio;
import br.com.techchallenge.fase2.domain.entities.Restaurante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtualizarItemCardapioUseCaseTest {

    private ItemCardapioGateway itemGateway;
    private RestauranteGateway restauranteGateway;
    private AtualizarItemCardapioUseCase useCase;

    @BeforeEach
    void setup() {
        itemGateway = mock(ItemCardapioGateway.class);
        restauranteGateway = mock(RestauranteGateway.class);
        useCase = new AtualizarItemCardapioUseCase(itemGateway, restauranteGateway);
    }

    @Test
    void deveAtualizarItemCardapioComSucesso() {
        AtualizarItemCardapioDTO dto = new AtualizarItemCardapioDTO(
                1L, "Pizza", "Pizza grande", new BigDecimal("39.90"),
                false, "/pizza.png", 10L
        );

        Restaurante restaurante = mock(Restaurante.class);

        when(itemGateway.buscarPorId(1L))
                .thenReturn(Optional.of(mock(ItemCardapio.class)));
        when(restauranteGateway.buscarPorId(10L))
                .thenReturn(Optional.of(restaurante));
        when(itemGateway.salvar(any()))
                .thenAnswer(inv -> inv.getArgument(0));

        ItemCardapio resultado = useCase.executar(dto);

        assertEquals("Pizza", resultado.getNome());
        assertEquals(restaurante, resultado.getRestaurante());
    }

    @Test
    void deveLancarExcecaoQuandoItemNaoExiste() {
        when(itemGateway.buscarPorId(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> useCase.executar(mock(AtualizarItemCardapioDTO.class)));
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoExiste() {
        AtualizarItemCardapioDTO dto = mock(AtualizarItemCardapioDTO.class);

        when(itemGateway.buscarPorId(any())).thenReturn(Optional.of(mock(ItemCardapio.class)));
        when(restauranteGateway.buscarPorId(any())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> useCase.executar(dto));
    }
}