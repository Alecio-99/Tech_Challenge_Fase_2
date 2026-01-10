package br.com.techchallenge.fase2.application.usecases.itemcardapio;

import br.com.techchallenge.fase2.application.exceptions.EntityNotFoundException;
import br.com.techchallenge.fase2.application.gateways.ItemCardapioGateway;
import br.com.techchallenge.fase2.domain.entities.ItemCardapio;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarItemCardapioPorIdUseCaseTest {

    @Test
    void deveBuscarItemCardapioPorId() {
        ItemCardapioGateway gateway = mock(ItemCardapioGateway.class);
        BuscarItemCardapioPorIdUseCase useCase = new BuscarItemCardapioPorIdUseCase(gateway);

        ItemCardapio item = mock(ItemCardapio.class);
        when(gateway.buscarPorId(1L)).thenReturn(Optional.of(item));

        ItemCardapio resultado = useCase.executar(1L);

        assertEquals(item, resultado);
    }

    @Test
    void deveLancarExcecaoQuandoItemNaoExiste() {
        ItemCardapioGateway gateway = mock(ItemCardapioGateway.class);
        BuscarItemCardapioPorIdUseCase useCase = new BuscarItemCardapioPorIdUseCase(gateway);

        when(gateway.buscarPorId(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> useCase.executar(1L));
    }
}