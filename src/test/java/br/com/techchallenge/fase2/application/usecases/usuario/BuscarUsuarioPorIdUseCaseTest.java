package br.com.techchallenge.fase2.application.usecases.usuario;

import br.com.techchallenge.fase2.application.exceptions.EntityNotFoundException;
import br.com.techchallenge.fase2.application.gateways.UsuarioGateway;
import br.com.techchallenge.fase2.domain.entities.Usuario;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarUsuarioPorIdUseCaseTest {

    @Test
    void deveBuscarUsuarioPorId() {
        UsuarioGateway gateway = mock(UsuarioGateway.class);
        BuscarUsuarioPorIdUseCase useCase = new BuscarUsuarioPorIdUseCase(gateway);

        Usuario usuario = mock(Usuario.class);
        when(gateway.buscarPorId(1L)).thenReturn(Optional.of(usuario));

        Usuario resultado = useCase.executar(1L);

        assertEquals(usuario, resultado);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExiste() {
        UsuarioGateway gateway = mock(UsuarioGateway.class);
        BuscarUsuarioPorIdUseCase useCase = new BuscarUsuarioPorIdUseCase(gateway);

        when(gateway.buscarPorId(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> useCase.executar(1L));
    }
}