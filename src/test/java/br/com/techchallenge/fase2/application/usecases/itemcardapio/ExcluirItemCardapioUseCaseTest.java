package br.com.techchallenge.fase2.application.usecases.itemcardapio;

import br.com.techchallenge.fase2.application.exceptions.EntityNotFoundException;
import br.com.techchallenge.fase2.application.gateways.ItemCardapioGateway;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ExcluirItemCardapioUseCaseTest {

    @Test
    void deveExcluirItemCardapioComSucesso() {
        ItemCardapioGateway gateway = mock(ItemCardapioGateway.class);
        ExcluirItemCardapioUseCase useCase = new ExcluirItemCardapioUseCase(gateway);

        when(gateway.buscarPorId(1L))
                .thenReturn(Optional.of(mock(br.com.techchallenge.fase2.domain.entities.ItemCardapio.class)));

        useCase.executar(1L);

        verify(gateway).deletarPorId(1L);
    }

    @Test
    void deveLancarExcecaoQuandoItemNaoExiste() {
        ItemCardapioGateway gateway = mock(ItemCardapioGateway.class);
        ExcluirItemCardapioUseCase useCase = new ExcluirItemCardapioUseCase(gateway);

        when(gateway.buscarPorId(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> useCase.executar(1L));
    }
}