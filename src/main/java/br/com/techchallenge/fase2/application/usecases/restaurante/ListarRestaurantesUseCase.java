package br.com.techchallenge.fase2.application.usecases.restaurante;

import br.com.techchallenge.fase2.domain.entities.Restaurante;
import br.com.techchallenge.fase2.application.gateways.RestauranteGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarRestaurantesUseCase {

    private final RestauranteGateway repository;

    public ListarRestaurantesUseCase(RestauranteGateway repository) {
        this.repository = repository;
    }

    public List<Restaurante> executar() {
        // TODO: implementar listagem
        throw new UnsupportedOperationException("Não implementado ainda");
    }
}