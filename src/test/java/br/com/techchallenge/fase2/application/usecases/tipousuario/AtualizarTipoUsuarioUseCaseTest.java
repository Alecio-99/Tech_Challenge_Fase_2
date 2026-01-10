package br.com.techchallenge.fase2.application.usecases.tipousuario;

import br.com.techchallenge.fase2.application.dtos.AtualizarTipoUsuarioDTO;
import br.com.techchallenge.fase2.application.exceptions.EntityNotFoundException;
import br.com.techchallenge.fase2.application.gateways.TipoUsuarioGateway;
import br.com.techchallenge.fase2.domain.entities.TipoUsuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtualizarTipoUsuarioUseCaseTest {

    private TipoUsuarioGateway gateway;
    private AtualizarTipoUsuarioUseCase useCase;

    @BeforeEach
    void setup() {
        gateway = mock(TipoUsuarioGateway.class);
        useCase = new AtualizarTipoUsuarioUseCase(gateway);
    }

    @Test
    void deveAtualizarTipoUsuarioComSucesso() {
        AtualizarTipoUsuarioDTO dto = new AtualizarTipoUsuarioDTO(1L, "ADMIN");

        when(gateway.buscarPorId(1L)).thenReturn(Optional.of(new TipoUsuario(1L, "USER")));
        when(gateway.salvar(any())).thenAnswer(inv -> inv.getArgument(0));

        TipoUsuario resultado = useCase.executar(dto);

        assertEquals(1L, resultado.getId());
        assertEquals("ADMIN", resultado.getNomeTipo());
        verify(gateway).salvar(any());
    }

    @Test
    void deveLancarExcecaoQuandoTipoUsuarioNaoExiste() {
        when(gateway.buscarPorId(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> useCase.executar(new AtualizarTipoUsuarioDTO(1L, "ADMIN")));
    }
}