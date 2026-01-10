package br.com.techchallenge.fase2.application.usecases.restaurante;

import br.com.techchallenge.fase2.application.exceptions.EntityNotFoundException;
import br.com.techchallenge.fase2.application.gateways.RestauranteGateway;
import br.com.techchallenge.fase2.domain.entities.Restaurante;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarRestaurantePorIdUseCaseTest {

    @Test
    void deveBuscarRestaurantePorId() {
        RestauranteGateway gateway = mock(RestauranteGateway.class);
        BuscarRestaurantePorIdUseCase useCase = new BuscarRestaurantePorIdUseCase(gateway);

        Restaurante restaurante = mock(Restaurante.class);
        when(gateway.buscarPorId(1L)).thenReturn(Optional.of(restaurante));

        Restaurante resultado = useCase.executar(1L);

        assertEquals(restaurante, resultado);
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoExiste() {
        RestauranteGateway gateway = mock(RestauranteGateway.class);
        BuscarRestaurantePorIdUseCase useCase = new BuscarRestaurantePorIdUseCase(gateway);

        when(gateway.buscarPorId(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> useCase.executar(1L));
    }
}