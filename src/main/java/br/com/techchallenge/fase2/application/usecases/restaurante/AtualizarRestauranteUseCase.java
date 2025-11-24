package br.com.techchallenge.fase2.application.usecases.restaurante;

import br.com.techchallenge.fase2.domain.entities.Restaurante;
import br.com.techchallenge.fase2.application.gateways.RestauranteGateway;
import org.springframework.stereotype.Service;

@Service
public class AtualizarRestauranteUseCase {

    private final RestauranteGateway repository;

    public AtualizarRestauranteUseCase(RestauranteGateway repository) {
        this.repository = repository;
    }

    public Restaurante executar(Long id, Restaurante restauranteAtualizado) {
        // TODO: implementar atualização
        throw new UnsupportedOperationException("Não implementado ainda");
    }
}
