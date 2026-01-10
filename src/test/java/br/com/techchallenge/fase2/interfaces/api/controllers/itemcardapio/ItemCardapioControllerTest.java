package br.com.techchallenge.fase2.interfaces.api.controllers.itemcardapio;

import br.com.techchallenge.fase2.application.usecases.itemcardapio.*;
import br.com.techchallenge.fase2.domain.entities.ItemCardapio;
import br.com.techchallenge.fase2.domain.entities.Restaurante;
import br.com.techchallenge.fase2.domain.entities.TipoUsuario;
import br.com.techchallenge.fase2.domain.entities.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ItemCardapioController.class)
class ItemCardapioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CriarItemCardapioUseCase criarItemCardapioUseCase;

    @MockitoBean
    private BuscarItemCardapioPorIdUseCase buscarItemCardapioPorIdUseCase;

    @MockitoBean
    private ListarItensCardapioUseCase listarItensCardapioUseCase;

    @MockitoBean
    private AtualizarItemCardapioUseCase atualizarItemCardapioUseCase;

    @MockitoBean
    private ExcluirItemCardapioUseCase excluirItemCardapioUseCase;

    private ItemCardapio itemMock() {
        TipoUsuario tipo = new TipoUsuario(1L, "DONO");
        Usuario dono = new Usuario(1L, "João", tipo);

        Restaurante restaurante = new Restaurante(
                1L,
                "Restaurante Teste",
                "Rua A, 123",
                "Italiana",
                "10:00 - 22:00",
                dono
        );

        return new ItemCardapio(
                1L,
                "Pizza Margherita",
                "Pizza clássica",
                new BigDecimal("49.90"),
                false,
                "/img/pizza.png",
                restaurante
        );
    }

    @Test
    void deveCriarItemCardapio() throws Exception {
        when(criarItemCardapioUseCase.executar(any()))
                .thenReturn(itemMock());

        String body = """
            {
              "nome": "Pizza Margherita",
              "descricao": "Pizza clássica",
              "preco": 49.90,
              "somenteNoLocal": false,
              "fotoPath": "/img/pizza.png",
              "restauranteId": 1
            }
        """;

        mockMvc.perform(post("/api/v1/itens-cardapio")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Pizza Margherita"))
                .andExpect(jsonPath("$.restauranteNome").value("Restaurante Teste"));
    }

    @Test
    void deveBuscarItemCardapioPorId() throws Exception {
        when(buscarItemCardapioPorIdUseCase.executar(1L))
                .thenReturn(itemMock());

        mockMvc.perform(get("/api/v1/itens-cardapio/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Pizza Margherita"))
                .andExpect(jsonPath("$.descricao").value("Pizza clássica"))
                .andExpect(jsonPath("$.preco").value(49.90))
                .andExpect(jsonPath("$.somenteNoLocal").value(false))
                .andExpect(jsonPath("$.fotoPath").value("/img/pizza.png"))
                .andExpect(jsonPath("$.restauranteNome").value("Restaurante Teste"));
    }

    @Test
    void deveListarItensCardapio() throws Exception {
        when(listarItensCardapioUseCase.executar())
                .thenReturn(List.of(itemMock()));

        mockMvc.perform(get("/api/v1/itens-cardapio"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("Pizza Margherita"))
                .andExpect(jsonPath("$[0].restauranteNome").value("Restaurante Teste"));
    }

    @Test
    void deveAtualizarItemCardapio() throws Exception {
        when(atualizarItemCardapioUseCase.executar(any()))
                .thenReturn(itemMock());

        String body = """
            {
              "nome": "Pizza Atualizada",
              "descricao": "Nova descrição",
              "preco": 59.90,
              "somenteNoLocal": true,
              "fotoPath": "/img/pizza2.png",
              "restauranteId": 1
            }
        """;

        mockMvc.perform(put("/api/v1/itens-cardapio/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Pizza Margherita"))
                .andExpect(jsonPath("$.restauranteNome").value("Restaurante Teste"));
    }

    @Test
    void deveDeletarItemCardapio() throws Exception {
        doNothing().when(excluirItemCardapioUseCase).executar(1L);

        mockMvc.perform(delete("/api/v1/itens-cardapio/{id}", 1))
                .andExpect(status().isNoContent());
    }
}
