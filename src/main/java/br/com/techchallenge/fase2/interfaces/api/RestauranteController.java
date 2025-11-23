package br.com.techchallenge.fase2.interfaces.api;

import br.com.techchallenge.fase2.application.usecases.restaurante.*;
import br.com.techchallenge.fase2.application.usecases.usuario.BuscarUsuarioPorIdUseCase;
import br.com.techchallenge.fase2.domain.entities.Restaurante;
import br.com.techchallenge.fase2.domain.entities.Usuario;
import br.com.techchallenge.fase2.interfaces.dtos.restaurante.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final CriarRestauranteUseCase criarRestauranteUseCase;
    private final BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase;
    private final ListarRestaurantesUseCase listarRestaurantesUseCase;
    private final AtualizarRestauranteUseCase atualizarRestauranteUseCase;
    private final ExcluirRestauranteUseCase excluirRestauranteUseCase;
    private final BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;

    public RestauranteController(
            CriarRestauranteUseCase criarRestauranteUseCase,
            BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase,
            ListarRestaurantesUseCase listarRestaurantesUseCase,
            AtualizarRestauranteUseCase atualizarRestauranteUseCase,
            ExcluirRestauranteUseCase excluirRestauranteUseCase,
            BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase
    ) {
        this.criarRestauranteUseCase = criarRestauranteUseCase;
        this.buscarRestaurantePorIdUseCase = buscarRestaurantePorIdUseCase;
        this.listarRestaurantesUseCase = listarRestaurantesUseCase;
        this.atualizarRestauranteUseCase = atualizarRestauranteUseCase;
        this.excluirRestauranteUseCase = excluirRestauranteUseCase;
        this.buscarUsuarioPorIdUseCase = buscarUsuarioPorIdUseCase;
    }

    @PostMapping
    public ResponseEntity<RestauranteCreateResponseDTO> criar(
            @RequestBody RestauranteCreateRequestDTO dto
    ) {
        Usuario dono = buscarUsuarioPorIdUseCase.executar(dto.donoId())
                .orElseThrow(() -> new RuntimeException("Dono não encontrado"));

        Restaurante restaurante = new Restaurante(
                null,
                dto.nome(),
                dto.endereco(),
                dto.tipoCozinha(),
                dto.horarioFuncionamento(),
                dono
        );

        Restaurante salvo = criarRestauranteUseCase.executar(restaurante);

        RestauranteCreateResponseDTO response = new RestauranteCreateResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getTipoCozinha(),
                salvo.getHorarioFuncionamento(),
                salvo.getDono().getNome()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestauranteGetResponseDTO> buscarPorId(@PathVariable Long id) {
        return buscarRestaurantePorIdUseCase.executar(id)
                .map(r -> new RestauranteGetResponseDTO(
                        r.getId(),
                        r.getNome(),
                        r.getEndereco(),
                        r.getTipoCozinha(),
                        r.getHorarioFuncionamento(),
                        r.getDono().getNome()
                ))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<RestauranteGetResponseDTO>> listar() {
        List<RestauranteGetResponseDTO> lista = listarRestaurantesUseCase.executar()
                .stream()
                .map(r -> new RestauranteGetResponseDTO(
                        r.getId(),
                        r.getNome(),
                        r.getEndereco(),
                        r.getTipoCozinha(),
                        r.getHorarioFuncionamento(),
                        r.getDono().getNome()
                ))
                .toList();

        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantePutResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody RestaurantePutRequestDTO dto
    ) {
        Usuario dono = buscarUsuarioPorIdUseCase.executar(dto.donoId())
                .orElseThrow(() -> new RuntimeException("Dono não encontrado"));

        Restaurante atualizado = new Restaurante(
                id,
                dto.nome(),
                dto.endereco(),
                dto.tipoCozinha(),
                dto.horarioFuncionamento(),
                dono
        );

        Restaurante salvo = atualizarRestauranteUseCase.executar(id, atualizado);

        RestaurantePutResponseDTO response = new RestaurantePutResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getTipoCozinha(),
                salvo.getHorarioFuncionamento(),
                salvo.getDono().getNome()
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        excluirRestauranteUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}