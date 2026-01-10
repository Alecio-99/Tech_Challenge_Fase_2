package br.com.techchallenge.fase2.interfaces.api.controllers.restaurante;

import br.com.techchallenge.fase2.application.usecases.restaurante.*;
import br.com.techchallenge.fase2.domain.entities.Restaurante;
import br.com.techchallenge.fase2.domain.entities.TipoUsuario;
import br.com.techchallenge.fase2.domain.entities.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestauranteController.class)
class RestauranteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CriarRestauranteUseCase criarRestauranteUseCase;

    @MockitoBean
    private BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase;

    @MockitoBean
    private ListarRestaurantesUseCase listarRestaurantesUseCase;

    @MockitoBean
    private AtualizarRestauranteUseCase atualizarRestauranteUseCase;

    @MockitoBean
    private ExcluirRestauranteUseCase excluirRestauranteUseCase;

    private Restaurante restauranteMock() {
        TipoUsuario tipo = new TipoUsuario(1L, "DONO");
        Usuario dono = new Usuario(1L, "João", tipo);

        return new Restaurante(
                1L,
                "Restaurante Teste",
                "Rua A, 123",
                "Italiana",
                "10:00 - 22:00",
                dono
        );
    }

    @Test
    void deveCriarRestaurante() throws Exception {
        when(criarRestauranteUseCase.executar(any()))
                .thenReturn(restauranteMock());

        String body = """
            {
              "nome": "Restaurante Teste",
              "endereco": "Rua A, 123",
              "tipoCozinha": "Italiana",
              "horarioFuncionamento": "10:00 - 22:00",
              "donoId": 1
            }
        """;

        mockMvc.perform(post("/api/v1/restaurantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Restaurante Teste"))
                .andExpect(jsonPath("$.tipoCozinha").value("Italiana"))
                .andExpect(jsonPath("$.horarioFuncionamento").value("10:00 - 22:00"))
                .andExpect(jsonPath("$.donoNome").value("João"));
    }

    @Test
    void deveBuscarRestaurantePorId() throws Exception {
        when(buscarRestaurantePorIdUseCase.executar(1L))
                .thenReturn(restauranteMock());

        mockMvc.perform(get("/api/v1/restaurantes/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Restaurante Teste"))
                .andExpect(jsonPath("$.endereco").value("Rua A, 123"))
                .andExpect(jsonPath("$.tipoCozinha").value("Italiana"))
                .andExpect(jsonPath("$.horarioFuncionamento").value("10:00 - 22:00"))
                .andExpect(jsonPath("$.donoNome").value("João"));
    }

    @Test
    void deveListarRestaurantes() throws Exception {
        when(listarRestaurantesUseCase.executar())
                .thenReturn(List.of(restauranteMock()));

        mockMvc.perform(get("/api/v1/restaurantes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("Restaurante Teste"))
                .andExpect(jsonPath("$[0].donoNome").value("João"));
    }

    @Test
    void deveAtualizarRestaurante() throws Exception {
        when(atualizarRestauranteUseCase.executar(any()))
                .thenReturn(restauranteMock());

        String body = """
            {
              "nome": "Restaurante Atualizado",
              "endereco": "Rua B, 456",
              "tipoCozinha": "Japonesa",
              "horarioFuncionamento": "11:00 - 23:00",
              "donoId": 1
            }
        """;

        mockMvc.perform(put("/api/v1/restaurantes/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Restaurante Teste"));
    }

    @Test
    void deveDeletarRestaurante() throws Exception {
        doNothing().when(excluirRestauranteUseCase).executar(1L);

        mockMvc.perform(delete("/api/v1/restaurantes/{id}", 1))
                .andExpect(status().isNoContent());
    }
}