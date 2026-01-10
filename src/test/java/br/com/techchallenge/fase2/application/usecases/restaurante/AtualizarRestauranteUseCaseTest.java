package br.com.techchallenge.fase2.application.usecases.restaurante;

import br.com.techchallenge.fase2.application.dtos.AtualizarRestauranteDTO;
import br.com.techchallenge.fase2.application.exceptions.EntityNotFoundException;
import br.com.techchallenge.fase2.application.gateways.RestauranteGateway;
import br.com.techchallenge.fase2.application.gateways.UsuarioGateway;
import br.com.techchallenge.fase2.domain.entities.Restaurante;
import br.com.techchallenge.fase2.domain.entities.TipoUsuario;
import br.com.techchallenge.fase2.domain.entities.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtualizarRestauranteUseCaseTest {

    private RestauranteGateway restauranteGateway;
    private UsuarioGateway usuarioGateway;
    private AtualizarRestauranteUseCase useCase;

    @BeforeEach
    void setup() {
        restauranteGateway = mock(RestauranteGateway.class);
        usuarioGateway = mock(UsuarioGateway.class);
        useCase = new AtualizarRestauranteUseCase(restauranteGateway, usuarioGateway);
    }

    @Test
    void deveAtualizarRestauranteComSucesso() {
        AtualizarRestauranteDTO dto = new AtualizarRestauranteDTO(
                1L, "Restaurante X", "Rua A", "Italiana", "08:00-22:00", 10L
        );

        TipoUsuario tipoUsuario = new TipoUsuario(1L, "DONO");
        Usuario dono = new Usuario(10L, "Dono", tipoUsuario);

        when(restauranteGateway.buscarPorId(1L))
                .thenReturn(Optional.of(mock(Restaurante.class)));
        when(usuarioGateway.buscarPorId(10L))
                .thenReturn(Optional.of(dono));
        when(restauranteGateway.salvar(any()))
                .thenAnswer(inv -> inv.getArgument(0));

        Restaurante resultado = useCase.executar(dto);

        assertEquals("Restaurante X", resultado.getNome());
        assertEquals(dono, resultado.getDono());
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoExiste() {
        when(restauranteGateway.buscarPorId(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> useCase.executar(mock(AtualizarRestauranteDTO.class)));
    }

    @Test
    void deveLancarExcecaoQuandoDonoNaoExiste() {
        AtualizarRestauranteDTO dto = new AtualizarRestauranteDTO(
                1L, "Nome", "End", "Tipo", "Horario", 10L
        );

        when(restauranteGateway.buscarPorId(1L))
                .thenReturn(Optional.of(mock(Restaurante.class)));
        when(usuarioGateway.buscarPorId(10L))
                .thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> useCase.executar(dto));
    }
}