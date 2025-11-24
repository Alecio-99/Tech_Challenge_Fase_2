package br.com.techchallenge.fase2.interfaces.api.itemcardapio;

import br.com.techchallenge.fase2.application.usecases.itemcardapio.*;
import br.com.techchallenge.fase2.application.usecases.restaurante.BuscarRestaurantePorIdUseCase;
import br.com.techchallenge.fase2.domain.entities.ItemCardapio;
import br.com.techchallenge.fase2.domain.entities.Restaurante;
import br.com.techchallenge.fase2.interfaces.api.itemcardapio.requests.ItemCardapioCreateRequestDTO;
import br.com.techchallenge.fase2.interfaces.api.itemcardapio.requests.ItemCardapioPutRequestDTO;
import br.com.techchallenge.fase2.interfaces.presenters.dtos.itemcardapio.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/itens-cardapio")
public class ItemCardapioController {

    private final CriarItemCardapioUseCase criarItemCardapioUseCase;
    private final BuscarItemCardapioPorIdUseCase buscarItemCardapioPorIdUseCase;
    private final ListarItensCardapioUseCase listarItensCardapioUseCase;
    private final AtualizarItemCardapioUseCase atualizarItemCardapioUseCase;
    private final ExcluirItemCardapioUseCase excluirItemCardapioUseCase;
    private final BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase;

    public ItemCardapioController(
            CriarItemCardapioUseCase criarItemCardapioUseCase,
            BuscarItemCardapioPorIdUseCase buscarItemCardapioPorIdUseCase,
            ListarItensCardapioUseCase listarItensCardapioUseCase,
            AtualizarItemCardapioUseCase atualizarItemCardapioUseCase,
            ExcluirItemCardapioUseCase excluirItemCardapioUseCase,
            BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase
    ) {
        this.criarItemCardapioUseCase = criarItemCardapioUseCase;
        this.buscarItemCardapioPorIdUseCase = buscarItemCardapioPorIdUseCase;
        this.listarItensCardapioUseCase = listarItensCardapioUseCase;
        this.atualizarItemCardapioUseCase = atualizarItemCardapioUseCase;
        this.excluirItemCardapioUseCase = excluirItemCardapioUseCase;
        this.buscarRestaurantePorIdUseCase = buscarRestaurantePorIdUseCase;
    }

    @PostMapping
    public ResponseEntity<ItemCardapioCreateResponseDTO> criar(
            @RequestBody ItemCardapioCreateRequestDTO dto
    ) {
        Restaurante restaurante = buscarRestaurantePorIdUseCase.executar(dto.restauranteId())
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        ItemCardapio item = new ItemCardapio(
                null,
                dto.nome(),
                dto.descricao(),
                dto.preco(),
                dto.somenteNoLocal(),
                dto.fotoPath(),
                restaurante
        );

        ItemCardapio salvo = criarItemCardapioUseCase.executar(item);

        ItemCardapioCreateResponseDTO response =
                new ItemCardapioCreateResponseDTO(
                        salvo.getId(),
                        salvo.getNome(),
                        salvo.getRestaurante().getNome()
                );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemCardapioGetResponseDTO> buscarPorId(@PathVariable Long id) {
        return buscarItemCardapioPorIdUseCase.executar(id)
                .map(i -> new ItemCardapioGetResponseDTO(
                        i.getId(),
                        i.getNome(),
                        i.getDescricao(),
                        i.getPreco(),
                        i.isSomenteNoLocal(),
                        i.getFotoPath(),
                        i.getRestaurante().getNome()
                ))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ItemCardapioGetResponseDTO>> listar() {
        List<ItemCardapioGetResponseDTO> lista = listarItensCardapioUseCase.executar()
                .stream()
                .map(i -> new ItemCardapioGetResponseDTO(
                        i.getId(),
                        i.getNome(),
                        i.getDescricao(),
                        i.getPreco(),
                        i.isSomenteNoLocal(),
                        i.getFotoPath(),
                        i.getRestaurante().getNome()
                ))
                .toList();

        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemCardapioPutResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody ItemCardapioPutRequestDTO dto
    ) {
        Restaurante restaurante = buscarRestaurantePorIdUseCase.executar(dto.restauranteId())
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        ItemCardapio atualizado = new ItemCardapio(
                id,
                dto.nome(),
                dto.descricao(),
                dto.preco(),
                dto.somenteNoLocal(),
                dto.fotoPath(),
                restaurante
        );

        ItemCardapio salvo = atualizarItemCardapioUseCase.executar(id, atualizado);

        ItemCardapioPutResponseDTO response = new ItemCardapioPutResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getRestaurante().getNome()
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        excluirItemCardapioUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}