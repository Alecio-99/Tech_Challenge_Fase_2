package br.com.techchallenge.fase2.application.usecases.usuario;

import br.com.techchallenge.fase2.application.dtos.CriarUsuarioDTO;
import br.com.techchallenge.fase2.application.exceptions.EntityNotFoundException;
import br.com.techchallenge.fase2.application.gateways.TipoUsuarioGateway;
import br.com.techchallenge.fase2.application.gateways.UsuarioGateway;
import br.com.techchallenge.fase2.domain.entities.TipoUsuario;
import br.com.techchallenge.fase2.domain.entities.Usuario;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CriarUsuarioUseCaseTest {

    @Test
    void deveCriarUsuarioComSucesso() {
        UsuarioGateway usuarioGateway = mock(UsuarioGateway.class);
        TipoUsuarioGateway tipoUsuarioGateway = mock(TipoUsuarioGateway.class);
        CriarUsuarioUseCase useCase = new CriarUsuarioUseCase(usuarioGateway, tipoUsuarioGateway);

        CriarUsuarioDTO dto = new CriarUsuarioDTO("João", 1L);
        TipoUsuario tipoUsuario = new TipoUsuario(1L, "CLIENTE");

        when(tipoUsuarioGateway.buscarPorId(1L)).thenReturn(Optional.of(tipoUsuario));
        when(usuarioGateway.salvar(any())).thenAnswer(inv -> inv.getArgument(0));

        Usuario usuario = useCase.executar(dto);

        assertEquals("João", usuario.getNome());
        assertEquals(tipoUsuario, usuario.getTipoUsuario());
    }

    @Test
    void deveLancarExcecaoQuandoTipoUsuarioNaoExiste() {
        UsuarioGateway usuarioGateway = mock(UsuarioGateway.class);
        TipoUsuarioGateway tipoUsuarioGateway = mock(TipoUsuarioGateway.class);
        CriarUsuarioUseCase useCase = new CriarUsuarioUseCase(usuarioGateway, tipoUsuarioGateway);

        when(tipoUsuarioGateway.buscarPorId(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> useCase.executar(new CriarUsuarioDTO("João", 1L)));
    }
}