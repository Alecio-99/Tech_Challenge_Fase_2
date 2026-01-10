package br.com.techchallenge.fase2.application.usecases.tipousuario;

import br.com.techchallenge.fase2.application.dtos.CriarTipoUsuarioDTO;
import br.com.techchallenge.fase2.application.exceptions.EntityAlreadyExistsException;
import br.com.techchallenge.fase2.application.gateways.TipoUsuarioGateway;
import br.com.techchallenge.fase2.domain.entities.TipoUsuario;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CriarTipoUsuarioUseCaseTest {

    @Test
    void deveCriarTipoUsuarioComSucesso() {
        TipoUsuarioGateway gateway = mock(TipoUsuarioGateway.class);
        CriarTipoUsuarioUseCase useCase = new CriarTipoUsuarioUseCase(gateway);

        CriarTipoUsuarioDTO dto = new CriarTipoUsuarioDTO("ADMIN");

        when(gateway.buscarPorNome("ADMIN")).thenReturn(Optional.empty());
        when(gateway.salvar(any())).thenAnswer(inv -> inv.getArgument(0));

        TipoUsuario resultado = useCase.executar(dto);

        assertEquals("ADMIN", resultado.getNomeTipo());
        assertNull(resultado.getId());
    }

    @Test
    void deveLancarExcecaoQuandoNomeJaExiste() {
        TipoUsuarioGateway gateway = mock(TipoUsuarioGateway.class);
        CriarTipoUsuarioUseCase useCase = new CriarTipoUsuarioUseCase(gateway);

        when(gateway.buscarPorNome("ADMIN"))
                .thenReturn(Optional.of(new TipoUsuario(1L, "ADMIN")));

        assertThrows(EntityAlreadyExistsException.class,
                () -> useCase.executar(new CriarTipoUsuarioDTO("ADMIN")));
    }
}