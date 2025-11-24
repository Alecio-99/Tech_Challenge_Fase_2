package br.com.techchallenge.fase2.interfaces.api.tipousuario;

import br.com.techchallenge.fase2.application.usecases.tipousuario.*;
import br.com.techchallenge.fase2.domain.entities.TipoUsuario;
import br.com.techchallenge.fase2.interfaces.api.tipousuario.requests.TipoUsuarioCreateRequestDTO;
import br.com.techchallenge.fase2.interfaces.api.tipousuario.requests.TipoUsuarioPutRequestDTO;
import br.com.techchallenge.fase2.interfaces.presenters.dtos.tipousuario.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tipos-usuario")
public class TipoUsuarioController {

    private final CriarTipoUsuarioUseCase criarTipoUsuarioUseCase;
    private final BuscarTipoUsuarioPorIdUseCase buscarTipoUsuarioPorIdUseCase;
    private final ListarTiposUsuarioUseCase listarTiposUsuarioUseCase;
    private final AtualizarTipoUsuarioUseCase atualizarTipoUsuarioUseCase;
    private final ExcluirTipoUsuarioUseCase excluirTipoUsuarioUseCase;

    public TipoUsuarioController(
            CriarTipoUsuarioUseCase criarTipoUsuarioUseCase,
            BuscarTipoUsuarioPorIdUseCase buscarTipoUsuarioPorIdUseCase,
            ListarTiposUsuarioUseCase listarTiposUsuarioUseCase,
            AtualizarTipoUsuarioUseCase atualizarTipoUsuarioUseCase,
            ExcluirTipoUsuarioUseCase excluirTipoUsuarioUseCase
    ) {
        this.criarTipoUsuarioUseCase = criarTipoUsuarioUseCase;
        this.buscarTipoUsuarioPorIdUseCase = buscarTipoUsuarioPorIdUseCase;
        this.listarTiposUsuarioUseCase = listarTiposUsuarioUseCase;
        this.atualizarTipoUsuarioUseCase = atualizarTipoUsuarioUseCase;
        this.excluirTipoUsuarioUseCase = excluirTipoUsuarioUseCase;
    }

    @PostMapping
    public ResponseEntity<TipoUsuarioCreateResponseDTO> criar(
            @RequestBody TipoUsuarioCreateRequestDTO dto
    ) {
        TipoUsuario tipo = new TipoUsuario(null, dto.nomeTipo());
        TipoUsuario salvo = criarTipoUsuarioUseCase.executar(tipo);

        TipoUsuarioCreateResponseDTO response =
                new TipoUsuarioCreateResponseDTO(salvo.getId(), salvo.getNomeTipo());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuarioGetResponseDTO> buscarPorId(@PathVariable Long id) {
        return buscarTipoUsuarioPorIdUseCase.executar(id)
                .map(tipo -> ResponseEntity.ok(
                        new TipoUsuarioGetResponseDTO(tipo.getId(), tipo.getNomeTipo())
                ))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TipoUsuarioGetResponseDTO>> listar() {
        List<TipoUsuarioGetResponseDTO> lista = listarTiposUsuarioUseCase.executar()
                .stream()
                .map(t -> new TipoUsuarioGetResponseDTO(t.getId(), t.getNomeTipo()))
                .toList();

        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoUsuarioPutResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody TipoUsuarioPutRequestDTO dto
    ) {
        TipoUsuario atualizado = new TipoUsuario(id, dto.nomeTipo());
        TipoUsuario salvo = atualizarTipoUsuarioUseCase.executar(id, atualizado);

        TipoUsuarioPutResponseDTO response =
                new TipoUsuarioPutResponseDTO(salvo.getId(), salvo.getNomeTipo());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        excluirTipoUsuarioUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}
