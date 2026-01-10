package br.com.techchallenge.fase2.application.usecases.tipousuario;

import br.com.techchallenge.fase2.application.exceptions.EntityNotFoundException;
import br.com.techchallenge.fase2.application.gateways.TipoUsuarioGateway;
import br.com.techchallenge.fase2.domain.entities.TipoUsuario;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarTipoUsuarioPorIdUseCaseTest {

    @Test
    void deveBuscarTipoUsuarioPorId() {
        TipoUsuarioGateway gateway = mock(TipoUsuarioGateway.class);
        BuscarTipoUsuarioPorIdUseCase useCase = new BuscarTipoUsuarioPorIdUseCase(gateway);

        TipoUsuario tipo = new TipoUsuario(1L, "ADMIN");
        when(gateway.buscarPorId(1L)).thenReturn(Optional.of(tipo));

        TipoUsuario resultado = useCase.executar(1L);

        assertEquals(tipo, resultado);
    }

    @Test
    void deveLancarExcecaoQuandoTipoUsuarioNaoExiste() {
        TipoUsuarioGateway gateway = mock(TipoUsuarioGateway.class);
        BuscarTipoUsuarioPorIdUseCase useCase = new BuscarTipoUsuarioPorIdUseCase(gateway);

        when(gateway.buscarPorId(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> useCase.executar(1L));
    }
}