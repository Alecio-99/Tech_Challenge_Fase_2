package br.com.techchallenge.fase2.application.usecases.usuario;

import br.com.techchallenge.fase2.application.dtos.AtualizarUsuarioDTO;
import br.com.techchallenge.fase2.application.exceptions.EntityNotFoundException;
import br.com.techchallenge.fase2.application.gateways.TipoUsuarioGateway;
import br.com.techchallenge.fase2.application.gateways.UsuarioGateway;
import br.com.techchallenge.fase2.domain.entities.TipoUsuario;
import br.com.techchallenge.fase2.domain.entities.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtualizarUsuarioUseCaseTest {

    private UsuarioGateway usuarioGateway;
    private TipoUsuarioGateway tipoUsuarioGateway;
    private AtualizarUsuarioUseCase useCase;

    @BeforeEach
    void setup() {
        usuarioGateway = Mockito.mock(UsuarioGateway.class);
        tipoUsuarioGateway = Mockito.mock(TipoUsuarioGateway.class);
        useCase = new AtualizarUsuarioUseCase(usuarioGateway, tipoUsuarioGateway);
    }

    @Test
    void deveAtualizarUsuarioComSucesso() {
        AtualizarUsuarioDTO dto = new AtualizarUsuarioDTO(1L, "Novo Nome", 2L);
        TipoUsuario tipoUsuario = new TipoUsuario(2L, "ADMIN");

        when(usuarioGateway.buscarPorId(1L)).thenReturn(Optional.of(new Usuario(1L, "Antigo", tipoUsuario)));
        when(tipoUsuarioGateway.buscarPorId(2L)).thenReturn(Optional.of(tipoUsuario));
        when(usuarioGateway.salvar(any())).thenAnswer(inv -> inv.getArgument(0));

        Usuario resultado = useCase.executar(dto);

        assertEquals("Novo Nome", resultado.getNome());
        assertEquals(tipoUsuario, resultado.getTipoUsuario());
        verify(usuarioGateway).salvar(any());
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExiste() {
        AtualizarUsuarioDTO dto = new AtualizarUsuarioDTO(1L, "Nome", 2L);

        when(usuarioGateway.buscarPorId(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> useCase.executar(dto));
    }

    @Test
    void deveLancarExcecaoQuandoTipoUsuarioNaoExiste() {
        AtualizarUsuarioDTO dto = new AtualizarUsuarioDTO(1L, "Nome", 2L);

        when(usuarioGateway.buscarPorId(1L)).thenReturn(Optional.of(mock(Usuario.class)));
        when(tipoUsuarioGateway.buscarPorId(2L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> useCase.executar(dto));
    }
}