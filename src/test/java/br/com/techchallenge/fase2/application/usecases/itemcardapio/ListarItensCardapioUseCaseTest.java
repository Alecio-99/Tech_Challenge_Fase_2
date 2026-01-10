package br.com.techchallenge.fase2.application.usecases.itemcardapio;

import br.com.techchallenge.fase2.application.gateways.ItemCardapioGateway;
import br.com.techchallenge.fase2.domain.entities.ItemCardapio;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ListarItensCardapioUseCaseTest {

    @Test
    void deveListarItensCardapio() {
        ItemCardapioGateway gateway = mock(ItemCardapioGateway.class);
        ListarItensCardapioUseCase useCase = new ListarItensCardapioUseCase(gateway);

        when(gateway.buscarTodos())
                .thenReturn(List.of(
                        mock(ItemCardapio.class),
                        mock(ItemCardapio.class)
                ));

        List<ItemCardapio> resultado = useCase.executar();

        assertEquals(2, resultado.size());
    }
}