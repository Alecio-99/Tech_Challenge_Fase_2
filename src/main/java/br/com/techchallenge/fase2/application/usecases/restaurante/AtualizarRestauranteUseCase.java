package br.com.techchallenge.fase2.application.usecases.restaurante;

import br.com.techchallenge.fase2.application.dtos.AtualizarRestauranteDTO;
import br.com.techchallenge.fase2.application.exceptions.EntityNotFoundException;
import br.com.techchallenge.fase2.application.gateways.RestauranteGateway;
import br.com.techchallenge.fase2.application.gateways.UsuarioGateway;
import br.com.techchallenge.fase2.domain.entities.Restaurante;
import br.com.techchallenge.fase2.domain.entities.Usuario;

public class AtualizarRestauranteUseCase {

    private final RestauranteGateway restauranteGateway;
    private final UsuarioGateway usuarioGateway;

    public AtualizarRestauranteUseCase(RestauranteGateway restauranteGateway, UsuarioGateway usuarioGateway) {
        this.restauranteGateway = restauranteGateway;
        this.usuarioGateway = usuarioGateway;
    }

    public Restaurante executar(AtualizarRestauranteDTO dto) {
        restauranteGateway.buscarPorId(dto.id())
                .orElseThrow(() -> new EntityNotFoundException("Restaurante com ID " + dto.id() + " não encontrado."));

        Usuario dono = usuarioGateway.buscarPorId(dto.donoId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário (dono) com ID " + dto.donoId() + " não encontrado."));

        Restaurante restauranteAtualizado = new Restaurante(
                dto.id(),
                dto.nome(),
                dto.endereco(),
                dto.tipoCozinha(),
                dto.horarioFuncionamento(),
                dono
        );

        return restauranteGateway.salvar(restauranteAtualizado);
    }
}
