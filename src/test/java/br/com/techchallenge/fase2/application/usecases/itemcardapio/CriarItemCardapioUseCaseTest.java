package br.com.techchallenge.fase2.application.usecases.itemcardapio;

import br.com.techchallenge.fase2.application.dtos.CriarItemCardapioDTO;
import br.com.techchallenge.fase2.application.exceptions.EntityAlreadyExistsException;
import br.com.techchallenge.fase2.application.exceptions.EntityNotFoundException;
import br.com.techchallenge.fase2.application.gateways.ItemCardapioGateway;
import br.com.techchallenge.fase2.application.gateways.RestauranteGateway;
import br.com.techchallenge.fase2.domain.entities.ItemCardapio;
import br.com.techchallenge.fase2.domain.entities.Restaurante;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CriarItemCardapioUseCaseTest {

    @Test
    void deveCriarItemCardapioComSucesso() {
        ItemCardapioGateway itemGateway = mock(ItemCardapioGateway.class);
        RestauranteGateway restauranteGateway = mock(RestauranteGateway.class);
        CriarItemCardapioUseCase useCase =
                new CriarItemCardapioUseCase(itemGateway, restauranteGateway);

        CriarItemCardapioDTO dto = new CriarItemCardapioDTO(
                "Hamburguer", "Artesanal", new BigDecimal("29.90"),
                false, "/burger.png", 10L
        );

        Restaurante restaurante = mock(Restaurante.class);

        when(itemGateway.buscarPoNome("Hamburguer")).thenReturn(null);
        when(restauranteGateway.buscarPorId(10L)).thenReturn(Optional.of(restaurante));
        when(itemGateway.salvar(any())).thenAnswer(inv -> inv.getArgument(0));

        ItemCardapio resultado = useCase.executar(dto);

        assertEquals("Hamburguer", resultado.getNome());
        assertEquals(restaurante, resultado.getRestaurante());
        assertNull(resultado.getId());
    }

    @Test
    void deveLancarExcecaoQuandoNomeJaExiste() {
        ItemCardapioGateway itemGateway = mock(ItemCardapioGateway.class);
        RestauranteGateway restauranteGateway = mock(RestauranteGateway.class);
        CriarItemCardapioUseCase useCase =
                new CriarItemCardapioUseCase(itemGateway, restauranteGateway);

        when(itemGateway.buscarPoNome("Hamburguer"))
                .thenReturn(mock(ItemCardapio.class));

        CriarItemCardapioDTO dto = new CriarItemCardapioDTO(
                "Hamburguer",
                "Artesanal",
                new BigDecimal("29.90"),
                false,
                "/burger.png",
                10L
        );

        assertThrows(EntityAlreadyExistsException.class,
                () -> useCase.executar(dto));
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoExiste() {
        ItemCardapioGateway itemGateway = mock(ItemCardapioGateway.class);
        RestauranteGateway restauranteGateway = mock(RestauranteGateway.class);
        CriarItemCardapioUseCase useCase =
                new CriarItemCardapioUseCase(itemGateway, restauranteGateway);

        CriarItemCardapioDTO dto = new CriarItemCardapioDTO(
                "Hamburguer",
                "Artesanal",
                new BigDecimal("29.90"),
                false,
                "/burger.png",
                999L // restaurante que NÃO existe
        );

        when(itemGateway.buscarPoNome("Hamburguer")).thenReturn(null);
        when(restauranteGateway.buscarPorId(999L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> useCase.executar(dto));
    }
}