package br.com.techchallenge.fase2.application.usecases.usuario;

import br.com.techchallenge.fase2.application.gateways.UsuarioGateway;
import br.com.techchallenge.fase2.domain.entities.Usuario;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ListarUsuariosUseCaseTest {

    @Test
    void deveListarUsuarios() {
        UsuarioGateway gateway = mock(UsuarioGateway.class);
        ListarUsuariosUseCase useCase = new ListarUsuariosUseCase(gateway);

        List<Usuario> usuarios = List.of(mock(Usuario.class), mock(Usuario.class));
        when(gateway.buscarTodos()).thenReturn(usuarios);

        List<Usuario> resultado = useCase.executar();

        assertEquals(2, resultado.size());
    }
}