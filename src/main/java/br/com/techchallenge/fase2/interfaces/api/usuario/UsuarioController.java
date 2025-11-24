package br.com.techchallenge.fase2.interfaces.api.usuario;

import br.com.techchallenge.fase2.application.usecases.usuario.*;
import br.com.techchallenge.fase2.domain.entities.TipoUsuario;
import br.com.techchallenge.fase2.domain.entities.Usuario;
import br.com.techchallenge.fase2.application.gateways.TipoUsuarioGateway;
import br.com.techchallenge.fase2.interfaces.api.usuario.requests.UsuarioCreateRequestDTO;
import br.com.techchallenge.fase2.interfaces.api.usuario.requests.UsuarioPutRequestDTO;
import br.com.techchallenge.fase2.interfaces.presenters.dtos.usuario.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final CriarUsuarioUseCase criarUsuarioUseCase;
    private final BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;
    private final ListarUsuariosUseCase listarUsuariosUseCase;
    private final AtualizarUsuarioUseCase atualizarUsuarioUseCase;
    private final ExcluirUsuarioUseCase excluirUsuarioUseCase;
    private final TipoUsuarioGateway tipoUsuarioGateway;

    public UsuarioController(
            CriarUsuarioUseCase criarUsuarioUseCase,
            BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase,
            ListarUsuariosUseCase listarUsuariosUseCase,
            AtualizarUsuarioUseCase atualizarUsuarioUseCase,
            ExcluirUsuarioUseCase excluirUsuarioUseCase,
            TipoUsuarioGateway tipoUsuarioGateway
    ) {
        this.criarUsuarioUseCase = criarUsuarioUseCase;
        this.buscarUsuarioPorIdUseCase = buscarUsuarioPorIdUseCase;
        this.listarUsuariosUseCase = listarUsuariosUseCase;
        this.atualizarUsuarioUseCase = atualizarUsuarioUseCase;
        this.excluirUsuarioUseCase = excluirUsuarioUseCase;
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    @PostMapping
    public ResponseEntity<UsuarioCreateResponseDTO> criar(
            @RequestBody UsuarioCreateRequestDTO dto
    ) {
        TipoUsuario tipo = tipoUsuarioGateway.buscarPorId(dto.tipoUsuarioId())
                .orElseThrow(() -> new RuntimeException("Tipo de usuário não encontrado"));

        Usuario usuario = new Usuario(null, dto.nome(), tipo);
        Usuario salvo = criarUsuarioUseCase.executar(usuario);

        UsuarioCreateResponseDTO response = new UsuarioCreateResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getTipoUsuario().getNomeTipo()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioGetResponseDTO> buscarPorId(@PathVariable Long id) {
        return buscarUsuarioPorIdUseCase.executar(id)
                .map(u -> new UsuarioGetResponseDTO(
                        u.getId(),
                        u.getNome(),
                        u.getTipoUsuario().getNomeTipo()
                ))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UsuarioGetResponseDTO>> listar() {
        List<UsuarioGetResponseDTO> lista = listarUsuariosUseCase.executar()
                .stream()
                .map(u -> new UsuarioGetResponseDTO(
                        u.getId(),
                        u.getNome(),
                        u.getTipoUsuario().getNomeTipo()
                ))
                .toList();

        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioPutResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody UsuarioPutRequestDTO dto
    ) {
        TipoUsuario tipo = tipoUsuarioGateway.buscarPorId(dto.tipoUsuarioId())
                .orElseThrow(() -> new RuntimeException("Tipo de usuário não encontrado"));

        Usuario usuarioAtualizado = new Usuario(id, dto.nome(), tipo);
        Usuario salvo = atualizarUsuarioUseCase.executar(usuarioAtualizado);

        UsuarioPutResponseDTO response = new UsuarioPutResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getTipoUsuario().getNomeTipo()
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        excluirUsuarioUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}
