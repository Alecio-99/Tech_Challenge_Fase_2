package br.com.techchallenge.fase2.application.usecases.restaurante;

import br.com.techchallenge.fase2.application.dtos.CriarRestauranteDTO;
import br.com.techchallenge.fase2.application.exceptions.EntityNotFoundException;
import br.com.techchallenge.fase2.application.gateways.RestauranteGateway;
import br.com.techchallenge.fase2.application.gateways.UsuarioGateway;
import br.com.techchallenge.fase2.domain.entities.Restaurante;
import br.com.techchallenge.fase2.domain.entities.Usuario;

public class CriarRestauranteUseCase {

    private final RestauranteGateway restauranteGateway;
    private final UsuarioGateway usuarioGateway;

    public CriarRestauranteUseCase(RestauranteGateway restauranteGateway, UsuarioGateway usuarioGateway) {
        this.restauranteGateway = restauranteGateway;
        this.usuarioGateway = usuarioGateway;
    }

    public Restaurante executar(CriarRestauranteDTO dto) {
        Usuario dono = usuarioGateway.buscarPorId(dto.donoId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário (dono) com ID " + dto.donoId() + " não encontrado."));

        Restaurante novoRestaurante = new Restaurante(
                null,
                dto.nome(),
                dto.endereco(),
                dto.tipoCozinha(),
                dto.horarioFuncionamento(),
                dono
        );

        return restauranteGateway.salvar(novoRestaurante);
    }
}
