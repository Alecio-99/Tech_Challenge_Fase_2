package br.com.techchallenge.fase2.application.usecases.usuario;

import br.com.techchallenge.fase2.application.exceptions.EntityNotFoundException;
import br.com.techchallenge.fase2.application.gateways.UsuarioGateway;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ExcluirUsuarioUseCaseTest {

    @Test
    void deveExcluirUsuarioComSucesso() {
        UsuarioGateway gateway = mock(UsuarioGateway.class);
        ExcluirUsuarioUseCase useCase = new ExcluirUsuarioUseCase(gateway);

        when(gateway.buscarPorId(1L)).thenReturn(Optional.of(mock(br.com.techchallenge.fase2.domain.entities.Usuario.class)));

        useCase.executar(1L);

        verify(gateway).deletarPorId(1L);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExiste() {
        UsuarioGateway gateway = mock(UsuarioGateway.class);
        ExcluirUsuarioUseCase useCase = new ExcluirUsuarioUseCase(gateway);

        when(gateway.buscarPorId(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> useCase.executar(1L));
    }
}