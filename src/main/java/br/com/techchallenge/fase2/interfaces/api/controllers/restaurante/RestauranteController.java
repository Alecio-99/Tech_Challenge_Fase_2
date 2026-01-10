package br.com.techchallenge.fase2.interfaces.api.controllers.restaurante;

import br.com.techchallenge.fase2.application.dtos.AtualizarRestauranteDTO;
import br.com.techchallenge.fase2.application.dtos.CriarRestauranteDTO;
import br.com.techchallenge.fase2.application.usecases.restaurante.*;
import br.com.techchallenge.fase2.interfaces.api.controllers.restaurante.requests.RestauranteCreateRequestDTO;
import br.com.techchallenge.fase2.interfaces.api.controllers.restaurante.requests.RestaurantePutRequestDTO;
import br.com.techchallenge.fase2.interfaces.presenters.dtos.restaurante.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurantes")
public class RestauranteController {

    private final CriarRestauranteUseCase criarRestauranteUseCase;
    private final BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase;
    private final ListarRestaurantesUseCase listarRestaurantesUseCase;
    private final AtualizarRestauranteUseCase atualizarRestauranteUseCase;
    private final ExcluirRestauranteUseCase excluirRestauranteUseCase;

    public RestauranteController(
            CriarRestauranteUseCase criarRestauranteUseCase,
            BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase,
            ListarRestaurantesUseCase listarRestaurantesUseCase,
            AtualizarRestauranteUseCase atualizarRestauranteUseCase,
            ExcluirRestauranteUseCase excluirRestauranteUseCase
    ) {
        this.criarRestauranteUseCase = criarRestauranteUseCase;
        this.buscarRestaurantePorIdUseCase = buscarRestaurantePorIdUseCase;
        this.listarRestaurantesUseCase = listarRestaurantesUseCase;
        this.atualizarRestauranteUseCase = atualizarRestauranteUseCase;
        this.excluirRestauranteUseCase = excluirRestauranteUseCase;
    }

    @PostMapping
    public ResponseEntity<RestauranteCreateResponseDTO> criar(
            @RequestBody RestauranteCreateRequestDTO request
    ) {
        CriarRestauranteDTO dto = new CriarRestauranteDTO(
                request.nome(),
                request.endereco(),
                request.tipoCozinha(),
                request.horarioFuncionamento(),
                request.donoId()
        );

        var restauranteSalvo = criarRestauranteUseCase.executar(dto);

        RestauranteCreateResponseDTO response = new RestauranteCreateResponseDTO(
                restauranteSalvo.getId(),
                restauranteSalvo.getNome(),
                restauranteSalvo.getTipoCozinha(),
                restauranteSalvo.getHorarioFuncionamento(),
                restauranteSalvo.getDono().getNome()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestauranteGetResponseDTO> buscarPorId(@PathVariable Long id) {
        var restaurante = buscarRestaurantePorIdUseCase.executar(id);
        RestauranteGetResponseDTO response = new RestauranteGetResponseDTO(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getEndereco(),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioFuncionamento(),
                restaurante.getDono().getNome()
        );
        return ResponseEntity.ok(response);
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
            @RequestBody RestaurantePutRequestDTO request
    ) {
        AtualizarRestauranteDTO dto = new AtualizarRestauranteDTO(
                id,
                request.nome(),
                request.endereco(),
                request.tipoCozinha(),
                request.horarioFuncionamento(),
                request.donoId()
        );

        var restauranteSalvo = atualizarRestauranteUseCase.executar(dto);

        RestaurantePutResponseDTO response = new RestaurantePutResponseDTO(
                restauranteSalvo.getId(),
                restauranteSalvo.getNome(),
                restauranteSalvo.getTipoCozinha(),
                restauranteSalvo.getHorarioFuncionamento(),
                restauranteSalvo.getDono().getNome()
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        excluirRestauranteUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}