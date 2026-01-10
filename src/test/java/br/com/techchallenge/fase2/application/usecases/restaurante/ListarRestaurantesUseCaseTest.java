package br.com.techchallenge.fase2.application.usecases.restaurante;

import br.com.techchallenge.fase2.application.gateways.RestauranteGateway;
import br.com.techchallenge.fase2.domain.entities.Restaurante;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ListarRestaurantesUseCaseTest {

    @Test
    void deveListarRestaurantes() {
        RestauranteGateway gateway = mock(RestauranteGateway.class);
        ListarRestaurantesUseCase useCase = new ListarRestaurantesUseCase(gateway);

        when(gateway.buscarTodos())
                .thenReturn(List.of(
                        mock(Restaurante.class),
                        mock(Restaurante.class)
                ));

        List<Restaurante> resultado = useCase.executar();

        assertEquals(2, resultado.size());
    }
}