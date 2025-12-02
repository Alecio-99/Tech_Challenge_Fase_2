package br.com.techchallenge.fase2.interfaces.api.usuario;

import br.com.techchallenge.fase2.application.dtos.AtualizarUsuarioDTO;
import br.com.techchallenge.fase2.application.dtos.CriarUsuarioDTO;
import br.com.techchallenge.fase2.application.usecases.usuario.*;
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

    public UsuarioController(
            CriarUsuarioUseCase criarUsuarioUseCase,
            BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase,
            ListarUsuariosUseCase listarUsuariosUseCase,
            AtualizarUsuarioUseCase atualizarUsuarioUseCase,
            ExcluirUsuarioUseCase excluirUsuarioUseCase
    ) {
        this.criarUsuarioUseCase = criarUsuarioUseCase;
        this.buscarUsuarioPorIdUseCase = buscarUsuarioPorIdUseCase;
        this.listarUsuariosUseCase = listarUsuariosUseCase;
        this.atualizarUsuarioUseCase = atualizarUsuarioUseCase;
        this.excluirUsuarioUseCase = excluirUsuarioUseCase;
    }

    @PostMapping
    public ResponseEntity<UsuarioCreateResponseDTO> criar(
            @RequestBody UsuarioCreateRequestDTO request
    ) {
        CriarUsuarioDTO dto = new CriarUsuarioDTO(request.nome(), request.tipoUsuarioId());
        var usuarioSalvo = criarUsuarioUseCase.executar(dto);

        UsuarioCreateResponseDTO response = new UsuarioCreateResponseDTO(
                usuarioSalvo.getId(),
                usuarioSalvo.getNome(),
                usuarioSalvo.getTipoUsuario().getNomeTipo()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioGetResponseDTO> buscarPorId(@PathVariable Long id) {
        var usuario = buscarUsuarioPorIdUseCase.executar(id);
        UsuarioGetResponseDTO response = new UsuarioGetResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getTipoUsuario().getNomeTipo()
        );
        return ResponseEntity.ok(response);
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
            @RequestBody UsuarioPutRequestDTO request
    ) {
        AtualizarUsuarioDTO dto = new AtualizarUsuarioDTO(id, request.nome(), request.tipoUsuarioId());
        var usuarioSalvo = atualizarUsuarioUseCase.executar(dto);

        UsuarioPutResponseDTO response = new UsuarioPutResponseDTO(
                usuarioSalvo.getId(),
                usuarioSalvo.getNome(),
                usuarioSalvo.getTipoUsuario().getNomeTipo()
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        excluirUsuarioUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}
