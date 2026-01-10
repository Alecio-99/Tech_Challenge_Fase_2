package br.com.techchallenge.fase2.application.usecases.restaurante;

import br.com.techchallenge.fase2.application.dtos.CriarRestauranteDTO;
import br.com.techchallenge.fase2.application.exceptions.EntityNotFoundException;
import br.com.techchallenge.fase2.application.gateways.RestauranteGateway;
import br.com.techchallenge.fase2.application.gateways.UsuarioGateway;
import br.com.techchallenge.fase2.domain.entities.Restaurante;
import br.com.techchallenge.fase2.domain.entities.TipoUsuario;
import br.com.techchallenge.fase2.domain.entities.Usuario;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CriarRestauranteUseCaseTest {

    @Test
    void deveCriarRestauranteComSucesso() {
        RestauranteGateway restauranteGateway = mock(RestauranteGateway.class);
        UsuarioGateway usuarioGateway = mock(UsuarioGateway.class);
        CriarRestauranteUseCase useCase =
                new CriarRestauranteUseCase(restauranteGateway, usuarioGateway);

        CriarRestauranteDTO dto = new CriarRestauranteDTO(
                "Rest X", "Rua B", "Japonesa", "10:00-23:00", 5L
        );

        TipoUsuario tipoUsuario = new TipoUsuario(1L, "DONO");
        Usuario dono = new Usuario(5L, "Dono", tipoUsuario);

        when(usuarioGateway.buscarPorId(5L)).thenReturn(Optional.of(dono));
        when(restauranteGateway.salvar(any())).thenAnswer(inv -> inv.getArgument(0));

        Restaurante resultado = useCase.executar(dto);

        assertEquals("Rest X", resultado.getNome());
        assertEquals(dono, resultado.getDono());
        assertNull(resultado.getId());
    }

    @Test
    void deveLancarExcecaoQuandoDonoNaoExiste() {
        RestauranteGateway restauranteGateway = mock(RestauranteGateway.class);
        UsuarioGateway usuarioGateway = mock(UsuarioGateway.class);
        CriarRestauranteUseCase useCase =
                new CriarRestauranteUseCase(restauranteGateway, usuarioGateway);

        when(usuarioGateway.buscarPorId(5L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> useCase.executar(mock(CriarRestauranteDTO.class)));
    }
}