package br.com.techchallenge.fase2.interfaces.api.controllers.tipousuario;

import br.com.techchallenge.fase2.application.usecases.tipousuario.*;
import br.com.techchallenge.fase2.domain.entities.TipoUsuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TipoUsuarioController.class)
class TipoUsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CriarTipoUsuarioUseCase criarTipoUsuarioUseCase;

    @MockitoBean
    private BuscarTipoUsuarioPorIdUseCase buscarTipoUsuarioPorIdUseCase;

    @MockitoBean
    private ListarTiposUsuarioUseCase listarTiposUsuarioUseCase;

    @MockitoBean
    private AtualizarTipoUsuarioUseCase atualizarTipoUsuarioUseCase;

    @MockitoBean
    private ExcluirTipoUsuarioUseCase excluirTipoUsuarioUseCase;

    @Test
    void deveCriarTipoUsuario() throws Exception {
        TipoUsuario tipo = new TipoUsuario(1L, "ADMIN");

        when(criarTipoUsuarioUseCase.executar(any()))
                .thenReturn(tipo);

        mockMvc.perform(post("/api/v1/tipos-usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "nomeTipo": "ADMIN"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nomeTipo").value("ADMIN"));
    }

    @Test
    void deveBuscarTipoUsuarioPorId() throws Exception {
        TipoUsuario tipo = new TipoUsuario(1L, "CLIENTE");

        when(buscarTipoUsuarioPorIdUseCase.executar(1L))
                .thenReturn(tipo);

        mockMvc.perform(get("/api/v1/tipos-usuario/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nomeTipo").value("CLIENTE"));
    }

    @Test
    void deveListarTiposUsuario() throws Exception {
        List<TipoUsuario> lista = List.of(
                new TipoUsuario(1L, "ADMIN"),
                new TipoUsuario(2L, "CLIENTE")
        );

        when(listarTiposUsuarioUseCase.executar())
                .thenReturn(lista);

        mockMvc.perform(get("/api/v1/tipos-usuario"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nomeTipo").value("ADMIN"))
                .andExpect(jsonPath("$[1].nomeTipo").value("CLIENTE"));
    }

    @Test
    void deveAtualizarTipoUsuario() throws Exception {
        TipoUsuario tipoAtualizado = new TipoUsuario(1L, "GERENTE");

        when(atualizarTipoUsuarioUseCase.executar(any()))
                .thenReturn(tipoAtualizado);

        mockMvc.perform(put("/api/v1/tipos-usuario/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "nomeTipo": "GERENTE"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nomeTipo").value("GERENTE"));
    }

    @Test
    void deveDeletarTipoUsuario() throws Exception {
        doNothing().when(excluirTipoUsuarioUseCase).executar(1L);

        mockMvc.perform(delete("/api/v1/tipos-usuario/1"))
                .andExpect(status().isNoContent());

        verify(excluirTipoUsuarioUseCase).executar(1L);
    }
}