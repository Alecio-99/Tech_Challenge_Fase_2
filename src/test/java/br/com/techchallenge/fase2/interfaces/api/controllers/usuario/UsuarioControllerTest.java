package br.com.techchallenge.fase2.interfaces.api.controllers.usuario;

import br.com.techchallenge.fase2.application.usecases.usuario.*;
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

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CriarUsuarioUseCase criarUsuarioUseCase;

    @MockitoBean
    private BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;

    @MockitoBean
    private ListarUsuariosUseCase listarUsuariosUseCase;

    @MockitoBean
    private AtualizarUsuarioUseCase atualizarUsuarioUseCase;

    @MockitoBean
    private ExcluirUsuarioUseCase excluirUsuarioUseCase;

    private Usuario usuarioMock() {
        return new Usuario(
                1L,
                "João",
                new TipoUsuario(1L, "ADMIN")
        );
    }

    @Test
    void deveCriarUsuario() throws Exception {
        when(criarUsuarioUseCase.executar(any()))
                .thenReturn(usuarioMock());

        mockMvc.perform(post("/api/v1/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "nome": "João",
                              "tipoUsuarioId": 1
                            }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("João"))
                .andExpect(jsonPath("$.tipoUsuarioNome").value("ADMIN"));
    }

    @Test
    void deveBuscarUsuarioPorId() throws Exception {
        when(buscarUsuarioPorIdUseCase.executar(1L))
                .thenReturn(usuarioMock());

        mockMvc.perform(get("/api/v1/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("João"))
                .andExpect(jsonPath("$.tipoUsuarioNome").value("ADMIN"));
    }

    @Test
    void deveListarUsuarios() throws Exception {
        when(listarUsuariosUseCase.executar())
                .thenReturn(List.of(usuarioMock()));

        mockMvc.perform(get("/api/v1/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("João"))
                .andExpect(jsonPath("$[0].tipoUsuarioNome").value("ADMIN"));
    }

    @Test
    void deveAtualizarUsuario() throws Exception {
        when(atualizarUsuarioUseCase.executar(any()))
                .thenReturn(usuarioMock());

        mockMvc.perform(put("/api/v1/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "nome": "João",
                              "tipoUsuarioId": 1
                            }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("João"))
                .andExpect(jsonPath("$.tipoUsuarioNome").value("ADMIN"));
    }

    @Test
    void deveDeletarUsuario() throws Exception {
        doNothing().when(excluirUsuarioUseCase).executar(1L);

        mockMvc.perform(delete("/api/v1/usuarios/1"))
                .andExpect(status().isNoContent());
    }
}