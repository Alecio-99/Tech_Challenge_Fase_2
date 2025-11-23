package br.com.techchallenge.fase2.application.usecases.restaurante;

import br.com.techchallenge.fase2.domain.entities.Restaurante;
import br.com.techchallenge.fase2.domain.repositories.RestauranteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarRestaurantePorIdUseCase {

    private final RestauranteRepository repository;

    public BuscarRestaurantePorIdUseCase(RestauranteRepository repository) {
        this.repository = repository;
    }

    public Optional<Restaurante> executar(Long id) {
        // TODO: implementar busca por id
        throw new UnsupportedOperationException("Não implementado ainda");
    }
}
