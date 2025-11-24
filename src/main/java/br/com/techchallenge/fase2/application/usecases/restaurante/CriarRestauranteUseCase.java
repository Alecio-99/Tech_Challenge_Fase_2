package br.com.techchallenge.fase2.application.usecases.restaurante;

import br.com.techchallenge.fase2.domain.entities.Restaurante;
import br.com.techchallenge.fase2.application.gateways.RestauranteGateway;
import org.springframework.stereotype.Service;

@Service
public class CriarRestauranteUseCase {

    private final RestauranteGateway repository;

    public CriarRestauranteUseCase(RestauranteGateway repository) {
        this.repository = repository;
    }

    public Restaurante executar(Restaurante restaurante) {
        // TODO: implementar criação
        throw new UnsupportedOperationException("Não implementado ainda");
    }
}
