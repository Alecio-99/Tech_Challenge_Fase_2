package br.com.techchallenge.fase2.application.usecases.restaurante;

import br.com.techchallenge.fase2.application.exceptions.EntityNotFoundException;
import br.com.techchallenge.fase2.application.gateways.RestauranteGateway;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ExcluirRestauranteUseCaseTest {

    @Test
    void deveExcluirRestauranteComSucesso() {
        RestauranteGateway gateway = mock(RestauranteGateway.class);
        ExcluirRestauranteUseCase useCase = new ExcluirRestauranteUseCase(gateway);

        when(gateway.buscarPorId(1L))
                .thenReturn(Optional.of(mock(br.com.techchallenge.fase2.domain.entities.Restaurante.class)));

        useCase.executar(1L);

        verify(gateway).deletarPorId(1L);
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoExiste() {
        RestauranteGateway gateway = mock(RestauranteGateway.class);
        ExcluirRestauranteUseCase useCase = new ExcluirRestauranteUseCase(gateway);

        when(gateway.buscarPorId(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> useCase.executar(1L));
    }
}