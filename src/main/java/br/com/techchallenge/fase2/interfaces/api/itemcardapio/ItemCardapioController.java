package br.com.techchallenge.fase2.interfaces.api.itemcardapio;

import br.com.techchallenge.fase2.application.dtos.AtualizarItemCardapioDTO;
import br.com.techchallenge.fase2.application.dtos.CriarItemCardapioDTO;
import br.com.techchallenge.fase2.application.usecases.itemcardapio.*;
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

    public ItemCardapioController(
            CriarItemCardapioUseCase criarItemCardapioUseCase,
            BuscarItemCardapioPorIdUseCase buscarItemCardapioPorIdUseCase,
            ListarItensCardapioUseCase listarItensCardapioUseCase,
            AtualizarItemCardapioUseCase atualizarItemCardapioUseCase,
            ExcluirItemCardapioUseCase excluirItemCardapioUseCase
    ) {
        this.criarItemCardapioUseCase = criarItemCardapioUseCase;
        this.buscarItemCardapioPorIdUseCase = buscarItemCardapioPorIdUseCase;
        this.listarItensCardapioUseCase = listarItensCardapioUseCase;
        this.atualizarItemCardapioUseCase = atualizarItemCardapioUseCase;
        this.excluirItemCardapioUseCase = excluirItemCardapioUseCase;
    }

    @PostMapping
    public ResponseEntity<ItemCardapioCreateResponseDTO> criar(
            @RequestBody ItemCardapioCreateRequestDTO request
    ) {
        CriarItemCardapioDTO dto = new CriarItemCardapioDTO(
                request.nome(),
                request.descricao(),
                request.preco(),
                request.somenteNoLocal(),
                request.fotoPath(),
                request.restauranteId()
        );

        var itemSalvo = criarItemCardapioUseCase.executar(dto);

        ItemCardapioCreateResponseDTO response = new ItemCardapioCreateResponseDTO(
                itemSalvo.getId(),
                itemSalvo.getNome(),
                itemSalvo.getRestaurante().getNome()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemCardapioGetResponseDTO> buscarPorId(@PathVariable Long id) {
        var item = buscarItemCardapioPorIdUseCase.executar(id);
        ItemCardapioGetResponseDTO response = new ItemCardapioGetResponseDTO(
                item.getId(),
                item.getNome(),
                item.getDescricao(),
                item.getPreco(),
                item.isSomenteNoLocal(),
                item.getFotoPath(),
                item.getRestaurante().getNome()
        );
        return ResponseEntity.ok(response);
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
            @RequestBody ItemCardapioPutRequestDTO request
    ) {
        AtualizarItemCardapioDTO dto = new AtualizarItemCardapioDTO(
                id,
                request.nome(),
                request.descricao(),
                request.preco(),
                request.somenteNoLocal(),
                request.fotoPath(),
                request.restauranteId()
        );

        var itemSalvo = atualizarItemCardapioUseCase.executar(dto);

        ItemCardapioPutResponseDTO response = new ItemCardapioPutResponseDTO(
                itemSalvo.getId(),
                itemSalvo.getNome(),
                itemSalvo.getRestaurante().getNome()
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        excluirItemCardapioUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}