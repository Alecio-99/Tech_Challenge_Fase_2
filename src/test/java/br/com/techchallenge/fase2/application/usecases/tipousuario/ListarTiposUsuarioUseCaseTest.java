package br.com.techchallenge.fase2.application.usecases.tipousuario;

import br.com.techchallenge.fase2.application.gateways.TipoUsuarioGateway;
import br.com.techchallenge.fase2.domain.entities.TipoUsuario;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ListarTiposUsuarioUseCaseTest {

    @Test
    void deveListarTiposUsuario() {
        TipoUsuarioGateway gateway = mock(TipoUsuarioGateway.class);
        ListarTiposUsuarioUseCase useCase = new ListarTiposUsuarioUseCase(gateway);

        when(gateway.buscarTodos())
                .thenReturn(List.of(
                        new TipoUsuario(1L, "ADMIN"),
                        new TipoUsuario(2L, "CLIENTE")
                ));

        List<TipoUsuario> resultado = useCase.executar();

        assertEquals(2, resultado.size());
    }
}