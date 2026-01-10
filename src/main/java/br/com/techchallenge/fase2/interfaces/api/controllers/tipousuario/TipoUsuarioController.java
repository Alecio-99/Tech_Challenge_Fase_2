package br.com.techchallenge.fase2.interfaces.api.controllers.tipousuario;

import br.com.techchallenge.fase2.application.dtos.AtualizarTipoUsuarioDTO;
import br.com.techchallenge.fase2.application.dtos.CriarTipoUsuarioDTO;
import br.com.techchallenge.fase2.application.usecases.tipousuario.*;
import br.com.techchallenge.fase2.interfaces.api.controllers.tipousuario.requests.TipoUsuarioCreateRequestDTO;
import br.com.techchallenge.fase2.interfaces.api.controllers.tipousuario.requests.TipoUsuarioPutRequestDTO;
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
            @RequestBody TipoUsuarioCreateRequestDTO request
    ) {
        CriarTipoUsuarioDTO dto = new CriarTipoUsuarioDTO(request.nomeTipo());
        var tipoSalvo = criarTipoUsuarioUseCase.executar(dto);

        TipoUsuarioCreateResponseDTO response = new TipoUsuarioCreateResponseDTO(
                tipoSalvo.getId(),
                tipoSalvo.getNomeTipo()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuarioGetResponseDTO> buscarPorId(@PathVariable Long id) {
        var tipo = buscarTipoUsuarioPorIdUseCase.executar(id);
        TipoUsuarioGetResponseDTO response = new TipoUsuarioGetResponseDTO(
                tipo.getId(),
                tipo.getNomeTipo()
        );
        return ResponseEntity.ok(response);
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
            @RequestBody TipoUsuarioPutRequestDTO request
    ) {
        AtualizarTipoUsuarioDTO dto = new AtualizarTipoUsuarioDTO(id, request.nomeTipo());
        var tipoSalvo = atualizarTipoUsuarioUseCase.executar(dto);

        TipoUsuarioPutResponseDTO response = new TipoUsuarioPutResponseDTO(
                tipoSalvo.getId(),
                tipoSalvo.getNomeTipo()
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        excluirTipoUsuarioUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}
