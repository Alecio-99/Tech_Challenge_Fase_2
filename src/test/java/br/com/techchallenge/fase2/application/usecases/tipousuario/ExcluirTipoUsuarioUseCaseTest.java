package br.com.techchallenge.fase2.application.usecases.tipousuario;

import br.com.techchallenge.fase2.application.exceptions.EntityNotFoundException;
import br.com.techchallenge.fase2.application.gateways.TipoUsuarioGateway;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ExcluirTipoUsuarioUseCaseTest {

    @Test
    void deveExcluirTipoUsuarioComSucesso() {
        TipoUsuarioGateway gateway = mock(TipoUsuarioGateway.class);
        ExcluirTipoUsuarioUseCase useCase = new ExcluirTipoUsuarioUseCase(gateway);

        when(gateway.buscarPorId(1L))
                .thenReturn(Optional.of(mock(br.com.techchallenge.fase2.domain.entities.TipoUsuario.class)));

        useCase.executar(1L);

        verify(gateway).deletarPorId(1L);
    }

    @Test
    void deveLancarExcecaoQuandoTipoUsuarioNaoExiste() {
        TipoUsuarioGateway gateway = mock(TipoUsuarioGateway.class);
        ExcluirTipoUsuarioUseCase useCase = new ExcluirTipoUsuarioUseCase(gateway);

        when(gateway.buscarPorId(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> useCase.executar(1L));
    }
}